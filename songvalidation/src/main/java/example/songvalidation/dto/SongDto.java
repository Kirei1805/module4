package example.songvalidation.dto;

public class SongDto {

    private Long id;
    private String name;
    private String artist;
    private String genre;

    public SongDto() {}

    public SongDto(Long id, String name, String artist, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }

    public String getArtist() { 
        return artist; 
    }
    
    public void setArtist(String artist) { 
        this.artist = artist; 
    }

    public String getGenre() { 
        return genre; 
    }
    
    public void setGenre(String genre) { 
        this.genre = genre; 
    }
}
