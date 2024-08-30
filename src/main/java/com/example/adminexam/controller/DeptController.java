package com.example.adminexam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.adminexam.dto.DeptDTO;
import com.example.adminexam.service.DeptService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DeptController {

    private static final String VIEW_PREFIX = "dept/";
    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
    
    @Autowired
    private DeptService deptService;

    //부서 페이지 이동
    @GetMapping("/dept")
    public String loadMyinfo(ModelMap model, HttpServletRequest request) throws Exception{
		
    	List<Map<String,Object>> list = deptService.getDeptAll();
    	
    	model.addAttribute("deptList",list);
    	model.addAttribute("size",list.size());
    	
        return VIEW_PREFIX+"dept.html";
    }
    
   //부서 전체 조회
    @GetMapping("/dept-all")
    @ResponseBody
    public List<Map<String,Object>> callAllDept(){
    	logger.debug("[ Call /dept-all - GET ]");
    	return deptService.getDeptAll();
    }
    
    
    //부서 저장
    @PostMapping("/dept")
    @ResponseBody
    public Map<String, Object> setDept(@RequestBody Map<String, Object> params) {
    	logger.debug("[ Call /dept - POST ]");
    	
    	//서비스 호출 및 메시지 반환
    	String msg = deptService.setDept(params);
    	
    	Map<String, Object> response = new HashMap<>();
	    response.put("msg", msg);
    	
    	return response;
    }
    
    //부서 업데이트
    @PatchMapping("/dept/{deptno}")
    @ResponseBody
    public Map<String, Object> callDeptUpdateApi(@RequestBody Map<String, Object> params,  @PathVariable("deptno") int deptno) {
    	logger.debug("[ Call /dept - PATCH ]");
    	params.put("deptno", deptno);
    	
    	//서비스 호출 및 메시지 반환
    	String msg = deptService.updateDept(params);
    	
    	Map<String, Object> response = new HashMap<>();
	    response.put("msg", msg);
    	
    	return response;
    }
    
    //해당 부서 조회
  	@GetMapping("/dept/{deptno}")
  	@ResponseBody
	public Map<String, Object> getDeptById(@PathVariable("deptno") int deptno) { 
		return deptService.getDeptById(deptno);
	}
  	
  	
  	//사원 삭제
  	@DeleteMapping("/dept/{deptno}")
  	@ResponseBody
	public String delDept(@PathVariable("deptno") int deptno) {
  		logger.debug("[ Call /deptno/ - DELETE ]");
		return deptService.delDept(deptno);
  	}
  	
  //부서명으로 검색
  	@GetMapping("/dept/search")
  	@ResponseBody
  	public List<DeptDTO> searchDept(@RequestParam("dname") String dname){
  		logger.debug("[ Call /dept/search - GET ]");
  		
  		List<DeptDTO> deptList = deptService.searchDept(dname);
  		
  		return deptList;
  	}
}
