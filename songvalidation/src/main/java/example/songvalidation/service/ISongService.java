package example.songvalidation.service;

import example.songvalidation.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ISongService implements SongService {
    private final List<Song> songs = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @Override
    public List<Song> findAll() {
        return songs;
    }

    @Override
    public Song findById(Long id) {
        return songs.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Song song) {
        song.setId(counter.getAndIncrement());
        songs.add(song);
    }

    @Override
    public void update(Long id, Song song) {
        Song existing = findById(id);
        if (existing != null) {
            existing.setName(song.getName());
            existing.setArtist(song.getArtist());
            existing.setGenre(song.getGenre());
        }
    }
}
