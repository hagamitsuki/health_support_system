<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
            <h2>そんなあなたの今日の過ごし方は...？</h2>
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
                            <th>この気分の作成日</th>
                            <td><fmt:formatDate value='${mood.mood_date}' pattern='yyyy-MM-dd' /></td>
                        </tr>
                    </tbody>
                </table>
                <br /><br />
                <h3>ヾ(＠⌒ー⌒＠)ノ　今日も一日、健康に過ごせると良いね　ヾ(＠⌒ー⌒＠)ノ</h3>
                <br /><br />

    </c:param>
</c:import>