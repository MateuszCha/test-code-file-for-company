function ChangeColorFuction(InputText)
{	
	if (InputText.value != "")
		{						
			InputText.style.backgroundColor="#A4F89F";
			InputText.style.border="3px solid #129B0B";
		}
		else
		{
			InputText.style.backgroundColor="#FF8A8A";
			InputText.style.border="3px solid #FF3232";
		}	
}

function RoundValue(value)
{
	var RoundValue=value/100;	
	return RoundValue=Math.round(RoundValue)*100;	
}
function isANumber(InputText)
{
	if(isNaN(parseInt(InputText.value)) || InputText.value < 0)
			InputText.value=0;
	ChangeColorFuction(InputText);
}
function SetSecendNumberTextValue(InputText1,InputText2)
{	
	let value1;
	let value2;	
	
	isANumber(InputText1);
	isANumber(InputText2);
	value1 = parseInt(InputText1.value);
	value2 = parseInt(InputText2.value);	
	console.log("dsadasdads");
	console.log(value1);
	console.log(value2);	
	if (value1>=value2)
	{
		InputText2.value=value1;
		InputText1.value=value1;
	}	
}

function SelectorsChange(e,InputText)
{
	
	isANumber(e.target)
	if (parseInt(e.target.value)  > 10000 ) 
		e.target.value = 10000;
	e.target.value = RoundValue(e.target.value);
	SetSecendNumberTextValue(InputText[0],InputText[1]);	
}

function isNumberPhone(NumberText)
{
	let LenghtNumberText = 13;
	
	if(LenghtNumberText >= NumberText.value.length)			
			LenghtNumberText=NumberText.value.length;		
		
	for(var i = 0; i<LenghtNumberText; i++)
	{
		if ((parseInt(NumberText.value.charAt(i)) >= 0 ) && (parseInt(NumberText.value.charAt(i)) <= 9 ))			
		{ 			
			if(i == LenghtNumberText-1) NumberText.value =  NumberText.value.substr(0,i+1);
		}
		else
			NumberText.value = NumberText.value.substr(0,i)+"-"+NumberText.value.substr(i+1,LenghtNumberText-(i+1));
	}
	for(var i = 0; i<LenghtNumberText; i++) 
		if(	NumberText.value.charAt(i) == "-") 
		{
			NumberText.style.backgroundColor="#FF8A8A";
			NumberText.style.border="3px solid #FF3232";
		}
}

document.addEventListener("DOMContentLoaded",function()
{	
	const InputText = document.querySelectorAll(".ImputNumberCss");
	
	document.querySelector("#firma").addEventListener("input", function(e) {ChangeColorFuction(e.target);});	
	document.querySelector("#numberPhone").addEventListener("input", function(e){ChangeColorFuction(e.target);});
	document.querySelector("#numberPhone").addEventListener("change", function(e){isNumberPhone(e.target);});
	
	for(let i=0;i<InputText.length;i++)
	{		
		InputText[i].addEventListener("input",function(e) {ChangeColorFuction(e.target);});
		InputText[i].addEventListener("change",function(e) {SelectorsChange(e,InputText);});
	}		
});
