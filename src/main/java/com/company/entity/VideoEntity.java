package com.company.entity;

import com.company.enums.ChanelStatus;
import com.company.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(nullable = false,unique = true)
    private String key;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private VideoStatus status;

    @Column(nullable = false, name = "view_count")
    private Integer viewCount = 0;

    @Column(nullable = false, name = "shared_count")
    private Integer sharedCount = 0;

    @JoinColumn(name = "preview_image_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity image;

    @JoinColumn(name = "attach_id")
    @OneToOne(fetch = FetchType.LAZY)
    private AttachEntity attach;

    @JoinColumn(name = "channel_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ChannelEntity channel;

    @JoinColumn(name = "category_id")
    @OneToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDateTime publishedDate;


    @Column
    private Boolean visible = Boolean.TRUE;


    @Column(nullable = false, name = "like_count")
    private Integer likeCount = 0;

    @Column(nullable = false, name = "dislike_count")
    private Integer disLikeCount = 0;

    public VideoEntity() {
    }

    public VideoEntity(String videoId) {
    }
}
