package com.springmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springmail.entity.MailDetails;



@Repository
public interface MailRepository extends JpaRepository<MailDetails, Integer> {

}
