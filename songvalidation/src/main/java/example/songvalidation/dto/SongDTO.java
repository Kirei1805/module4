package example.songvalidation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không vượt quá 800 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9\\s]+$", message = "Tên bài hát không được chứa ký tự đặc biệt")
    private String name;

    @NotBlank(message = "Nghệ sĩ không được để trống")
    @Size(max = 300, message = "Nghệ sĩ không vượt quá 300 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9\\s]+$", message = "Nghệ sĩ không được chứa ký tự đặc biệt")
    private String artist;

    @NotBlank(message = "Thể loại không được để trống")
    @Size(max = 1000, message = "Thể loại không vượt quá 1000 ký tự")
    @Pattern(regexp = "^[\\p{L}0-9\\s,]+$", message = "Thể loại chỉ được chứa chữ, số, khoảng trắng và dấu phẩy")
    private String genre;
}
