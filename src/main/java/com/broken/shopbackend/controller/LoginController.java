package com.broken.shopbackend.controller;

import com.broken.shopbackend.config.Constant;
import com.broken.shopbackend.config.HttpMsg;
import com.broken.shopbackend.domain.LoginForm;
import com.broken.shopbackend.domain.R;
import com.broken.shopbackend.domain.User;
import com.broken.shopbackend.mapper.LoginDao;
import com.broken.shopbackend.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录
 **/
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginDao loginDao;

    /**
     * 登录
     * @param form
     * @return
     */
    @RequestMapping("/doLogin")
    public R doLogin(@RequestBody LoginForm form) {
        R r = new R();
        if (!VerifyUtil.verifyLoginForm(form)) {
            return r.setCode(400).setMsg(HttpMsg.ERROR_INPUT);
        }
        User loginUser = loginDao.login(form);
        if (loginUser != null) {
            return r.setCode(200).setMsg("欢迎您：" + loginUser.getName()).setData(loginUser);
        }
        return r.setCode(400).setMsg(HttpMsg.ERROR_VERIFY);
    }
}
