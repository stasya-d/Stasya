function run(){ 
var appContainer = document.getElementsByClassName('content')[0]; 
	appContainer.addEventListener('click', delegateEvent); 
	/*appContainer.addEventListener('change', delegateEvent);*/
}

function delegateEvent(evtObj) { 
	if(evtObj.type === 'click' && evtObj.target.classList.contains('todo-button')){ 
	myFunction(); 
	} 
}
/*
function myDeleteFunction() {
    document.getElementById("myTable").deleteRow(0);
}*/
function myFunction(){ 
	var text = document.getElementById("todoText"); 
	if(text.value == "") return; 
	var table = document.getElementById("table"); 
	var row = table.insertRow(-1); 
	var cell1 = row.insertCell(0); 
	var cell2 = row.insertCell(1); 
	var cell3 = row.insertCell(2); 
	/*
	var check = row.insertCell(3); 
	var cell5 = row.insertCell(4); 
	*/
	/*cell1.innerHTML = document.getElementById("you").value; */
	cell1.innerHTML = "Kate"; 
	cell2.innerHTML = text.value; 
	
	/*cell3.innerHTML = text.value; */
	cell2.innerHTML +='<button id="delete-message" class="delete-message"><button id="change-message" class="change-message">';
										
	/*cell5.innerHTML ='<input type="button" value="/" onclick="changeRow(this)" class="chg">'; */
	var d=new Date(); 
	cell3.innerHTML=d.getHours()+":"+d.getMinutes(); 
	text.value=""; 
}
