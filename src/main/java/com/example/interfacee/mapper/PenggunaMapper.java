package com.example.interfacee.mapper;

import com.example.DTO.PenggunaDTO;
import com.example.model.Pengguna;
import org.springframework.stereotype.Component;

@Component
public class PenggunaMapper {
    public PenggunaDTO toDTO(Pengguna pengguna) {
        PenggunaDTO dto = new PenggunaDTO();
        dto.setId(pengguna.getId());
        dto.setNama(pengguna.getNama());
        dto.setEmail(pengguna.getEmail());
        return dto;
    }

    public Pengguna toEntity(PenggunaDTO penggunaDTO) {
        Pengguna pengguna = new Pengguna();
        pengguna.setId(penggunaDTO.getId());
        pengguna.setNama(penggunaDTO.getNama());
        pengguna.setEmail(penggunaDTO.getEmail());
        return pengguna;
    }

}
