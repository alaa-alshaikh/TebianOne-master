package org.islamright.tebian.util;

import android.os.Environment;

import org.islamright.tebian.App;

import java.io.File;


public class Constant {


    public static final String MAIN_PATH =Environment.getExternalStorageDirectory()+File.separator+"Tebian"+ File.separator;
    public static final String PATH_IN_SD = MAIN_PATH + "Tebian_one"+File.separator+"%1$d.gif";
    public static final int MIN_PAGES = 5;
    public static final int MAX_PAGES = 604;
    // new constant
    public static final String MAIN_PATH_NEW = Environment.getExternalStorageDirectory()+File.separator+"TebianOne"+ File.separator;
    public static final String PATH_IN_SD_NEW =  MAIN_PATH_NEW +"pages"+File.separator+"%1$d.jpg";
    public static final String DATA_IN_SD_NEW =  MAIN_PATH_NEW +"data"+File.separator+"%1$d.txt";

    public static final String DOWNLOAD_FILE_LOCATION =Environment.getExternalStorageDirectory()+File.separator+"qur2an.zip";
    public static final float  IMAGE_WIDTH= 653;
    public static final float IMAGE_HEIGHT = 1026;
    public static final String FAHRAS_SORA_PATH = "fahras/1";
    public static final String FAHRAS_JZUA_PATH = "fahras/2";


    public static final String STARTFOREGROUND_ACTION = "org.islamright.tebian.action.startforeground";
    public static final String STOPFOREGROUND_ACTION = "org.islamright.tebian.action.stopforeground";
    public static final String DATABASE_ACTION = "org.islamright.tebian.action.database";


    public static final String SEND_DATA_SERVICE = "IS_ENABLED";
    public static final String START_EXTRACT_AFTER_DOWNLOAD = "START_EXTRACT";
    public static final String CLOSE_PROGRESS = "CLOSE_PROGRESS";
    public static final String OPEN_PROGRESS = "OPEN_PROGRESS";


    //events
    public static interface EventsType{
        public static final String SET_LABEL_VALUE= "setLabelValue";
        public static final String SET_PROGRESS_MAX= "setProgressMax";
        public static final String SET_PROGRESS_VALUE= "setProgressValue";
        public static final String FINISH_VIEW= "finishView";
        public static final String SHOW_MESSAGE= "showMessage";
    }


    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }



}
