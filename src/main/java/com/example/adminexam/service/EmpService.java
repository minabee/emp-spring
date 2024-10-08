package com.example.adminexam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adminexam.dto.EmpDTO;
import com.example.adminexam.mapper.EmpMapper;

@Service //-> Spring이 해당 클래스를 서비스 컴포넌트로 인식하여, 자동으로 빈(Bean)으로 등록
public class EmpService {
	@Autowired
	private EmpMapper empMapper;
	
	//전체 사원 조회
	public List<Map<String,Object>> getEmpAll(){
		return empMapper.getEmpAll();
	}
	
	//사원 저장
	public Map<String,Object> setEmp(Map<String,Object> params) {
		Map<String, Object> response = new HashMap<>();
		try {
			int result = empMapper.setEmp(params);
			if(result > 0) {
				response.put("msg", "사원이 성공적으로 저장되었습니다.");
			}else {
				response.put("msg", "사원 저장 중 오류가 발생했습니다.");
			}
		}catch(Exception e) {
			response.put("msg", "사원 저장 중 오류가 발생했습니다.");
		}
		return response;
	}
	
	//사원 조회
	public Map<String,Object> getEmpById(int empno){
		return empMapper.getEmpById(empno);
	}
	
	//사원 업데이트
	public Map<String, Object> updateEmp(Map<String,Object> params) {
		Map<String, Object> response = new HashMap<>();
		try {
			int result = empMapper.setEmp(params);
			if (result > 0) {
				response.put("msg", "사원이 성공적으로 저장되었습니다.");
			} else {
				response.put("msg", "사원 저장 중 오류가 발생했습니다.");
			}
		} catch (Exception e) {
			response.put("msg", "사원 저장 중 오류가 발생했습니다.");
		}
		return response;
	}
	
	//사원 삭제
	public String delEmp(int empno) {
		try {
			int result = empMapper.delEmp(empno);
			if(result > 0) {
				return "사원 정보가 삭제되었습니다.";
			}else {
				return "삭제 실패";
			}
		}catch(Exception e) {
			return "사원 삭제 중 오류가 발생했습니다.";
		}
	}
	
	//사원이름으로 사원 조회
	public List<EmpDTO> searchEmpByName(String ename){
		return empMapper.searchEmpByName(ename);
	}
	
	//통계
	public Map<String, Object> getStats(){
		return empMapper.getStats();
	}
}
