<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판내용</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"  rel="stylesheet"/>
</head>
<body>
<div class="container"> 
<!--  	글번호 : 	<input type="text" name="brd_no" value="${obj.brd_no }" placeholder="글번호" readonly /> <br/>
	제목 : <input type="text" name="brd_title" value="${obj.brd_title }" placeholder="글제목" readonly /> <br/>
																	
	내용 : <textarea id="content"  name="brd_content"    placeholder='${fn:replace(obj.brd_content, newLineChar, "<br/>")}' readonly></textarea> <br/>
	
	조회수 	${obj.brd_hit}<br/>
	작성자 : <input type="text" name= "brd_id" value="${obj.brd_id }" readonly/><br/>
	  		 <input type= "file" name= "imgs" /> <br/>
	생성날짜	${obj.brd_date}<br/>
	-->
	<div class="card border-primary rounded-0">
                            <div class="card-header p-0">
                                <div class="bg-info text-white text-center py-2">
                                    <h3><i class="fa fa-envelope"></i> Board</h3>
                                    <p class="m-0">게시글 상세정보</p>
                                </div>
                            </div>
                            <div class="card-body p-3">

                                <!--Body-->
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">글 번호<i class="fa fa-user text-info"></i></div>
                                        </div>
                                        <input type="text" class="form-control" name="brd_no" value="${obj.brd_no }" placeholder="글번호" style= "background:white;"readonly /> <br/>
                                        
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">글 제목<i class="fa fa-envelope text-info"></i></div>
                                        </div>
                                        
                                        <input type="text" class="form-control" name="brd_title" value="${obj.brd_title }" placeholder="글제목"  style= "background:white;" readonly /> <br/>
                                     
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">글 내용<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        
                                        <textarea  class="form-control" id="content"  name="brd_content"  style="background:white;"  placeholder='${fn:replace(obj.brd_content, newLineChar, "<br/>")}' readonly></textarea>
                                     
                                    </div>
                                </div>
								
								<div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">작성자<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        
                                       <input type="text" class="form-control" name= "brd_id"  style="background:white;" value="${obj.brd_id }" readonly/>
                                     
                                    </div>
                                </div>
								
								<div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">조회수<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        
                                       <input type="text" class="form-control" name= "brd_id"  style="background:white;" value="${obj.brd_no }" readonly/>
                                     
                                      
                                    </div>
                                </div>
								
								<div class="form-group">
                                    <div class="input-group mb-2">
                                        <div class="input-group-prepend">
                                            <div class="input-group-text">등록일<i class="fa fa-comment text-info"></i></div>
                                        </div>
                                        
                                        <input type="text" class="form-control" name= "brd_id"  style="background:white;" value="${obj.brd_date }" readonly/>
                                     
                                 
                                    </div>
                                </div>
								
                            
                            </div>

                        </div>
                        <hr/>
	<a href="${pageContext.request.contextPath}/board/list"		class="btn btn-success">목록</a>
	<a href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no }"		class="btn btn-success">수정</a>
	<a href="${pageContext.request.contextPath}/board/delete?no=${obj.brd_no }"		class="btn btn-success">삭제</a>
  
<!--	<img src="${pageContext.request.contextPath}/board/getimg?no=${obj.brd_no}" width="100px" height="100px" /><br />
	<hr/>
	<a href="${pageContext.request.contextPath}/board/list"		class="btn btn-success">목록</a>
	<a href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no }"		class="btn btn-success">수정</a>
	<a href="${pageContext.request.contextPath}/board/delete?no=${obj.brd_no }"		class="btn btn-success">삭제</a>
  	<c:if test= "${prev!=0 }">
			
			<a href="${pageContext.request.contextPath}/board/content?no=${prev}"		class="btn btn-success">이전</a>
			</c:if>
			<c:if test= "${next!=0 }">
			<a href="${pageContext.request.contextPath}/board/content?no=${next}"		class="btn btn-success">다음</a>
			</c:if>-->
	</div>
</body>
</html>