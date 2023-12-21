package org.PizzaCode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {
    //Handles requests at path /hello
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello(){
//        return "Hello, Spring!";
//    }
    // lives at /hello/goodbye
    //Handles requests at path /goodbye
    @GetMapping("goodbye")
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    //lives at /hello/hello,fixed not anymore
    //Handles requests of the form /hello?name=LaunchCode
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    private String helloWithQueryParam(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    //Handles request of the form /hello/LaunchCode
    @GetMapping("/{name}")
    public String helloWithPathParam(@PathVariable String name){
        return "Hello, " + name + "!";
    }

    //lives at /hello/form
    @GetMapping("form")
    public String helloForm(){
        return "<html>" +
                "<body>" +
                "<form action='hello' method = 'post'>" +   //submit a request to /hello
                "<input type='text' name='name'>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
