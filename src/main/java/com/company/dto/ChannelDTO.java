package com.company.dto;

import com.company.entity.AttachEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ChannelStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ChannelDTO {
    //   id(uuid),name,photo,description,status (ACTIVE, BLOCK),banner,profile_id,key
    private String uuid;
    private String name;
    private String attach;
    private String banner;
    private String websiteUrl;
    private String telegramUrl;
    private String instagramUrl;
    private ProfileEntity profile;
    private ChannelStatus status = ChannelStatus.ACTIVE;
    private Boolean visible = Boolean.TRUE;
    private LocalDateTime createdDate = LocalDateTime.now();
    private String jwt;

    public void setId(String uuid) {
    }
}
