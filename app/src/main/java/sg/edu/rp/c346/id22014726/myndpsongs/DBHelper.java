package sg.edu.rp.c346.id22014726.myndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_TASK = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGER = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VER);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertSong(String title, String singers, int year, int stars) {
        //Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        //Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        //Insert the row into the TABLE_TASK
        long result = db.insert(TABLE_TASK, null, values);
        //Close the database connection
        db.close();
        Log.d("SQL Insert", "ID:" + result);
        return result;
    }

    public ArrayList<String> getSongTitle() {
        ArrayList<String> Songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Songs.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<String> getSongSingers() {
        ArrayList<String> Songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Songs.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<String> getSongYear() {
        ArrayList<String> Songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Songs.add(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<String> getSongStars() {
        ArrayList<String> Songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Songs.add(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Songs;
    }

    public ArrayList<Songs> getSongContent() {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        Cursor cursor = db.query(TABLE_TASK, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Songs obj = new Songs(id, title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int deleteSong (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int results = db.delete(TABLE_TASK, condition, args);
        db.close();

        return results;
    }

    public int deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int results = db.delete(TABLE_TASK, condition, args);
        db.close();

        return results;
    }

    public int updateSong(Songs title, Songs singers, Songs year, Songs stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title.getTitle());
        values.put(COLUMN_SINGER, singers.getSingers());
        values.put(COLUMN_YEAR, year.getYear());
        values.put(COLUMN_STARS, stars.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(title.getId())};
        int results = db.update(TABLE_TASK, values, condition, args);
        db.close();

        return results;
    }

    public ArrayList<Songs> getAllSongContent(String keyword) {
        ArrayList<Songs> songs = new ArrayList<Songs>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGER, COLUMN_YEAR, COLUMN_STARS};
        String condition = COLUMN_STARS + " LIKE ?";
        String[] args = {"%" + keyword + "%"};
        Cursor cursor = db.query(TABLE_TASK, columns, condition, args,null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Songs obj = new Songs(id, title, singers, year, stars);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
}


