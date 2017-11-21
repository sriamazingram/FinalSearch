<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<title>Search Engine</title>
<style>
body {
  background: #e03a29;
}

body *, html * {
  box-sizing: border-box;
}

.container {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%);
}

.search-box input[type="text"] {
  border: none;
  background: none;
  z-index: 1;
  width: 25px;
  height: 25px;
  transition: all .25s ease-in .25s;
  color: transparent;
  font-size: .75rem;
  line-height: 25px;
}
.search-box input[type="text"]:hover {
  cursor: pointer;
}
.search-box input[type="text"]:hover:focus {
  cursor: text;
}
.search-box input[type="text"]:hover + span {
  background: rgba(255, 255, 255, 0.2);
}
.search-box input[type="text"]:focus {
  width: 200px;
  padding: 0 10px;
  outline: none;
  color: black;
  background: none;
  color: white;
}
.search-box input[type="text"]:focus + span {
  width: 200px;
}
.search-box input[type="text"]:focus + span::before {
  width: 2px;
  opacity: 0;
  transition: all .25s ease-in;
}
.search-box input[type="text"] + span {
  z-index: -1;
  position: absolute;
  border: 2px solid white;
  top: 0;
  width: 25px;
  height: 25px;
  transition: all .25s ease-in .25s;
  border-radius: 25px;
  left: 0;
}
.search-box input[type="text"] + span::before {
  transition: all .25s ease-in .5s;
  transform-origin: left top;
  content: '';
  position: absolute;
  width: 10px;
  height: 5px;
  border-radius: 5px;
  background: white;
  transform: rotate(45deg) translate(26px, -2px);
}

</style>
</head>
<body>
<div class="container">
	<form action="file1">
	<div class="search-box">
		<input type="text" name="query" />
		<span></span>
	</div>
	</form>
</div>
</body>
</html>