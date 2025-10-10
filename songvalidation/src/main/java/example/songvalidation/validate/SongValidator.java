package example.songvalidation.validate;

import example.songvalidation.dto.SongDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SongValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SongDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongDTO song = (SongDTO) target;

        if (song.getGenre() != null) {
            if (song.getGenre().contains(",,")) {
                errors.rejectValue("genre", "genre.invalid", "Thể loại không được chứa hai dấu phẩy liên tiếp");
            }
            String trimmed = song.getGenre().trim();
            if (trimmed.startsWith(",") || trimmed.endsWith(",")) {
                errors.rejectValue("genre", "genre.invalid", "Thể loại không được bắt đầu hoặc kết thúc bằng dấu phẩy");
            }
        }

    }
}
