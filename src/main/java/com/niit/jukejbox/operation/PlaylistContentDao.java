package com.niit.jukejbox.operation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistContentDao {
    public boolean addSongsToPlaylist(int songId,int playlistId) throws SQLException {
        PreparedStatement insertPlaylistcontent=MyConnection.getConnection().prepareStatement("insert into playlistcontent( playlistId,songId)values(?,?);", Statement.RETURN_GENERATED_KEYS);
        insertPlaylistcontent.setInt(1,playlistId);
        insertPlaylistcontent.setInt(2,songId);
       int result=insertPlaylistcontent.executeUpdate();
        return result>0?true:false;

    }
    public ArrayList<Integer> viewSongsInPlaylist(int playlistId) throws SQLException {

        ArrayList<Integer>playlistContentlist=new ArrayList<>();
        PreparedStatement selectPlaylistContent=MyConnection.getConnection().prepareStatement("select * from playlistcontent where playlistId=?");
        selectPlaylistContent.setInt(1,playlistId);
        ResultSet resultSet=selectPlaylistContent.executeQuery();
        if(resultSet.next())
        {

            playlistContentlist.add(resultSet.getInt(2));

        }
        //System.out.println(playlistContentlist);
        return playlistContentlist;
    }
}

