/**
 * 
 */

const categoryGroup = ["community", "qna", "study"];


function postsLatestListFetch() {
	const loading = document.getElementById("loading_id");
	const error = document.getElementById("error_id");
	const viewContents = document.getElementById("viewContents_id");
	const postsLatestList = document.getElementById("postsLatestList_id");
	
	loading.style.display = "block";
	error.style.display = "none";
	viewContents.style.display = "none";
	
//	boardList_id.innerHtml = "";


	fetch(`/api/board`, {
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
			
			postsLatestList.appendChild(tr);
		})		
	})
	.catch(error => {
		console.error("Error: ", error);
		error.style.display = "block";
		loading.style.display = "none";
	})
	
}	


document.addEventListener("DOMContentLoaded", postsLatestListFetch);