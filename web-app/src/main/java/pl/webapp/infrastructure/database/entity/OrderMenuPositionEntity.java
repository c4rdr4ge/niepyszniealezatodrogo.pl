package pl.webapp.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@With
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "orderMenuPositionId")
@Table(name = "order_menu_position")
public class OrderMenuPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_menu_position_id")
    private Integer orderMenuPositionId;

    @Column(name = "menu_position_id")
    private Integer menuPositionId;

    // TODO: finish relations
}
