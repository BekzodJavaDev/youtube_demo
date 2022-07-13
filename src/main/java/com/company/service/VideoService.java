package com.company.service;


import com.company.dto.video.VideoCreateDTO;
import com.company.dto.video.VideoDTO;
import com.company.entity.VideoEntity;
import com.company.exp.ItemNotFoundException;
import com.company.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Value("${server.url}")
    private String serverUrl;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttachService attachService;



    public VideoDTO create(VideoCreateDTO dto, Integer profileId){
        VideoEntity entity = new VideoEntity();
        entity.setDescription(dto.getDescription());
        if(dto.getImageId()!=null){
          entity.setAttach(attachService.get(dto.getImageId()));
        }


        entity.setCategory(categoryService.get(dto.getCategoryId()));





      return null;
    }

    public void update(String id,VideoDTO videoDTO){


    }

    public  void delete(String id){

    }

    public  void changeStatus(Integer profileId , String id) {


    }


    public PageImpl pagination(int page, int size) {
        // page = 1
    /*    Iterable<TypesEntity> all = typesRepository.pagination(size, size * (page - 1));
        long totalAmount = typesRepository.countAllBy();*/
        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<VideoEntity> all = videoRepository.findAll(pageable);

//        long totalAmount = all.getTotalElements();
//        int totalPages = all.getTotalPages();
        List<VideoEntity> list = all.getContent();

        List<VideoDTO> dtoList = new LinkedList<>();

        list.forEach(video -> {
                VideoDTO dto = new VideoDTO();
                dto.setName(video.getName());
                dto.setDescription(video.getDescription());
                dto.setSharedCount(video.getSharedCount());
                dto.setViewCount(video.getViewCount());
                dtoList.add(dto);

        });

        return new PageImpl(dtoList,pageable, all.getTotalElements());
    }


    public VideoEntity get(String id){
        Optional<VideoEntity> byId = videoRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("video not found");
        }
        return byId.get();

    }



}

