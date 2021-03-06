package com.github.howeyc.slideshow.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.GregorianCalendar;

import com.github.howeyc.slideshow.R;
import com.github.howeyc.slideshow.helper.GlobalPhoneFuncs;
import com.github.howeyc.slideshow.helper.alarm.TimeConverter;
import com.github.howeyc.slideshow.settings.AppData;

public class StatusActivity extends ActionBarActivity {

    private TextView nbFiles;
    private TextView currentFolder;
    private TextView nextDownload;
    private TextView lastLoginCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nbFiles = (TextView) findViewById(R.id.status_nbFiles);
        currentFolder = (TextView) findViewById(R.id.status_currentFolder);
        nextDownload = (TextView) findViewById(R.id.statusOC_nextAlarm);
//        nbRemoteOCFiles= (TextView) findViewById(R.id.statusOC_nbRomoteFiles);
        lastLoginCheck = (TextView) findViewById(R.id.statusOC_loginCheck);
        Button aboutButton = (Button) findViewById(R.id.status_buttonAbout);
        aboutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            }
        });


    }

    protected void onResume(){
        super.onResume();
        setLocalFolderAndFileCount();

    }

    private void setLocalFolderAndFileCount(){
        String localFolder = AppData.getImagePath();
        String localFileCount;
        if(localFolder.equals("")){
            localFolder = "No path set";
            localFileCount = "-";
        } else {
            localFileCount = String.valueOf(GlobalPhoneFuncs.getFileList(localFolder).size());
        }
        currentFolder.setText(localFolder);
        nbFiles.setText(localFileCount);
    }

}
