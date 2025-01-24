package com.statuscheck.truefalse.projectservicepacakage; 

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
@Service
public class ServiceImp implements com.statuscheck.truefalse.projectservicepacakage.ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceImp.class);
    @Override
    public ResponseEntity<Object> statusCheck(String status) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String method = attributes.getRequest().getMethod();
        String url = attributes.getRequest().getRequestURL().toString();
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> meta = new HashMap<>();
        logger.info("Received request - URL: {}", url);
        if (status.equalsIgnoreCase("true")) {
            meta.put("code", "000");
            meta.put("description", "SUCCESS");
            meta.put("status", 0);

            Map<String, Object> rule = new HashMap<>();
            rule.put("ruleId", 1);
            rule.put("ruleCode", "rule1");
            rule.put("timeFrame", "DAY");
            rule.put("amountLimit", 10000);
            rule.put("ruleGroup", "dmrc");
            rule.put("startDateTime", LocalDateTime.of(2022, 5, 25, 7, 30).toString() + ".000+0000");
            rule.put("keyTemplate", "${transactionDetails.customerId}");
            rule.put("eligibility", "true");
            rule.put("precedence", 1);
            rule.put("status", "ACTIVE");

            Map<String, Object> data = new HashMap<>();
            data.put("rule", rule);

            response.put("meta", meta);
            response.put("data", data);
            
            logger.info("Returning success response for status: {}", status);
            
            logger.info("Meta details: {}", meta);
            logger.info("data details: {}", data);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else if (status.equalsIgnoreCase("false")) {
            meta.put("code", "111");
            meta.put("description", "Something bad happened internally");
            meta.put("status", 1);

            Map<String, Object> data = new HashMap<>();
            data.put("isSuccessful", false);

            response.put("meta", meta);
            response.put("data", data);
            
            logger.info("Returning failure response for status: {}", status);
            logger.info("data details: {}", data);
            logger.info("Meta details: {}", meta);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            meta.put("code", "400");
            meta.put("description", "Invalid status value. Accepted values are 'true' or 'false'.");
            meta.put("status", -1);

            response.put("meta", meta);
            
            logger.warn("Invalid status received: {}", status);
            logger.info("Meta details: {}", meta);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
