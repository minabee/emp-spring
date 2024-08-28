package com.example.adminexam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adminexam.dto.DeptDTO;
import com.example.adminexam.mapper.DeptMapper;

@Service
public class DeptService {
	@Autowired
	private DeptMapper deptMapper;
	
	//전체 부서 조회
	public List<Map<String,Object>> getDeptAll(){
		return deptMapper.getDeptAll();
	}
	
	// 부서 저장
	public String setDept(Map<String, Object> params) {
		try {
			
			int result = deptMapper.setDept(params);
	        if (result > 0) {
	            return "부서가 성공적으로 저장되었습니다.";
	        } else {
	            return "부서 저장 중 오류가 발생했습니다.";
	        }
		} catch(Exception e) {
			return "부서 저장 중 오류가 발생했습니다.";
		}
	}
		
	
	//부서번호로 부서 조회
	public Map<String, Object> getDeptById(int deptno) {
		return deptMapper.getDeptById(deptno);
	}
	
	//사원 업데이트
	public String updateDept(Map<String, Object> params) {
		try {
			int result = deptMapper.updateDept(params);
	        if (result > 0) {
	            return "부서가 성공적으로 수정되었습니다.";
	        } else {
	            return "부서 수정 중 오류가 발생했습니다.";
	        }
		} catch(Exception e) {
			return "부서 수정 중 오류가 발생했습니다.";
		}
	}
	
	//사원 삭제
	public String delDept(int deptno) {
		int delRows = deptMapper.delDept(deptno);
		if(delRows > 0) {
			return "부서 정보가 삭제되었습니다.";
		}else {
			return "삭제 실패";
		}
	}
	
	//부서 검색
	public List<DeptDTO> searchDept(String dname){
		return deptMapper.searchDept(dname);
	}
}
