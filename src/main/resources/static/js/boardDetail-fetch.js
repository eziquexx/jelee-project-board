/**
 * 
 */

function boardDetailFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const posts = document.getElementById("posts");
	const urlPathName = window.location.pathname;
	const urlReplace = urlPathName.replace("/board/","");
	const urlSplit = urlReplace.split("/");
	const category = urlSplit[0];
	const id = urlSplit[1];
//	console.log(urlSplit);
//	console.log(category);
//	console.log(id);
	
	
	loading.style.display = "block";
	error.style.display = "none";
	posts.innerHtml = "";
	
	fetch(`/api/board/${category}/${id}`, {
		method: "GET",
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		}
	})
	.then(response => {
		if (!response.ok) {
			throw new Error("Network response was not ok");
		}
		return response.json();
	})
	.then(data => {
		loading.style.display = "none";
		const items = Object.entries(data);
		
		items.forEach(item => {
			console.log(item);
			const li = document.createElement("li");
			li.innerHTML = `${item[0]} : ${item[1]}`;
			posts.appendChild(li);
		})
		
	})
	.catch(error => {
		console.error("Error: ", error);
		loading.style.display = "none";
		error.style.display = "block";
	})
}

document.addEventListener("DOMContentLoaded", boardDetailFetch);


