package com.order.mall.ui.activity.cash;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.AlipayList;
import com.order.mall.data.network.user.BankList;
import com.order.mall.data.network.user.CashSuccess;
import com.order.mall.data.network.user.WeixinList;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.BankListAdapter;
import com.order.mall.ui.adapter.WeixinkListAdapter;
import com.order.mall.ui.adapter.WithdrawalAdapter;
import com.order.mall.util.RetrofitUtils;
import com.order.mall.util.ScreenUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 提现
 */
public class WithdrawalActivity extends BaseActivity implements TextWatcher {

    Unbinder unbinder;

    Dialog dialog;


    WithdrawalAdapter ZhifubaoAdapeter;

    BankListAdapter BankAdapeter;
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
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_equal_grade)
    TextView tvEqualGrade;
    @BindView(R.id.tip)
    TextView tip;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.rl_chose_withdrawal)
    RelativeLayout rlChoseWithdrawal;
    private IUserApi iUserApi;
    private double rate;
    private double cashNum;
    private long userId ;
    private List<AlipayList> alipayLists = new ArrayList<>();
    private List<BankList> bankLists = new ArrayList<>();
    private List<WeixinList> weixinLists = new ArrayList<>();
    private WeixinkListAdapter wxAdapeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        unbinder = ButterKnife.bind(this);
        iUserApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        init();
        userId = SharedPreferencesHelp.getInstance(this).getUser().getId();

        etPrice.addTextChangedListener(this);
        initDialog();
        getRate();
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
                    ZhifubaoAdapeter.notifyDataSetChanged();
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
                    BankAdapeter.notifyDataSetChanged();
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
                    wxAdapeter.notifyDataSetChanged();
                }
            }
        });

    }

    private void getRate() {
        addObserver(iUserApi.encashValueRate(), new NetworkObserver() {
            @Override
            public void onReady(ApiResult apiResult) {
                if (apiResult.getData() != null) {
                    rate = (double) apiResult.getData();
                }
            }

        });

    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    private void initDialog() {
        if (dialog == null) {
            dialog = new Dialog(this, R.style.BottomDialogStyle);
            View view = View.inflate(this, R.layout.dialog_withdrawal, null);
            TextView mCancel = view.findViewById(R.id.tv_cancel);
            TextView tOk = view.findViewById(R.id.ok);
            RecyclerView rvBank = view.findViewById(R.id.rv_bank);
            RecyclerView rvWx = view.findViewById(R.id.rv_wx);
            final RecyclerView rvZhifubao = view.findViewById(R.id.rv_zhifubao);
            wxAdapeter = new WeixinkListAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, weixinLists);
            ZhifubaoAdapeter = new WithdrawalAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, alipayLists);
            BankAdapeter = new BankListAdapter(WithdrawalActivity.this, R.layout.item_withdrawal_amount, bankLists);
            rvBank.setAdapter(BankAdapeter);
            rvWx.setAdapter(wxAdapeter);
            rvZhifubao.setAdapter(ZhifubaoAdapeter);
            rvBank.setLayoutManager(new LinearLayoutManager(this));
            rvWx.setLayoutManager(new LinearLayoutManager(this));
            rvZhifubao.setLayoutManager(new LinearLayoutManager(this));
            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(true);
//        view.setMinimumHeight((int) (ScreenUtils.getScreenHeight() * 0.23f));
            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = (int) (ScreenUtils.getScreenWidth(this));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            dialogWindow.setAttributes(lp);
            mCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                }
            });
            tOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ZhifubaoAdapeter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    notifyCheckPayWay(position, 1);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            BankAdapeter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    notifyCheckPayWay(position, 0);
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            wxAdapeter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    notifyCheckPayWay(position, 2);

                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }
    }

    @OnClick(R.id.cash)
    public void cash() {
        String sPrice = etPrice.getText().toString();
        if (!sPrice.isEmpty()) {
            if (accountNo != null) {
                toCash();
            } else {
                showToast("请选择到账方式");
            }
        }
    }

    private void toCash() {
        addObserver(iUserApi.userEncashment(userId, cashNum, reciveWay, accountNo, accountName), new NetworkObserver<ApiResult<CashSuccess>>() {


            @Override
            public void onReady(ApiResult<CashSuccess> cashSuccessApiResult) {
                if (cashSuccessApiResult.getData() != null) {
                    //成功
                    Intent intent = new Intent(WithdrawalActivity.this, WithdrawalStatusActivity.class);
                    intent.putExtra("id", cashSuccessApiResult.getData().getId());
                    startActivity(intent);
                }
            }
        });

    }

    private int reciveWay = 0;
    private String accountNo;
    private String accountName;

    private void notifyCheckPayWay(int position, int type) {
        for (int i = 0; i < alipayLists.size(); i++) {
            alipayLists.get(i).setCheck(false);
        }
        for (int i = 0; i < bankLists.size(); i++) {
            bankLists.get(i).setCheck(false);

        }
        for (int i = 0; i < weixinLists.size(); i++) {
            weixinLists.get(i).setCheck(false);
        }
        if (type == 0) {
            //银行卡
            bankLists.get(position).setCheck(true);
            reciveWay = 2;
            accountNo = bankLists.get(position).getBankNo();
            accountName = bankLists.get(position).getAccountName();
        } else if (type == 1) {
            //支付宝
            alipayLists.get(position).setCheck(true);
            reciveWay = 0;
            accountNo = alipayLists.get(position).getAliPayAccount();
            accountName = alipayLists.get(position).getAccountRealName();
        } else if (type == 2) {
            weixinLists.get(position).setCheck(true);
            accountNo = weixinLists.get(position).getWeixinPayAccount();
            accountName = weixinLists.get(position).getAccountRealName();
        }
        ZhifubaoAdapeter.notifyDataSetChanged();
        BankAdapeter.notifyDataSetChanged();
        wxAdapeter.notifyDataSetChanged();
    }

    private void init() {
        tvTitle.setText("提现");
    }

    @OnClick(R.id.back)
    public void back() {
        this.finish();
    }

    @OnClick(R.id.rl_chose_withdrawal)
    public void choseWitdrawalAmount() {
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String string = s.toString();
        if (!string.isEmpty()) {
            cashNum = (Integer.valueOf(string) * rate);
            tvEqualGrade.setText("折合" + cashNum + "元");
        } else {
            tvEqualGrade.setText("折合0元");

        }
    }
}
