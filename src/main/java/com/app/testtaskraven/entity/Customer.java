package com.app.testtaskraven.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET is_active = false WHERE id = ?")
@Where(clause = "is_active = true")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
