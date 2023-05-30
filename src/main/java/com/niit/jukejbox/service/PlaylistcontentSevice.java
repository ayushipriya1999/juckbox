package com.niit.jukejbox.service;

import com.niit.jukejbox.model.Song;
import com.niit.jukejbox.operation.PlaylistContentDao;
import com.niit.jukejbox.operation.SongDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlaylistcontentSevice {
    SongDao songDao=new SongDao();
    PlaylistContentDao playlistContentDao=new PlaylistContentDao();


    public boolean addSongToPlaylist(ArrayList<Song> songlist,String songName, Hashtable<String, Integer> playlist,String playlistName) throws SQLException, JukeBoxException {
        boolean result = false;

        if (songlist.isEmpty() || playlist.isEmpty() || songName == null || playlistName == null)
            throw new JukeBoxException("Please provide all values");//first we check song is empty ya not,if it is empty ya null then tell please provide value

        else {
            if (!playlist.containsKey(playlistName))//if song is not there in playlist then write playlist not found if song is there come to else part
                throw new JukeBoxException("playlist not found");
            else {
                int playlistid = playlist.get(playlistName);//here we get song from playfrom playlist
                int songid = 0;//storing id
                for (Song song : songlist) {
                    if (song.getSongName().trim().equalsIgnoreCase(songName)) {//
                        songid = song.getSongId();
                        result = true;
                        break;
                    }
                }
                if(playlistid==0)
                    throw new JukeBoxException("Playlist Not present");
                else if (songid == 0)
                    throw new JukeBoxException("Song Not present");
                else
                playlistContentDao.addSongsToPlaylist(songid,playlistid);
            }
        }
        return result;
    }



    public ArrayList<Song> addSongByAlbum(ArrayList<Song>songList, String ablumName, Hashtable<String,Integer>playlist, String playlistName) throws JukeBoxException, SQLException {

        int playlistid=0;
        int songid = 0;
        boolean result = false;
        //ArrayList<Integer> songidlist = null;//new ArrayList<>();
       // ArrayList<Song>songArrayList=null;
        if (songList.isEmpty() || playlist.isEmpty() || ablumName == null || playlistName == null)

        throw new JukeBoxException("Please Provide All the value");
        else {
            if (!playlist.containsKey(playlistName))
                // playlistid = playlist.get(playlistName);
                throw new JukeBoxException("Album not found");
            else {
                playlistid = playlist.get(playlistName);
                ArrayList<Integer> songidlist = new ArrayList<>();

                for (Song song : songList) {

                    if (song.getAlbumName().trim().equalsIgnoreCase(ablumName)) {
                        songidlist.add(song.getSongId());
                        result = true;
                        break;
                    }
                }

                if (playlistid == 0)
                    throw new JukeBoxException("Playlist is not present");
                else if (songidlist.isEmpty())
                    throw new JukeBoxException("Song is not present");
                else {
                  //  System.out.println(playlistid);

                    for (int id : songidlist) {

                    playlistContentDao.addSongsToPlaylist(id, playlistid);
                    }
                }

            }
        }
        return songList;
    }


    public ArrayList<Song> playlistContent(String playlistName, Hashtable<String,Integer>playlist, ArrayList<Song>songList) throws JukeBoxException, SQLException {

        ArrayList<Integer>songIdList;
        ArrayList<Song>songList1 = null;
        if(playlistName==null||playlist.isEmpty()||songList.isEmpty())
        throw new JukeBoxException("Provide All the details");
       else{
           int playlistid=playlist.get(playlistName);
            if (playlistid==0)
                throw new JukeBoxException("Song is not present");
            else {
                songIdList= playlistContentDao.viewSongsInPlaylist(playlistid);
            }
            if(songIdList.isEmpty()==false){
                songList1=new ArrayList();
                for(int id:songIdList){
                  for (Song s:songList){
                      if(s.getSongId()==id)
                          songList1.add(s);
                  }
                }
            }
            else
                throw new JukeBoxException("Provide All the details");

        }
       return songList1;

    }


}














