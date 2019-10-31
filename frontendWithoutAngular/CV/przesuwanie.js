var wysokosc;

function Logs(Input)
{
	
	var wysokosc2=window.pageYOffset;		
	if(wysokosc2 >= wysokosc)		
		Input.style.top = wysokosc-wysokosc+wysokosc2 +"px";
	else
		Input.style.top = wysokosc+"px";
}
document.addEventListener("DOMContentLoaded",function()
{
	
	var Input = document.getElementById("lista");
	wysokosc = Input.offsetTop;
	window.addEventListener("scroll", function()
	{
		Logs(Input);
	});
});