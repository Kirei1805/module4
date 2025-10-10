package example.formvalidation.validation;

import example.formvalidation.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;

        if (user.getPhoneNumber() != null &&
                !user.getPhoneNumber().matches("^(\\+84|0)\\d{9,10}$")) {
            errors.rejectValue("phoneNumber", "Invalid.phoneNumber", "Số điện thoại không hợp lệ");
        }
    }
}
