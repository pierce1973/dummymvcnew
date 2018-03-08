package com.example.restmvc.restmvcdemo.controller;

import com.example.restmvc.restmvcdemo.profile.ProfileForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm){
        return "searchFormPage";
    }

    @RequestMapping(name="/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/profile";
        }
        System.out.println("saved:" + profileForm);
        return "redirect:/profile";
    }
}
