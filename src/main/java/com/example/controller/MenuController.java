package com.example.controller;

import com.example.model.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> createMenu(@RequestBody Menu menu) {
        try {
            Menu createdMenu = menuService.saveMenu(menu);
            return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Menu>> saveMenus(@RequestBody List<Menu> menus) {
        List<Menu> savedMenus = menuService.saveMenus(menus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMenus);
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

    @DeleteMapping("/batch")
    public ResponseEntity<String> deleteMenus(@RequestBody List<Long> ids) {
        String responseMessage = menuService.deleteMenus(ids);
        return ResponseEntity.ok(responseMessage);
    }
}
