package com.example.demo.repositories;

import com.example.demo.entities.views.ActorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO consider making a read-only repository
public interface ActorInfoRepository extends JpaRepository<ActorInfo, Long> {
}
