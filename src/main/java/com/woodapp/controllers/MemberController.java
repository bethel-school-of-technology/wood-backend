package com.woodapp.controllers;

import com.woodapp.models.Member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @GetMapping("/member")                              
    public ResponseEntity<Member> getMember(){
        return new ResponseEntity(new Member("Jone", "Smith", "Male",24), HttpStatus.OK);
    }
}
