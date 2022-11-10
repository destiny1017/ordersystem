package marketit.ordersystem.service;

import marketit.ordersystem.domain.OrderStatus;
import marketit.ordersystem.domain.Orders;
import marketit.ordersystem.domain.repository.OrdersRepository;
import marketit.ordersystem.exception.NotFoundEntityException;
import marketit.ordersystem.web.dto.OrderDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersRepository ordersRepository;

    @AfterEach
    void clearData() {
        ordersRepository.deleteAll();
    }

    @Test
    @DisplayName("주문 생성 테스트")
    void createOrderTest() {
        Orders order = createOrder();
        assertThat(order).isNotNull();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    @DisplayName("주문 접수처리 테스트")
    void acceptOrder() {
        Orders order = createOrder();
        orderService.acceptOrder(order.getId());

        Orders foundOrder = orderService.findOrderEntity(order.getId());

        assertThat(foundOrder.getOrderStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }

    @Test
    @DisplayName("주문 완료처리 테스트")
    void completeOrder() {
        Orders order = createOrder();
        orderService.completeOrder(order.getId());

        Orders foundOrder = orderService.findOrderEntity(order.getId());

        assertThat(foundOrder.getOrderStatus()).isEqualTo(OrderStatus.COMPLETE);
    }

    @Test
    @DisplayName("전체 주문 조회 테스트")
    void findAllOrders() {
        for(int i = 0; i < 3; i++) {
            createOrder();
        }
        List<OrderDto> allOrders = orderService.findAllOrders();

        assertThat(allOrders).hasSize(3);
        assertThat(allOrders.get(0)).isNotNull();
        assertThat(allOrders.get(0).getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    @DisplayName("단일 주문 조회 테스트")
    void findSingleOrder() {
        Orders order = createOrder();

        OrderDto foundOrder = orderService.findSingleOrder(order.getId());

        assertThat(foundOrder.getOrderId()).isNotNull();
        assertThat(foundOrder.getOrderStatus()).isNotNull();
        assertThat(foundOrder.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    @DisplayName("주문 엔티티 조회 테스트")
    void findOrderEntity() {
        assertThatThrownBy(() -> orderService.findOrderEntity(0L))
                .isInstanceOf(NotFoundEntityException.class);
    }

    Orders createOrder() {
        Orders order = Orders.builder().orderStatus(OrderStatus.CREATED).build();
        return ordersRepository.save(order);
    }
}