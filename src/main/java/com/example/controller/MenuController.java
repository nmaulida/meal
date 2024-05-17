package com.example.controller;

import com.example.model.KategoriMenu;
import com.example.model.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;



    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Optional<Menu> menu = menuService.getMenuById(id);
        return menu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<Menu> getAllMenus(Pageable pageable) {
        return menuService.getAllMenus(pageable);
    }

      @GetMapping("/search")
    public Page<Menu> searchMenus(@RequestParam("namaMenu") String namaMenu, Pageable pageable) {
        return menuService.searchMenus(namaMenu, pageable);
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        Optional<Menu> menuOptional = menuService.getMenuById(id);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            menu.setNamaMenu(menuDetails.getNamaMenu());
            menu.setKategoriMenu(menuDetails.getKategoriMenu());
            return ResponseEntity.ok(menuService.saveMenu(menu));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        if (menuService.getMenuById(id).isPresent()) {
            menuService.deleteMenu(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
