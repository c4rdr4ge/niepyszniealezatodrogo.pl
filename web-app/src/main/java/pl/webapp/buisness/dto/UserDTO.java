package pl.webapp.buisness.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String userUsername;
    private String userPassword;
    private String userName;
    private String userSurname;
    private String userPhone;
    private String userEmail;
    private Integer userAddressId;
}
