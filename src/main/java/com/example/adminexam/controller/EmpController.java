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

import com.example.adminexam.dto.EmpDTO;
import com.example.adminexam.service.EmpService;

import jakarta.servlet.http.HttpServletRequest;
/*
 * @Controller vs @RestController 차이
 * @RestController : JSON/XML 응답을 반환
 * @Controller : 일반적인 뷰를 반환
 */
@Controller
public class EmpController {
	
	//final : 변수를 한 번 초기화하면 더 이상 변경 불가능. (값이 고정,재할당 불가능)
    private static final String VIEW_PREFIX = "emp/";
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class); //로그 메세지 기록
    
    @Autowired
    private EmpService empService;
    
    /** 사원페이지 조회(페이지 호출)
     * */
    @GetMapping("/emp")
    public String loadEmpPage(ModelMap model, HttpServletRequest request) throws Exception{
    	logger.debug("[ Call /emp - GET ]");
    	
    	//전체 사원 목록 조회
    	List<Map<String,Object>> list = empService.getEmpAll(); //서비스에서 데이터 가져옴
    	
    	//사원 목록을 모델에 추가
    	model.addAttribute("empList",list); //"emp/emp" 경로에 데이터 전달
    	model.addAttribute("size",list.size()); 
    	
        return VIEW_PREFIX+"emp.html";
    }
    
    //추가
    //사원 저장
    @PostMapping("/emp")
    @ResponseBody
    public Map<String, Object> callInsertEmp(@RequestBody Map<String, Object> params) {
    	logger.debug("[ Call /emp - POST ]");
    	return empService.setEmp(params);
    }
    
    //사원 업데이트
    @PatchMapping("/emp/{empno}")
    @ResponseBody
    public Map<String, Object> callEmpUpdateApi(@RequestBody Map<String, Object> params,  @PathVariable("empno") int empno) {
    	logger.debug("[ Call /emp - PATCH ]");
    	params.put("empno", empno);
    	return empService.updateEmp(params);
    }
    //해당 사원 조회
  	@GetMapping("/emp/{empno}")
  	@ResponseBody
  	public Map<String, Object> getEmpById(@PathVariable("empno") int empno) { 
  		logger.debug("[ Call /emp/{empno} - GET ]");
  		return empService.getEmpById(empno);
  	}
  	//사원 삭제
  	@DeleteMapping("/emp/{empno}")
  	@ResponseBody
	public String delEmp(@PathVariable("empno") int empno) {
  		logger.debug("[ Call /emp/search - GET ]");
		return empService.delEmp(empno);
  	}
  	
  	//사원 이름으로 사원 조회
  	@GetMapping("/emp/search")
  	@ResponseBody
  	public List<EmpDTO> searchEmpByName(@RequestParam String ename){ 
  		logger.debug("[ Call /emp/search - GET ]");
  		return empService.searchEmpByName(ename);
  	}
  	
  	//통계
  	@GetMapping("/emp/stats") 
  	public Map<String, Object> getStats(){
  		logger.debug("[ Call /emp/stats - GET ]");
  		Map<String, Object> stats = new HashMap<>();
  		stats.put("empStatistics", empService.getStats());
  		return stats;
  	}
        
}
