<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- function taglib 추가---------------------------------------- -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ------------------------------------------------------------ -->
<c:import url="/top"/>
<style>
#boardBody>tr>td:nth-child(2){
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<div class="container mt-3" style="height:600px;overflow:auto;">
	<h1 class="text-center">Spring Board</h1>
<%-- ${boardArr} --%>
	<p class="text-center my-4">
		<a href="write">WRITE</a> | <a href="list">LIST</a>
	</p>
	<!-- 검색폼 시작====================== -->
	
	<!-- ================================= -->
	
	<!-- ================================= -->
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
							<img src="../images/attach.jpg" style='width:18px' title="<c:out value="${board.originFilename}"/>">
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
				<%-- 이렇게하면 직접 스크립트로 게시글 장난칠수 있어서 실무에선 위처럼 결과만 출력시
				<td>${board.num}</td>
				<td>${board.subject}</td>
				<td>${board.name}</td>
				<td>${board.wdate}</td>
				<td>${board.readnum}</td> --%>
			</tr>
			</c:forEach>
		</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3" class="text-center">
				<ul class="pagination justify-content-center">
				<!-- 이전 5개 -->
				<c:if test="${paging.prevBlock>0}">
					<li class='page-item'>
						<a class='page-link' href='list?cpage=<c:out value="${paging.prevBlock}"/>'>Prev</a>
					</li>	
				</c:if>
				<!-- 페이지 번호---- -->
				<c:forEach var="i" begin="${paging.prevBlock+1}" end="${paging.nextBlock-1}">
					<c:if test="${i <=paging.pageCount}">
					<li class='page-item <c:if test="${i eq paging.cpage}">active</c:if>'>
						<a class='page-link' href='list?cpage=<c:out value="${i }"/>'> 
						<c:out value="${i}"/> </a>
					</li>	
					</c:if>
				</c:forEach>
				
				<!-- 이후 5개 -->
				<c:if test="${paging.nextBlock <=paging.pageCount}">
					<li class='page-item'>
					  <a class='page-link' href='list?cpage=<c:out value="${paging.nextBlock}"/>'>Next</a>
					</li>  
				</c:if>
				</ul>
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

<c:import url="/foot"/>
