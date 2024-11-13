package com.ra.session_08.controller;

import com.ra.session_08.model.dto.ResponseDTO;
import com.ra.session_08.model.dto.aclass.ClassRequestDTO;
import com.ra.session_08.model.dto.aclass.ClassResponseDTO;
import com.ra.session_08.model.dto.aclass.ClassUpdateRequestDTO;
import com.ra.session_08.service.aclass.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassService classService;

    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

//    @GetMapping
//    public ResponseEntity<List<ClassResponseDTO>> index() {
//        List<ClassResponseDTO> response = classService.findAll();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    Phan trang va sap xep
    @GetMapping
    public ResponseEntity<?> index(@RequestParam(defaultValue = "0",name = "page") int page,
                                   @RequestParam(defaultValue = "3",name = "limit") int limit,
                                   @RequestParam(defaultValue = "ASC",name = "order") String sort,
                                   @RequestParam(defaultValue = "id",name = "sortBy") String sortBy){
        Pageable pageable;
        if(sort.equalsIgnoreCase("ASC")){
            pageable = PageRequest.of(page,limit, Sort.by(sortBy).ascending());
        }else {
            pageable = PageRequest.of(page,limit, Sort.by(sortBy).descending());
        }

        Page<ClassResponseDTO> responseDTOPage = classService.paginate(pageable);
        ResponseDTO<Page<ClassResponseDTO>> responseDTO = new ResponseDTO<>(200,"paginate or search success", responseDTOPage);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<ClassResponseDTO>create(@RequestBody ClassRequestDTO classRequestDTO) {
//        ClassResponseDTO response = classService.create(classRequestDTO);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ClassResponseDTO>>create(@RequestBody ClassRequestDTO classRequestDTO) {
        ClassResponseDTO response = classService.create(classRequestDTO);
        ResponseDTO<ClassResponseDTO> responseDTO = new ResponseDTO<>(201,"create category success",response);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> findById(@PathVariable Long id) {
        ClassResponseDTO response = classService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> update(@PathVariable Long id,@RequestBody ClassUpdateRequestDTO classUpdateRequestDTO) {
        classUpdateRequestDTO.setId(id);
        ClassResponseDTO response = classService.update(classUpdateRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> delete(@PathVariable Long id) {
        classService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClassResponseDTO>> search(@RequestParam(name = "keyword") String keyword) {
        List<ClassResponseDTO> responseDTOS = classService.searchByClassName(keyword);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

}
