function setTime()
{
	var date = new Date();
	document.getElementById("clock").innerHTML = changeTime(date.getHours()) +":" +changeTime(date.getMinutes()) +":" +changeTime(date.getSeconds());
	document.getElementById("calenderDay").innerHTML = date.getDate();
	document.getElementById("calenderMonth").innerHTML = changeNameOfMonth(date.getMonth());
	document.getElementById("calenderYear").innerHTML = date.getFullYear();
	setTimeout("setTime()",1000);	
}

function changeTime(iValue)
{
	if(iValue<10)
		iValue ="0"+iValue;
	return iValue;	
}

function changeNameOfMonth(iMonth)
{
	const tab=["Styczeñ","Luty","Marzec","Kwiecieñ","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"];
	
	return tab[iMonth];
}