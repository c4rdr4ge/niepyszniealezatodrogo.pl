package pl.webapp.buisness.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+\\s\\d\\d\\d\\s\\d\\d\\d\\s\\d\\d\\d$")
    private String userPhone;
    @Email
    private String userEmail;
    private Integer userAddressId;
}
