package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.TeksBerjalan;
import com.example.repository.TeksBerjalanRepository;

@Service
public class TeksBerjalanService {

    @Autowired
    private TeksBerjalanRepository teksBerjalanRepository;

    public Optional<TeksBerjalan> getTeksBerjalanById(Long id) {
        return teksBerjalanRepository.findById(id);
    }

    public Optional<TeksBerjalan> getTeksBerjalanByTeks(String teks) {
        return teksBerjalanRepository.findByTeks(teks);
    }

    public TeksBerjalan saveTeksBerjalan(TeksBerjalan teksBerjalan) {
        return teksBerjalanRepository.save(teksBerjalan);
    }

    public void deleteTeksBerjalan(Long id) {
        teksBerjalanRepository.deleteById(id);
    }

    public Page<TeksBerjalan> getAllMenus(Pageable pageable) {
        return teksBerjalanRepository.findAll(pageable);
    }

    public Page<TeksBerjalan> searchMenus(String teks, Pageable pageable) {
        return teksBerjalanRepository.findByTeksContaining(teks, pageable);
    }
}
