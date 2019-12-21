package com.zms.web.controller;

import com.zms.common.AjaxResult;
import com.zms.common.GetTimeUtils;
import com.zms.common.MD5Utils;
import com.zms.domin.User;
import com.zms.domin.UserLogin;
import com.zms.service.UserService;
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

import javax.servlet.http.HttpServletResponse;

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
    public String show(Model model){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user==null){
            return "redirect:login";
        }
        User userUser = userService.queryAndUpdateUser(user.getNumber());
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
            return "redirect:login";
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
            values = MD5Utils.getMD5values(oldpass, user.getRealName());
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
    public String upNewPassword(UserLogin userLogin){
        return null;
    }
}
