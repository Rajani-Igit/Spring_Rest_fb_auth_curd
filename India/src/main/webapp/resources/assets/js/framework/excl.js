var data;

// WORK all except IE 11
function loadFile(event) {
	alert("hii");
    alasql('SELECT * FROM FILE(?,{headers:true})',[event],function(res){
    	
        data = res;
        alert(data.length);
        for(var i=0;i<data.length;i++)
        	{
        	    var obj=data[i];
        	    alert(obj['EMPLOYEE NAME']);
        	}
       // document.getElementById("res").textContent = JSON.stringify(res);
    });
}

function getdata(){
	return data;
}

function saveFile(){
	
	alert("Hiiiisavefile");
	alert(data);
	return data;
}