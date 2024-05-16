package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TeksBerjalan;
import com.example.repository.TeksBerjalanRepository;

@Service
public class TeksBerjalanService {

    @Autowired
    private TeksBerjalanRepository teksBerjalanRepository;

    public List<TeksBerjalan> getAllTeksBerjalans() {
        return teksBerjalanRepository.findAll();
    }

    public Optional<TeksBerjalan> getTeksBerjalanById(Long id) {
        return teksBerjalanRepository.findById(id);
    }

    public TeksBerjalan saveTeksBerjalan(TeksBerjalan teksBerjalan) {
        return teksBerjalanRepository.save(teksBerjalan);
    }

    public void deleteTeksBerjalan(Long id) {
        teksBerjalanRepository.deleteById(id);
    }
}
