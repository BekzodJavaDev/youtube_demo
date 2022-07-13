package com.company.dto.video;

import com.company.dto.attach.AttachDTO;
import com.company.dto.category.CategoryDTO;
import com.company.dto.profile.ProfileDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO{
    private String id;
    private String title;
    private String description;
    private String name;
    private Integer viewCount;
    private Integer sharedCount;
    private Integer LikeCount;

    private LocalDateTime publishedDate;
    private LocalDateTime createdDate;


    private Boolean visible;




    private CategoryDTO category;
    private AttachDTO attach;

}
