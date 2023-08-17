package pl.webapp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {
        "orderNumber",
        "orderAddressId",
        "orderDateTime",
        "orderDelivered",
        "orderDeliveredDateTime",
        "orderAddInfo",
        "orderRestaurantId"
})
@EqualsAndHashCode(of = "orderNumber")
@Table(name = "order_table")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "order_address_id")
    private Integer orderAddressId;

    @Column(name = "order_date_time")
    private OffsetDateTime orderDateTime;

    @Column(name = "order_delivered")
    private Boolean orderDelivered;

    @Column(name = "order_delivered_date_time")
    private OffsetDateTime orderDeliveredDateTime;

    @Column(name = "order_add_info")
    private String orderAddInfo;

    @Column(name = "order_restaurant_id")
    private Integer orderRestaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderHistoryEntity> orderHistoryEntities;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderMenuPositionEntity> orderMenuPositionEntities;
}
