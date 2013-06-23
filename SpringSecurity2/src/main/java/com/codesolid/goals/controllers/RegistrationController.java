package com.codesolid.goals.controllers;

import com.codesolid.goals.model.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processForm(@Valid User user, BindingResult result)
    {
        if (result.hasErrors())
            return "registration/index";

        return "registration/success";

        //return new ModelAndView("registration/success", "user", new User());
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index(ModelMap model)  {
        model.addAttribute("user", new User());
        return "registration/index";
    }

}
