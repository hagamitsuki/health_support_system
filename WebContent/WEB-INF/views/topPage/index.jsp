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

            <label for="mood">今の気分を選択してください</label><br />
            <form method="GET" action="<c:url value='/results/index' />">
            <select name="mood">
                <option value="what">-- 今の気分は？ --</option>
                <c:forEach var="mood" items="${moods}">
                <option value="mood"><c:out value="${mood}" /></option>
                </c:forEach>
            </select>
            <input type="submit" name="submit" value="入力">
            </form>
                <br /><br />
    </c:param>
</c:import>