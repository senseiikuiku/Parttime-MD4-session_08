package com.ra.session_08.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "classes")
public class aClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class_name",length = 100,unique = true,nullable = false)
    private String className;
    @Column(name = "status")
    private boolean status;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Student> students;
}
