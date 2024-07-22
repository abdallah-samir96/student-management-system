package com.bank.boubyan.dto;

public class CourseRegistrationDTO {
    private Integer courseId;
    private String token;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
