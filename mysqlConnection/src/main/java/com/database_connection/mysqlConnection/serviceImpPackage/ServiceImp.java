package com.database_connection.mysqlConnection.serviceImpPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.database_connection.mysqlConnection.entities.LimitRule;
import com.database_connection.mysqlConnection.entityInterface.RuleInterface;
import com.database_connection.mysqlConnection.servicePackage.ServiceInterface;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceImp implements ServiceInterface{
    @Autowired
    private RuleInterface limitRule;

    @Override
    public ResponseEntity<Object> getAllRules() {
        Map<String, Object> response = new LinkedHashMap<>(); 
        Map<String, Object> meta = new LinkedHashMap<>(); 
        meta.put("code", "000");
        meta.put("description", "SUCCESS");
        meta.put("status", 0);

       
        List<LimitRule> rules = limitRule.findAll();
        response.put("meta", meta);
        response.put("data", rules);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
