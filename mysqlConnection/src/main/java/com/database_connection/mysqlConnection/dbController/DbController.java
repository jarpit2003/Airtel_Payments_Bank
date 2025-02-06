package com.database_connection.mysqlConnection.dbController;

import com.database_connection.mysqlConnection.serviceImpPackage.ServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("api/v1/limit-config")
public class DbController {
    private static final Logger logger = LoggerFactory.getLogger(DbController.class);
    @Autowired
    private ServiceImp rule;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/get-rules")
    public ResponseEntity<Object> getAllRules() {
        ResponseEntity<Object> response = rule.getAllRules();  
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        String requestType = req.getMethod();
        
        try {
            String json = objectMapper.writeValueAsString(response.getBody());
            logger.info("Response: " + json);  
        } catch (Exception e) {
            logger.error("Error converting to JSON", e);
        }
        logger.info("This is a {} request type.",requestType);
        logger.info("URL:{}",req.getRequestURI());

        return response;
    }
}
