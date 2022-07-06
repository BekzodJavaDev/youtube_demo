package com.company.controller;


import com.company.common.ApiResponse;
import com.company.dto.profile.ProfileChangePasswordDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDto) {
        ProfileDTO dto = profileService.create(profileDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("")
    public ResponseEntity<List<ProfileDTO>> getProfileList() {
        List<ProfileDTO> list = profileService.getList();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/detail")
    public ResponseEntity<ApiResponse> update(@RequestBody ProfileDTO dto) {
        profileService.update(dto);
        return  new ResponseEntity<>(new ApiResponse(true,"Successfully updated profile"), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    private ResponseEntity<?> changePassword(@RequestBody ProfileChangePasswordDTO dto) {
        profileService.update(dto.getPassword());
        return  new ResponseEntity<>(new ApiResponse(true,"Successfully updated profile password"), HttpStatus.OK);
    }







}
