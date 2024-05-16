package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.model.KategoriMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriMenuRepository extends JpaRepository<KategoriMenu, Long> {
    // Paging and sorting
    Page<KategoriMenu> findAll(Pageable pageable);

    // Searching
    Page<KategoriMenu> findByNamaContaining(String nama, Pageable pageable);
}
