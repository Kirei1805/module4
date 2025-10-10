package example.formvalidation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Firstname không được để trống")
    @Size(min = 5, max = 45, message = "Firstname phải từ 5 đến 45 ký tự")
    private String firstname;

    @NotBlank(message = "Lastname không được để trống")
    @Size(min = 5, max = 45, message = "Lastname phải từ 5 đến 45 ký tự")
    private String lastname;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 18, message = "Tuổi phải >= 18")
    private Integer age;
}
