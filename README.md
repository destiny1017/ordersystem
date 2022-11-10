## 주문 관리 시스템 사전과제


### 구현 기능

- 주문 접수처리: /api/orders/{id}/accept
- 주문 완료처리: /api/orders/{id}/complete
- 단일 주문조회: /api/orders/{id}
- 주문 목록조회: /api/orders

### 패키지 구조
```xml
├─ main
│  ├─ generated
│  ├─ java
│  │  └─ marketit
│  │      └─ ordersystem
│  │          │  OrdersystemApplication.java
│  │          │
│  │          ├─ domain
│  │          │  │  BaseTimeEntity.java
│  │          │  │  Item.java
│  │          │  │  OrderItem.java
│  │          │  │  Orders.java
│  │          │  │  OrderStatus.java
│  │          │  │
│  │          │  └─ repository
│  │          │          ItemRepository.java
│  │          │          OrderItemRepository.java
│  │          │          OrdersRepository.java
│  │          │
│  │          ├─ exception
│  │          │      NotFoundEntityException.java
│  │          │
│  │          ├─ service
│  │          │      OrderService.java
│  │          │
│  │          └─ web
│  │              │  OrderApiController.java
│  │              │
│  │              └─ dto
│  │                      OrderDto.java
│  │
│  └─ resources
│      │  application.yml
│      │
│      ├─static
│      └─templates
└─ test
    └─ java
        └─ marketit
            └─ ordersystem
                │  OrdersystemApplicationTests.java
                │
                ├─ service
                │      OrderServiceTest.java
                │
                └─ web
                        OrderApiControllerTest.java
```


### 사용 기술

Gradle, SpringBoot, JPA, DataJPA, H2, Junit5, IntelliJ, Postman


### 구현 내용

- Orders, OrderItem, Item 세가지 엔티티를 구현하고 연관관계 매핑을 시켰으나, 구현 요구사항상 OrderItem, Item의 비즈니스 로직은 따로 구현하지 않았습니다.
- BaseTimeEntity를 통해 모든 엔티티에 생성&수정 시점을 업데이트 하도록 하였습니다.
- 유닛 테스트는 서비스 로직 테스트와 API 요청 테스트로 나누어 수행하였습니다.
