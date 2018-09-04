package com.sino.newasia.neworder.Controller.LoginController;

import com.alibaba.fastjson.JSONObject;
import com.sino.newasia.neworder.Entity.Officer;
import com.sino.newasia.neworder.Service.UserService.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "asiaTour")
public class LoginController {
    @Autowired
    public OfficerService officerService;

    //http://localhost:9999/asiaTour/login  with x-www-form-urlencoded  email:zhouming
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Officer loginSimple(@RequestParam(value = "email", required = true) String email , Model model){
/*           model.addAttribute("userName", userName);
           String loginStatus = ls.Login(userName);
           model.addAttribute("loginStatus", loginStatus);*/

        Officer listObj = officerService.getOfficerByName(email);

        return listObj;
    }
    //http://localhost:9999/asiaTour/loginWithJson  with app/json {"email":"zhouming"}
    @RequestMapping(value = "/loginWithJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Officer loginJson(@RequestBody JSONObject jsonParam){
        if(jsonParam.containsKey("email")){
            String email = (String)jsonParam.get("email");
            Officer listObj = officerService.getOfficerByName(email);
            return listObj;
        }
        return null;
    }

    //{"officerId":"zhouming","password":"123456"}
    @RequestMapping(value = "/assign_Login/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject jsonParam){
        boolean chlResult = false;
        if(jsonParam.containsKey("officerId") && jsonParam.containsKey("password")){
            String username = (String)jsonParam.get("officerId");
            String pwd = (String)jsonParam.get("password");
            chlResult = officerService.verify(username,pwd);
            Officer listObj = officerService.getOfficerByName(username);
        }

        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", chlResult);

        return result.toJSONString();

    }
}
