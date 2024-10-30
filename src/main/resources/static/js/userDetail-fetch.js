

function userListFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const viewContents = document.getElementById("viewContents_id");
	const boardList_id = document.getElementById("boardList_id");
	const urlPathName = window.location.pathname;
	const urlReplace = urlPathName.replace("/user/","");
	const urlSplit = urlReplace.split("/");
	const userId = urlSplit[0];
	const roleId = document.getElementById("roleId");
		
	loading.style.display = "block";
	error.style.display = "none";
	viewContents.style.display = "none";
	
	fetch (`/api/user/${userId}/roles`, {
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
		console.log(data);
		loading.style.display = "none";
		viewContents.style.display = "block";
		const allRoles = data.allRoles;
		if(data.roles.length > 0) {
			for (let i = 0; i < data.roles.length; i ++) {
				const tr = document.createElement("tr");
				tr.innerHTML = `
					<td>${data.roles[i].id}</td>
					<td>${data.roles[i].name}</td>
				`;
				boardList_id.appendChild(tr);
			}
		}
		
		allRoles.forEach(role => {
			const option = document.createElement("option");
			option.value = role.id;
			option.innerHTML = role.name;
			
			roleId.appendChild(option);
		});
	})
	.catch(error => {
		console.error("Error: ", error);
		error.style.display = "block";
		loading.style.display = "none";
	})
}


document.addEventListener("DOMContentLoaded", userListFetch);
