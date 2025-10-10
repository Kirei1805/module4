package example.songvalidation.service;

import example.songvalidation.model.Song;
import example.songvalidation.dto.SongDto;
import java.util.List;

public interface SongService {
    List<SongDto> findAll();
    SongDto findById(Long id);
    void save(SongDto songDto);
    void update(Long id, SongDto songDto);
    
    // Conversion methods
    SongDto convertToDto(Song song);
    Song convertToEntity(SongDto songDto);
    List<SongDto> convertToDtoList(List<Song> songs);
}
