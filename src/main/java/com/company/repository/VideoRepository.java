package com.company.repository;

import com.company.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity,String> {
}
