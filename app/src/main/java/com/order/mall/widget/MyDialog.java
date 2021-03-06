package com.order.mall.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.util.ScreenUtils;

public class MyDialog extends Dialog {


    private CancelDialogListener cancelDialogListener ;

    private OkDialogListener okDialogListener ;

    private TextView cancel ;

    private TextView ok ;

    private TextView content ;

    private TextView title ;

    public MyDialog(@NonNull Context context) {
        this(context , R.style.BottomDialogStyle);
        Window window = this.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        init();
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.BottomDialogStyle );
    }



    public void setCancelDialogListener(CancelDialogListener cancelDialogListener) {
        this.cancelDialogListener = cancelDialogListener;
    }

    public void setOkDialogListener(OkDialogListener okDialogListener) {
        this.okDialogListener = okDialogListener;
    }

    private void init(){
        setContentView(R.layout.dialog);
        setCanceledOnTouchOutside(true);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (ScreenUtils.getScreenWidth(getContext()) * 0.8f);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
        ok = findViewById(R.id.ok);
        cancel = findViewById(R.id.cancel);
        content = findViewById(R.id.content);
        title = findViewById(R.id.title);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (okDialogListener != null) okDialogListener.ok();
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelDialogListener != null) cancelDialogListener.cancel();
                dismiss();
            }
        });
    }
    public void setContent(String contentTxt){
        if (content != null){
            content.setText(contentTxt);
        }
    }

    public void setTitle(String titleTxt){
        if (title != null){
            title.setText(titleTxt);
        }
    }


    public interface CancelDialogListener{
        void  cancel() ;
    }

    public interface OkDialogListener{
        void ok();
    }
}
