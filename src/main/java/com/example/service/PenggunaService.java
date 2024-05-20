package com.example.service;

import com.example.repository.PenggunaRepository;
import com.example.model.Pengguna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PenggunaService {
    private final PenggunaRepository penggunaRepository;

      @Autowired
      public PenggunaService(PenggunaRepository penggunaRepository) {
          this.penggunaRepository = penggunaRepository;
      }

      public Optional<Pengguna> getPenggunaById(Long id) {
          return penggunaRepository.findById(id);
      }

      public Pengguna savePengguna(Pengguna pengguna) {
          return penggunaRepository.save(pengguna);
      }

}
