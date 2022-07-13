package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.auth.AuthDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.profile.RegistrationDTO;
import com.company.dto.response.ResponseInfoDTO;
import com.company.service.AuthService;
import com.company.util.springSicurityUtil.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Authorization and Registration")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Login", notes = "Method for Authorization")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO dto) {
        log.info("request for login{}",dto);
        ProfileDTO profileDTO = authService.login(dto);
        return ResponseEntity.ok(profileDTO);
    }

    @ApiOperation(value = "Registration", notes = "Method for Registration")
    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> registration(@RequestBody RegistrationDTO dto) {
        String registration = authService.registration(dto);
        return  new ResponseEntity<>(new ApiResponse(true,registration),HttpStatus.OK);
    }

    @GetMapping("/email/verification/{token}")
    public ResponseEntity<String> login(@PathVariable("token") String token) {
        Integer id = JwtUtil.decode(token);
        String response = authService.emailVerification(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/resend/email/{email}")
    public ResponseEntity<ResponseInfoDTO> resendEmail(@PathVariable("email") String email) {
        ResponseInfoDTO responseInfoDTO = authService.resendEmail(email);
        return ResponseEntity.ok(responseInfoDTO);
    }


}
