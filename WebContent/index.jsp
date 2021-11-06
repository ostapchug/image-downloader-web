<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Downloader</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="page-header">
<div class="container">
   <h2>Search Images</h2>
</div>
</div>

<div class="jumbotron text-center">
<form class="form-inline" action="search">
  <div class="form-group">
  <label for="url">URL:</label>
  <input type="text" name="url" placeholder="Enter URL" class="form-control">
  </div>
    
  <input type="submit" value="Search" class="btn btn-default"> 
</form>
</div>

<div class="container">
<div id="tips">
    <h3>With this application you can:</h3>
    <ul>
    <li>See images that the page contains;</li>
    <li>Filter them by width and height;</li>
    <li>Select images to download by clicking on the image.</li>
    </ul>
</div>
</div>

</body>
</html>