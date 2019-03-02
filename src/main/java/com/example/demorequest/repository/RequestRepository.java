package com.example.demorequest.repository;

import com.example.demorequest.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
