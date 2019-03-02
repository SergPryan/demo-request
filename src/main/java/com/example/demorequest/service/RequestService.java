package com.example.demorequest.service;

import com.example.demorequest.entity.Request;
import com.example.demorequest.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RequestService {
    Request createNewRequest(String description);
    boolean updateStatus(Long id, Status status);
    boolean addComment(Long id, String comment);
    Optional<Request> getRequest(Long id);
    Page<Request> getRequestPagination(Pageable pageable);
    boolean deleteRequest(Long id);
}
