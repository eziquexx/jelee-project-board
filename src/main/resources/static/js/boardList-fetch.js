/**
 * 
 */

function boardListFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const boardList_id = document.getElementById("boardList_id");
	const viewContents = document.getElementById("viewContents_id");
	const urlPathName = window.location.pathname;
	const category = urlPathName.replace("/board/", "");
	console.log(category); //test
	
	loading.style.display = "block";
	error.style.display = "none";
	viewContents.style.display = "none";
	
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
		//console.log(response);
		return response.json();
	})
	.then(data => {
		loading.style.display = "none";
		viewContents.style.display = "block";
		//console.log(data); //test
		
		data.forEach(list => {
			const date = new Date(list.createdAt);
			const date_str = [date.getFullYear(), date.getMonth()+1, date.getDate()].join('/')
			//console.log(date_str); // test
			const tr = document.createElement("tr");
			tr.innerHTML = `
				<td>${list.category}</td>
				<td class="postsTitle"><a href="/board/${list.category}/${list.id}">${list.title}</a></td>
				<td>${list.authorId}</td>
				<td>${date_str}</td>
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

document.addEventListener("DOMContentLoaded", boardListFetch);