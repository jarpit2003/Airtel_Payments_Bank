package com.database_connection.mysqlConnection.entityInterface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database_connection.mysqlConnection.entities.LimitRule;
@Repository
public interface RuleInterface extends JpaRepository <LimitRule, Integer>{

}
