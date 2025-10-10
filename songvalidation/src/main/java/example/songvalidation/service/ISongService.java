package example.songvalidation.service;

import example.songvalidation.model.Song;
import example.songvalidation.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISongService implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }
}
