package com.example.controller;

import com.example.model.WaktuMakan;
import com.example.service.WaktuMakanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@RestController

@RequestMapping("/api/waktu-makan")
public class WaktuMakanController {
    // Menginjeksi dependency KategoriMenuService
    @Autowired
    private WaktuMakanService waktuMakanService;

    // Menangani permintaan GET ke endpoint /api/waktu-makan, mengembalikan daftar semua KategoriMenu

    // Menangani permintaan GET ke endpoint /api/waktu-makan{id}, mengembalikan KategoriMenu berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<WaktuMakan> getWaktuMakanById(@PathVariable Long id) {
        Optional<WaktuMakan> waktuMakan = waktuMakanService.getWaktuMakanById(id);
        return waktuMakan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Page<WaktuMakan> getAllWaktuMakans(Pageable pageable) {
        return waktuMakanService.getAllWaktuMakans(pageable);
    }

    @GetMapping("/search")
    public Page<WaktuMakan> searchWaktuMakans(@RequestParam("nama") String nama, Pageable pageable) {
        return waktuMakanService.searchWaktuMakans(nama, pageable);
    }

    // Menangani permintaan POST ke endpoint /api/kategori-menu, membuat KategoriMenu baru
    // @PostMapping
    // public WaktuMakan createWaktuMakan(@RequestBody WaktuMakan waktuMakan) {
    //     return waktuMakanService.saveWaktuMakan(waktuMakan);
    // }

    @PostMapping
    public ResponseEntity<WaktuMakan> createWaktuMakan(@RequestBody WaktuMakan waktuMakan) {
        Optional<WaktuMakan> existingWaktuMakan = waktuMakanService.getWaktuMakanByNama(waktuMakan.getNama());
        if (existingWaktuMakan.isPresent()) { // melakukan pengecekan apakah data sebelumnya memiliki nama yang sama
            return ResponseEntity.status(409).build(); // Conflict
        }
        WaktuMakan createdWaktuMakan = waktuMakanService.saveWaktuMakan(waktuMakan);
        return ResponseEntity.ok(createdWaktuMakan); //.ok memberikan status 'ok' pada body
    }

    // Menangani permintaan PUT ke endpoint /api/kategori-menu/{id}, memperbarui KategoriMenu berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<WaktuMakan> updateWaktuMakan(@PathVariable Long id, @RequestBody WaktuMakan waktuMakanDetails) {
        Optional<WaktuMakan> waktuMakanOptional = waktuMakanService.getWaktuMakanById(id);
        if (waktuMakanOptional.isPresent()) {
            WaktuMakan waktuMakan = waktuMakanOptional.get();
            waktuMakan.setNama(waktuMakanDetails.getNama());
            waktuMakan.setJamMulai(waktuMakanDetails.getJamMulai());
            waktuMakan.setJamBerakhir(waktuMakanDetails.getJamBerakhir());
            return ResponseEntity.ok(waktuMakanService.saveWaktuMakan(waktuMakan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Menangani permintaan DELETE ke endpoint /api/kategori-menu/{id}, menghapus KategoriMenu berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaktuMakan(@PathVariable Long id) {
        if (waktuMakanService.getWaktuMakanById(id).isPresent()) {
            waktuMakanService.deleteWaktuMakan(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}