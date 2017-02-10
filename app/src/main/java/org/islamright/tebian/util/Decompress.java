package org.islamright.tebian.util;
import android.os.Environment;
import android.util.Log;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/**
 * Created by AlaaAlShaikh on 10/10/2015.
 */
public class Decompress {
    private String _zipFile;
    private String _location;

    public Decompress(String zipFile, String location) {
        _zipFile = zipFile;
        _location = location;


    }



    public void unzip() {

        try {
            ZipFile zipFile = new ZipFile(_zipFile);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword("");
            }
            zipFile.extractAll(_location);
        } catch (ZipException e) {
            e.printStackTrace();
        }

    }




}
