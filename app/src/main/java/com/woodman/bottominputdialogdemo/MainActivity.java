package com.woodman.bottominputdialogdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhouqianbgin.commomdialog.inputdialog.BottomInputDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomInputDialog
                .newInstance()
                .setDialogInputHint("输入的信息")
                .setDialogInputListen(new BottomInputDialog.OnDialogInputListen() {
                    @Override
                    public void onResult(String result) {

                    }
                })
        .showDialog(getSupportFragmentManager());

    }


}
