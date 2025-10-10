package example.songvalidation.validate;

import example.songvalidation.dto.SongDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SongValidate implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return SongDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SongDto songDto = (SongDto) target;

        if (songDto.getName() == null || songDto.getName().trim().isEmpty()) {
            errors.rejectValue("name", "notEmpty", "Tên bài hát không được để trống");
        } else if (songDto.getName().length() > 800) {
            errors.rejectValue("name", "name.length", "Tên bài hát không vượt quá 800 ký tự");
        } else if (!songDto.getName().matches("^[^@;,.=\\-+]+$")) {
            errors.rejectValue("name", "name.pattern", "Tên bài hát không được chứa ký tự đặc biệt @;,.=-+");
        }

        if (songDto.getArtist() == null || songDto.getArtist().trim().isEmpty()) {
            errors.rejectValue("artist", "notEmpty", "Nghệ sĩ không được để trống");
        } else if (songDto.getArtist().length() > 300) {
            errors.rejectValue("artist", "artist.length", "Tên nghệ sĩ không vượt quá 300 ký tự");
        } else if (!songDto.getArtist().matches("^[^@;,.=\\-+]+$")) {
            errors.rejectValue("artist", "artist.pattern", "Tên nghệ sĩ không được chứa ký tự đặc biệt @;,.=-+");
        }

        if (songDto.getGenre() == null || songDto.getGenre().trim().isEmpty()) {
            errors.rejectValue("genre", "notEmpty", "Thể loại nhạc không được để trống");
        } else if (songDto.getGenre().length() > 1000) {
            errors.rejectValue("genre", "genre.length", "Thể loại nhạc không vượt quá 1000 ký tự");
        } else if (!songDto.getGenre().matches("^[a-zA-ZÀ-ỹ0-9,\\s]+$")) {
            errors.rejectValue("genre", "genre.pattern", "Thể loại chỉ cho phép chữ, số và dấu phẩy");
        }
    }
}
