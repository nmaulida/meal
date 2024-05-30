package com.example.controller;

import com.example.model.KategoriMenu;
import com.example.model.RequestKategorimenu;
import com.example.service.KategoriMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RestController

@RequestMapping("/api/kategori-menu")
public class KategoriMenuController {

    // Menginjeksi dependency KategoriMenuService
    @Autowired
    private KategoriMenuService kategoriMenuService;

    // Menangani permintaan GET ke endpoint /api/kategori-menu/{id}, mengembalikan KategoriMenu berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<KategoriMenu> getKategoriMenuById(@PathVariable Long id) {
        Optional<KategoriMenu> kategoriMenu = kategoriMenuService.getKategoriMenuById(id);
        return kategoriMenu.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<KategoriMenu> getAllMenus(Pageable pageable) {
        return kategoriMenuService.getAllMenus(pageable);
    }

    @GetMapping("/search")
    public Page<KategoriMenu> searchMenus(@RequestParam("nama") String nama, Pageable pageable) {
        return kategoriMenuService.searchMenus(nama, pageable);
    }

    // Menangani permintaan POST ke endpoint /api/kategori-menu, membuat KategoriMenu baru
    @PostMapping
    public KategoriMenu createKategoriMenu(@RequestBody KategoriMenu kategoriMenu) {
        return kategoriMenuService.saveKategoriMenu(kategoriMenu);
    }

    // Menangani permintaan PUT ke endpoint /api/kategori-menu/{id}, memperbarui KategoriMenu berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<KategoriMenu> updateKategoriMenu(@PathVariable Long id, @RequestBody KategoriMenu kategoriMenuDetails) {
        Optional<KategoriMenu> kategoriMenuOptional = kategoriMenuService.getKategoriMenuById(id);
        if (kategoriMenuOptional.isPresent()) {
            KategoriMenu kategoriMenu = kategoriMenuOptional.get();
            kategoriMenu.setNama(kategoriMenuDetails.getNama());
            kategoriMenu.setMenuUtama(kategoriMenuDetails.getMenuUtama());
            kategoriMenu.setMenuSehat(kategoriMenuDetails.getMenuSehat());
            return ResponseEntity.ok(kategoriMenuService.saveKategoriMenu(kategoriMenu));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Menangani permintaan DELETE ke endpoint /api/kategori-menu/{id}, menghapus KategoriMenu berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategoriMenu(@PathVariable Long id) {
        if (kategoriMenuService.getKategoriMenuById(id).isPresent()) {
            kategoriMenuService.deleteKategoriMenu(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/kategori-menus")
    public RequestKategorimenu createKategoriMenus(@RequestBody RequestKategorimenu requestKategorimenu) {
        return requestKategorimenu;
    }
}
