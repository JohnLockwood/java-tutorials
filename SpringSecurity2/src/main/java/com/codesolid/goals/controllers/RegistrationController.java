package com.codesolid.goals.controllers;

import com.codesolid.goals.security.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetails;

    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public String processForm(@Valid @ModelAttribute("user") SiteUser user, BindingResult result)
    {
        if (result.hasErrors())
            return "registration/index";

        return "registration/success";

        //return new ModelAndView("registration/success", "user", new User());
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String index(ModelMap model)  {
        model.addAttribute("user", new SiteUser());
        return "registration/index";
    }

}
