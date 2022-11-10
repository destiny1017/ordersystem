package marketit.ordersystem.web;

import marketit.ordersystem.domain.OrderStatus;
import marketit.ordersystem.domain.Orders;
import marketit.ordersystem.domain.repository.OrdersRepository;
import marketit.ordersystem.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrdersRepository ordersRepository;

    @MockBean
    private OrderService mockOrderService;

    @Test
    @DisplayName("주문 접수처리 API요청 테스트")
    void orderAcceptRequest() throws Exception {
        Long orderId = createOrder().getId();
        String uri = String.format("/api/orders/%s/accept", orderId);

        mvc.perform(put(uri))
                .andExpect(status().isOk());

        verify(mockOrderService, atLeastOnce()).acceptOrder(any());
    }

    @Test
    @DisplayName("주문 완료처리 API요청 테스트")
    void orderCompleteRequest() throws Exception {
        Long orderId = createOrder().getId();
        String uri = String.format("/api/orders/%s/complete", orderId);

        mvc.perform(put(uri))
                .andExpect(status().isOk());

        verify(mockOrderService, atLeastOnce()).completeOrder(any());
    }

    @Test
    @DisplayName("단일 주문 조회 API요청 테스트")
    void findSingleOrderRequest() throws Exception {
        Long orderId = createOrder().getId();
        String uri = String.format("/api/orders/%s", orderId);

        mvc.perform(get(uri))
                .andExpect(status().isOk());

        verify(mockOrderService, atLeastOnce()).findSingleOrder(any());
    }

    @Test
    @DisplayName("전체 주문 조회 API요청 테스트")
    void findAllOrderRequest() throws Exception {
        String uri = "/api/orders";

        mvc.perform(get(uri))
                .andExpect(status().isOk());

        verify(mockOrderService, only()).findAllOrders();
    }

    Orders createOrder() {
        Orders order = Orders.builder().orderStatus(OrderStatus.CREATED).build();
        return ordersRepository.save(order);
    }
}