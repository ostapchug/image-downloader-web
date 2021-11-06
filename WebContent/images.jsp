<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Downloader</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="page-header">
<div class="container">
   <h2>Download Images</h2>
</div>
</div>

<div class="jumbotron text-center">
<form id="form" class="form-inline" action="download">
  
  <div class="form-group">
  <div class="checkbox-inline">
  		<label><input type="checkbox" id="minWidthFilter">Min Width:</label>
  </div>
  <label id="widthLabel" for="minWidth">350</label>
  <input  type="range" id="minWidth" min="100" max="1000" value="350" disabled>
  </div>
  
  <div class="form-group">
  <div class="checkbox-inline">
  		<label><input type="checkbox" id="minHeightFilter">Min Height:</label>
  </div>
  <label id="heightLabel" for="minHeight">500</label>
  <input type="range" id="minHeight" min="100" max="1000" value="500" disabled>
  </div>
  
  <input type="hidden" name="images" id="selectedImages"/>
  
  <input type="button" value="Download" class="btn btn-default" id="dwnlButton" disabled> 
</form>
</div>

<div class="container">
<div class="row" id="imageBox">
<% 
String [] images = (String []) request.getAttribute("images");
for (String u: images){
	out.println("<div class=col-sm-4>");
	out.println("<img src="+u+">");
	out.println("</div>");
}
%>
</div>
</div>

<script src="script.js"></script>
</body>
</html>