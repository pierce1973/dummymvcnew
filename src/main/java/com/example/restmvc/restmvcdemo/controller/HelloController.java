package com.example.restmvc.restmvcdemo.controller;

import com.example.restmvc.restmvcdemo.profile.ProfileForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String runHello(Model model, ProfileForm profileForm){
        model.addAttribute("message", "Hi, welcome!");
        return "searchFormPage";
    }

//    @RequestMapping("/")
//    public String hello(@RequestParam("name") String username, Model model){
//        model.addAttribute("message", "Hi from " + username + "in  contoller message");
//        return "homePage";
//    }
//
//    @RequestMapping("/search")
//    public String search(ProfileForm profileForm){
//        return "searchFormPage";
//    }

    @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request,
                             RedirectAttributes redirectAttributes){
        String search = request.getParameter("search");
        redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String showResult(Model model, @RequestParam(value="search") String id){
        model.addAttribute("searchId", id);
        return "resultPage";
    }
}
