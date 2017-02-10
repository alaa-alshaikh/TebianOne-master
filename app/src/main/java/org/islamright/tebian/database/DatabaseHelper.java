package org.islamright.tebian.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import org.islamright.tebian.App;
import org.islamright.tebian.model.Aya;
import org.islamright.tebian.model.Explanation;
import org.islamright.tebian.model.Page;
import org.islamright.tebian.util.Constant;
import org.islamright.tebian.util.Key;
import org.islamright.tebian.util.Logging;
import org.islamright.tebian.util.Util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by AlaaAlShaikh on 05/05/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tebian_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String PAGES_TABLE = "pagestable";
    private static final String AYAT_TABLE = "ayattable";
    private static final String AYAT_TABLE_FTS = "searchayattable";
    private static final String EXPLANATION_TABLE = "explanationtable";

    private static final String CREATE_PAGES_TABLE = "CREATE TABLE " + PAGES_TABLE + " ( " +
            Key.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Key.PAGE_NUMBER + "  INTEGER ," +
            Key.SORA_NAME + " TEXT ," +
            Key.JZUA_NUMBER + " INTERGER " + "); ";
    private static final String CREATE_AYAT_TABLE = "CREATE TABLE " + AYAT_TABLE + " ( " +
            Key.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Key.PAGE_ID + "  INTEGER , " +
            Key.PAGE_NUMBER + "  INTEGER , " +
            Key.AYA_NUMBER + "  INTEGER , " +
            Key.AYA_NUMBER_GENERAL + "  INTEGER , " +
            Key.SORA_NUMBER + "  INTEGER , " +
            Key.SORA_NAME + " TEXT , " +
            Key.TEXT + " TEXT , " +
            Key.TEXT_WITHOUT_TASHKIL + " TEXT, " +
            Key.X + " REAL , " +
            Key.Y + " REAL , " +
            Key.W + " REAL , " +
            Key.H + " REAL ," +
            Key.NEW_X + " REAL , " +
            Key.NEW_Y + " REAL , " +
            Key.NEW_W + " REAL , " +
            Key.NEW_H + " REAL ," +
            " FOREIGN KEY ( " + Key.PAGE_ID + ") REFERENCES " + PAGES_TABLE + " (" + Key.ID + ")  );";
    private static final String CREATE_AYAT_TABLE_FTS = "CREATE VIRTUAL TABLE " + AYAT_TABLE_FTS + " USING fts3 ( " +
            Key.ID + " , " +
            Key.PAGE_ID + "   , " +
            Key.PAGE_NUMBER + "   , " +
            Key.AYA_NUMBER + "   , " +
            Key.AYA_NUMBER_GENERAL + "   , " +
            Key.SORA_NUMBER + "   , " +
            Key.SORA_NAME + "  , " +
            Key.TEXT + "  , " +
            Key.TEXT_WITHOUT_TASHKIL + " , " +
            Key.X + "  , " +
            Key.Y + "  , " +
            Key.W + "  , " +
            Key.H + "  ," +
            Key.NEW_X + "  ," +
            Key.NEW_Y + "  ," +
            Key.NEW_W + "  ," +
            Key.NEW_H + "  ," + " );";

    private static final String CREATE_EXPLANATION_TABLE = "CREATE TABLE " + EXPLANATION_TABLE + " ( " +
            Key.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            Key.AYA_ID + " INTEGER ," +
            Key.TYPE_ID + " INTEGER ," +
            Key.NAME + " TEXT ," +
            Key.EXPLANATION + " TEXT ," +
            Key.VERIFICATION + " TEXT ," +
            " FOREIGN KEY ( " + Key.AYA_ID + " ) REFERENCES " + AYAT_TABLE + " ( " + Key.ID + " )  ) ;";
    private static final String DB_PATH = "/data/data/org.islamright.tebian/databases";

    private static DatabaseHelper ourInstance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance() {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (ourInstance == null) {
            ourInstance = new DatabaseHelper(App.getInstance());
        }
        return ourInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_PAGES_TABLE);
        db.execSQL(CREATE_AYAT_TABLE);
        db.execSQL(CREATE_EXPLANATION_TABLE);
        db.execSQL(CREATE_AYAT_TABLE_FTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + PAGES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AYAT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EXPLANATION_TABLE);
        // create new tables
        onCreate(db);
    }

    //opening database
    private SQLiteDatabase openDB() {
//        String myPath = DB_PATH +"/"+DATABASE_NAME;
//        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//        return myDataBase;
        return this.getWritableDatabase();
    }

    private SQLiteDatabase openDB_() {
//        String myPath = DB_PATH +"/"+DATABASE_NAME;
//        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
//        return myDataBase;
        return this.getReadableDatabase();
    }

    // closing database
    private void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * Insert table name
     *
     * @param tableName     name of the table
     * @param contentValues
     * @return
     */
    private long insert(String tableName, ContentValues contentValues) {
        return openDB().insert(tableName, null, contentValues);
    }

    /**
     * Return number of page
     *
     * @param pageNumber number of page
     * @return page number
     */
    public Page selectPageByPageNumber(int pageNumber) {

        String selectQuery = "SELECT  * FROM " + PAGES_TABLE + " WHERE " + Key.PAGE_NUMBER + " = " + pageNumber;

        Cursor cursor = openDB_().rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new Page(cursor);
        } else {
            return null;
        }
    }

    /**
     * Return id of page
     *
     * @param pageID page id
     * @return Id page
     */
    public Page selectPageByPageID(long pageID) {

        String selectQuery = "SELECT  * FROM " + PAGES_TABLE + " WHERE " + Key.ID + " = " + pageID;

        Cursor cursor = openDB().rawQuery(selectQuery, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return new Page(cursor);
        } else {
            return null;
        }
    }

    /**
     * Return list of ayat based page Id
     *
     * @param pageID page id
     * @return List of ayat
     */
    private ArrayList<Aya> selectAyatByPageID(long pageID) {

        ArrayList<Aya> ayatList = new ArrayList<>();


        String selectQuery = "SELECT  * FROM " + AYAT_TABLE + " WHERE " + Key.PAGE_ID + " = " + pageID;

        Cursor cursor = openDB().rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ayatList.add(new Aya(cursor));

            } while (cursor.moveToNext());
        }
        return ayatList;
    }


    /**
     * this method for search
     *
     * @param word
     * @return
     */
    public ArrayList<Aya> selectAyatByKeyWordFTS(String word) {

        ArrayList<Aya> ayatList = new ArrayList<>();

        int searchPages = Util.allImagesAndPagesDownloaded() ? Constant.MAX_PAGES : Constant.MIN_PAGES;

        String selectQuery = "SELECT  * FROM " + AYAT_TABLE_FTS + " WHERE "
                + Key.TEXT_WITHOUT_TASHKIL + " MATCH '" + word + "' AND "
                + Key.PAGE_NUMBER + " <= " + searchPages;

        Cursor cursor = openDB().rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                ayatList.add(new Aya(cursor));

            } while (cursor.moveToNext());
        }
        return ayatList;
    }

    /**
     * Return list of explanation based of aya Id
     *
     * @param ayaID
     * @return
     */
    private ArrayList<Explanation> selectExplanationByAyaID(long ayaID) {

        String selectQuery = "SELECT  * FROM " + EXPLANATION_TABLE + " WHERE " + Key.AYA_ID + " = " + ayaID;

        Cursor cursor = openDB().rawQuery(selectQuery, null);

        ArrayList<Explanation> explanations = new ArrayList<>();

        // looping through all rows and adding to list
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                explanations.add(new Explanation(cursor));

            } while (cursor.moveToNext());
        }
        return explanations;
    }

    public int deleteTable(String tableName) {
        return openDB().delete(tableName, null, null);
    }

    /**
     * get Page contain all ayat and explanation
     *
     * @param pageNumber
     * @return
     */
    public Page selectPageComponent(int pageNumber) {
        Page page = selectPageByPageNumber(pageNumber);

        ArrayList<Aya> ayatList = selectAyatByPageID(page.getID());

        //loop  to add explanations to related ayat
        for (int i = 0; i < ayatList.size(); i++) {
            ayatList.get(i).setExplanationsList(selectExplanationByAyaID(ayatList.get(i).getID()));
        }

        page.setAyatList(ayatList);

        closeDB();

        return page;
    }

    /**
     * Take Full Page Object To InsertIt In DataBase
     */
    public void insertPageComponent(Page page) {

        long pageId = insert(PAGES_TABLE, page.getContentValues());

        if (pageId != -1) {

            insertAyatComponent(pageId, page.getPageNumber(), page.getAyatList());

        } else {
            Logging.e(getClass(), "Error insertPageComponent PageNumber" + page.getPageNumber());
        }

    }

    /**
     * Inert ayat pased on page number and page id
     *
     * @param pageID
     * @param pageNumber
     * @param ayatList
     */
    private void insertAyatComponent(long pageID, int pageNumber, ArrayList<Aya> ayatList) {

        for (Aya aya : ayatList) {

            aya.setPageID(pageID);
            aya.setPageNumber(pageNumber);

            long ayaID = insert(AYAT_TABLE, aya.getContentValues());

            if (ayaID != -1) {
                insert(AYAT_TABLE_FTS, aya.getContentValuesFTS(ayaID));
                insertExplanationsComponent(ayaID, aya.getExplanationsList());
            } else {
                Logging.e(getClass(), "Error insertAyatComponent AyaNumber" + aya.getAyaNumber());
            }
        }
    }

    /**
     * Inset explanations
     *
     * @param ayaID
     * @param explanationsList
     */
    private void insertExplanationsComponent(long ayaID, ArrayList<Explanation> explanationsList) {

        Collections.reverse(explanationsList);
        for (Explanation explanation : explanationsList) {
            explanation.setAyaID(ayaID);

            long explanationID = insert(EXPLANATION_TABLE, explanation.getContentValues());

            if (explanationID == -1) {
                Logging.e(getClass(), "Error insertExplanationsComponent explanation" + explanation.getName());
            }
        }
    }


    public void deletePagesComponent() {
        deleteTable(EXPLANATION_TABLE);
        deleteTable(AYAT_TABLE);
        deleteTable(PAGES_TABLE);
        deleteTable(AYAT_TABLE_FTS);
    }

    public boolean checkIfTableIsNotEmpty() {
        Cursor mCursor = openDB().rawQuery("SELECT * FROM " + PAGES_TABLE, null);
        Boolean rowExists;

        if (mCursor.moveToFirst()) {
            // DO SOMETHING WITH CURSOR
            rowExists = true;

        } else {
            // I AM EMPTY
            rowExists = false;
        }
        return rowExists;
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_NAME, null,
                    SQLiteDatabase.OPEN_READONLY);
            //checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        return checkDB != null;
    }

}
