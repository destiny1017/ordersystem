package marketit.ordersystem.domain;

public enum OrderStatus {
    CREATED("주문생성"),
    ACCEPTED("주문접수"),
    COMPLETE("주문완료");

    String info;

    OrderStatus(String info) {
        this.info = info;
    }
}
