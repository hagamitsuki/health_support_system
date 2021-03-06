<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>健康サポートシステム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">

        <%-- フルカレンダー 始まり--%>
        <link rel="stylesheet" href="<c:url value='/css/fullcalendar.min.css' />" type="text/css">
        <script src="<c:url value='/js/moment.min.js' />" type="text/javascript"></script>
            <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="<c:url value='/js/jquery-ui.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/js/fullcalendar.min.js' />" type="text/javascript"></script>
        <script src="<c:url value='/js/ja.js' />" type="text/javascript"></script>
        <%-- フルカレンダー 終わり--%>

    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1><a href="<c:url value='/' />">健康サポートシステム</a></h1>&nbsp;&nbsp;&nbsp;
                    <c:if test="${sessionScope.login_user != null}">
                        <c:if test="${sessionScope.login_user.admin_flag == 1}">
                            <a href="<c:url value='/users/index' />">ユーザー管理</a>&nbsp;
                        </c:if>
                        <a href="<c:url value='/moods/index' />">気分一覧</a>&nbsp;
                    </c:if>
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <c:out value="${sessionScope.login_user.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Chang-mitsu.
            </div>
        </div>
    </body>
</html>
