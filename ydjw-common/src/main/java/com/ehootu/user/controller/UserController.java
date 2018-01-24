package com.ehootu.user.controller;

import com.ehootu.core.generic.BaseController;
import com.ehootu.core.util.ApplicationUtils;
import com.ehootu.core.util.ShiroUtils;
import com.ehootu.user.model.Police;
import com.ehootu.user.model.User;
import com.ehootu.user.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author KongXiaoPing
 * @version V1.0
 * @Title: UserController.java
 * @Package com.ehootu.web.controller
 * @Description: 登陆用户控制器
 * @date 2017年9月21日 下午2:42:41
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * PC端用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String data, HttpServletRequest request) {
        try {
            User user = getJsonAjax(data, User.class);

            log.info(""+user);
            Subject subject = ShiroUtils.getSubject();
            // 验证码
            String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            if (!user.getCode().equalsIgnoreCase(kaptcha)) {
                resultError("2", "验证码不正确");
                return;
            }
            // 身份验证 sha256 加密
            user.setPassword(ApplicationUtils.sha256Hex(user.getPassword()));
            subject.login(new UsernamePasswordToken(user.getUserName(), user.getPassword()));

            // 验证成功在Session中保存用户信息
            User authUserInfo = userService.selectByUsername(user.getUserName());

            request.getSession().setAttribute("userInfo", authUserInfo);
            // 插入登录 Log
            // 返回成功
            resultSuccess(authUserInfo);

        } catch (AuthenticationException e) {
            // 身份验证失败
            e.printStackTrace();
            resultError("11", "用户名或密码错误！");
        }

    }


    /**
     * 用户登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        ShiroUtils.logout();
        // 注销 Log
        return "redirect:/login.html";
    }

    /**
     * 广元智慧门牌微信服务号注册添加用户 | 修改用户信息
     *
     * @param user
     */
    @RequestMapping("/weixinServer/adduser")
    public void addUser(User user) {
        log.info("User--" + user);
        try {
            //注册添加用户
            if (null == user.getId()) {
                // 判断是否有重复 根据电话查询
                List<User> personInfos = userService.findUser(user);
                if (null == personInfos || personInfos.size() == 0) {
                    user.setPassword(ApplicationUtils.sha256Hex(user.getPassword()));
                    userService.insert(user);
                    resultSuccess();
                } else {
                    resultError("500", "该用户已存在！");
                }
                // 修改用户
            } else {
                userService.update(user);
                resultSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultError("500", "数据库操作异常");
        }

    }


    /**
     * 广元智慧门牌微信服务号 用户登录
     */
    @RequestMapping(value = "/weixinServer/login")
    public void weixinServerLogin(User user) {
        try {
            log.info("PersonInfo--》" + user);
            log.info("userName--》" + user.getUserName());
            log.info("userPassword--》" + user.getPassword());
            log.info("code--》" + user.getCode());
            log.info("winxinId--》" + user.getWeixinId());

            // 根据登陆电话查询
            List<User> personInfos = userService.findUser(user);

            if (null == personInfos || personInfos.size() == 0) {
                resultError("500", "您还没有注册哦,请注册！");
                return;
            } else {
                log.info("PersonInfo--" + user);
//                //获取后台验证码
//                String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//                //对比接收到的验证码和session中存的验证码
//                if (!user.getCode().equalsIgnoreCase(kaptcha)) {
//                    resultError("500", "验证码不正确");
//                    return;
//                }
                // 身份验证 sha256 加密
                user.setPassword(ApplicationUtils.sha256Hex(user.getPassword()));
                User result = userService.login(user);
                if (result != null) {
                    result.setLoginSign(result.getId());
                    resultSuccess(result);
                } else {
                    resultError("500", "用户名或密码错误！");
                }
            }
        } catch (AuthenticationException e) {
            // 身份验证失败
            e.printStackTrace();
            resultError("500", "用户名或密码错误！");
        }
    }

    /**
     * 微信公众号端用户登录
     */
    @RequestMapping(value = "/app/loginSign")
    public void appNoLogin(User user) {
        if (user.getLoginSign() != null) {
            User result = userService.selectById(user.getLoginSign());
            if (result != null) {
                resultSuccess(result);
                return;
            }
        }
        resultError("11", "用户名信息已过期！");
    }

    /**
     * 广元智慧门牌 警察app端用户登录
     */
    @RequestMapping(value = "/app/policelogin")
    public void policeLogin(Police police) {
        String passWord = police.getPolicePassword();
        log.info("policeLogin========>" + police);
        try {
            Session s = ShiroUtils.getSession();
            log.info("s========>" + s);
            //获取后台验证码
            String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
            log.info("kaptcha========>" + kaptcha);
            //对比接收到的验证码和session中存的验证码
            if (!police.getCode().equalsIgnoreCase(kaptcha)) {
                resultError("2", "验证码不正确");
                return;
            }
            //将接收到的密码加密
            police.setPolicePassword(ApplicationUtils.sha256Hex(police.getPolicePassword()));
            //验证码正确将去数据库查询是否存在该用户
            Police result = userService.policeLogin(police);
            //result！=null 证明数据库有该用户则返回，否则返回else
            if (result != null) {
                //返回不加密的秘码给前端
                result.setPolicePassword(passWord);
                resultSuccess(result);
            } else {
                resultError("11", "用户名或密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultError("11", "数据库操作异常");
        }
    }

    /**
     * 广元智慧门牌微信服务号 用户修改密码
     * @param params oldPassword 旧密码
     *               password 新密码
     *               id 用户id
     */
    @RequestMapping(value = "/updatePassword")
    public void updatePassword(@RequestParam Map<String,Object> params){
        //用户id
        String userId = (String) params.get("id");
        Integer id = Integer.valueOf(userId);
        //旧密码
        String oldPassword = (String)params.get("oldPassword");
        //新密码
        String password = (String)params.get("password");
        //根据用户id查询用户
        User user = userService.selectById(id);
        //加密
        String sha256HexOldPassword = ApplicationUtils.sha256Hex(oldPassword);
        //判断旧密码是否正确 ,正确，则修改密码
        if (sha256HexOldPassword.equals(user.getPassword())){
            user.setPassword(ApplicationUtils.sha256Hex(password));
            user.setId(id);
            //修改密码
            userService.update(user);
            resultSuccess();
        }else {
            resultError("500","旧密码错误，请再次输入");
        }

    }

    /**
     *广元智慧门牌微信服务号 根据用户id查询个人资料
     * @param id
     */
    @RequestMapping(value = "/queryPersonInfoById")
    public void  queryPersonInfoById(Integer id){
        try {
            User user = userService.selectById(id);
            resultSuccess(user);
        } catch (Exception e) {
            e.printStackTrace();
            resultError("500", "数据库异常");
        }

    }
}
