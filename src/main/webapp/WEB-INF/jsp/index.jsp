<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h3 {
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
li.it {
  display: flex;
  text-align: left;
  max-height: 100px;
  overflow: hidden;

  padding-top: 10px;
}

.url {
  margin: 5px;
  float: left;
  cursor: pointer;
}
.cur {
  background: gray;
}
.bt {
  cursor: pointer;
  margin: 5px 3px;
}
.showAll_k.it {
  max-height: 100%;
}

.figure-list {
  margin: 0;
  padding: 0;
}
.figure-list:after {
  content: "";
  display: block;
  clear: both;
  height: 0;
  overflow: hidden;
  visibility: hidden;
}
.figure-list li {
  list-style: none;
  float: left;
  width: 23%;
  margin: 0 2% 2% 0;
}
.figure-list figure {
  border: 1px solid #000;
  position: relative;
  width: 100%;
  height: 0;
  overflow: hidden;
  margin: 0;
  padding-bottom: 100%; /* 关键就在这里 */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
.figure-list figure a {
  display: block;
  position: absolute;
  width: 100%;
  top: 0;
  bottom: 0;
}
</style>
</head>
<body>
ddfd
</body>
</html>