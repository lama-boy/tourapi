package com.example.demo.mapper;

import com.example.demo.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<TestDto> getTestData();

}
