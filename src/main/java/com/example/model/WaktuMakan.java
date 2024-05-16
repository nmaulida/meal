package com.example.model;

import lombok.Data;

import java.sql.Time;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "waktu_makan")
@Data
public class WaktuMakan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private Time jamMulai;
    private Time jamBerakhir;
}
