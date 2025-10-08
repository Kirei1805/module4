package example.songvalidation.service;

import example.songvalidation.model.Song;
import java.util.List;

public interface SongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    void update(Long id, Song song);
}
