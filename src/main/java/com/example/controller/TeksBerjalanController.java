package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.model.TeksBerjalan;
import com.example.service.TeksBerjalanService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teksberjalan")
public class TeksBerjalanController {

    @Autowired
    private TeksBerjalanService teksBerjalanService;

    @GetMapping
    public String getAllTeksBerjalans() {
    List<TeksBerjalan> teksList = teksBerjalanService.getAllTeksBerjalans();
    StringBuilder result = new StringBuilder();

    for (TeksBerjalan teks : teksList) {
        result.append(generateMarqueeText(teks.getTeks(), 20)); // Ganti 20 dengan panjang teks yang diinginkan
    }

    return result.toString();
}


    @GetMapping("/{id}")
    public Optional<TeksBerjalan> getTeksBerjalanById(@PathVariable Long id) {
        return teksBerjalanService.getTeksBerjalanById(id);
    }

    @PostMapping
    public TeksBerjalan saveTeksBerjalan(@RequestBody TeksBerjalan teksBerjalan) {
        return teksBerjalanService.saveTeksBerjalan(teksBerjalan);
    }

    @DeleteMapping("/{id}")
    public void deleteTeksBerjalan(@PathVariable Long id) {
        teksBerjalanService.deleteTeksBerjalan(id);
    }

private String generateMarqueeText(String text, int length) {
    if (text == null || text.isEmpty()) {
        throw new IllegalArgumentException("Text must not be null or empty");
    }
    if (length <= 0) {
        throw new IllegalArgumentException("Length must be greater than zero");
    }

    StringBuilder marqueeText = new StringBuilder();
    String spaces = " ".repeat(length);

    for (int i = 0; i < text.length(); i++) {
        marqueeText.append(spaces).append(text.substring(i)).append(text.substring(0, i)).append(spaces);
    }

    return marqueeText.toString();
}
    
}
