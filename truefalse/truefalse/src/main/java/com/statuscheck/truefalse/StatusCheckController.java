package com.statuscheck.truefalse;
import com.statuscheck.truefalse.projectservicepacakage.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class StatusCheckController {
    private static final Logger logger = LoggerFactory.getLogger(StatusCheckController.class);
    private final ProjectService service;
    
    StatusCheckController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("/api/v1/config/limit-rules/{status}")

    public ResponseEntity<Object> statusOutput(@PathVariable String status) {
    
        logger.info("Method: statusOutput, URL: {}, Received request to get limit rules with status: {}", status);
        ResponseEntity<Object> response= service.statusCheck(status);

        return response;
}
}
