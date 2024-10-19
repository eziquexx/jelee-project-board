/**
 * 
 */

function boardListFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const boardList_id = document.getElementById("boardList_id");
	const urlPathName = window.location.pathname;
	const category = urlPathName.replace("/board/", "");
	console.log(category); //test
	
	loading.style.display = "block";
	error.style.display = "none";
	boardList_id.innerHtml = "";
	
	fetch(`/api/board/${category}`, {
		method: "GET",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
	})
	.then(response => {
		if (!response.ok) {
			throw new Error("Network response was not ok");
		}
		console.log(response);
		return response.json();
	})
	.then(data => {
		loading.style.display = "none";
		console.log(data); //test
		
		data.forEach(list => {
			const li = document.createElement("li");
			li.innerHTML = `<a href="${category}/${list.id}">${list.category} - ${list.id} - ${list.title}</a>`;
			
			boardList_id.appendChild(li);
		})		
	})
	.catch(error => {
		console.error("Error: ", error);
		error.style.display = "block";
		loading.style.display = "none";
	})
}

document.addEventListener("DOMContentLoaded", boardListFetch);