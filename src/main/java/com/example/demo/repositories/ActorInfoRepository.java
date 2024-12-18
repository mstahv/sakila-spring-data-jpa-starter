package com.example.demo.repositories;

import com.example.demo.entities.views.ActorInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

// TODO consider making a read-only repository
public interface ActorInfoRepository extends JpaRepository<ActorInfo, Long> {

    Stream<ActorInfo> findByFirstNameStartingWithIgnoreCase(String firstName, Pageable pageable);
}
