package com.demo.livenation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.livenation.entities.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

}
