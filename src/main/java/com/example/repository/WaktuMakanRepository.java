package com.example.repository;

import com.example.model.WaktuMakan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaktuMakanRepository extends JpaRepository<WaktuMakan, Long>{
}
