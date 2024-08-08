package com.ecommerce.haitc.order;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import com.ecommerce.haitc.order.dto.OrderRequestDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }
	 @Test
    public void shouldCreateOrder() throws Exception {
        OrderRequestDto orderRequest = getOrderRequest();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(orderRequest)
                .when()
                .post("/api/order")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("orderNumber", Matchers.equalTo(orderRequest.orderNumber()))
                .body("skuCode", Matchers.equalTo(orderRequest.skuCode()))
                .body("price", Matchers.is(orderRequest.price().intValueExact()))
                .body("quantity", Matchers.equalTo(orderRequest.quantity()));
    }

    @Test
    public void shouldGetAllOrder() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/order")
                .then()
                .log().all()
                .statusCode(200)
                .body("$", Matchers.not(Matchers.empty()));
    }

    private OrderRequestDto getOrderRequest() {
        return new OrderRequestDto("202400001", "123456", BigDecimal.valueOf(12000), 10);
    }

}
