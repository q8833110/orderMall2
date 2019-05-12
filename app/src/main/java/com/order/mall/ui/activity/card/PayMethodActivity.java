package com.order.mall.ui.activity.card;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.AlipayList;
import com.order.mall.data.network.user.BankList;
import com.order.mall.data.network.user.WeixinList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.activity.user.ChangePasswordActivity;
import com.order.mall.ui.adapter.AlipayMethodAdapter;
import com.order.mall.ui.adapter.PayMethodAdapter;
import com.order.mall.ui.adapter.WeixinMethodAdapter;
import com.order.mall.util.RetrofitUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 收款方式
 */
public class PayMethodActivity extends BaseActivity {

    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    Unbinder unbinder;
    PayMethodAdapter adapter;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.add_bank)
    ImageView addBank;
    @BindView(R.id.rl_bank)
    RelativeLayout rlBank;
    @BindView(R.id.rv_bank)
    RecyclerView rvBank;
    @BindView(R.id.iv_zhifubao)
    ImageView ivZhifubao;
    @BindView(R.id.tv_zhifubao)
    TextView tvZhifubao;
    @BindView(R.id.add_zhifubao)
    ImageView addZhifubao;
    @BindView(R.id.rl_zhifubao)
    RelativeLayout rlZhifubao;
    @BindView(R.id.rv_zhifubao)
    RecyclerView rvZhifubao;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.add_weixin)
    ImageView addWeixin;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.rv_weixin)
    RecyclerView rvWeixin;
    private IUserApi iUserApi;
    private long userId = 500000;
    private List<AlipayList> alipayLists = new ArrayList<>();
    private List<BankList> bankLists = new ArrayList<>();
    private List<WeixinList> weixinLists = new ArrayList<>();
    private AlipayMethodAdapter alipayMethodAdapter;
    private WeixinMethodAdapter weixinMethodAdapter;

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_method);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        unbinder = ButterKnife.bind(this);
        initRecy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alipayLists.clear();
        bankLists.clear();
        weixinLists.clear();
        getAlipayList();
        getBankList();
        getWeixinList();

    }

    private void getAlipayList() {
        addObserver(iUserApi.userPayWayAliList(userId), new NetworkObserver<ApiResult<List<AlipayList>>>() {

            @Override
            public void onReady(ApiResult<List<AlipayList>> listApiResult) {
                if (listApiResult.getData() != null) {
                    alipayLists.addAll(listApiResult.getData());
                    alipayMethodAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void getBankList() {
        addObserver(iUserApi.userPayWayBankList(userId), new NetworkObserver<ApiResult<List<BankList>>>() {

            @Override
            public void onReady(ApiResult<List<BankList>> listApiResult) {
                if (listApiResult.getData() != null) {
                    bankLists.addAll(listApiResult.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void getWeixinList() {
        addObserver(iUserApi.userPayWayWeixinList(userId), new NetworkObserver<ApiResult<List<WeixinList>>>() {

            @Override
            public void onReady(ApiResult<List<WeixinList>> listApiResult) {
                if (listApiResult.getData() != null) {
                    weixinLists.addAll(listApiResult.getData());
                    weixinMethodAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void initRecy() {

        //银行卡
        adapter = new PayMethodAdapter(this, R.layout.item_paymentod, bankLists);
        rvBank.setLayoutManager(new LinearLayoutManager(this));
        rvBank.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(PayMethodActivity.this, AddBankActivity.class);
                intent.putExtra("sName", bankLists.get(position).getAccountName());
                intent.putExtra("sBank", bankLists.get(position).getBankName());
                intent.putExtra("sSubBank", bankLists.get(position).getSubbranchName());
                intent.putExtra("sCard", bankLists.get(position).getBankNo());
                intent.putExtra("openOrNot", bankLists.get(position).isOpenOrNot());
                intent.putExtra("id", bankLists.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        //支付宝
        alipayMethodAdapter = new AlipayMethodAdapter(this, R.layout.item_paymentod, alipayLists);
        rvZhifubao.setLayoutManager(new LinearLayoutManager(this));
        rvZhifubao.setAdapter(alipayMethodAdapter);
        alipayMethodAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(PayMethodActivity.this, AddZhifubaoActivity.class);
                intent.putExtra("sName", alipayLists.get(position).getAccountRealName());
                intent.putExtra("sAccount", alipayLists.get(position).getAliPayAccount());
                intent.putExtra("openOrNot", alipayLists.get(position).isOpenOrNot());
                intent.putExtra("id", alipayLists.get(position).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        //微信
        weixinMethodAdapter = new WeixinMethodAdapter(this, R.layout.item_paymentod, weixinLists);
        rvWeixin.setLayoutManager(new LinearLayoutManager(this));
        rvWeixin.setAdapter(weixinMethodAdapter);
        weixinMethodAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(PayMethodActivity.this, AddWXActivity.class);
                intent.putExtra("sName", weixinLists.get(position).getAccountRealName());
                intent.putExtra("sAccount", weixinLists.get(position).getWeixinPayAccount());
                intent.putExtra("openOrNot", weixinLists.get(position).isOpenOrNot());
                intent.putExtra("id", weixinLists.get(position).getId());

                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @OnClick(R.id.add_bank)
    public void addBank() {
        startActivity(new Intent(this, AddBankActivity.class));

    }

    @OnClick(R.id.add_zhifubao)
    public void addAlipay() {
        startActivity(new Intent(this, AddZhifubaoActivity.class));

    }

    @OnClick(R.id.add_weixin)
    public void addWx() {
        startActivity(new Intent(this, AddWXActivity.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }
}
