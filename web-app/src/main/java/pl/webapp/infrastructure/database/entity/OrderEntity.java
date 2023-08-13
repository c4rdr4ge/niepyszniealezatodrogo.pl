package pl.webapp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {
        "orderNumber",
        "addressId",
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

    @Column(name = "address_id")
    private Integer addressId;

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

    // TODO: finish relations
}
