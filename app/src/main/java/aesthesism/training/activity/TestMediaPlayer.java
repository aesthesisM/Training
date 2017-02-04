package aesthesism.training.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 01.02.2017.
 */

public class TestMediaPlayer extends AppCompatActivity {
    MediaMetadataRetriever metaRetriver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("OnCreate");
        getSongList();
        HashMap<String, Integer> artists = new HashMap<String, Integer>();
        loadAudio(artists);
        System.out.println(artists.size());
        Iterator it = artists.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }


    }
    public void getSongList() {
        // retrieve song info

        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, MediaStore.Audio.Media.IS_MUSIC+"=1", null,
                null);

        metaRetriver = new MediaMetadataRetriever();

        if (musicCursor != null && musicCursor.moveToFirst()) {
            // get columns
            int titleColumn = musicCursor.getColumnIndex(MediaStore.MediaColumns.TITLE);
            int idColumn = musicCursor.getColumnIndex(BaseColumns._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
            int column_index = musicCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            byte[] art;
            // add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String path = musicCursor.getString(column_index);
                String title = musicCursor.getString(titleColumn);
                String artist = musicCursor.getString(artistColumn);

                //Log.d(this.getClass().getName(), "path id=" + pathId);
                System.out.println("songPath : "+path+"\ttitle : "+title+"\tartist : "+artist+"\tid : "+thisId);
                metaRetriver.setDataSource(path);
                Bitmap songImage = null;
                try {
                    art = metaRetriver.getEmbeddedPicture();
                    BitmapFactory.Options opt = new BitmapFactory.Options();
                    opt.inSampleSize = 2;
                    opt.inJustDecodeBounds = false;
                    songImage = BitmapFactory.decodeByteArray(art, 0, art.length, opt);
                } catch (Exception e) {
                   //imgAlbumArt.setBackgroundColor(Color.GRAY);
                    if(songImage!=null) {
                        ImageView image = (ImageView) findViewById(R.id.testmediaplayer_image);
                        image.setImageBitmap(songImage);
                        image.setAdjustViewBounds(false);
                    }
                }

                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);


                //              if(songImage!=null)
                //              {
                //              songImage.recycle();
                //              }
            } while (musicCursor.moveToNext());

        }
    }
    private void loadAudio(HashMap<String, Integer> artists) {
        ContentResolver contentResolver = getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri,
                new String[]{MediaStore.Audio.Media.DATA,
                        MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.ALBUM_ID}, selection, null, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                Cursor cursor_Album_Art = contentResolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                        new String[] {MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART},
                        MediaStore.Audio.Albums._ID+ "=?",
                        new String[] {String.valueOf(albumId)},
                        null);
                String albumArt = null;
                if (cursor_Album_Art.moveToFirst()) {
                    albumArt = cursor_Album_Art.getString(cursor_Album_Art.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));
                    if(albumArt!=null)
                        System.out.println("albumArt : "+albumArt);
                    // do whatever you need to do
                }

                /*
                Bitmap bm= BitmapFactory.decodeFile(thisArt);
                ImageView image=(ImageView)findViewById(R.id.image);
                image.setImageBitmap(bm);
                 */
                if (artists.get(artist) != null) {
                    Integer artistSongs = artists.get(artist);
                    artistSongs++;
                    artists.put(artist, artistSongs);
                } else {
                    artists.put(artist, 1);
                }

            }
        }
        cursor.close();
    }
}
/*
MediaMetadataExtractor tags = new MediaMetadataExtractor(path);
		if (!tags.isMediaFile()) {
			mustInsert = false; // does not have any useable metadata: won't insert even if it is a playable file
		}

		if (hasChanged) {
			boolean purgeUserData = (mustInsert ? false : true);
			mBackend.cleanOrphanedEntries(purgeUserData);
		}

		if (mustInsert) {
			hasChanged = true;

			// Get tags which always must be set
			String title = tags.getFirst(MediaMetadataExtractor.TITLE);
			if (title == null)
				title = "<"+file.getName()+">";

			String album = tags.getFirst(MediaMetadataExtractor.ALBUM);
			if (album == null)
				album = "<No Album>";

			String artist = tags.getFirst(MediaMetadataExtractor.ARTIST);
			if (artist == null)
				artist = "<No Artist>";

			String discNumber = tags.getFirst(MediaMetadataExtractor.DISC_NUMBER);
			if (discNumber == null)
				discNumber = "1"; // untagged, but most likely '1' - this prevents annoying sorting issues with partially tagged files

			long albumId = MediaLibrary.hash63(album);
			long artistId = MediaLibrary.hash63(artist);

			ContentValues v = new ContentValues();
			v.put(MediaLibrary.SongColumns._ID,         songId);
			v.put(MediaLibrary.SongColumns.TITLE,       title);
			v.put(MediaLibrary.SongColumns.TITLE_SORT,  MediaLibrary.keyFor(title));
			v.put(MediaLibrary.SongColumns.ALBUM_ID,    albumId);
			v.put(MediaLibrary.SongColumns.DURATION,    tags.getFirst(MediaMetadataExtractor.DURATION));
			v.put(MediaLibrary.SongColumns.SONG_NUMBER, tags.getFirst(MediaMetadataExtractor.TRACK_NUMBER));
			v.put(MediaLibrary.SongColumns.DISC_NUMBER, discNumber);
			v.put(MediaLibrary.SongColumns.YEAR,        tags.getFirst(MediaMetadataExtractor.YEAR));
			v.put(MediaLibrary.SongColumns.PATH,        path);
			mBackend.insert(MediaLibrary.TABLE_SONGS, null, v);

			v.clear();
			v.put(MediaLibrary.AlbumColumns._ID,               albumId);
			v.put(MediaLibrary.AlbumColumns.ALBUM,             album);
			v.put(MediaLibrary.AlbumColumns.ALBUM_SORT,        MediaLibrary.keyFor(album));
			v.put(MediaLibrary.AlbumColumns.PRIMARY_ARTIST_ID, artistId);
			v.put(MediaLibrary.AlbumColumns.PRIMARY_ALBUM_YEAR,tags.getFirst(MediaMetadataExtractor.YEAR));
			mBackend.insert(MediaLibrary.TABLE_ALBUMS, null, v);

			v.clear();
			v.put(MediaLibrary.ContributorColumns._ID,               artistId);
			v.put(MediaLibrary.ContributorColumns._CONTRIBUTOR,      artist);
			v.put(MediaLibrary.ContributorColumns._CONTRIBUTOR_SORT, MediaLibrary.keyFor(artist));
			mBackend.insert(MediaLibrary.TABLE_CONTRIBUTORS, null, v);

			v.clear();
			v.put(MediaLibrary.ContributorSongColumns._CONTRIBUTOR_ID, artistId);
			v.put(MediaLibrary.ContributorSongColumns.SONG_ID,         songId);
			v.put(MediaLibrary.ContributorSongColumns.ROLE,            MediaLibrary.ROLE_ARTIST);
			mBackend.insert(MediaLibrary.TABLE_CONTRIBUTORS_SONGS, null, v);

			// Composers are optional: only add if we found it
			String composer = tags.getFirst(MediaMetadataExtractor.COMPOSER);
			if (composer != null) {
				long composerId = MediaLibrary.hash63(composer);
				v.clear();
				v.put(MediaLibrary.ContributorColumns._ID,               composerId);
				v.put(MediaLibrary.ContributorColumns._CONTRIBUTOR,      composer);
				v.put(MediaLibrary.ContributorColumns._CONTRIBUTOR_SORT, MediaLibrary.keyFor(composer));
				mBackend.insert(MediaLibrary.TABLE_CONTRIBUTORS, null, v);

				v.clear();
				v.put(MediaLibrary.ContributorSongColumns._CONTRIBUTOR_ID, composerId);
				v.put(MediaLibrary.ContributorSongColumns.SONG_ID,         songId);
				v.put(MediaLibrary.ContributorSongColumns.ROLE,            MediaLibrary.ROLE_COMPOSER);
				mBackend.insert(MediaLibrary.TABLE_CONTRIBUTORS_SONGS, null, v);
			}

			// A song might be in multiple genres
			if (tags.containsKey(MediaMetadataExtractor.GENRE)) {
				ArrayList<String> genres = tags.get(MediaMetadataExtractor.GENRE);
				for (String genre : genres) {
					long genreId = MediaLibrary.hash63(genre);
					v.clear();
					v.put(MediaLibrary.GenreColumns._ID,         genreId);
					v.put(MediaLibrary.GenreColumns._GENRE,      genre);
					v.put(MediaLibrary.GenreColumns._GENRE_SORT, MediaLibrary.keyFor(genre));
					mBackend.insert(MediaLibrary.TABLE_GENRES, null, v);

					v.clear();
					v.put(MediaLibrary.GenreSongColumns._GENRE_ID, genreId);
					v.put(MediaLibrary.GenreSongColumns.SONG_ID, songId);
					mBackend.insert(MediaLibrary.TABLE_GENRES_SONGS, null, v);
				}
			}
		} // end if (mustInsert)

		Log.v("VanillaMusic", "MediaScanner: inserted "+path);
		return hasChanged;
}
i could fill my menus just like this.
 */