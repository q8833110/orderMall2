package com.order.mall.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.order.mall.R;
import com.order.mall.ui.activity.Baodanjifen;
import com.order.mall.ui.activity.CashJifenActivity;
import com.order.mall.ui.activity.bonus.BonusMainActivity;
import com.order.mall.ui.activity.user.AddressActivity;
import com.order.mall.ui.activity.user.InvitationActivity;
import com.order.mall.ui.activity.user.MyteamActivity;
import com.order.mall.ui.activity.user.SettingActivity;
import com.order.mall.ui.activity.user.ShoppingCenterActivity;
import com.order.mall.ui.activity.user.TradeAllActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends LazyLoadFragment {


    @BindView(R.id.iv_user)
    CircleImageView ivUser;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.ll_grade)
    LinearLayout llGrade;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_baodanjifen)
    TextView tvBaodanjifen;
    @BindView(R.id.tv_xianjinjifen)
    TextView tvXianjinjifen;
    @BindView(R.id.tv_jiangjinjifen)
    TextView tvJiangjinjifen;
    @BindView(R.id.tv_xiaofeijifen)
    TextView tvXiaofeijifen;
    @BindView(R.id.ll_jifen)
    LinearLayout llJifen;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.line3)
    View line3;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.all)
    TextView all;
    @BindView(R.id.ll_jiaoyi_title)
    RelativeLayout llJiaoyiTitle;
    @BindView(R.id.ll_qiangdan)
    LinearLayout llQiangdan;
    @BindView(R.id.ll_shouyiy)
    LinearLayout llShouyiy;
    @BindView(R.id.ll_guamai)
    LinearLayout llGuamai;
    @BindView(R.id.ll_maichu)
    LinearLayout llMaichu;
    @BindView(R.id.ll_jiaoyi)
    LinearLayout llJiaoyi;
    @BindView(R.id.line5)
    View line5;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.rl_tuandui)
    RelativeLayout rlTuandui;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.rl_yaoqinghaoyou)
    RelativeLayout rlYaoqinghaoyou;
    @BindView(R.id.line6)
    View line6;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.rl_gouwudingdan)
    RelativeLayout rlGouwudingdan;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.rl_dizhiguanli)
    RelativeLayout rlDizhiguanli;
    @BindView(R.id.line8)
    View line8;
    @BindView(R.id.iv6)
    ImageView iv6;
    @BindView(R.id.rl_shoukuanfangshi)
    RelativeLayout rlShoukuanfangshi;
    @BindView(R.id.iv7)
    ImageView iv7;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;
    @BindView(R.id.iv8)
    ImageView iv8;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    Unbinder unbinder1;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick(R.id.baodanjifen)
    public void toBaodan(){
        Intent intent = new Intent(getContext() , Baodanjifen.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_gouwudingdan)
    public void toShopping(){
        Intent intent = new Intent(getContext() , ShoppingCenterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_jiaoyi_title)
    public void toTrade(){
        Intent intent = new Intent(getContext() , TradeAllActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_setting)
    public void toSetting(){
        Intent intent = new Intent(getContext() , SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_xianjinjifen)
    public void toCash(){
        Intent intent = new Intent(getContext() , CashJifenActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_dizhiguanli)
    public void toDizhi(){
        Intent intent = new Intent(getContext() , AddressActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_jiangjinjifen)
    public void toBonus(){
        Intent intent = new Intent(getContext() , BonusMainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_yaoqinghaoyou)
    public void toYaoqing(){
        Intent intent = new Intent(getContext() , InvitationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_tuandui)
    public void toTeam(){
        Intent intent = new Intent(getContext() , MyteamActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unbinder1.unbind();
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
