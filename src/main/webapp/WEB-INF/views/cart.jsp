<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />
<h2>Your cart</h2>
<c:choose>
    <c:when test="${not empty sessionScope.cart}">
        <ul>
            <c:forEach var="item" items="${sessionScope.cart}">
                <li>${item.name} - <fmt:formatNumber value="${item.price}" type="currency"/></li>
            </c:forEach>
        </ul>
        <a href="${pageContext.request.contextPath}/checkout">Proceed to checkout</a>
    </c:when>
    <c:otherwise><p>Your cart is empty.</p></c:otherwise>
</c:choose>
<jsp:include page="footer.jsp" />