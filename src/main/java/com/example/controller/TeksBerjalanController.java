package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.TeksBerjalan;
import com.example.model.WaktuMakan;
import com.example.service.TeksBerjalanService;

import java.util.Optional;

@RestController
@RequestMapping("/api/teksberjalan")
public class TeksBerjalanController {

    @Autowired
    private TeksBerjalanService teksBerjalanService;

    @GetMapping
    public Page<TeksBerjalan> getAllMenus(Pageable pageable) {
        return teksBerjalanService.getAllMenus(pageable);
    }

    @GetMapping("/search")
    public Page<TeksBerjalan> searchMenus(@RequestParam("teks") String teks, Pageable pageable) {
        return teksBerjalanService.searchMenus(teks, pageable);
    }


    // Menangani permintaan GET ke endpoint /api/kategori-menu/{id}, mengembalikan KategoriMenu berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<TeksBerjalan> getTeksBerjalanById(@PathVariable Long id) {
        Optional<TeksBerjalan> teksBerjalan = teksBerjalanService.getTeksBerjalanById(id);
        return teksBerjalan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Menangani permintaan POST ke endpoint /api/kategori-menu, membuat KategoriMenu baru
    @PostMapping
    // public TeksBerjalan createTeksBerjalan(@RequestBody TeksBerjalan teksBerjalan) {
    //     return teksBerjalanService.saveTeksBerjalan(teksBerjalan);
    // }
        public ResponseEntity<TeksBerjalan> createTeksBerjalan(@RequestBody TeksBerjalan teksBerjalan) {
        Optional<TeksBerjalan> existingTeksBerjalan= teksBerjalanService.getTeksBerjalanByTeks(teksBerjalan.getTeks());
        if (existingTeksBerjalan.isPresent()) { // melakukan pengecekan apakah data sebelumnya memiliki nama yang sama
            return ResponseEntity.status(409).build(); // Conflict
        }
        TeksBerjalan createTeksBerjalan = teksBerjalanService.saveTeksBerjalan(teksBerjalan);
        return ResponseEntity.ok(createTeksBerjalan); //.ok memberikan status 'ok' pada body
    }

    // Menangani permintaan PUT ke endpoint /api/kategori-menu/{id}, memperbarui KategoriMenu berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<TeksBerjalan> updateTeksBerjalan(@PathVariable Long id, @RequestBody TeksBerjalan teksBerjalanDetails) {
        Optional<TeksBerjalan> teksBerjalanOptional = teksBerjalanService.getTeksBerjalanById(id);
        if (teksBerjalanOptional.isPresent()) {
            TeksBerjalan teksBerjalan = teksBerjalanOptional.get();
            teksBerjalan.setTeks(teksBerjalanDetails.getTeks());
            return ResponseEntity.ok(teksBerjalanService.saveTeksBerjalan(teksBerjalan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Menangani permintaan DELETE ke endpoint /api/kategori-menu/{id}, menghapus KategoriMenu berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeksBerjalan(@PathVariable Long id) {
        if (teksBerjalanService.getTeksBerjalanById(id).isPresent()) {
            teksBerjalanService.deleteTeksBerjalan(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}
