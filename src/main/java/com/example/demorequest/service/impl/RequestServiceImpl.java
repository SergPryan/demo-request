package com.example.demorequest.service.impl;

import com.example.demorequest.entity.Comment;
import com.example.demorequest.entity.Request;
import com.example.demorequest.entity.Status;
import com.example.demorequest.repository.RequestRepository;
import com.example.demorequest.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request createNewRequest(String description) {
        Request request = new Request();
        request.setDescription(description);
        request.setStatus(Status.NEW);
        return requestRepository.save(request);
    }

    @Override
    public boolean updateStatus(Long id, Status status) {
        Optional<Request> request = requestRepository.findById(id);
        if(request.isPresent()){
            request.get().setStatus(status);
            requestRepository.save(request.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addComment(Long idRequest, String textComment) {
        Optional<Request> request = requestRepository.findById(idRequest);
        if(request.isPresent()){
            Comment comment = new Comment();
            comment.setText(textComment);
            requestRepository.save(request.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Request> getRequest(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Page<Request> getRequestPagination(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Override
    public boolean deleteRequest(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if(request.isPresent()){
           requestRepository.delete(request.get());
           return true;
        } else {
            return false;
        }
    }

}
