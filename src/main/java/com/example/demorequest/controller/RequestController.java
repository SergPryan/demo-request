package com.example.demorequest.controller;

import com.example.demorequest.entity.Request;
import com.example.demorequest.entity.Status;
import com.example.demorequest.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/request")
public class RequestController {

    private static Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    RequestService requestService;

    /**
     * Создание новой заявки.
     */
    @PostMapping
    public ResponseEntity createRequest(@RequestBody Map<String, String> json) {
        if (!json.containsKey("description")) {
            return ResponseEntity.badRequest().build();
        } else {
            Request request = requestService.createNewRequest(json.get("description"));
            return ResponseEntity.ok(request);
        }
    }

    /**
     * Обновление поля статус
     */
    @PostMapping(path = "/update_status")
    public ResponseEntity updateStatus(@RequestBody Map<String, String> json) {
        try {
            Long id = Long.valueOf(json.get("id"));
            Status status = Status.valueOf(json.get("status"));
            boolean result = requestService.updateStatus(id, status);
            if (result) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("update_status bad request", e);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Добавления комментария к заявке
     */
    @PostMapping(path = "/comment_add")
    public ResponseEntity addComment(@RequestBody Map<String, String> json) {
        try {
            Long id = Long.valueOf(json.get("id"));
            String comment = json.get("comment");
            boolean result = requestService.addComment(id, comment);
            if (result) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("comment_add bad request", e);
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Получение заявки
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity getRequest(@PathVariable Long id) {
        Optional<Request> request = requestService.getRequest(id);
        if (request.isPresent()) {
            return ResponseEntity.ok(request.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Получение списка заявок
     */
    @GetMapping(path = "/pagination")
    @ResponseBody
    public Page<Request> getRequestPagination(Pageable pageable) {
        return requestService.getRequestPagination(pageable);
    }


    /**
     * Удаление заявки
     */
    @DeleteMapping(path = "/delete_request")
    public ResponseEntity deleteRequest(@RequestBody Map<String, String> json) {
        try {
            Long id = Long.valueOf(json.get("id"));
            boolean result = requestService.deleteRequest(id);
            if (result) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("delete_request bad request",e);
            return ResponseEntity.badRequest().build();
        }
    }


}
