package com.company.repository;

import com.company.entity.ChannelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChannelRepository extends CrudRepository<ChannelEntity, Integer> {

    Optional<ChannelEntity> findByName(String name);

    Page<ChannelEntity> findAll(Pageable pageable);
}
