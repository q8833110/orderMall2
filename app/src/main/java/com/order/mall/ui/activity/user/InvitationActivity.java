package com.order.mall.ui.activity.user;

import android.os.Bundle;

import com.order.mall.R;
import com.order.mall.ui.BaseActivity;

public class InvitationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false ;
    }
}
