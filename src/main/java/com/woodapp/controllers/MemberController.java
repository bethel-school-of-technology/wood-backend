package com.woodapp.controllers;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import com.woodapp.models.Member;
import com.woodapp.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class MemberController {

//     @Autowired
//     private MemberService memberService;

//     @GetMapping("/member-info")
//     @ResponseStatus(HttpStatus.OK) 
//     public ResponseEntity<Member> getMember(){
//         return ResponseEntity.ok(new Member("Jones","Smith","Male",24, "Address"));
//     }

    // @GetMapping("/members")
    // ResponseEntity<Iterable<Member>> getAllMembers(){
    // return  ResponseEntity(memberService.findAll(), HttpStatus.OK);
    // } **Can't figure out this error with ResponseEntity**

// **EXAMPLE from tutorial**
// @RestController
// public class MemberController {

//     @Autowired
//     private MemberService memberService;

//     @GetMapping("/member")
//     public ResponseEntity<Member> getMember(){
//         return new ResponseEntity(new Member("Jone", "Smith",     "Male",24),HttpStatus.OK);
//     }
// }


@RequestMapping("api/member")
@RestController
public class MemberController {
    
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping
    public void addMember(@Valid @NonNull @RequestBody Member member){
        memberService.addMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping(path = "{id}")
    public Member getMemberById(@PathVariable("id") UUID id){
        return memberService.getMemberById(id)
            .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateMember(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Member MemberToUpdate){
        memberService.updateMember(id, MemberToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMemberById(@PathVariable("id") UUID id){
        memberService.deleteMember(id);
    }
}