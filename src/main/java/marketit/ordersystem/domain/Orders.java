package marketit.ordersystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> OrderItems = new ArrayList<>();


    @Builder
    public Orders(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 비즈니스 로직
     */
    public void updateStatus(OrderStatus ordersStatus){
        this.orderStatus = ordersStatus;
    }
}
