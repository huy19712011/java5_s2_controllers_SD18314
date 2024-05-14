package com.example.java5_s2_controllers_sd18314.controllers;

import com.example.java5_s2_controllers_sd18314.entities.Student;
import com.example.java5_s2_controllers_sd18314.services.StudentService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

    final
    StudentService studentService;

    public BasicController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public String showStudents(Model model){ // Model, ModelMap, ModelAndView
        // chuyen data tu service ve view
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);

        return "home";
    }

    //1. @RequestParam
    // http://localhost:8080/hello?username=Poly&email=Gmail&id=100
    //@GetMapping("/hello")
    //@ResponseBody
    //public String sayHello(
    //        @RequestParam(name = "username") String name,
    //        @RequestParam(name = "email") @Nullable String email,
    //        @RequestParam(name = "id", required = false) String id
    //){
    //    return "Hello " + name + " ! " + email + " ! " + id;
    //}

    // Optional<T>
    // http://localhost:8080/hello?username=Poly&email=Gmail&id=100
    //@GetMapping("/hello")
    //@ResponseBody
    //public String sayHelloV2c(
    //        @RequestParam(name = "username") Optional<String> name,
    //        @RequestParam(name = "email", required = false) String email
    //){
    //    return "Hello " + name.orElseGet(() -> "Not Provided") + " ! " + email;
    //}

    // default value
    // http://localhost:8080/hello?username=Poly&email=Gmail&id=100
    //@GetMapping("/hello")
    //@ResponseBody
    //public String sayHelloV2d(
    //        @RequestParam(name = "username") Optional<String> name,
    //        @RequestParam(name = "email", defaultValue = "default-EMAIL") String email
    //){
    //    return "Hello " + name.orElseGet(() -> "Not Provided") + " ! " + email;
    //}

    // get all parameters
    // http://localhost:8080/hello?username=Poly&email=Gmail&id=100
    //@GetMapping("/hello")
    //@ResponseBody
    //public String sayHelloV2e(
    //        @RequestParam Map<String, String> allParams
    //        ){
    //    return "Parameters: " + allParams.entrySet();
    //}

    // http://localhost:8080/hello?username=Poly&email=Gmail&id=100
    @GetMapping(value = "/hello", params = {"username", "email"})
    @ResponseBody
    public String sayHelloV2e(
            @RequestParam Map<String, String> allParams
            ){
        return "Parameters: " + allParams.entrySet();
    }


    //3. @PathVariable
    // http:/localhost:8080/hello/1
    @GetMapping(value = "hello/{id}/{email}")
    @ResponseBody
    public String sayHelloV3a(
            @PathVariable("id") int id,
            @PathVariable("email") String email
    ) {
        return "Student with id = " + id + " email = " + email;
    }

    //4. Class level

    //5. Working with headers
    @RequestMapping(value = "/method4", headers = "key1=value1")
    @ResponseBody
    public  String method4() {
        return "method4";
    }

    //6.@RequestMapping with Produces and Consumes
    @RequestMapping(value="/method6", produces={"application/json","application/xml"}, consumes="text/html")
    @ResponseBody
    public String method6(){
        return "method6";
    }

    //7. @CookieValue
    @RequestMapping(value = "/readCookie")
    @ResponseBody
    public String readCookieValue(@CookieValue(value = "username", defaultValue = "defaultName") String username) {
        return "Cookie Value: " + username;
    }

    // set Cookie value
    @RequestMapping(value = "/change-username")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "NewName");
        response.addCookie(cookie);

        return "Usename is changed...";
    }

    @RequestMapping(value = "/all-cookies")
    @ResponseBody
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + " = " + c.getValue())
                    .collect(Collectors.joining(", "));
        }
        return "No cookies";
    }

}
