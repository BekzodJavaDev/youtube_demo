package com.company.controller;


import com.company.dto.ChannelDTO;
import com.company.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Api("Channel Controller")
@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;


    @ApiOperation(value = "Channel Create", notes = "Method for channel create")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            //HttpServletRequest request,
            @RequestBody ChannelDTO dto) {
        log.info("Request for channel created dto: {}", dto);
        // HttpHeaderUtil.getId(request, ProfileRole.ADMIN);

        // JwtUtil.decode(jwt, ProfileRole.ADMIN);
        ChannelDTO channelDTO = channelService.create(dto);

        return ResponseEntity.ok(channelDTO);
    }

    @ApiOperation(value="Channel update", notes = "Method for channel update")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChannelDTO dto,
                                    @PathVariable("id") Integer id){
        ChannelDTO update =channelService.update(id,dto);
        log.info("Request for channel update UpdateDto: {}, channelId: {}", dto , id);
        return ResponseEntity.ok(update);
    }


    @ApiOperation(value="Channel update photo", notes = "Method for channel update photo")
    @PutMapping("/update/photo/{id}")
    public ResponseEntity<?> updatePhoto(@RequestBody ChannelDTO dto,
                                    @PathVariable("id") Integer id){
        ChannelDTO update =channelService.updatePhoto(id,dto);
        log.info("Request for update channel photo UpdateDto: {}, channelId: {}", dto , id);
        return ResponseEntity.ok(update);
    }

    @ApiOperation(value="Channel update banner", notes = "Method for channel update banner")
    @PutMapping("/update/banner/{id}")
    public ResponseEntity<?> updateBanner(@RequestBody ChannelDTO dto,
                                         @PathVariable("id") Integer id){
        ChannelDTO update =channelService.updateBanner(id,dto);
        log.info("Request for update channel banner UpdateDto: {}, channelId: {}", dto , id);
        return ResponseEntity.ok(update);
    }

    @ApiOperation(value = "Channel Change Status", notes = "Method for channel change status")
    @DeleteMapping("/delete{id}")
    public ResponseEntity<?> changeVisible(@PathVariable("id") Integer id){

        String channelDTO = channelService.changeVisible(id);
        log.info("Request for channel delete id: {}", id);
        return ResponseEntity.ok(channelDTO);
    }

    @ApiOperation(value = "Channel get", notes = "Method to get a channel by its id")
    @GetMapping("/get/{id}}")
    public ResponseEntity<?> getChannel(@PathVariable("id") Integer id) {

        ChannelDTO channelDTO = channelService.getChannelDTOById(id);
        log.info("Request profile get by admin profileId:{}", id);
        return ResponseEntity.ok(channelDTO);
    }

    @ApiOperation(value = "Channel Pagination By page and size", notes = "Channel pagination by attachId")
    @GetMapping("/pagination")
    public ResponseEntity<?> pagination(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size) {
        PageImpl pagination = channelService.pagination(page, size);
        log.info("Request pagination channel page: {}, size: {}", page, size);
        return ResponseEntity.ok(pagination);
    }


}
