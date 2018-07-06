package com.example.dungali.file;

import android.os.Environment;
import android.util.Log;

import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Dung Ali on 6/24/2017.
 */

public class FileManager {
    public static final String ROOT_PATH
            = Environment.getExternalStorageDirectory().getPath();
    private static final String ERROR = "error_FileManager";

    public File[] readFolder(String path) {
        File f = new File(path);
        return f.listFiles();
    }

    public FileOutputStream getFileOutputStream(String path) {
        try {
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileOutputStream os = new FileOutputStream(f);
            return os;
        } catch (Exception ex) {
            Log.e(ERROR, "Error create file");
        }
        return null;
    }

}
