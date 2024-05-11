package com.example.java5_s2_controllers_sd18314.controllers;

import com.example.java5_s2_controllers_sd18314.entities.Student;
import com.example.java5_s2_controllers_sd18314.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
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

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestParam String name){
        return "Hello " + name + "!";
    }
}
