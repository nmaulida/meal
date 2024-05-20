package com.example.repository;

import com.example.model.Pengguna;
import java.util.Optional;

public interface PenggunaRepository {
    Optional<Pengguna> findById(Long id);
    Pengguna save(Pengguna user);

}
