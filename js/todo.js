'use strict';
var taskList = [];

function run(){ 
var appContainer = document.getElementsByClassName('content')[0]; 
	appContainer.addEventListener('click', delegateEvent); 
	taskList = loadTasks() || [
		newTask('Сделать разметку')
	];
	render(taskList);	
}

function render(tasks) {/*
	for(var i = 0; i < tasks.length; i++) {
		//renderTask(tasks[i]);
	}
*/
	for (var i=0; i< taskList.length; i++){
			renderLocalStorage(taskList[i]);
	}
	//renderLocalStorage(taskList);
	/*renderCounter(taskList);*/
}
/*function renderTask(task){
	var items = document.getElementsByClassName('content')[0];
	//var items = document.getElementsByClassName('items')[0];
	var element = elementFromTemplate();
	renderTaskState(element, task);
	items.appendChild(element);
}*/

function elementFromTemplate() {
	var template = document.getElementById("table");
	return template.firstElementChild.cloneNode(true);
}

function loadTasks() {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}
	var item = localStorage.getItem("TODOs taskList");
	//Является строкой DOMString, содержащей значение для соответствующего ключа.  
	//Если ключ не существует, то будет возвращено значение null.
	return item && JSON.parse(item);
}

function renderLocalStorage(value){
	var table = document.getElementById("table"); 
	var row = table.insertRow(-1); 
	var cell1 = row.insertCell(0); 
	var cell2 = row.insertCell(1); 
	var cell3 = row.insertCell(2); 
	var cell4 = row.insertCell(3); 
	cell1.innerHTML = "Kate"; 
	cell2.innerHTML = JSON.stringify(value, null, 2)+";";
	//var v = value[value.length-1];
	//cell2.innerHTML =
	//table.innerText = "localStorage:\n" + JSON.stringify(value, null, 2) + ";";
	cell1.innerHTML = value.name;
    cell2.innerHTML = value.message;
    cell3.innerHTML ='<button id="delete-message" onclick="deleteRow(this)" class="delete-message"><button id="change-message" onclick="changeContent(this)" class="change-message">';
    var d=new Date(value.date);
    cell4.innerHTML = d.getHours() + ":" + d.getMinutes();/*var date= value.date;
		cell4.innerHTML = date.getHours()+":"+date.getMinutes()+".";;*/
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
	taskList.splice(i-1, 1);
	r.parentElement.removeChild(r);
	//renderCounter(taskList);
	//renderLocalStorage(taskList);
	saveTasks(taskList);

}
function changeContent(r)
{
	var i=r.parentNode.parentNode.rowIndex;
	var x=document.getElementById('table').rows[i].cells;
	var d=new Date(); 
	var text = document.getElementById("todoText"); 
	/*text.value = x[1].innerHTML;*/
	x[1].innerHTML = text.value +" Tекст был изменён "+d.getHours()+":"+d.getMinutes()+".";
	taskList[i-1].message = text.value +" Tекст был изменён "+d.getHours()+":"+d.getMinutes()+".";
	saveTasks(taskList);
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
	
	var task = newTask(text.value);
	taskList.push(task);
	todoText.value = '';
	//render([task]);
	saveTasks(taskList);
	text.value=""; 
}
function uniqueId() {
	var date = Date.now();
	var random = Math.random() * Math.random();
	return Math.floor(date * random);
}
function newTask(text) {
	return {
		name: "Kate",
		message: text,
		id: '' + uniqueId(),
		date: new Date
	};
}
function saveTasks(listToSave) {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}
	localStorage.setItem("TODOs taskList", JSON.stringify(listToSave));
}/*
function renderTaskState(element, task){
	element.classList.remove('strikeout');
	element.setAttribute('data-task-id', task.id);
	element.lastChild.textContent = task.description;
}*/