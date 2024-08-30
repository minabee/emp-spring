//날짜 형식 검증
function valDateFormat(date){
    const datePattern = /^\d{4}-\d{2}-\d{2}$/;
    return datePattern.test(date); //test 함수가 true or false로 반환함
}

//사원 등록
function addEmp(){
    
    const empno = $('#i_empno').val();
    const ename = $('#i_ename').val();
    const job = $('#i_job').val();
    const sal = $('#i_sal').val();
    const hiredate = $('#i_hiredate').val();
    const comm = $('#i_comm').val();
    const deptno = $('#i_dname').val();
	
	// 확인용 로그
   console.log('dname:', deptno);

    //필수값 확인
    if(!empno){
        alert("사원 번호를 입력해 주세요.");
        return;
    } 
    if(!ename){
        alert("사원 이름을 입력해 주세요.");
        return;
    } 
    if(!job){
        alert("담당 업무를 입력해 주세요.");
        return;
    } 
    if(!sal){
        alert("급여를 입력해 주세요.");
        return;
    } 
    if(!hiredate){
        alert("입사 날짜를 입력해 주세요.");
        return;
    } 
    if(!valDateFormat(hiredate)){
        alert("입사 날짜는 YYYY-MM-DD 형식으로 입력해주세요.");
        return;
    } 
    if(!deptno){
        alert("부서를 선택해주세요.");
        return;
    } 

    let empData = {
            empno: empno,
            ename: ename,
            job: job,
            sal: sal,
            hiredate: hiredate,
            comm: comm || '',
            deptno: deptno
    };

    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/emp',
        data: JSON.stringify(empData), //자바스크립트 객체를 JSON 문자열로 별환하여 전송
        dataType: 'json',
        success: function(response){
            alert(response.msg);
            $('.write-popup').css('display', 'none'); 
            location.reload(); 
        },
		error:function(xhr,status,error){
			console.log('Error: ' + error);
		}
    });
}
//사원 수정
function updateEmp(){

    const empno = $('#u_empno').val();
    const ename = $('#u_ename').val();
    const job = $('#u_job').val();
    const sal = $('#u_sal').val();
    const hiredate = $('#u_hiredate').val();
    const comm = $('#u_comm').val();
    const deptno = $('#u_dname').val();

    //필수값 확인
    if(!empno){
        alert("사원 번호를 입력해 주세요.");
        return false;
    } 
    if(!ename){
        alert("사원 이름을 입력해 주세요.");
        return false;
    } 
    if(!job){
        alert("담당 업무를 입력해 주세요.");
        return false;
    } 
    if(!sal){
        alert("급여를 입력해 주세요.");
        return false;
    } 
    if(!hiredate){
        alert("입사 날짜를 입력해 주세요.");
        return false;
    }
    if(!valDateFormat(hiredate)){
        alert("입사 날짜는 YYYY-MM-DD 형식으로 입력해주세요.");
        return;
    } 
    if(!deptno){
        alert("부서를 선택해주세요.");
        return;
    } 

    let empData = {
            empno: empno,
            ename: ename,
            job: job,
            sal: sal,
            hiredate: hiredate,
            comm: comm || '',
            deptno: deptno
    };

    $.ajax({
        type: 'PATCH',
        contentType: "application/json",
        url: `/emp/${empno}`,
        data: JSON.stringify(empData), 
        dataType: 'json',
		success: function(response){
            alert(response.msg);
            $('.update-popup').css('display', 'none'); 
            location.reload(); 
        },
        error: function(xhr,status,error){
            console.log('error'+error);
        }
    });
}

//사원 삭제
   function delEmp(){
       let empno = $('#u_empno').val();
       if(confirm('정말 삭제하시겠습니까?')){
           $.ajax({
           type: 'DELETE',
           contentType: "application/json",
           url: `/emp/${empno}`,
           success: function(response){
               alert(response);
               $(".update-popup").css('display','none');
               location.reload(); 
           },
           error: function(xhr,status,error){
               console.log('error'+error);
           }
       });
       }
       
   }

//사원 조회
function clickRow(empno){
    $.ajax({
        type: 'GET',
        url: `/emp/${empno}`,
        dataType: 'json',
        success: function(data){
            $('#u_empno').val(data.empno);
            $('#u_ename').val(data.ename);
            $('#u_job').val(data.job);
            $('#u_sal').val(data.sal);
            $('#u_hiredate').val(data.hiredate);
            $('#u_comm').val(data.comm);
            $('#u_dname').val(data.deptno);

            $('.update-popup').css('display','block');
			
        },
        error: function(xhr, status, error) {
            console.log('Error: ' + error);
        }
    });
}

//사원이름 검색
    function searchEmp(){
        let ename = $('#searchName').val();
        $.ajax({
            type: 'GET',
            url: `/emp/search?ename=${ename}`,
            dataType: 'json',
            success: function(data){
				let empData = $('#empData');
	            empData.empty();
				
				if (data.length === 0) {
	                empData.append('<tr><td colspan="7">데이터가 없습니다.</td></tr>');
	            } else {
	                data.forEach(function(emp) {
	                    let row = `<tr onclick="clickRow(${emp.empno})">
	                        <td>${emp.empno}</td>
	                        <td>${emp.ename}</td>
	                        <td>${emp.job}</td>
	                        <td>${emp.sal}</td>
	                        <td>${emp.hiredate}</td>
	                        <td>${emp.dname}</td>
	                        <td>${emp.comm}</td>
	                    </tr>`;
	                    empData.append(row);
	                });
	            }
            },
            error: function(xhr, status, error){
                console.log("Error: " + error);
            }
        });
    }
	
//부서이름 검색
    function searchDept(){
        let dname = $('#searchDeptName').val()
        $.ajax({
            type: 'GET',
            url: `/dept/search?dname=${dname}`,
            dataType: 'json',
            success: function(data){
				let deptData = $('#deptData');
	            deptData.empty();
				
				if (data.length === 0) {
	                deptData.append('<tr><td colspan="4">데이터가 없습니다.</td></tr>');
	            } else {
	                data.forEach(function(dept) {
	                    let row = `<tr onclick="clickDept(${dept.deptno})">
	                        <td>${dept.deptno}</td>
	                        <td>${dept.dname}</td>
	                        <td>${dept.loc}</td>
	                        <td>${dept.empcnt}</td>
	                    </tr>`;
	                    deptData.append(row);
	                });
	            }
            },
            error: function(xhr,status,error){
                console.log('error : '+error);
            }
        });
    }


//부서 등록
   function addDept(){

       const deptno = $('#i_deptno').val();
       const dname = $('#i_dname').val();
       const loc = $('#i_loc').val();
       
       //필수값 확인
       if(!deptno){
           alert("부서 번호를 입력해 주세요.");
           return;
       }
       if(!dname){
           alert("부서 이름을 입력해 주세요.");
           return;
       }
       if(!loc){
           alert("부서 지역을 입력해 주세요.");
           return;
       }

       let deptData = {
               deptno: deptno,
               dname: dname,
               loc: loc
       }

       $.ajax({
           type: 'POST',
           contentType: "application/json",
           url: '/dept',
           data: JSON.stringify(deptData),
           dataType: 'json',
           success: function(response){
               alert(response.msg);
               $(".write-popup").css("display","none");
               location.reload();
           },
           error : function(xhr,status,error){
               console.log('error'+error);
           }
       });
   }

   //부서 수정
   function updateDept(){

       const deptno = $('#u_deptno').val();
       const dname = $('#u_dname').val();
       const loc = $('#u_loc').val();
       
       //필수값 확인
       if(!deptno){
           alert("부서 번호를 입력해 주세요.");
           return;
       }
       if(!dname){
           alert("부서 이름을 입력해 주세요.");
           return;
       }
       if(!loc){
           alert("부서 지역을 입력해 주세요.");
           return;
       }

       let deptData = {
               deptno: deptno,
               dname: dname,
               loc: loc
       }

       $.ajax({
           type: 'PATCH',
           contentType: "application/json",
           url: `/dept/${deptno}`,
           data: JSON.stringify(deptData),
           dataType: 'json',
           success: function(response){
               alert(response.msg);
               $(".update-popup").css('display','none');
               location.reload();
           },
           error: function(xhr,status,error){
               console.log('error'+error);
           }
       });
   }

   //부서 삭제
   function delDept(){
       let depno = $('#u_deptno').val();
       if(confirm('정말 삭제하시겠습니까?')){
           $.ajax({
           type: 'DELETE',
           contentType: "application/json",
           url: `/dept/${depno}`,
           success: function(response){
               alert(response)
               $(".update-popup").css('display','none');
               location.reload();
           },
           error: function(xhr,status,error){
               console.log('error : '+error);
           }
       });
       }
   }
   //부서 조회
   function clickDept(deptno){
       $.ajax({
           type: 'GET',
           url: `/dept/${deptno}`,
           dataType: 'json',
           success: function(data){
               $('#u_deptno').val(data.deptno);
               $('#u_dname').val(data.dname);
               $('#u_loc').val(data.loc);

               $('.update-popup').css('display', 'block');
           },
           error : function(xhr,status,error){
               console.log('error'+error);
           }
       });
   }
   
   // 로그아웃 클릭
       $('#logout').on('click', function (event) {
           event.preventDefault(); // 기본 동작을 막음
           
           $.ajax({
               type: 'POST',
               url: '/logout',
               success: function(response) {
                   location.href = '/login'; // 로그아웃 후 로그인 페이지로 리다이렉트
               },
               error: function(xhr, status, error) {
                   console.log('Error: ' + error);
               }
           });
       });