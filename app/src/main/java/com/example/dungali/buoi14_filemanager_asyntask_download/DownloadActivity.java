package com.example.dungali.buoi14_filemanager_asyntask_download;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dungali.asyntask.MyAsyntask;

/**
 * Created by Dung Ali on 6/24/2017.
 */

public class DownloadActivity extends Activity implements View.OnClickListener {
    EditText edtLink;
    Button btnDownload;
    ProgressBar pbDownload;
    MyAsyntask myAsyntask ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDownload.setOnClickListener(this);
    }

    private void addControls() {
        edtLink = findViewById(R.id.edtLink);
        btnDownload = findViewById(R.id.btnDownload);
        pbDownload = findViewById(R.id.pbDownload);
        //edtLink.setText("http://img.saobiz.net/d/2017/05/tieu-su-hoa-hau-dang-thu-thao-3.jpg");
        edtLink.setText("http://zmp3-mp3-s1-te-zmp3-fpthn-1.zadn.vn/3292444e9b0a72542b1b/6792228024536335773?key=5MyeSxiBWOBpxmAlM03-3A&expires=1498375380");
    }

    @Override
    public void onClick(View view) {
        String link = edtLink.getText().toString();
        if (link.isEmpty()){
            Toast.makeText(this,"Url invalid",Toast.LENGTH_SHORT).show();
            return;
        }
        myAsyntask = new MyAsyntask(handler);
        myAsyntask.execute(link);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if(message.what== MyAsyntask.WHAT_PROGRESS_UPDATE){
                int percent = message.arg1;
                pbDownload.setProgress(percent);
            }else if(message.what==MyAsyntask.WHAT_POST_EXCUTE){
                Toast.makeText(DownloadActivity.this,"Download Complete",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });

}
