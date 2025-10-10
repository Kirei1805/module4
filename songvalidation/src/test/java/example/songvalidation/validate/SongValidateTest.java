package example.songvalidation.validate;

import example.songvalidation.dto.SongDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

class SongValidateTest {

    private SongValidate songValidate;

    @BeforeEach
    void setUp() {
        songValidate = new SongValidate();
    }

    @Test
    void testSupports() {
        assertTrue(songValidate.supports(SongDto.class));
        assertFalse(songValidate.supports(String.class));
    }

    @Test
    void testValidSongDto() {
        SongDto songDto = new SongDto();
        songDto.setName("Hello World");
        songDto.setArtist("Artist Name");
        songDto.setGenre("Pop, Rock");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertFalse(result.hasErrors(), "Valid song should not have errors");
    }

    @Test
    void testEmptyName() {
        SongDto songDto = new SongDto();
        songDto.setName("");
        songDto.setArtist("Artist Name");
        songDto.setGenre("Pop");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertTrue(result.hasErrors(), "Empty name should have errors");
        assertTrue(result.hasFieldErrors("name"), "Should have name field error");
        assertEquals("Tên bài hát không được để trống", result.getFieldError("name").getDefaultMessage());
    }

    @Test
    void testNameWithSpecialCharacters() {
        SongDto songDto = new SongDto();
        songDto.setName("Song@Name");
        songDto.setArtist("Artist Name");
        songDto.setGenre("Pop");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertTrue(result.hasErrors(), "Name with special characters should have errors");
        assertTrue(result.hasFieldErrors("name"), "Should have name field error");
    }

    @Test
    void testNameTooLong() {
        SongDto songDto = new SongDto();
        songDto.setName("a".repeat(801)); // 801 characters
        songDto.setArtist("Artist Name");
        songDto.setGenre("Pop");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertTrue(result.hasErrors(), "Name too long should have errors");
        assertTrue(result.hasFieldErrors("name"), "Should have name field error");
    }

    @Test
    void testEmptyArtist() {
        SongDto songDto = new SongDto();
        songDto.setName("Song Name");
        songDto.setArtist("");
        songDto.setGenre("Pop");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertTrue(result.hasErrors(), "Empty artist should have errors");
        assertTrue(result.hasFieldErrors("artist"), "Should have artist field error");
        assertEquals("Nghệ sĩ không được để trống", result.getFieldError("artist").getDefaultMessage());
    }

    @Test
    void testEmptyGenre() {
        SongDto songDto = new SongDto();
        songDto.setName("Song Name");
        songDto.setArtist("Artist Name");
        songDto.setGenre("");

        BindingResult result = new BeanPropertyBindingResult(songDto, "songDto");
        songValidate.validate(songDto, result);
        
        assertTrue(result.hasErrors(), "Empty genre should have errors");
        assertTrue(result.hasFieldErrors("genre"), "Should have genre field error");
        assertEquals("Thể loại nhạc không được để trống", result.getFieldError("genre").getDefaultMessage());
    }
}
