package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TeksBerjalan;

@Repository
public interface TeksBerjalanRepository extends JpaRepository<TeksBerjalan, Long> {
    // No obvious bugs found. Feel free to provide a stack trace for further investigation.
    // Paging and sorting
    Page<TeksBerjalan> findAll(Pageable pageable);

    // Searching
    Page<TeksBerjalan> findByTeksContaining(String teks, Pageable pageable);
}

