<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>気分 一覧</h2>
        <table id="mood_list">
            <tbody>
                <tr>
                    <th class="mood_title">気分</th>
                    <th class="mood_content">対処法</th>
                    <th class="mood_date">作成日</th>
                    <th class="mood_action">操作</th>
                </tr>
                <c:forEach var="mood" items="${moods}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="mood_title"><c:out value="${mood.title}" /></td>
                        <td class="mood_content"><c:out value="${mood.content}" /></td>
                        <td class="mood_date"><fmt:formatDate value='${mood.mood_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="mood_action"><a href="<c:url value='/moods/show?id=${mood.id}' />">編集する</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${moods_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((moods_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/moods/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/moods/new' />">新しい気分の登録</a></p>

    </c:param>
</c:import>