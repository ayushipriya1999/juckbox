package com.niit.jukejbox.operation;
import com.niit.jukejbox.model.Song;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class SongDao {
   public static boolean insertSong(Song song) throws SQLException {
       PreparedStatement insertStatement=MyConnection.getConnection().prepareStatement("insert into Song(songName,albumName,genre,artistName,duration) values(?,?,?,?,?);");//we can pass the value at dynamic
       insertStatement.setString(1,song.getSongName());
       insertStatement.setString(2,song.getAlbumName());
       insertStatement.setString(3,song.getGenre());
       insertStatement.setString(4,song.getArtistName());
       insertStatement.setString(5,song.getDuration());
      int result=insertStatement.executeUpdate();//this executeUpdate will perform the opration and it will return me the  integer value.so that integer value is nothing but number of row    affected.
       System.out.println(result);
      return result>0?true:false;
   }
    //Fetch all the Song  and store as ArrayList
public static ArrayList<Song>selectAllSong() throws SQLException{
    ArrayList<Song>songArrayList=null;                                      // create statement return me an object of Statement interface.
    Statement selectStatment=MyConnection.getConnection().createStatement();//this is used when we want to execute  a query that take no value to the user.
    ResultSet rs= selectStatment.executeQuery("select * from Song;");
    if(rs.isBeforeFirst()) {//this is check my is there on first ya not
        songArrayList=new ArrayList<>();
        while (rs.next()) {
            songArrayList.add(new Song(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
    }
    return songArrayList;
}
}









