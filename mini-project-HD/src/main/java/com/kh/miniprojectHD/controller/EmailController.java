package com.kh.miniprojectHD.controller;

import com.kh.miniprojectHD.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000" )
@RestController
public class EmailController {
    @Autowired
    private EmailService es = new EmailService();

    //회원가입 시 이메일 인증 컨트롤러
    @PostMapping("/signup/email")
    public ResponseEntity<String> mailConfirm(@RequestBody Map<String, String> email) throws Exception{
        System.out.println("회원가입 이메일 인증코드 전송");
        String mail = email.get("email");
        String authCode = es.authCodeSendMessage(mail);
        return new ResponseEntity<>(authCode, HttpStatus.OK);
    }

    //사업자 회원가입 시 이메일 인증 컨트롤러
    @PostMapping("/bizSignup/email")
    public ResponseEntity<String> bizMailConfirm(@RequestBody Map<String, String> email) throws Exception{
        System.out.println("사업자 회원가입 이메일 인증코드 전송");
        String mail = email.get("email");
        String authCode = es.bizAuthCodeSendMessage(mail);
        return new ResponseEntity<>(authCode, HttpStatus.OK);
    }

    @PostMapping("/findId")
    public ResponseEntity<Boolean> mailFindId(@RequestBody Map<String, String> email) throws Exception{
        System.out.println("이메일 ID찾기 컨트롤러 작동");
        String mail = email.get("email");
        Boolean result = es.findIdSendMessage(mail);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findPw")
    public ResponseEntity<Boolean> mailFindPw(@RequestBody Map<String, String> object) throws Exception{
        System.out.println("PW변경 컨트롤러 작동");
        String mail = object.get("email");
        String id = object.get("id");
        Boolean result = es.findPwSendMessage(mail, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
