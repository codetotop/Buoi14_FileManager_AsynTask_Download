package com.example.dungali.buoi14_filemanager_asyntask_download;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dungali.adapter.FileAdapter;
import com.example.dungali.file.FileManager;

import java.io.File;
import java.security.Permission;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Manifest;

import static android.os.Build.VERSION.SDK_INT;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final int REQUES_PERMISSION = 1;
    ListView lvFile;
    ArrayList<File> arrFile;
    FileAdapter fileAdapter;
    FileManager fm;
    String listPermission[] = new String[]{
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    String currentPath = FileManager.ROOT_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkPermission()){
            addControls();
        }else {
            if(SDK_INT>=android.os.Build.VERSION_CODES.M){
                requestPermissions(listPermission,REQUES_PERMISSION);
            }
        }
    }

    private boolean checkPermission(){
        if(SDK_INT>=android.os.Build.VERSION_CODES.M){
            for(String p:listPermission){
                int state = checkSelfPermission(p);
                if(state!= PackageManager.PERMISSION_GRANTED)
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(checkPermission()){
            addControls();
        }else {
            finish();
        }
    }

    private void addControls() {
        fm = new FileManager();
        lvFile = (ListView) findViewById(R.id.lvFile);
        arrFile = new ArrayList<>();
        arrFile.addAll(Arrays.asList(fm.readFolder(FileManager.ROOT_PATH)));
        fileAdapter = new FileAdapter(this,arrFile);
        lvFile.setAdapter(fileAdapter);

        lvFile.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        File file = arrFile.get(i);
        if(file.isDirectory()){
            currentPath = file.getPath();
            arrFile.clear();
            arrFile.addAll(Arrays.asList(fm.readFolder(currentPath)));
            fileAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onBackPressed() {
        if(currentPath!=null){
            File f = new File(currentPath);
            currentPath = f.getParentFile().getPath();
            arrFile.clear();
            arrFile.addAll(Arrays.asList(fm.readFolder(currentPath)));
            fileAdapter.notifyDataSetChanged();
        }else {
            super.onBackPressed();
        }
    }
}
