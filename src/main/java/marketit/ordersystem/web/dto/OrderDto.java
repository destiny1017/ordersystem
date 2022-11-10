package marketit.ordersystem.web.dto;

import lombok.Data;
import marketit.ordersystem.domain.OrderStatus;
import marketit.ordersystem.domain.Orders;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime lastUpdatedDate;

    public OrderDto(Orders orders) {
        this.orderId = orders.getId();
        this.orderStatus = orders.getOrderStatus();
        this.lastUpdatedDate = orders.getModifiedDate();
    }
}
