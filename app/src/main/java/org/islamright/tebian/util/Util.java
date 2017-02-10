package org.islamright.tebian.util;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.islamright.tebian.App;
import org.islamright.tebian.R;
import org.islamright.tebian.network.Apis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/**
 * Created by AlaaAlShaikh on 05/05/15.
 */
public class Util {
    private static Toast toast;

    public static void toaster(Activity activity, String message) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.tvToastText);
        text.setText(message);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(activity);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void createNoMediaFile() {
        String path = Constant.MAIN_PATH + ".nomedia";
        File fileNoMedia = new File(Environment.getExternalStorageDirectory(), path);
        fileNoMedia.mkdir();
    }

    public static void saveImage(Bitmap bmp, int imageNumber) {
        try {
            File file = new File(String.format(Constant.PATH_IN_SD, imageNumber));
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean firstImagesAndPagesDownloaded() {
        int lastPageDownloaded = Preferences.getInstance().getInt(Key.LAST_PAGE_DOWNLOADED, 0);
        int lastImageDownloaded = Preferences.getInstance().getInt(Key.LAST_IMAGE_DOWNLOADED, 0);
        return lastPageDownloaded >= Constant.MIN_PAGES && lastImageDownloaded >= Constant.MIN_PAGES;
    }

    public static boolean allImagesAndPagesDownloaded() {
        int lastPageDownloaded = Preferences.getInstance().getInt(Key.LAST_PAGE_DOWNLOADED, 0);
        int lastImageDownloaded = Preferences.getInstance().getInt(Key.LAST_IMAGE_DOWNLOADED, 0);
        return lastPageDownloaded == Constant.MAX_PAGES && lastImageDownloaded == Constant.MAX_PAGES;
    }

    public static int getPositionComplement(int position) {

//        if (allImagesAndPagesDownloaded()) {
//            return Constant.MAX_PAGES - position;
//        } else {
//            return Constant.MIN_PAGES - position;
//        }
        return Constant.MAX_PAGES - position;
    }

    public static String readFileFromAssets(String path) throws IOException {
        AssetManager am = App.getInstance().getAssets();
        InputStream is = am.open(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,
                "utf-8"), 8);
        String response = "";
        String line = null;
        while ((line = reader.readLine()) != null) {
            response += line;
        }
        is.close();
        return response;
    }

//    public static Drawable getImageFromSdcard(int pageNumber) {
//        File file = new File(String.format(Constant.PATH_IN_SD, pageNumber));
//        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//        Drawable drawable = new BitmapDrawable(App.getInstance().getResources(), myBitmap);
//        return drawable;
//    }

    public static Drawable getImageFromSdcardNew(int pageNumber) {
        File file = new File(String.format(Locale.ENGLISH,Constant.PATH_IN_SD_NEW, pageNumber));
        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Drawable drawable = new BitmapDrawable(App.getInstance().getResources(), myBitmap);
        return drawable;
    }

    public static double applyEquationToWidth(double value) {
        double p = Preferences.getInstance().getFloat(Key.PERCENTAGE_WIDTH, 0) * value;
        return p;
    }

    public static double applyEquationToHeight(double value) {
        double p = (Preferences.getInstance().getFloat(Key.PERCENTAGE_HEIGHT, 0) * value);
        p = p * Preferences.getInstance().getFloat(Key.PERCENTAGE_NEW_HEIGHT, 0);
        return p;
    }

    public static int safeLongToInt(long longNumber) {
        if (longNumber < Integer.MIN_VALUE || longNumber > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(longNumber + " cannot be cast to int without changing its value.");
        }
        return (int) longNumber;
    }

    public static String openDataFile(int dataNumber) {
        String line = null;
        File file = new File(String.format(Locale.ENGLISH,Constant.DATA_IN_SD_NEW,dataNumber));
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return text.toString();
    }


    public static int getFileSize() {

        URL url = null;
        URLConnection urlConnection = null;
        int file_size = 75078042;
        try {
            url = new URL(Apis.FILE);
            urlConnection = url.openConnection();
            urlConnection.connect();
            file_size = urlConnection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file_size;
    }
}
