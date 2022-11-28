<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top"/>

<div class="container mt-3" style="height:600px;overflow:auto;">
	<h1 class="text-center">File Upload Result</h1>

	<h2>올린이 : ${name}</h2>
	<h2>첨부파일명 : ${mfilefname1}</h2>
	<h2>첨부파일사이즈 : ${fsize1} bytes</h2>
	<h2>첨부파일명 : ${mfilefname2}</h2>
	<h2>첨부파일사이즈 : ${fsize2} bytes</h2>
	
</div>

<c:import url="/foot"/>
