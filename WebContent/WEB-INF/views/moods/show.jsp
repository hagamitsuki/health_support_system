<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${mood != null}">
                <h2>気分　詳細ページ</h2>

                <table>
                    <tbody>

                        <tr>
                            <th>気分</th>
                            <td>
                                <pre><c:out value="${mood.title}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>対処法など</th>
                            <td>
                                <pre><c:out value="${mood.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>作成日</th>
                            <td><fmt:formatDate value='${mood.mood_date}' pattern='yyyy-MM-dd' /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${mood.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${mood.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.id == mood.user.id}">
                    <p><a href="<c:url value='/moods/edit?id=${mood.id}' />">この気分を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/moods/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>