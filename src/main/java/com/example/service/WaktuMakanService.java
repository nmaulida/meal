package com.example.service;

import com.example.model.WaktuMakan;
import com.example.repository.WaktuMakanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaktuMakanService {
    
    @Autowired
    private WaktuMakanRepository waktuMakanRepository;

    public List<WaktuMakan> getAllWaktuMakans() {
        return waktuMakanRepository.findAll();
    }

    public Optional<WaktuMakan> getWaktuMakanById(Long id) {
        return waktuMakanRepository.findById(id);
    }

    public WaktuMakan saveWaktuMakan(WaktuMakan waktuMakan) {
        return waktuMakanRepository.save(waktuMakan);
    }

    public void deleteWaktuMakan(Long id) {
        waktuMakanRepository.deleteById(id);
    }
}
