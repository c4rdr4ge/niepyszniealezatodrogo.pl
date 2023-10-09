package pl.webapp.buisness.dto.webdtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebDishDTO {

    private String dishName;
    private String dishDescription;
    private Integer dishWeight;
    private MultipartFile dishPhotoFile;
    private BigDecimal dishPrice;
    private String category;
    private String kitchenType;
}
