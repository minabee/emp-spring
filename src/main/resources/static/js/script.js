//페이지 로드 시 리스트 로드
    loadEmpList(); //사원
    loadDeptNameList(); //부서명
    loadStats(); //통계

//폼 초기화
function resetForm() {
    $('#i_empno').val('');
    $('#i_ename').val('');
    $('#i_job').val('');
    $('#i_sal').val('');
    $('#i_hiredate').val('');
    $('#i_comm').val('');
    $('#i_dname').val('');
}

//날짜 형식 검증
function valDateFormat(date){
    const datePattern = /^\d{4}-\d{2}-\d{2}$/;
    return datePattern.test(date); //test 함수가 true or false로 반환함
}
//사원 등록
function addEmp() {

	const empno = $('#i_empno').val();
	const ename = $('#i_ename').val();
	const job = $('#i_job').val();
	const sal = $('#i_sal').val();
	const hiredate = $('#i_hiredate').val();
	const comm = $('#i_comm').val();
	const dname = $('#i_dname').val();

	//필수값 확인
	if (!empno) {
		alert("사원 번호를 입력해 주세요.");
		return;
	}
	if (!ename) {
		alert("사원 이름을 입력해 주세요.");
		return;
	}
	if (!job) {
		alert("담당 업무를 입력해 주세요.");
		return;
	}
	if (!sal) {
		alert("급여를 입력해 주세요.");
		return;
	}
	if (!hiredate) {
		alert("입사 날짜를 입력해 주세요.");
		return;
	}
	if (!valDateFormat(hiredate)) {
		alert("입사 날짜는 YYYY-MM-DD 형식으로 입력해주세요.");
		return;
	}
	if (!dname) {
		alert("부서를 선택해주세요.");
		return;
	}

	let empData = {
		empno: empno,
		ename: ename,
		job: job,
		sal: sal,
		hiredate: hiredate,
		comm: comm,
		dname: dname
	};

	$.ajax({
		type: 'POST',
		contentType: "application/json",
		url: '/emp', //HTML이 프로젝트 내부에 있을 때는 컨트롤러 경로만 작성해도 된다.
		data: JSON.stringify(empData), //자바스크립트 객체를 JSON 문자열로 별환하여 전송
		dataType: 'json',
		success: function(response) {
			console.log(response);
			alert("사원정보가 추가되었습니다.");
			$('.write-popup').css('display', 'none');
			loadEmpList();
		},
		error: function(xhr, status, error) {
			console.log('error:' + error);
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
    const dname = $('#u_dname').val();

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
    if(!dname){
        alert("부서를 선택해주세요.");
        return;
    } 

    let empData = {
            empno: empno,
            ename: ename,
            job: job,
            sal: sal,
            hiredate: hiredate,
            comm: comm,
            dname: dname
    };

    $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: `http://localhost:8080/emp/${empno}`,
        data: JSON.stringify(empData), 
        dataType: 'json',
        success: function(response){
            alert("사원정보가 수정되었습니다.");
            $(".update-popup").css('display','none');
            loadEmpList();
        },
        error: function(xhr,status,error){
            console.log('error'+error);
        }
    });
}

//사원 조회
function clickRow(empno){
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/emp/${empno}`,
        dataType: 'json',
        success: function(data){
            $('#u_empno').val(data.empno);
            $('#u_ename').val(data.ename);
            $('#u_job').val(data.job);
            $('#u_sal').val(data.sal);
            $('#u_hiredate').val(data.hiredate);
            $('#u_comm').val(data.comm);
            $('#u_dname').val(data.dname);

            $('.update-popup').css('display','block');
        },
        error: function(xhr, status, error) {
            console.log('Error: ' + error);
        }
    });
}