package org.example.orderservic;

public record Order(
        String orderID,
        String product,
        Integer quantity
) {
}
