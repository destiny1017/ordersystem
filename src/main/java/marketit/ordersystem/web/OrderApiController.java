package marketit.ordersystem.web;

import lombok.RequiredArgsConstructor;
import marketit.ordersystem.service.OrderService;
import marketit.ordersystem.web.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PutMapping("/api/orders/{id}/accept")
    public void orderAcceptRequest(@PathVariable Long id) {
        orderService.acceptOrder(id);
    }

    @PutMapping("/api/orders/{id}/complete")
    public void orderCompleteRequest(@PathVariable Long id) {
        orderService.completeOrder(id);
    }

    @GetMapping("/api/orders/{id}")
    public OrderDto findSingleOrderRequest(@PathVariable Long id) {
        return orderService.findSingleOrder(id);
    }

    @GetMapping("/api/orders")
    public List<OrderDto> findAllOrderRequest() {
        return orderService.findAllOrders();
    }

    // 테스트용 생성 요청
//    @PostMapping("/api/orders/create")
//    public OrderDto createOrder() {
//        return new OrderDto(orderService.createOrder());
//    }

}
