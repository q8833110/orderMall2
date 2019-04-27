package com.order.mall.util;

import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.order.mall.R;

public class CountDownUtil extends CountDownTimer {
    private TextView mTextView;
    private String content ;
    /**
     * 5    * @param textView     The TextView
     * 6    *
     * 7    *
     * 8    * @param millisInFuture  The number of millis in the future from the call
     * 9    *             to {@link #start()} until the countdown is done and {@link #onFinish()}
     * 10    *             is called.
     * 11    * @param countDownInterval The interval along the way to receiver
     * 12    *             {@link #onTick(long)} callbacks.
     * 13
     */
    public CountDownUtil(TextView textView, long millisInFuture, long countDownInterval ) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        content = this.mTextView.getText().toString();
    }

    /**
     * 20    * 倒计时期间会调用
     * 21    * @param millisUntilFinished
     * 22
     */
    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒"); //设置倒计时时间
        mTextView.setBackgroundResource(R.drawable.shape_verify_btn_press); //设置按钮为灰色，这时是不能点击的

        SpannableString spannableString = new SpannableString(mTextView.getText().toString()); //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(R.color.colorFullRed);
        /**
         32      * public void setSpan(Object what, int start, int end, int flags) {
         33      * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         34      * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         35      */
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    /**
     * 41    * 倒计时完成后调用
     * 42
     */
    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.shape_verify_btn_normal); //还原背景色
    }

    public void reset(){
        mTextView.setText(content);
        mTextView.setClickable(true);//重新获得点击
        mTextView.setBackgroundResource(R.drawable.shape_verify_btn_normal); //还原背景色
    }
}