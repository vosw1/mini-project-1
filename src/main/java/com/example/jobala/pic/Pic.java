package com.example.jobala.pic;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "pic_tb")
@Entity
public class Pic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String imgFilename; // 파일 패스

    public Pic() {

    }
    public Pic(String title, String imgFilename) {
        this.title = title;
        this.imgFilename = imgFilename;
    }
}