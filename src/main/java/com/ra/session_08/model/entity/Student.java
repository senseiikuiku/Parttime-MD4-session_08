package com.ra.session_08.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;
    @Column(name = "phone",length = 100,unique = true)
    private String phone;
    @Column(name = "address",length = 100)
    private String address;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "date")
    private Date date;
    @Column(name = "gender")
    private boolean gender;
    @ManyToOne
    @JoinColumn(name = "class_id",referencedColumnName = "id")
    private aClass classes;
}
