package com.example.repository;

import com.example.model.Menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    // Paging and sorting
    Page<Menu> findAll(Pageable pageable);

    // Searching
    Page<Menu> findByNamaMenuContaining(String namaMenu, Pageable pageable);

    Optional<Menu> findByNamaMenu(String namaMenu);
}
