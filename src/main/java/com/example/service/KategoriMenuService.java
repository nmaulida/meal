package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.model.KategoriMenu;
import com.example.repository.KategoriMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategoriMenuService {

    @Autowired
    private KategoriMenuRepository kategoriMenuRepository;

    public Optional<KategoriMenu> getKategoriMenuById(Long id) {
        return kategoriMenuRepository.findById(id);
    }

    public KategoriMenu saveKategoriMenu(KategoriMenu kategoriMenu) {
        return kategoriMenuRepository.save(kategoriMenu);
    }

    public void deleteKategoriMenu(Long id) {
        kategoriMenuRepository.deleteById(id);
    }

    public Page<KategoriMenu> getAllMenus(Pageable pageable) {
        return kategoriMenuRepository.findAll(pageable);
    }

    public Page<KategoriMenu> searchMenus(String nama, Pageable pageable) {
        return kategoriMenuRepository.findByNamaContaining(nama, pageable);
    }
}   