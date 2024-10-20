/**
 * 
 */

const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", function(e) {
	e.preventDefault();
	
	const user = {
		userId: document.getElementById("userId").value,
		userPw: document.getElementById("userPw").value,
	}
	console.log(user);
	
	fetch("/user/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(user),
	})
	.then(response => {
		console.log(response);
		if (response.ok) {
			alert("로그인 성공");
		} else {
			alert("로그인 실패");
		}
	})
//	.then(data => ({
//		status: response.status,
//		data: data
//	}))
//	.then(({status, data}) => {
//    if (status === 200) {
//    	alert('로그인에 성공했습니다.');
//        //document.getElementById('messageForm').reset();
//			window.location.href = '/';
//    } else {
//          //alert(data.message);
//			throw new Error(data.message);
//    }
//  })
	.catch(error => {
		console.error("Error: ", error);
		alert(error.message || "로그인에 실패했습니다.");
	})
	
});