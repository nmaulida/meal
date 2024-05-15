package com.example.repository;

import com.example.model.KategoriMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriMenuRepository extends JpaRepository<KategoriMenu, Long> {
}