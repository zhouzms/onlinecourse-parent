package com.zms.web.controller;

import com.alibaba.fastjson.JSON;
import com.zms.common.constant.RoleConstant;
import com.zms.domin.Cert;
import com.zms.domin.User;
import com.zms.service.CertService;
import com.zms.web.utils.redis.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zms
 * Date: 2019/12/16 20:32
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {
    public static final int ADMINTYPE=1;
    private static final int TEACHERTYPE=2;
    @Autowired
    private CertService certService;
    @RequestMapping("/list")
    public String goIndex(Model model){
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            return "redirect:login";
        }
        /**
         * 使用redis缓存
        */
        List<Cert> certs=null;
        if(user.getType()==IndexController.ADMINTYPE){
            String adminKey=RedisPoolUtil.get("admin_key");
            certs= IndexController.get(user, adminKey, "admin_key",certService);
        }else if(user.getType()==IndexController.TEACHERTYPE){
            String teacherKey=RedisPoolUtil.get("teacher_key");
            certs= IndexController.get(user, teacherKey, "teacher_key",certService);
        }else {
            String studentKey=RedisPoolUtil.get("student_key");
            certs= IndexController.get(user, studentKey, "student_key",certService);
        }
        model.addAttribute("parentCert",certs);
        model.addAttribute("username",user.getRealName());
        return "index";
    }
    @RequestMapping("/main")
    public String goMain(Model model){
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        if (user == null) {
            return "redirect:login";
        }
        //管理员main界面
        if(subject.hasRole(RoleConstant.ADMIN)){

        }
        //学生main界面
        if(subject.hasRole(RoleConstant.STUDENT)){

        }
        //老师main界面
        if(subject.hasRole(RoleConstant.TEACHER)){

        }
        return "main/main";
    }
    public static List<Cert> get(User user,String certKey,String key,CertService certService){
        List<Cert> certs=null;
        if(!StringUtils.isBlank(certKey)){
            certs = JSON.parseArray(certKey, Cert.class);
        }else{
            //遍历菜单
            Cert certType=new Cert();
            certType.setType(user.getType());
            //-1为根节点
            certType.setParentId(-1);
            certs = certService.getAllParentCertDate(certType);
            for (Cert certP : certs) {
                Cert certId=new Cert();
                certId.setParentId(certP.getId());
                List<Cert> childCert = certService.getAllChildCertById(certId);
                certP.setChildrenCert(childCert);
            }
            String jsonCerts = JSON.toJSONString(certs);
            RedisPoolUtil.setEx(key,jsonCerts,3600);
        }
        return certs;
    }
}
