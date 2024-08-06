package com.assignment.musinsacatalog.interfaces.category;

import com.assignment.musinsacatalog.application.category.CategoryFacade;
import com.assignment.musinsacatalog.common.response.CommonResponse;
import com.assignment.musinsacatalog.interfaces.category.dto.CategoryDto;
import com.assignment.musinsacatalog.interfaces.category.dto.CategoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryApiController {

    private final CategoryFacade categoryFacade;
    private final CategoryDtoMapper categoryDtoMapper;

    @PostMapping
    public CommonResponse registerCategory(@RequestBody CategoryDto.RegisterRequest request) {

        var command = request.toCommand();
        var categoryInfo = categoryFacade.registerCategory(command);
        var response = categoryDtoMapper.of(categoryInfo);

        return CommonResponse.success(response);
    }

    @PutMapping
    public CommonResponse modifyCategory(@RequestBody CategoryDto.ModifyRequest request) {

        var command = request.toCommand();
        var categoryInfo = categoryFacade.modifyCategory(command);
        var response = categoryDtoMapper.of(categoryInfo);

        return CommonResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public CommonResponse removeCategory(@PathVariable Long id) {

        categoryFacade.removeCategory(id);

        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse getCategory(@PathVariable Long id) {

        var categoryInfo = categoryFacade.getCategory(id);
        var response = categoryDtoMapper.of(categoryInfo);

        return CommonResponse.success(response);
    }

    @GetMapping
    public CommonResponse getCategoryList() {

        var categories = categoryFacade.getCategories();
        var response = categories.stream().map(categoryDtoMapper::of).toList();

        return CommonResponse.success(response);
    }
}
