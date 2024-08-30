package com.example.adminexam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.adminexam.dto.EmpDTO;

@Mapper //MyBatis 인터페이스를 매퍼로 등록하여, DB 접근 로직을 처리할 수 있게 함.
public interface EmpMapper{
	//전체 사원 조회
	List<Map<String,Object>> getEmpAll();
	
	//사원 저장
	int setEmp(Map<String,Object> params);
	
	//사원조회
	Map<String,Object> getEmpById(int empno);
	
	//사원 업데이트
	int updateEmp(Map<String,Object> params);
	
	//사원 삭제
	int delEmp(int empno);
	
	//사원이름으로 사원검색
	List<EmpDTO> searchEmpByName(String ename);
	
	//통계
	Map<String,Object> getStats();
	
}
