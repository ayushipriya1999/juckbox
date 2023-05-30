package com.niit.jukejbox.service;

import com.niit.jukejbox.model.Song;
import com.niit.jukejbox.operation.PlayListDao;

import java.sql.SQLException;
import java.util.*;

public class JukeBoxMain {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        SongService songService = new SongService();
        PlaylistSevice playlistSevice = new PlaylistSevice();
        PlaylistcontentSevice playlistcontentSevice = new PlaylistcontentSevice();
        ArrayList<Song> songArrayList ;
        Hashtable<String, Integer> playListDaoArrayList;
        Song songs;
        try {
            songArrayList = songService.getAllSong();
            playListDaoArrayList = playlistSevice.getAllPlaylist();
            songService.displaySong(songArrayList);
            char ans = 0;
            do {
                System.out.println("Press\n     1.Song\n     2.PlayList\n     3.Player\n     4.Exit");
                int enter = scanner.nextInt();
                scanner.nextLine();
                switch (enter) {
                    case 1:
                        System.out.println("Press 1.Add a song\n      2.Search Song\n      3.Search Song By Gener\n      " + "4.Search Song By Artist\n      5.Search Song By Album\n      6.Main Menu\n      7.Exit");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                String songName;
                                System.out.println("Enter a song Name");
                                songName = scanner.nextLine();


                                String albumName;
                                System.out.println("Enter a album Name");
                                albumName = scanner.nextLine();


                                String genre;
                                System.out.println("Enter a Genre");
                                genre = scanner.nextLine();


                                String artistName;
                                System.out.println("Enter a artist");
                                artistName = scanner.nextLine();


                                String duration;
                                System.out.println("Enter a duration");
                                duration = scanner.nextLine();

                                Song song = new Song(songName, albumName, genre, artistName, duration);
                                String result = songService.addSong(song, songArrayList) ? "Song Added" : "Song Not Added ";
                                System.out.println(result);
                                songArrayList = songService.getAllSong();//referce
                               // System.out.println(songArrayList);
                                 songService.displaySong(songArrayList);
                                break;

                            case 2:
                                // scanner.nextLine();//gab and Enter read
                                System.out.println("Enter the song");
                                String songsNames = scanner.nextLine();
                                Song song1 = songService.getASong(songArrayList, songsNames);
                                if (song1 != null)
                                    System.out.println(song1);
                                else
                                    System.out.println("Song is not there");
                                break;
                            case 3:
                                System.out.println("Enter the genre");
                                //scanner.nextLine();
                                String songGenre = scanner.nextLine();
                                ArrayList<Song> songArrayList1 = songService.getSongByGener(songGenre, songArrayList);
                                if (songArrayList1.isEmpty())
                                    System.out.println("Genre not found");
                                else
                                    System.out.println(songArrayList1);
                                break;

                            case 4:
                                System.out.println("Enter the Artist");
                                //scanner.nextLine();
                                String songArtist = scanner.nextLine();
                                ArrayList<Song> songArrayList2 = songService.getSongArtist(songArtist, songArrayList);
                                if (songArrayList2.isEmpty())
                                    System.out.println("Artist is not found");
                                else
                                    System.out.println(songArrayList2);
                                break;

                            case 5:
                                System.out.println("Enter the song Album");
                                //  scanner.nextLine();
                                String songAlbum = scanner.nextLine();
                                System.out.println(songAlbum);
                                ArrayList<Song> songArrayList3 = songService.getSongAlbum(songAlbum, songArrayList);
                                if (songArrayList3.isEmpty())
                                    System.out.println("Album is not found");
                                else
                                    System.out.println(songArrayList3);
                                break;
                            case 6:
                                System.out.println("Returning to main");
                                break;
                            case 7:
                                System.exit(0);
                                break;
                        }
                    default:
                        System.out.println("this is correct Option:\n");
                        break;

                    case 2:
                        System.out.println("Dear user! Press 1.)playlist  2.)create Playlist  3.)Add Song to Playlist  4.)Add album to playlist  5.)display playlist content  6.)back to main menu  7.)exit");
                        int choice1;
                        choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1:
                                Set<String> playlistNames = playListDaoArrayList.keySet();
                                Iterator<String> keyPresentInList = playlistNames.iterator();
                                while (keyPresentInList.hasNext()) {
                                    System.out.println(keyPresentInList.next());
                                }
                                break;

                            case 2:
                                //scanner.nextLine();
                                System.out.println("Enter Playlist Name");
                                scanner.nextLine();
                                String playListname = scanner.nextLine();
                                boolean fetchingPlaylist = playlistSevice.addPlaylist(playListname, playListDaoArrayList);
                                if (fetchingPlaylist) {
                                    System.out.println("PlayList added successfully");
                                    playListDaoArrayList = playlistSevice.getAllPlaylist();//refersh

                                }
                                break;

                            case 3:
                                scanner.nextLine();
                                System.out.println("Enter  Playlist name");
                                String playlistName = scanner.nextLine();
                                System.out.println("Enter the song Name");
                                String songName = scanner.nextLine();
                                boolean songInPlaylist = playlistcontentSevice.addSongToPlaylist(songArrayList, songName, playListDaoArrayList, playlistName);
                                if (songInPlaylist)
                                    playListDaoArrayList = playlistSevice.getAllPlaylist();
                                System.out.println("Song added to playlist successfully");
                                break;

                            case 4:
                                scanner.nextLine();
                                System.out.println("Enter a AlbumName");
                                String album = scanner.nextLine();
                                System.out.println("Enter PlaylistName: ");
                                String playlistNames1 = scanner.nextLine();
                                ArrayList<Song> playlist1 = playlistcontentSevice.addSongByAlbum(songArrayList, album, playListDaoArrayList, playlistNames1);
                                System.out.println("Album added successfully by album name");
                                break;
                            case 5:
                                //
                                System.out.println("playList Name");
                                scanner.nextLine();
                                String playlistNamess = scanner.nextLine();
                                ArrayList<Song> playlistContnt = playlistcontentSevice.playlistContent(playlistNamess, playListDaoArrayList, songArrayList);
                                System.out.println(playlistContnt);
                                break;

                            case 6:
                                System.out.println("Retuening Main menu");
                                break;
                            case 7:
                                System.out.println("thank you");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Provide correct details.");
                        }
                        break;


                    case 3:
                        scanner.nextLine();
                        System.out.println(">>>>>Players<<<<<");
                        System.out.println("1) Play Song\n2) Main Menu\n3) Exit");
                        //scanner.nextLine();
                        int option2 = scanner.nextInt();
                        switch (option2) {
                            case 1: {
                                scanner.nextLine();
                                System.out.println("Enter the Playlist Name to play");

                                String playlistname = scanner.nextLine();
                                PlayerService plyrServc = new PlayerService();
                                ArrayList<Integer> songidtoplay = new ArrayList<>();
                                ArrayList<Song> songsToPlay = playlistcontentSevice.playlistContent(playlistname, playListDaoArrayList, songArrayList);
                                Iterator<Song> songsIterator = songsToPlay.iterator();
                                while (songsIterator.hasNext()) {
                                    songidtoplay.add(songsIterator.next().getSongId());
                                }
                                int idToPlay = 0;
                                int optn;
                                try {
                                    do {
                                        plyrServc.playSong(songidtoplay.get(idToPlay));
                                        while (true) {
                                            System.out.println("1. Pause\n2. Resume\n3. Restart\n4. Stop\n5. Next\n6. Previous");
                                            optn = scanner.nextInt();
                                            plyrServc.chooseOperation(optn);
                                            if (optn == 4) {
                                                break;
                                            }
                                            if (optn == 5) {
                                                idToPlay++;
                                                break;
                                            }
                                            if (optn == 6) {
                                                idToPlay--;
                                                break;
                                            }
                                        }
                                    } while (optn == 5 || optn == 6);
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("No More Songs in your Playlist!");
                                }
                            }

                        }
                        System.out.println("Enter 'y' to continue: \n");
                        ans = scanner.next().charAt(0);

                }
            }
            while (ans == 'y') ;

        }
        catch(InputMismatchException e){
            System.out.println("give correct input");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}



