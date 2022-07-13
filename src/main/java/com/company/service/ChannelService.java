package com.company.service;


import com.company.dto.channel.ChannelDTO;
import com.company.entity.AttachEntity;

import com.company.entity.ChannelEntity;
import com.company.enums.ChannelStatus;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.ChannelRepository;

import com.company.util.springSicurityUtil.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Value("${server.url}")
    private String serverUrl;
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private AttachService attachService;

    public ChannelDTO create(ChannelDTO dto) {
        // name; surname; login; password;
        Optional<ChannelEntity> optional = channelRepository.findByName(dto.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("User already exists");
        }


        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setStatus(ChannelStatus.ACTIVE);
        entity.setVisible(Boolean.TRUE);

        entity.setPhoto(new AttachEntity(dto.getAttach()));
        entity.setBanner(new AttachEntity(dto.getBanner()));

        channelRepository.save(entity);
        dto.setId(entity.getId());
        dto.setJwt(JwtUtil.encode(Integer.valueOf(entity.getId())));
        return dto;
    }

    public ChannelDTO update(Integer id , ChannelDTO dto) {
        Optional<ChannelEntity> optional = channelRepository.findByName(dto.getName());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This channel id not fount");
        }

        ChannelEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setPhoto(new AttachEntity(dto.getAttach()));
        entity.setBanner(new AttachEntity(dto.getBanner()));
        entity.setStatus(ChannelStatus.ACTIVE);

        channelRepository.save(entity);
        return dto;
    }

    public ChannelDTO updatePhoto(Integer id , ChannelDTO dto) {
        Optional<ChannelEntity> optional = channelRepository.findByName(dto.getName());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This channel id not fount");
        }

        ChannelEntity entity = optional.get();
        entity.setPhoto(new AttachEntity(dto.getAttach()));

        channelRepository.save(entity);
        return dto;
    }


    public ChannelDTO updateBanner(Integer id , ChannelDTO dto) {
        Optional<ChannelEntity> optional = channelRepository.findByName(dto.getName());
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This channel id not fount");
        }

        ChannelEntity entity = optional.get();
        entity.setBanner(new AttachEntity(dto.getBanner()));

        channelRepository.save(entity);
        return dto;
    }

    public ChannelDTO getChannel(Integer id) {

        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This channel not found");
        }


        ChannelEntity entity = optional.get();
        ChannelDTO channelDTO = new ChannelDTO();
        channelDTO.setAttach(String.valueOf(entity.getPhotoId()));
        channelDTO.setBanner(String.valueOf(entity.getBannerId()));
        channelDTO.setCreatedDate(entity.getCreatedDate());
        channelDTO.setName(entity.getName());

        return channelDTO;
    }

    public String  changeVisible(Integer id) {

        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This channel not found");
        }

        ChannelEntity entity = optional.get();
        entity.setVisible(!entity.getVisible());

        channelRepository.save(entity);

        return "Status change";
    }

    public ChannelDTO getChannelDTOById(Integer id) {

        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("This user not fount");
        }

        ChannelEntity channelEntity = optional.get();
        ChannelDTO channeldto = new ChannelDTO();
        channeldto.setCreatedDate(channelEntity.getCreatedDate());
        channeldto.setName(channelEntity.getName());
        channeldto.setBanner(String.valueOf(channelEntity.getBanner()));
        channeldto.setInstagramUrl(channelEntity.getInstagramUrl());
        channeldto.setTelegramUrl(channelEntity.getTelegramUrl());
        channeldto.setWebsiteUrl(channelEntity.getWebsiteUrl());

        return channeldto;
    }

    public PageImpl pagination(int page, int size) {
        // page = 1
    /*    Iterable<TypesEntity> all = typesRepository.pagination(size, size * (page - 1));
        long totalAmount = typesRepository.countAllBy();*/
//        long totalAmount = all.getTotalElements();
//        int totalPages = all.getTotalPages();

//        TypesPaginationDTO paginationDTO = new TypesPaginationDTO(totalAmount, dtoList);
//        return paginationDTO;
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ChannelEntity> all = channelRepository.findAll(pageable);

        List<ChannelEntity> list = all.getContent();

        List<ChannelDTO> dtoList = new LinkedList<>();

        list.forEach(channel -> {
            ChannelDTO dto = new ChannelDTO();
            dto.setId(channel.getId());
            dto.setBanner(String.valueOf(channel.getBanner()));
            dto.setName(channel.getName());
            dto.setCreatedDate(channel.getCreatedDate());
            dto.setAttach(String.valueOf(channel.getPhotoId()));
            dto.setProfile(channel.getProfile());

            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());
    }





}
