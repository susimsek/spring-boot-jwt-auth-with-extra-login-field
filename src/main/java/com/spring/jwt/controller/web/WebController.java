package com.spring.jwt.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String index() {
      return "redirect:/documentation/swagger-ui/";
   }

}