package com.example.infrastructure.repository;

import com.example.repository.PenggunaRepository;
import com.example.model.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaJpaRepository extends PenggunaRepository, JpaRepository<Pengguna, Long> {
    
}
