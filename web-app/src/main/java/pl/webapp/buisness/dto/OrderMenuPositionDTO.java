package pl.webapp.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMenuPositionDTO {

    private Integer orderMenuPositionId;
    private Integer menuPositionId;
    private OrderDTO order;
}
