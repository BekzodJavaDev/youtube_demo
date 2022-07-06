package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer> {

    Boolean existsByEmail(String email);

    Optional<ProfileEntity> findByEmail(String email);

    Iterable<ProfileEntity> findAllByVisible(boolean b);

//    @Modifying
//    @Transactional
//    @Query("update ProfileEntity as p set p.status =?2 where p.phone =?1")
//    void updateStatusByPhone(String phone, ProfileStatus active);

}
