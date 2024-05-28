package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Menu;
import com.example.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Page<Menu> getAllMenus(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    public Page<Menu> searchMenus(String namaMenu, Pageable pageable) {
        return menuRepository.findByNamaMenuContaining(namaMenu, pageable);
    }

    @Transactional
    public Menu saveMenu(Menu menu) {
        
        Optional<Menu> existingMenu = menuRepository.findByNamaMenu(menu.getNamaMenu());
        if (existingMenu.isPresent()) {
            throw new IllegalArgumentException("nama menu sudah ada");
        }
        return menuRepository.save(menu);
    }
}
