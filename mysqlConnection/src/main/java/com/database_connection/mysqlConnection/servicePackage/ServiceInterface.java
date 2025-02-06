package com.database_connection.mysqlConnection.servicePackage;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.database_connection.mysqlConnection.entities.LimitRule;
public interface ServiceInterface {
    ResponseEntity<Object> getAllRules();
}
