/**
 * 
 */
var images = document.getElementsByTagName("img");
var minWidthFilter = document.getElementById("minWidthFilter");
var minHeightFilter = document.getElementById("minHeightFilter");
var minWidth = document.getElementById("minWidth");
var minHeight = document.getElementById("minHeight");
var dwnlButton = document.getElementById("dwnlButton");

for(var i=0;i<images.length;i++){
	images[i].style.border ='3px solid white';
	images[i].addEventListener("click", function(){
		if(this.style.border == '3px solid white'){
			this.style.border ='3px solid blue'
		}else{
			this.style.border ='3px solid white'
		}
		setDwnlButton ();
	});
}

dwnlButton.addEventListener("click", function(){
		document.getElementById("selectedImages").value=saveImages(images);
		var form = document.getElementById("form");
		form.submit();
});

minWidth.addEventListener("input", function(){
	document.getElementById('widthLabel').innerHTML = this.value;
});
minWidth.addEventListener("change", function(){
	imageFilter(this.value, (minHeightFilter.checked?minHeight.value:0), images);	
});

minHeight.addEventListener("input", function(){
	document.getElementById('heightLabel').innerHTML = this.value;	
});
minHeight.addEventListener("change", function(){
	imageFilter((minWidthFilter.checked?minWidth.value:0), this.value, images);
});

minWidthFilter.addEventListener("click", function(){
	if(this.checked){
		minWidth.disabled = false;
		imageFilter(minWidth.value, (minHeightFilter.checked?minHeight.value:0), images);
	}else{
		minWidth.disabled = true;
		imageFilter(0, (minHeightFilter.checked?minHeight.value:0), images);
	}
});

minHeightFilter.addEventListener("click", function(){
	if(this.checked){
		minHeight.disabled = false;
		imageFilter((minWidthFilter.checked?minWidth.value:0), minHeight.value, images);
	}else{
		minHeight.disabled = true;
		imageFilter((minWidthFilter.checked?minWidth.value:0), 0, images);
	}
});

function imageFilter (width, height, arr){
	console.log(width+" "+height);
	for(var i=0;i<arr.length;i++){
		if (arr[i].naturalWidth>width && arr[i].naturalHeight>height){
			arr[i].parentElement.style.display="initial";
		}else{
			arr[i].parentElement.style.display="none";
			arr[i].style.border ='3px solid white';
		}
	}	
}

function setDwnlButton (){
	var tmp = [];
	for(var i=0;i<images.length;i++)
		tmp.push(images[i].style.border);
	
	if (tmp.includes('3px solid blue')){
		dwnlButton.disabled = false;
	}else{
		dwnlButton.disabled = true;		
	}
}

function saveImages(arr){
	var img = [];
	
	for(var i=0;i<arr.length;i++){
		if (arr[i].style.border == '3px solid blue')
			img.push(arr[i].src);
	}
	
	return img;
		
}
