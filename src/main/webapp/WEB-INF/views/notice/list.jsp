<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp" %>

<html>
<head>
    <title>공지사항</title>
</head>
<body>
    <h1>공지사항</h1>
    <c:forEach var="list" items="${list}">
        <div>
            <row>
                <b>작성일</b> : <fmt:formatDate pattern="yyyy.MM.dd" value="${list.notice_mod_date}"/>
            </row>
            <low class = "notice_title">
                <b class="title">제목</b> : ${list.notice_content}
            </low>
            <button class="testbutton">click</button>
        </div>
    </c:forEach>

    <!-- modal -->
    <div class="modal" id="listModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Modal body text goes here.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function $(document) {
            console.log("funtion?")
            (".notice_title").on("click",function (e){
                $("#listModal").modal("show");
                console.log("Is in?")
            })

            (".testbutton").on("click",function (e) {
                e.preventDefault();
                console.log("button clicked")
            })
        }



    </script>
</body>



</html>

<%@ include file="../includes/footer.jsp" %>