package com.company.dto.video;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoLikeDTO {
    private String videoId;
    private Integer likes;
    private Integer dislikes;

}
