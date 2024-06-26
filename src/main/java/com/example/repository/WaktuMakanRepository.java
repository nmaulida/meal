package com.example.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.model.WaktuMakan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaktuMakanRepository extends JpaRepository<WaktuMakan, Long>{
    Optional<WaktuMakan> findByNama(String nama);
    // Paging and sorting
    Page<WaktuMakan> findAll(Pageable pageable);

    // Searching
    Page<WaktuMakan> findByNamaContaining(String nama, Pageable pageable);

    
}
