package com.niit.jukejbox.service;

import com.niit.jukejbox.model.Song;
import com.niit.jukejbox.operation.SongDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class SongService {
    //SongDao songDao;
    public  Song getASong(ArrayList<Song>songArrayList,String songName) throws JukeBoxException {//chose one song
        Song selectSong=null;//this data is check null ya not
        if(songArrayList.isEmpty() || songName==null)//first data hai ya nhi hai checking isEmpty
            throw new JukeBoxException("data is not there enter proper values");//used to expilicity
        else
            for(Song song: songArrayList){
                if(song.getSongName().trim().equalsIgnoreCase(songName)){//trim-remove space
                    selectSong=song;//getSongName-previous song,songName-user give the song//select song main saved
                    break;// i don't want to go
                }
            }
            return selectSong;
    }

    private boolean checkSong(String songName, ArrayList<Song> songArrayList) {
        boolean result = false;
        for (Song song1 : songArrayList) {//check song wheather song is there ya not
            if (song1.getSongName().equals(songName)) {
                result = true;
            }
        }
        return result;
    }

    //they will check if the songlist arraylist has the songs
//if song is present it will return true else it will return false
//this method i don't want to came in my main methos thats why i give private


    public boolean addSong(Song song2, ArrayList<Song> songArrayList) throws SQLException {
        boolean result = false;//i have inserted the song,song wheather song is there ya not
        if (checkSong(song2.getSongName(), songArrayList) == false) {
            SongDao.insertSong(song2);
            result = true;

        }//
        return result;
    }
    //here i calling a methods check song, and it will return true or false value
//(song2.getSongName(), songArrayList)== false) it means if the song is not present it will return false,
// if it is false,we can go inside and insertSong,it present
// means it will return true,already present how can i store the song


    public void displaySong(ArrayList<Song> songArrayList) {
        System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s","songId","songName","albumName","genre","artistName","duration");

        for (Song song3 : songArrayList) {//give step bt step
            System.out.println();
            System.out.println(song3);
        }
    }

    public ArrayList<Song>getSongAlbum(String albumName,ArrayList<Song>songArrayList){
        ArrayList<Song>filterByAlbumList=null;//we don't know how many song in album that's why created Arraylist
        if(songArrayList.isEmpty()==false && albumName!=null){//whatever song inside album,we want to see
           filterByAlbumList=new ArrayList();
           for (Song song:songArrayList){
               if(song.getAlbumName().equalsIgnoreCase(albumName))//if yes than add them
                   filterByAlbumList.add(song);

           }
        }

        return filterByAlbumList;
    }

   public ArrayList<Song>getSongArtist(String artistName,ArrayList<Song>songArrayList){
        ArrayList<Song>filterByArtisList=null;
        if(songArrayList.isEmpty()==false && artistName!=null){
            filterByArtisList=new ArrayList();
            for (Song song:songArrayList){
                if(song.getArtistName().equalsIgnoreCase(artistName))
                filterByArtisList.add(song);
            }
        }
        return filterByArtisList;
    }

  public ArrayList<Song>getSongByGener(String gener,ArrayList<Song>songArrayList){

        ArrayList<Song>filterByGener=null;
        if(songArrayList.isEmpty()==false && gener!=null){
            filterByGener=new ArrayList();}
            for(Song song:songArrayList){
                if(song.getGenre().equalsIgnoreCase(gener))
                filterByGener.add(song);
            }
        //System.out.println(filterByGener);

        return filterByGener;
    }


    public ArrayList<Song>getAllSong() throws SQLException {
       // songDao=new SongDao();
        return SongDao.selectAllSong();

    }
}
