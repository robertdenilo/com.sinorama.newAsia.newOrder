package com.sino.newasia.neworder.Controller.LoginController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sino.newasia.neworder.Entity.Officer;
import com.sino.newasia.neworder.Service.UserService.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

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
    @Autowired
    private HttpSession hs;

    //{"officerId":"zhouming","password":"123456"}  http://localhost:9999/asiaTour/assign_Login/login
    @RequestMapping(value = "/assign_Login/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(@RequestBody JSONObject jsonParam){
        boolean chkResult = false;
        StringBuilder strBuilder = new StringBuilder("");
        if(jsonParam.containsKey("officerId") && jsonParam.containsKey("password")) {
            String username = (String) jsonParam.get("officerId");
            String pwd = (String) jsonParam.get("password");
            chkResult = officerService.verify(username, pwd);
            if (chkResult){
                Officer officer = officerService.getOfficerByName(username);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    strBuilder.append(objectMapper.writeValueAsString(officer));
                } catch (JsonGenerationException e) {
                } catch (JsonMappingException e) {
                } catch (IOException e) {
                }
                officerService.addUserInRedisSession(strBuilder.toString(), hs);
                hs.setAttribute("login_status", true);
                LocalDateTime dt = LocalDateTime.now();
                //System.out.println("last login: "+hs.getAttribute("login_time").toString());
                hs.setAttribute("login_time", dt);
                System.out.println("this login: "+hs.getAttribute("login_time").toString());
            }
        }
        JSONObject result = new JSONObject();
        result.put("success", chkResult);
        result.put("message", "");
        result.put("data", strBuilder.toString());

        return result.toJSONString();

    }
    //http://localhost:9999/asiaTour/assign_Login/logout
    @RequestMapping(value = "/assign_Login/logout", method={RequestMethod.GET,RequestMethod.POST})
    public String logout(){
        JSONObject result = new JSONObject();
        if (hs.getAttribute("login_status") != null){
            hs.removeAttribute("login_status");
            hs.removeAttribute("login_time");

            result.put("success", true);
            result.put("message", "");
            result.put("data", "");
            return result.toJSONString();
        }
        result.put("success", false);
        result.put("message", "");
        result.put("data", "");
        return result.toJSONString();

    }
}
