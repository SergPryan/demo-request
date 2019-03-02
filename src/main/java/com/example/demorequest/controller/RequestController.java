package com.example.demorequest.controller;

import com.example.demorequest.entity.Comment;
import com.example.demorequest.entity.Request;
import com.example.demorequest.entity.Status;
import com.example.demorequest.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestRepository requestRepository;


    @GetMapping
    @ResponseBody
    public Map<String,String> updateRequest(){
        Request request = new Request();
        request.setDescription("asdasd");
        request.setStatus(Status.NEW);
        requestRepository.save(request);
        Comment comment = new Comment();


        Map<String,String> map = new HashMap<>();
        map.put("result","ok");
        return map;
    }

    @PostMapping
    public void createRequest(@RequestBody Request request){

    }
}
