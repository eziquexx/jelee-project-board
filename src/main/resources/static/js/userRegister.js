/**
 * 
 */

const registerForm = document.getElementById("registerForm");

registerForm.addEventListener("submit", function(e) {
	e.preventDefault();
	
	const user = {
		userId: document.getElementById("userId").value,
		userPw: document.getElementById("userPw").value,
		userName: document.getElementById("userName").value
	}
	console.log(user);
	
	fetch("/user/register", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(user),
	})
	.then(response => {
		if (response.ok) {
			alert("회원가입이 되었습니다.");
			registerForm.reset();
		} else {
			alert("회원가입에 실패했습니다.");
		}
	})
	.catch(error => {
		console.error("Error: ", error);
		alert("오류가 발생했습니다.");
	})
	
});