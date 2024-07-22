package com.bank.boubyan.repository;

import com.bank.boubyan.model.Student;

public interface StudentDao {
    Student findByEmail(String email);
}
