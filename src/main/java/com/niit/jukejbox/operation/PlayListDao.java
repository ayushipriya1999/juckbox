package com.niit.jukejbox.operation;

import com.niit.jukejbox.model.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlayListDao {
    public static boolean createPlaylist(String playlistName) throws SQLException {
        PreparedStatement insertPlaylist = MyConnection.getConnection().prepareStatement("insert into Playlist(playlistName)values(?);");
        insertPlaylist.setString(1, playlistName);
        int result=insertPlaylist.executeUpdate();//executeUpdate count row that's why i give int data type
        return result>0?true:false;
    }

   public Hashtable<String,Integer> viewAllPlaylist() throws SQLException {//my data is save as key or value
        Hashtable<String,Integer> playlist=new Hashtable();
        Statement selectStatement=MyConnection.getConnection().createStatement();
        ResultSet resultSet=selectStatement.executeQuery(" select * from Playlist");
        while(resultSet.next()){//it giving true value,mera data available hai ya nhi
            playlist.put(resultSet.getString(2),resultSet.getInt(1));
        }

        return playlist;
    }
}
