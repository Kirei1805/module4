package example.songvalidation.service;

import example.songvalidation.model.Song;
import java.util.List;

public interface SongService {
    Song save(Song song);
    Song findById(Long id);
    List<Song> findAll();
}
