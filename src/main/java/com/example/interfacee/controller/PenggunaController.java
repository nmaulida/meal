package com.example.interfacee.controller;

import com.example.DTO.PenggunaDTO;
import com.example.service.PenggunaService;
import com.example.interfacee.mapper.PenggunaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pengguna")
public class PenggunaController {
    private final PenggunaService penggunaService;
    private final PenggunaMapper penggunaMapper;

    @Autowired
    public PenggunaController(PenggunaService penggunaService, PenggunaMapper penggunaMapper) {
        this.penggunaService = penggunaService;
        this.penggunaMapper = penggunaMapper;
    }

    @GetMapping("/{id}")
      public PenggunaDTO getPenggunaById(@PathVariable Long id) {
          return penggunaService.getPenggunaById(id)
                  .map(penggunaMapper::toDTO)
                  .orElseThrow();
      }


    @PostMapping
    public PenggunaDTO createPengguna(@RequestBody PenggunaDTO penggunaDTO) {
        return penggunaMapper.toDTO(penggunaService.savePengguna(penggunaMapper.toEntity(penggunaDTO)));
    }

}
