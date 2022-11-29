<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/top"/>

<script>
	const openPop=function(img){
		//alert(img);
		let url='resources/product_images/'+img;
		let obj=new Image();
		obj.src=url;
		let w=obj.width+10;
		let h=obj.height+10;
		
		window.open(url, 'imgView', 'width='+w+'px, height='+h+'px, left=100px, top=100px');
	}
	
	const goCart=function(){
		frm.action="user/cartAdd";
		//frm.method='get';
		frm.submit();
	}
	
	const goOrder=function(){
		frm.action="user/order";
		frm.submit();
	}
	
	const goWish=function(){
		frm.action="user/wishAdd";
		frm.submit();
	}
	
</script>
<div class="container" style="text-align:center">
		<%-- Controller - Service - ServiceImpl - Mapper까지 하고 여기까지 들어오는지 확인 ${prod} --%>
		<div class="row">
         <div class="col-md-12">
            <table class="table">
               <thead>
                  <tr>
                     <th colspan="2"><h3 style="text-align:center">상품 정보</h3></th>
                  </tr>
               </thead>

               <tbody>
                  <tr>
                     <td align="center" width="50%">
                     <a href="#" onclick="openPop('${prod.pimage1}')"> 
                     <img
                           src="resources/product_images/${prod.pimage1}" class="img-fluid" style="width: 70%;"> <!-- </a> -->
                     </a></td>

                     <td align="left" width="50%" style="padding-left: 40px">
                        <h4>
                           <span class="label label-danger">${prod.pspec} </span>
                        </h4> 
                        	상품번호: ${prod.pnum} <br> 
                        	상품이름: ${prod.pname} <br> 
                        	정가:<del>
                            <fmt:formatNumber value="${prod.price}" pattern="###,###" />
                        	</del>원<br> 
                        	판매가:<span style="color: red; font-weight: bold">
                           <fmt:formatNumber value="${prod.saleprice}" pattern="###,###" />
                     			</span>원<br> 
                     	    할인율:<span style="color: red">${prod.percent} %</span><br>

                        POINT:<b style="color: green">[${prod.point}]</b>POINT<br>

                        <!-- form시작---------- -->
                        <form name="frm" id="frm" method="POST">
                           <!-- 상품번호를 hidden으로 넘기자------ -->
                           <input type="hidden" name="pnum" value="${prod.pnum}">
                           <input type="hidden" name="opnum" value="${prod.pnum}">
                           <!-- -------------------------------- -->
                           <label for="oqty">상품갯수</label> 
                           <input type="number" name="oqty"
                              id="oqty" min="1" max="50" size="2" value="1">

                        </form> <!-- form end------------ -->

                        <button type="button" onclick="goCart()" class="btn btn-primary">장바구니</button>
                        <button type="button" onclick="goOrder()" class="btn btn-warning">주문하기</button>
                        <button type="button" onclick="goWish()" class="btn btn-danger">위시리시트</button>
                     </td>

                  </tr>
                  <tr style="border: 0">
                     <td align="center">
                     	<img src="resources/product_images/${prod.pimage2}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                     <td align="center">
                     	<img src="resources/product_images/${prod.pimage3}"
                        class="img img-thumbnail" style="width: 70%;"></td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <p>상품설명</p> 
                        <pre>${prod.pcontents}</pre>
                     </td>
                  </tr>
               </tbody>

            </table>
         </div>
      </div>
	</div>

<c:import url="/foot"/>
