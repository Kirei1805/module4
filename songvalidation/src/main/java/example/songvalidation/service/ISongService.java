package example.songvalidation.service;

import example.songvalidation.model.Song;
import example.songvalidation.dto.SongDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ISongService implements SongService {
    private final List<Song> songs = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @Override
    public List<SongDto> findAll() {
        return convertToDtoList(songs);
    }

    @Override
    public SongDto findById(Long id) {
        Song song = songs.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        return song != null ? convertToDto(song) : null;
    }

    @Override
    public void save(SongDto songDto) {
        Song song = convertToEntity(songDto);
        song.setId(counter.getAndIncrement());
        songs.add(song);
    }

    @Override
    public void update(Long id, SongDto songDto) {
        Song existing = songs.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if (existing != null) {
            existing.setName(songDto.getName());
            existing.setArtist(songDto.getArtist());
            existing.setGenre(songDto.getGenre());
        }
    }

    @Override
    public SongDto convertToDto(Song song) {
        return new SongDto(song.getId(), song.getName(), song.getArtist(), song.getGenre());
    }

    @Override
    public Song convertToEntity(SongDto songDto) {
        return new Song(songDto.getId(), songDto.getName(), songDto.getArtist(), songDto.getGenre());
    }

    @Override
    public List<SongDto> convertToDtoList(List<Song> songs) {
        return songs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
