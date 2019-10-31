function setVisailityTextTrue(iValue)
{
		var sValue= "text"+iValue;
		document.getElementById("text0").style.display ="none";
		document.getElementById(sValue).style.display ="block";		
}
function setVisailityTextFalse(iValue)
{	
	var sValue= "text"+iValue;
	document.getElementById(sValue).style.display ="none";
	document.getElementById("text0").style.display ="block";
}