package com.example.jobala.apply;

import lombok.Data;

import java.sql.Date;

public class ApplyResponse {

    @Data
    public static class ApplyDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String edu;
        private Date endTime;
        private String state;

        public ApplyDTO(Integer id, String jobopenTitle, String resumeTitle, String name, String edu, Date endTime, String state) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.name = name;
            this.edu = edu;
            this.endTime = endTime;
            this.state = state;
        }
    }

    @Data
    public static class HireDTO {
        private Integer id;
        private String jobopenTitle;
        private String resumeTitle;
        private String name;
        private String state;

        public HireDTO(Integer id, String jobopenTitle, String resumeTitle, String name, String state) {
            this.id = id;
            this.jobopenTitle = jobopenTitle;
            this.resumeTitle = resumeTitle;
            this.name = name;
            this.state = state;
        }
    }
}