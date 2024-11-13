package com.ra.session_08.controller;

import com.ra.session_08.model.dto.student.StudentRequestDTO;
import com.ra.session_08.model.dto.student.StudentResponseDTO;
import com.ra.session_08.model.dto.student.StudentUpdateRequestDTO;
import com.ra.session_08.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> index() {
        List<StudentResponseDTO> studentResponseDTO = studentService.findAll();
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO studentResponseDTO = studentService.create(studentRequestDTO);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable long id) {
        StudentResponseDTO studentResponseDTO = studentService.findById(id);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable long id, @RequestBody StudentUpdateRequestDTO studentRequestDTO) {
        studentRequestDTO.setId(id);
        StudentResponseDTO studentResponseDTO = studentService.update(studentRequestDTO);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> delete(@PathVariable long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>> search(@RequestParam(name = "keyword") String keyword) {
        List<StudentResponseDTO> responseDTOS = studentService.searchByStudentName(keyword);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

}
