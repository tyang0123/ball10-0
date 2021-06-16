<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="includes/header.jsp" %>




<div class="row">
    <div class="col-sm-12">

        </head>
        <body>
        <button class="btn_show" style="padding:3px">show</button>
        <button class="btn_hide" style="padding:3px">hide</button>
        <div class="btn_word">Hello world!!!</div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function (){
        $('.btn_show').click(function(){
            $('.btn_word').show();
        });
        $('.btn_hide').click(function(){
            $('.btn_word').hide();
        });

    });
</script>



<%@ include file="includes/footer.jsp" %>