package com.zms.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zms.common.AjaxResult;
import com.zms.common.GetTimeUtils;
import com.zms.common.MD5Utils;
import com.zms.common.UUIDUtils;
import com.zms.common.http.HttpClientUtils;
import com.zms.domin.User;
import com.zms.domin.UserLogin;
import com.zms.service.UserService;
import com.zms.web.utils.redis.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zms
 * Date: 2019/11/23 23:28
 */
@Controller
@RequestMapping("/user")
public class UserLoginController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * 跳转到主页
     */

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping("/loginHtml")
    public String forwardLogin(){
        return "login";
    }

    /**
     * 身份验证
     * @return
     */
    @RequestMapping("/login/list")
    public String loginListByNumber(UserLogin userLogin, Model model, HttpServletResponse response) throws Exception {
        AjaxResult<String> ajaxResult=new AjaxResult<>();
        Boolean flag=false;
        UsernamePasswordToken token=new UsernamePasswordToken(userLogin.getUsername(),userLogin.getPassword().toCharArray());
        if(userLogin.getRememberMe()!=null){
            flag=true;
        }
        token.setRememberMe(flag);
        //封装参数
        Subject main = SecurityUtils.getSubject();
        try{
            main.login(token);
            logger.info("token-----"+token);
            //更新登录时间
            User user = userService.queryUserServiceByNumber(userLogin.getUsername());
            user.setLoginTime(GetTimeUtils.getCurrentTime());
            userService.updateLoginTime(user);
            logger.info("============="+user.getLoginTime()+"===================");
        }catch (UnknownAccountException e){
            ajaxResult.setCode("400");
            ajaxResult.setMsg("账号输入错误");
        }catch (IncorrectCredentialsException e){
            ajaxResult.setCode("500");
            ajaxResult.setMsg("密码错误");
        }catch (Exception e){
            ajaxResult.setCode("800");
            ajaxResult.setMsg("其他错误，请重新输入");
        }
        model.addAttribute("ajaxResult",ajaxResult);
        return "login";
    }

    /**
     * 个人信息展示并可进行部分修改
     * @return
     */
    @RequestMapping("/showMsg")
    public String show(Model model,HttpServletRequest request){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User githubUser = (User)request.getSession().getAttribute("user");
        User userUser=null;
        if(githubUser!=null){
            /**
             * GitHub用户登录信息展示
             */
            userUser = userService.queryAndUpdateUser(githubUser.getNumber());
        }else {
            userUser = userService.queryAndUpdateUser(user.getNumber());
        }
        model.addAttribute("user",userUser);
        return "main/UserMessage";
    }

    /**
     * 修改密码
     * @return
     */
    @RequestMapping("/updatePassword")
    public String upPassword(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user==null){
            return "redirect:/user/loginHtml";
        }
        model.addAttribute("user",user);
        return "main/updatePassword";
    }
    @RequestMapping("/checkPassword")
    @ResponseBody
    public AjaxResult<String> checkPassword(@RequestParam("oldpass") String oldpass){
        AjaxResult<String> ajaxResult=new AjaxResult<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String values = null;
        String pass = user.getPassword();
        if(!StringUtils.isBlank(oldpass)){
            values = MD5Utils.getMD5values(oldpass, user.getNumber());
        }
        /**
         * 老密码与当前登录的密码一致
         */
        if(pass.equals(values)){
            ajaxResult.setCode("200");
            ajaxResult.setMsg("当前密码一致");
        }else {
            ajaxResult.setCode("300");
            ajaxResult.setMsg("当前输入密码错误");
        }
        return ajaxResult;
    }
    @RequestMapping("/upNewPassword")
    @ResponseBody
    public AjaxResult<String> upNewPassword(User user){
        AjaxResult<String> result=new AjaxResult<>();
        User users = (User) SecurityUtils.getSubject().getPrincipal();
        user.setId(users.getId());
        user.setPassword(MD5Utils.getMD5values(user.getPassword(),users.getNumber()));
        int i = userService.updatePasswordById(user);
        if(i>0){
            result.setCode("200");
            result.setMsg("修改成功");
        }else {
            result.setCode("300");
            result.setMsg("修改失败");
        }
        return result;
    }
    /**
     * github登录
     */
    @RequestMapping("/callback")
    public String callback(@RequestParam("code")String code, HttpServletRequest request) throws UnknownHostException {
        /**
         * 获取access-toke
         */
        Map<String,String> param=new HashMap<>(3);
        /**
         * 加载配置文件
         */
        PropertiesUtil.loadProperties("public.properties");
        param.put("client_id",PropertiesUtil.getProperty("client_id"));
        param.put("client_secret",PropertiesUtil.getProperty("client_secret"));
        param.put("code",code);
        String response = HttpClientUtils.doPost("https://github.com/login/oauth/access_token", param);
        /**
         * 获取GitHub用户信息并保存到数据库中
         */
        String[] strs = response.split("&");
        String requestToken=strs[0];
        String[] split = requestToken.split("=");
        String accessToken=split[1];
        /**
         * 获取用户信息
         */
        String user = HttpClientUtils.doGet("https://api.github.com/user?" + requestToken);
        JSONObject jsonObject = JSON.parseObject(user);
        /**
         * 通过用户名获取获取用户
         */
        String number=jsonObject.get("id").toString();
        User user1=userService.queryUserServiceByNumber(number);
        if(user1==null) {
            /**
             * 用户第一次登录
             */
            User currentUser=new User();
            currentUser.setId(UUIDUtils.getUUID());
            currentUser.setRealName(jsonObject.get("login").toString());
            currentUser.setNumber(jsonObject.get("id").toString());
            currentUser.setAddress(jsonObject.get("location").toString());
            currentUser.setCreateTime(GetTimeUtils.getCurrentTime());
            currentUser.setLoginTime(GetTimeUtils.getCurrentTime());
            currentUser.setPassword(accessToken);
            currentUser.setStatus(1);
            currentUser.setType(1);
            currentUser.setIp(Inet4Address.getLocalHost().getHostAddress());
            userService.insertUser(currentUser);
            request.getSession().setAttribute("user",currentUser);
            }else {
                request.getSession().setAttribute("user",user1);
            }
            return "redirect:/index/list";
    }
}
