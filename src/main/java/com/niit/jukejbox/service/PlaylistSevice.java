package com.niit.jukejbox.service;
import com.niit.jukejbox.operation.PlayListDao;

import java.sql.SQLException;
import java.util.Hashtable;

    public class PlaylistSevice {
        PlayListDao playListDao=new PlayListDao();//here i'm check playlist is null ya not
        public boolean addPlaylist(String playlistName, Hashtable<String,Integer> playlist) throws JukeBoxException, SQLException {
          //  boolean result=true;
            if(playlist==null||playlist.isEmpty())
                throw new JukeBoxException("Error from addPlayList Enter Proper Value");

            boolean playlistispresent=playlist.containsKey(playlistName);//containsKey-return true if this mapping for the
            if (!playlistispresent)                                        //specified key
                playlistispresent=PlayListDao.createPlaylist(playlistName);
            return playlistispresent;
        }

       public Hashtable<String,Integer>getAllPlaylist() throws SQLException, JukeBoxException {
          if(playListDao.viewAllPlaylist().isEmpty())
              throw new JukeBoxException("Playlist Empty");
          else
              return playListDao.viewAllPlaylist();
    }

    }

