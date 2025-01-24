package com.statuscheck.truefalse.projectservicepacakage;

import org.springframework.http.ResponseEntity;

public interface ProjectService {
    ResponseEntity<Object> statusCheck(String status);
}
