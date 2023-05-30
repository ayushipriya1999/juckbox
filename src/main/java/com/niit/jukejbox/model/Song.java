package com.niit.jukejbox.model;

public class Song {
    private int songId;
    private String songName;
    private String albumName;
    private String genre;
    private String artistName;
    private String duration;

    public Song(int songId, String songName, String albumName, String genre, String artistName, String duration) {
        this.songId = songId;
        this.songName = songName;
        this.albumName = albumName;
        this.genre = genre;
        this.artistName = artistName;
        this.duration = duration;
    }

    public Song(String songName, String albumName, String genre, String artistName, String duration) {
        this.songName = songName;
        this.albumName = albumName;
        this.genre = genre;
        this.artistName = artistName;
        this.duration = duration;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s",songId,songName,albumName,genre,artistName,duration)+"\n";
    }
}
