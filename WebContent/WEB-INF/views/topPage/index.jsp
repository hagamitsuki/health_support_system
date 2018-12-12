<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>健康サポートシステムへようこそ</h2>

            <label for="mood">今の気分を選択してね</label><br />
            <form method="GET" action="<c:url value='/results/index' />">
                <select name="mood.id">
                <%-- "mood.id"という名前、value="${mood.id}"という内容で送信される。送信先のサーブレットで扱えるのはname="mood.id"--%>
                    <c:forEach var="mood" items="${moods}"><%--ログインユーザーのmoodsリストの中から一つずつ取得してmoodに格納--%>
                        <option value="${mood.id}"><c:out value="${mood.title}" /></option>
                    </c:forEach>
                </select>
                <input type="submit" value="入力">
            </form>
                <br /><br />

        <%-- フルカレンダー 始まり--%>
         <div id="calendar">
            <script>
                 $('#calendar').fullCalendar();
            </script>
         </div>
        <%-- フルカレンダー 終わり--%>
        <br /><br />

    </c:param>
</c:import>