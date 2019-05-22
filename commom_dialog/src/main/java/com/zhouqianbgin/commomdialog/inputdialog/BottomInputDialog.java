package com.zhouqianbgin.commomdialog.inputdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zhouqianbgin.commomdialog.R;
import com.zhouqianbgin.commomdialog.base.BaseDialogFragment;


public class BottomInputDialog extends BaseDialogFragment {


    public static BottomInputDialog newInstance() {
        Bundle args = new Bundle();
        BottomInputDialog fragment = new BottomInputDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private AppCompatTextView mTvTitle;
    private AppCompatEditText mEtContent;
    private AppCompatTextView mTvCancel;
    private AppCompatTextView mTvSure;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.InputDialog);
    }

    @Override
    protected int setLayout() { return R.layout.dialog_bottom_input; }

    @Override
    protected void initWidget(View rootView) {
        mTvTitle = rootView.findViewById(R.id.dialog_input_tv_title);
        mEtContent = rootView.findViewById(R.id.dialog_input_et_content);
        mTvCancel = rootView.findViewById(R.id.dialog_input_tv_cancel);
        mTvSure = rootView.findViewById(R.id.dialog_input_tv_sure);
    }


    @Override
    public void setListen() {
        super.setListen();

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomInputDialog.this.dismiss();
            }
        });

        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnDialogInputListen != null){
                    mOnDialogInputListen.onResult(mEtContent.getText().toString().trim());
                }
                BottomInputDialog.this.dismiss();
            }
        });

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0)
                    getDialog().cancel();
                return false;
            }
        });
    }


    @Override
    public void doBusiness(Bundle savedInstanceState, ViewGroup container) {
        super.doBusiness(savedInstanceState, container);

        Log.d("xxxxx  " , "mDialogInputTitleSize" + mDialogInputTitleSize);
        Log.d("xxxxx  " ,  "mDialogInputTitleColor" + mDialogInputTitleColor);

        mTvTitle.setTextColor(mDialogInputTitleColor);
        mTvTitle.setTextSize(mDialogInputTitleSize);
        if (!TextUtils.isEmpty(mDialogInputTitle)) {
            mTvTitle.setText(mDialogInputTitle);
        }else {
            mTvTitle.setVisibility(View.GONE);
        }

        mEtContent.setHint(mDialogInputHint);
        mEtContent.setText(mDialogInputText);
        mEtContent.requestFocus();

        getDialog().getWindow().getAttributes().windowAnimations = R.anim.dd_mask_in;
    }

    private String mDialogInputTitle = "";
    private int mDialogInputTitleSize = 18;
    private int mDialogInputTitleColor = Color.GRAY;

    public BottomInputDialog setDialogInoutTitleStyle(String dialogInoutTitle, int ititleSize, int titleColor) {
        this.mDialogInputTitle = dialogInoutTitle;
        this.mDialogInputTitleSize = ititleSize;
        this.mDialogInputTitleColor = titleColor;
        return this;
    }

    private String mDialogInputHint;
    public BottomInputDialog setDialogInputHint(String dialogInputHint) {
        this.mDialogInputHint = dialogInputHint;
        return this;
    }

    private String mDialogInputText;
    public BottomInputDialog setDialogInputText(String mDialogInputText) {
        this.mDialogInputText = mDialogInputText;
       return this;
    }


    public OnDialogInputListen mOnDialogInputListen;
    public BottomInputDialog setDialogInputListen(OnDialogInputListen dialogInputListen) {
        this.mOnDialogInputListen = dialogInputListen;
        return this;
    }
    public interface OnDialogInputListen {
        void onResult(String result);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window dialogWindow = getDialog().getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width =WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
    }

}
