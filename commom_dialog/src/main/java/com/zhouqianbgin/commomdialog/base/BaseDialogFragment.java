package com.zhouqianbgin.commomdialog.base;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


public abstract class BaseDialogFragment extends DialogFragment {


    protected abstract int setLayout();

    protected abstract void initWidget(View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(setLayout(), null);
        initWidget(rootView);
        doBusiness(savedInstanceState, container);
        setListen();
        return rootView;
    }

    public void doBusiness(Bundle savedInstanceState, ViewGroup container) {
    }

    public void setListen() {
    }


    public Dialog mDialog;
    private Window mWindow;

    @Override
    public void onStart() {
        super.onStart();
        mDialog = getDialog();
        mWindow = mDialog.getWindow();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();

        if (mBackgroundDrawable == null) {
            mWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            mWindow.setBackgroundDrawable(mBackgroundDrawable);
        }

        if (mDialogWidth == 0) {
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }else {
            lp.width = mDialogWidth;
        }

        if (mDialogHeight == 0) {
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }else {
            lp.width = mDialogHeight;
        }

        if (mDialogGravity == 0) {
            lp.gravity = Gravity.CENTER;
        }else {
            lp.gravity = mDialogGravity;
        }

        mDialog.getWindow().setAttributes(lp);

        if (mCannelOutside) {
            mDialog.setCanceledOnTouchOutside(true);
        } else {
            mDialog.setCanceledOnTouchOutside(false);
        }
    }

    private int mDialogGravity;
    public BaseDialogFragment setDialogGravity(int dialogGravity) {
        this.mDialogGravity = dialogGravity;
        return this;
    }

    private int mDialogWidth;
    private int mDialogHeight;
    public BaseDialogFragment setDialogSize(int dialogWidth, int dialogHeight) {
        this.mDialogWidth = dialogWidth;
        this.mDialogHeight = dialogHeight;
        return this;
    }

    private Drawable mBackgroundDrawable;
    public BaseDialogFragment setBackgroundDrawable(Drawable backgroundDrawable) {
        this.mBackgroundDrawable = backgroundDrawable;
        return this;
    }

    private boolean mCannelOutside;
    public BaseDialogFragment setCannelOutside(boolean cannelOutside) {
        this.mCannelOutside = cannelOutside;
        return this;
    }


    public void showDialog(FragmentManager fragmentManager){
        if(fragmentManager == null){
            return;
        }
        show(fragmentManager,BaseDialogFragment.class.getSimpleName());
    }
    public void showDialog(FragmentManager fragmentManager,String tag){
        if(fragmentManager == null){
            return;
        }
        show(fragmentManager,tag);
    }


}
