package com.example.demo.vaadinui;

import com.example.demo.entities.views.ActorInfo;
import com.example.demo.repositories.ActorInfoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class ActorInfoService {

    private final ActorInfoRepository repo;

    public ActorInfoService(ActorInfoRepository repo) {
        this.repo = repo;
    }

    public Stream<ActorInfo> findByFirstName(String firstName, Pageable pageable) {
        return repo.findByFirstNameStartingWithIgnoreCase(firstName, pageable).toList().stream();
    }
}
