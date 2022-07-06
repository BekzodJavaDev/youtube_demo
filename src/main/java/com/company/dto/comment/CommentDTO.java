package com.company.dto.comment;


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
public class CommentDTO {

    private String content;
    private String videoId;


    private Integer id;
    private ProfileDTO profile;
    private Integer replyId;



    private LocalDateTime createdDate;

    private Boolean visible;


}
