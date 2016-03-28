function run(){ 
var appContainer = document.getElementsByClassName('content')[0]; 
	appContainer.addEventListener('click', delegateEvent); 
	
}

function delegateEvent(evtObj) { 
	if(evtObj.type === 'click' && evtObj.target.classList.contains('todo-button')){ 
	myFunction(); 
	} 
	/*if(evtObj.type === 'click' && evtObj.target.classList.contains('delete-message')){ 
	deleteRow(r); 
	} 
	if(evtObj.type === 'click' && evtObj.target.classList.contains('change-message')){ 
	changeContent(r); 
	} */
}
function deleteRow(r)
{
var i=r.parentNode.parentNode.rowIndex;
document.getElementById('table').deleteRow(i);
}
function changeContent(r)
{
	var i=r.parentNode.parentNode.rowIndex;
	var x=document.getElementById('table').rows[i].cells;
	var d=new Date(); 
	var text = document.getElementById("todoText"); 
	/*text.value = x[1].innerHTML;*/
	x[1].innerHTML=text.value +" Tекст был изменён "+d.getHours()+":"+d.getMinutes()+".";	
	/*x[1].innerHTML +='<button id="delete-message" onclick="deleteRow(this)" class="delete-message"><button id="change-message" onclick="changeContent(this)" class="change-message">';
*/
}
function myFunction(){ 
	var text = document.getElementById("todoText"); 
	if(text.value == "") return; 
	var table = document.getElementById("table"); 
	var row = table.insertRow(-1); 
	var cell1 = row.insertCell(0); 
	var cell2 = row.insertCell(1); 
	var cell3 = row.insertCell(2); 
	var cell4 = row.insertCell(3); 
	/*cell1.innerHTML = document.getElementById("you").value; */
	cell1.innerHTML = "Kate"; 
	cell2.innerHTML = text.value; 
	cell3.innerHTML ='<button id="delete-message" onclick="deleteRow(this)" class="delete-message"><button id="change-message" onclick="changeContent(this)" class="change-message">';
	var d=new Date(); 
	cell4.innerHTML=d.getHours()+":"+d.getMinutes(); 
	text.value=""; 
}
