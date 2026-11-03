<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="header.jsp" />
<h2>Checkout</h2>
<c:if test="${not empty confirmationName}">
    <p><strong>Thank you, ${confirmationName}! Your order is confirmed.</strong></p>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/checkout">
    <p>Name: <input name="name" required></p>
    <p>Email: <input type="email" name="email" required></p>
    <p>Address: <textarea name="address" required></textarea></p>
    <button type="submit">Place order</button>
</form>
<jsp:include page="footer.jsp" />