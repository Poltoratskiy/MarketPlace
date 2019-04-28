package ru.marketplace.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getProduct(ModelMap model){
        return "Apple";
    }
}
