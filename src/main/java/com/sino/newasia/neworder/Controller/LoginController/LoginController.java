package com.sino.newasia.neworder.Controller.LoginController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    //@Autowired
    //private LoginService ls;

    public LoginController() {
    }

    @RequestMapping(value = "/asia/login")
    public String login(@RequestParam("username") String userName, Model model){
/*           model.addAttribute("userName", userName);
           String loginStatus = ls.Login(userName);
           model.addAttribute("loginStatus", loginStatus);*/
           return "loginResult";
    }

}
