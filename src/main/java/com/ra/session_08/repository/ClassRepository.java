package com.ra.session_08.repository;

import com.ra.session_08.model.entity.aClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClassRepository extends JpaRepository<aClass,Long> {

//    @Query("select c from aClass c where c.className LIKE %:keyword%")
    List<aClass> findAllByClassNameContains(String keyword);
}
