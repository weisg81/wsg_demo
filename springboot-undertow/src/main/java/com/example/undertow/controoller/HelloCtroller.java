package com.example.undertow.controoller;

import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author weisg
 * @description TODO
 * @date 2020/1/13
 */
@RestController
@RequestMapping("/hello")
public class HelloCtroller {

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public Object get(@RequestParam String name) throws Exception {
        return "hello "+name+"!";
    }
}
