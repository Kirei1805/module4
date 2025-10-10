package example.formvalidation.validation;

import example.formvalidation.dto.UserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        if (userDto.getFirstName() == null || userDto.getFirstName().trim().isEmpty()) {
            errors.rejectValue("firstName", "firstName.notEmpty", "First name is required");
        } else {
            String firstName = userDto.getFirstName().trim();
            if (firstName.length() < 5 || firstName.length() > 45) {
                errors.rejectValue("firstName", "firstName.length", "First name must be between 5 and 45 characters");
            }
        }

        if (userDto.getLastName() == null || userDto.getLastName().trim().isEmpty()) {
            errors.rejectValue("lastName", "lastName.notEmpty", "Last name is required");
        } else {
            String lastName = userDto.getLastName().trim();
            if (lastName.length() < 5 || lastName.length() > 45) {
                errors.rejectValue("lastName", "lastName.length", "Last name must be between 5 and 45 characters");
            }
        }

        if (userDto.getPhoneNumber() == null || !userDto.getPhoneNumber().matches("^\\d{10}$")) {
            errors.rejectValue("phoneNumber", "phoneNumber.pattern", "Phone number must be 10 digits");
        }

        if (userDto.getAge() < 18) {
            errors.rejectValue("age", "age.min", "Age must be at least 18");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "email.notEmpty", "Email is required");
        } else {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!userDto.getEmail().matches(emailRegex)) {
                errors.rejectValue("email", "email.pattern", "Email should be valid");
            }
        }
    }
}


