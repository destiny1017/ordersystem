package marketit.ordersystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long price;
    private Long stockQuantity;
    private String contents;

    @Builder
    public Item(String name, Long price, Long stockQuantity, String contents) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.contents = contents;
    }

    /**
     * 비즈니스 로직
     */

}
