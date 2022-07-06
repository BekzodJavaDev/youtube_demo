package com.company.controller;

import com.company.common.ApiResponse;
import com.company.dto.category.CategoryDTO;
import com.company.enums.LangEnum;
import com.company.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;



    @PostMapping("/admin/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        CategoryDTO categoryDTO = categoryService.create(dto);
        return  new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse>update(@RequestBody CategoryDTO dto, @PathVariable("id") Integer id){
        categoryService.update(id,dto);
        return  new ResponseEntity<>(new ApiResponse(true,"Successfully category updated"),HttpStatus.OK);
    }
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return  new ResponseEntity<>(new ApiResponse(true,"Successfully category deleted"),HttpStatus.OK);

    }
    @GetMapping("/public/list")
    public ResponseEntity<List<CategoryDTO>> getAll(@RequestHeader(value = "Accept-language", defaultValue = "uz") LangEnum langEnum) {
        List<CategoryDTO> categoryDTOS = categoryService.getList(langEnum);
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/admin/list")
    public ResponseEntity<List<CategoryDTO>> getList() {
        List<CategoryDTO> list = categoryService.getListOnlyForAdmin();
       return new ResponseEntity<>(list,HttpStatus.OK);
    }




}
