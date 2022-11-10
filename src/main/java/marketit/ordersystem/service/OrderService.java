package marketit.ordersystem.service;

import lombok.RequiredArgsConstructor;
import marketit.ordersystem.domain.OrderStatus;
import marketit.ordersystem.domain.Orders;
import marketit.ordersystem.domain.repository.OrdersRepository;
import marketit.ordersystem.exception.NotFoundEntityException;
import marketit.ordersystem.web.dto.OrderDto;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;

    @Transactional
    public void acceptOrder(Long id) {
        Orders order = findOrderEntity(id);
        order.updateStatus(OrderStatus.ACCEPTED);
    }

    @Transactional
    public void completeOrder(Long id) {
        Orders order = findOrderEntity(id);
        order.updateStatus(OrderStatus.COMPLETE);
    }

    public List<OrderDto> findAllOrders() {
        return ordersRepository.findAll()
                .stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public OrderDto findSingleOrder(Long id) {
        return new OrderDto(findOrderEntity(id));
    }

    public Orders findOrderEntity(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("NotFound Orders, id = " + id));
    }

//    @Transactional
//    public Orders createOrder() {
//        Orders order = Orders.builder().orderStatus(OrderStatus.CREATED).build();
//        return ordersRepository.save(order);
//    }
}
