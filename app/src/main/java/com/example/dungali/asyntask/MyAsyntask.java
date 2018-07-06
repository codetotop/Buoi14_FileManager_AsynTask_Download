package com.example.dungali.asyntask;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.example.dungali.file.FileManager;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dung Ali on 6/24/2017.
 */

public class MyAsyntask extends AsyncTask<String,Integer,String> {


    public static final int WHAT_PROGRESS_UPDATE = 1 ;
    public static final int WHAT_POST_EXCUTE = 2;
    Handler handler;
    public MyAsyntask(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();

            FileManager fm = new FileManager();
            //String pathOutFile = FileManager.ROOT_PATH+"/DownloadByCodetotop/img.jpg";
            String pathOutFile = FileManager.ROOT_PATH+"/DownloadByCodetotop/video.mp3";

            FileOutputStream os = fm.getFileOutputStream(pathOutFile);

            int totalSize = connection.getContentLength();
            int downloadedSize=0;
            Integer percent=0;
            byte b[] = new byte[1024];
            int lenght = is.read(b);
            while (lenght!=-1){
                os.write(b,0,lenght);
                downloadedSize+=lenght;
                percent = downloadedSize*100/totalSize;
                publishProgress(percent);
                lenght=is.read(b);
            }
            os.close();
            is.close();
            return pathOutFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Message message = new Message();
        message.arg1=values[0];
        message.what=WHAT_PROGRESS_UPDATE;
        handler.sendMessage(message);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Message message = new Message();
        message.what=WHAT_POST_EXCUTE;
        message.obj=s;
        handler.sendMessage(message);
    }

}
