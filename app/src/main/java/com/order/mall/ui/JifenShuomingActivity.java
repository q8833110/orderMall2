package com.order.mall.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.order.mall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class JifenShuomingActivity extends BaseActivity {

    Unbinder unbinder;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line5)
    View line5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jifenshuoming);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init(){
        tvTitle.setText("积分说明");
        line1.setVisibility(View.GONE);
        line5.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
