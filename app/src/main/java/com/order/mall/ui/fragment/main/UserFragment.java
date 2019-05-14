package com.order.mall.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.data.network.user.UserData;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.LoginActivity;
import com.order.mall.ui.activity.Baodanjifen;
import com.order.mall.ui.activity.CashJifenActivity;
import com.order.mall.ui.activity.bonus.BonusMainActivity;
import com.order.mall.ui.activity.card.PayMethodActivity;
import com.order.mall.ui.activity.consume.ConsumeMainActivity;
import com.order.mall.ui.activity.user.AddressActivity;
import com.order.mall.ui.activity.user.InvitationActivity;
import com.order.mall.ui.activity.user.MyteamActivity;
import com.order.mall.ui.activity.user.SettingActivity;
import com.order.mall.ui.activity.user.ShoppingCenterActivity;
import com.order.mall.ui.activity.user.TradeAllActivity;
import com.order.mall.util.ImageStringUtils;
import com.order.mall.util.LoginUtils;
import com.order.mall.util.RetrofitUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import me.jessyan.autosize.utils.LogUtils;

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
    private IUserApi iUserApi;
    private int tradeBalance;
    private int cashBalance;
    private int bonusBalance;
    private int consumeBalance;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    private View rootView;

    private UserRespDTO user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder1 = ButterKnife.bind(this, rootView);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserData();
        //初始化等级
    }


    /**
     * 获取用户数据
     */
    private void getUserData() {
        UserRespDTO user = SharedPreferencesHelp.getInstance(getActivity()).getUser();
        if (user != null) {
            if (!TextUtils.isEmpty(user.getAvatar())) {
                Glide.with(getContext()).load(user.getAvatar()).into(ivUser);
            } else {
                Glide.with(getContext()).load(R.mipmap.head_icon).into(ivUser);
            }
            tvGrade.setText("L" + user.getLevel() + ImageStringUtils.convertString(user.getLevel()));
            tvName.setText(user.getAccount());
            //刷新数据
            addObserver(iUserApi.getUserData(user.getId()), new NetworkObserver<ApiResult<UserData>>() {

                @Override
                public void onReady(ApiResult<UserData> userDataApiResult) {
                    initUserData(userDataApiResult.getData());
                }
            });
        }

    }


    private void initUserData(UserData data) {
        tvName.setText(data.getAccount());
        //报单积分
        tradeBalance = data.getTradeBalance();
        tvBaodanjifen.setText(tradeBalance);
        //现金积分
        cashBalance = data.getCashBalance();
        tvXianjinjifen.setText(cashBalance);
        //奖金积分
        bonusBalance = data.getBonusBalance();
        tvJiangjinjifen.setText(bonusBalance);
        //消费积分
        consumeBalance = data.getConsumeBalance();
        tvXiaofeijifen.setText(consumeBalance);
    }

    @OnClick(R.id.iv_user)
    public void headClick() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
    }

    @OnClick({R.id.ll_qiangdan, R.id.ll_shouyiy, R.id.ll_guamai, R.id.ll_maichu})
    public void onTrade(View v) {
        int position = 0;
        switch (v.getId()) {
            case R.id.ll_qiangdan:
                position = 1;
                break;
            case R.id.ll_shouyiy:
                position = 2;
                break;
            case R.id.ll_guamai:
                position = 3;
                break;
            case R.id.ll_maichu:
                position = 4;
                break;
        }
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), TradeAllActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @OnClick(R.id.baodanjifen)
    public void toBaodan() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), Baodanjifen.class);
        intent.putExtra("tradeBalance", tradeBalance);
        startActivity(intent);
    }

    @OnClick(R.id.ll_xiaofeijifen)
    public void toXiaofei() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), ConsumeMainActivity.class);
        intent.putExtra("consumeBalance", consumeBalance);
        startActivity(intent);
    }

    @OnClick(R.id.rl_gouwudingdan)
    public void toShopping() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), ShoppingCenterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_jiaoyi_title)
    public void toTrade() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), TradeAllActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_setting)
    public void toSetting() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), SettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_xianjinjifen)
    public void toCash() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), CashJifenActivity.class);
        intent.putExtra("cashBalance", cashBalance);
        startActivity(intent);
    }

    @OnClick(R.id.rl_dizhiguanli)
    public void toDizhi() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), AddressActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_shoukuanfangshi)
    public void toPayWay() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), PayMethodActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ll_jiangjinjifen)
    public void toBonus() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), BonusMainActivity.class);
        intent.putExtra("bonusBalance", bonusBalance);
        startActivity(intent);
    }

    @OnClick(R.id.rl_yaoqinghaoyou)
    public void toYaoqing() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), InvitationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.rl_tuandui)
    public void toTeam() {
        if (!LoginUtils.isLogin(getContext())) {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        Intent intent = new Intent(getContext(), MyteamActivity.class);
        startActivity(intent);
    }

    @Override
    protected void loadData() {
        user = SharedPreferencesHelp.getInstance(getContext()).getUser();
        if (user != null) {
            tvName.setText(user.getAccount());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
