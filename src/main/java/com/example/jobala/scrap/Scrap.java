package com.example.jobala.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "scrap_tb")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer jobopenId;
    private Integer resumeId;
    private Integer role; // 0 -> guest, 1 -> comp
    private LocalDateTime createAt;
}
