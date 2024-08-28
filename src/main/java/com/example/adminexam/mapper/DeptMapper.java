package com.example.adminexam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.adminexam.dto.DeptDTO;

@Mapper
public interface DeptMapper {
	
	//전체 부서 조회
	List<Map<String,Object>> getDeptAll();
	
	//부서 저장
	int setDept(Map<String, Object> dto);
	
	//부서번호로 조회
	Map<String, Object> getDeptById(int deptno);
	
	//부서 업데이트
	int updateDept(Map<String, Object> dto);
	
	//부서 삭제
	int delDept(int empno);
	
	//부서 검색
	List<DeptDTO> searchDept(String dname);

}
