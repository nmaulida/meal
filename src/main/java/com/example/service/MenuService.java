package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Menu;
import com.example.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<Menu> saveMenus(List<Menu> menus) {
        // Memeriksa duplikasi sebelum menyimpan
        List<String> menuNames = menus.stream().map(Menu::getNamaMenu).collect(Collectors.toList());
        List<Menu> existingMenus = menuRepository.findByNamaMenuIn(menuNames);
        if (!existingMenus.isEmpty()) {
            throw new IllegalArgumentException("Beberapa nama menu sudah ada: " + 
                existingMenus.stream().map(Menu::getNamaMenu).collect(Collectors.joining(", ")));
        }

        return menuRepository.saveAll(menus);
    }

    @Transactional
    public String deleteMenus(List<Long> ids) {
        menuRepository.deleteAllById(ids);
        return "Data berhasil dihapus";
    }

    
}
