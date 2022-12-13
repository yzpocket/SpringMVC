<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--  ---------------------------------------------------------- -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- function taglib------------------------------------------- -->

<c:import url="/top"/>

<style>
#boardBody>tr>td:nth-child(5n+2){
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<script>
	function check(){
		if(!searchF.findType.value){
			alert('검색 유형을 선택하세요');
			return false;
		}
		if(!searchF.findKeyword.value){
			alert('검색어를 입력하세요');
			searchF.findKeyword.focus();
			return false;
		}
		return true;
	}
</script>

<%-- ${boardArr} --%>
<div class="container mt-3" style="height:600px;overflow: auto;">
	<h1 class="text-center">
		Spring Board	
	</h1>
	<c:if test="${paging.findType ne null and paging.findType ne ''}">
		<h3 class="text-center text-secondary">검색어: <c:out value="${paging.findKeyword}"/></h3>
	</c:if>
	<p class="text-center my-4">
		<a href="write">글쓰기</a>|<a href="list">글목록</a>
	</p>
	<!-- 검색 폼 시작----------------------------- -->
	
		<div class="row py-3">
			<div class="col-md-9 text-center">
				<form name="searchF" action="list" onsubmit="return check()">
					<!-- hidden data -------------------------------------- -->
					<input type="hidden" name="pageSize" value="${pageSize}">
					<input type="hidden" name="cpage" value="${paging.cpage}">
					<!-- -------------------------------------------------- -->
					<select name="findType" style="padding:6px;">
						<option value="">:::검색 유형:::</option>
						<option value="1" <c:if test="${paging.findType eq 1}">selected</c:if>   >글제목</option>
						<option value="2" <c:if test="${paging.findType eq 2}">selected</c:if>   >작성자</option>
						<option value="3" <c:if test="${paging.findType eq 3}">selected</c:if>    >글내용</option>
					</select>
					<input type="text" name="findKeyword" placeholder="검색어를 입력하세요" autofocus="autofocus"
					 style='width:50%;padding:5px;'>
					<button class='btn btn-outline-primary'>검  색</button>
				</form>
			</div>
			<div class="col-md-3 text-right">
				<form name="pageSizeF" action="list">
					<!-- hidden data -------------------------------------- -->
					<input type="hidden" name="findType" value="${paging.findType}">
					<input type="hidden" name="findKeyword" value="${paging.findKeyword}">
					<input type="hidden" name="cpage" value="${paging.cpage}">
					<!-- -------------------------------------------------- -->
					<select name="pageSize" style="padding:6px;" onchange="submit()">
						<option value=''>::페이지 사이즈::</option>
						<c:forEach var="ps" begin="5" end="20" step="5">
							<option value='${ps}'  <c:if test="${pageSize eq ps}">selected</c:if> >페이지 사이즈 ${ps}</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</div>
	
	<!-- -------------------------------------- -->
	<table class="table table-condensed table-striped">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id="boardBody">
		<c:if test="${boardArr eq null or empty boardArr}">
			<tr>
				<td colspan="3"><b>데이터가 없습니다.</b></td>
			</tr>
		</c:if>
		<c:if test="${boardArr ne null and not empty boardArr}">
			<c:forEach var="board" items="${boardArr}">
			<tr>
				<td>
					<c:out value="${board.num}"/>
				</td>
				<td>
					<!-- 답변레벨에 따라 들여쓰기 하자 -->
					<c:forEach var="k" begin="1" end="${board.lev}">
						&nbsp;&nbsp;&nbsp;
					</c:forEach>
					<c:if test="${board.lev>0}">
						<img src="../images/re.png">
					</c:if>
					<!-- 글제목 ---------------- -->
					<a href="view/<c:out value="${board.num}"/>">
						<c:if test="${fn:length(board.subject)>20}">
							<c:out value="${fn:substring(board.subject,0,20)}"/> ...
						</c:if>
						<c:if test="${fn:length(board.subject)<=20}">
							<c:out value="${board.subject}"/>
						</c:if>
					</a>
					<!-- 첨부파일썸네일 -->
					<c:if test="${board.filesize>0}">
						<span class="float-right">
						<img src="../images/attach.jpg"  style='width:26px'  
						 title="<c:out value="${board.originFilename}"/>">
						</span>
					</c:if>
					
				</td>
				<td>
					<c:out value="${board.name}"/>
				</td>
				<td>
					<c:out value="${board.wdate}"/>
				</td>
				<td>
					<c:out value="${board.readnum}"/>
				</td>
			</tr>
			</c:forEach>
		</c:if>	
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3" class="text-center">
				${pageNavi}
				</td>
				<td colspan="2" class="text-right">
				총 게시글수:<b><c:out value="${paging.totalCount}"/></b> <br>
				<span class="text-danger"><c:out value="${paging.cpage}"/></span> 
				/ <c:out value="${paging.pageCount}"/>
				</td>
			</tr>
			
		</tfoot>
	</table>
</div> 
<c:import url="/foot" />