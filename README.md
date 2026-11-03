# Lab 3: EJB Service Layer

Jakarta EE web application (catalog, cart, checkout) refactored so that all business logic lives in an EJB/CDI service layer. Servlets only translate HTTP requests into service calls.

Companion to Chapter 3: Business Logic, EJB & CDI Basics.

## Technologies

- Java 21
- Jakarta EE (Servlet, JSP/JSTL, EJB, CDI, JTA)
- Maven (WAR packaging)
- WildFly 42 (Beta7)
- IntelliJ IDEA

## Architecture

```
Servlet (HTTP only)  ->  Service layer (EJB / CDI beans)  ->  Model
```

| Service | Type | Role |
|---|---|---|
| `ProductService` | `@Stateless` | Lists, filters and fetches products |
| `CartService` | `@Stateful` + `@SessionScoped` | One cart per user session |
| `OrderService` | `@Stateless` + `@Transactional` | Creates an order, rejects empty carts |
| `NotificationService` | `@Singleton` | Logs confirmations, keeps the last 5 |
| `PromotionService` | `@Stateless` | Applies a flat 10% discount to the cart total |
| `EmptyCartException` | `@ApplicationException(rollback = true)` | Thrown when checking out with an empty cart |

Servlets (`com.horizon.lab2.web`) use `@Inject` to receive services: `ProductListServlet`, `ProductDetailServlet`, `CartServlet`, `CheckoutServlet`. The Lab 2 filters (`AuthenticationFilter`, `RequestLoggingFilter`) are unchanged.

## Project structure

```
src/main/java/com/horizon/lab2/
  model/     ProductDTO, ProductCatalog
  service/   ProductService, CartService, OrderService,
             NotificationService, PromotionService, EmptyCartException
  web/       servlets and filters
src/main/webapp/WEB-INF/
  beans.xml
  views/     products.jsp, product-detail.jsp, cart.jsp, checkout.jsp, header.jsp, footer.jsp
```

## Run

1. Open the project in IntelliJ IDEA.
2. Build, then Rebuild Project.
3. Run the WildFly configuration (artifact `lab-2:war exploded`).
4. Open `http://localhost:8080/lab-2-1.0-SNAPSHOT/catalog/products`.

## Chapter 3 activities

1. **PromotionService**: a separate service from `CartService` (single responsibility: the cart knows which items it holds, pricing rules live elsewhere).
2. **Notification history**: `NotificationService` is a singleton, so the list of the last 5 confirmations is shared across all users.
3. **Empty cart guard**: `OrderService.createOrder` throws `EmptyCartException` before any notification is sent, and the transaction rolls back.
4. **Injection chain trace**: done with the IntelliJ debugger (breakpoint in `OrderService.createOrder`).
5. **Reflection**: in Lab 2 `CheckoutServlet` mixed HTTP handling and business logic; in Lab 3 it only maps form fields to `orderService.createOrder(...)`.

## Author

Your Name
