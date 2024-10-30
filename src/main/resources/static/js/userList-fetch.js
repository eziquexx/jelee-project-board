

function userListFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const viewContents = document.getElementById("viewContents_id");
	const boardList_id = document.getElementById("boardList_id");
	
	loading.style.display = "block";
	error.style.display = "none";
	viewContents.style.display = "none";
	
	fetch (`/api/user/list`, {
		method: "GET",
		heards: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
	})
	.then(response => {
		if (!response.ok) {
			throw new Error("Network response was not ok");
		}
		return response.json();
	})
	.then(data => {
		loading.style.display = "none";
		viewContents.style.display = "block";
		
		data.forEach(user => {
			console.log(user);
			const tr = document.createElement("tr");
			tr.innerHTML = `
				<td>${user.id}</td>
				<td>${user.userId}</td>
				<td>${user.userName}</td>
				<td>${user.userEmail}</td>
				<td>${user.roles[0].name}</td>
				<td>${user.enabled}</td>
				<td>권한관리 버튼</td>
				`;
				boardList_id.appendChild(tr);
		})
	})
	.catch(error => {
		console.error("Error: ", error);
		error.style.display = "block";
		loading.style.display = "none";
	})
}

document.addEventListener("DOMContentLoaded", userListFetch);
