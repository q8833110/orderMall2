package com.order.mall.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.AddressAdapter;
import com.order.mall.util.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddressActivity extends BaseActivity implements AddressAdapter.AddressListener {
    public static final String INTENT_KEY_EDIT_ADDRESS = "INTENT_KEY_EDIT_ADDRESS";
    public static final String INTENT_KEY_ADDRESS_TYPE = "INTENT_KEY_ADDRESS_TYPE" ;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;

    AddressAdapter adapter ;

    private IUserApi userApi ;

    private List<UserDeliverAddress> deliverAddressList ;
    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        unbinder = ButterKnife.bind(this);
        initRecy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData(){
        if (userApi == null)
        userApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        long userId = SharedPreferencesHelp.getInstance(this).getUser().getId();
        addObserver(userApi.getUserAddress(userId), new NetworkObserver<ApiResult<List<UserDeliverAddress>>>() {

            @Override
            public void onReady(ApiResult<List<UserDeliverAddress>> userDeliverAddressApiResult) {
                if (userDeliverAddressApiResult.getData() != null ){

                        List<UserDeliverAddress> addressList = userDeliverAddressApiResult.getData();
                        if (addressList != null && addressList.size() > 0){
                            deliverAddressList.clear();
                            deliverAddressList.addAll(addressList);
                            adapter.notifyDataSetChanged();
                        }
                }
            }
        });
    }



    private void initRecy(){
        deliverAddressList = new ArrayList<>();
        adapter = new AddressAdapter(this ,R.layout.item_address  , deliverAddressList);
        adapter.setListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    @OnClick(R.id.add)
    public void add(){
        Intent intent = new Intent(this , AddAddressActivity.class);
        startActivity(intent);
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

    @Override
    public void delete(int position) {
        if (deliverAddressList == null || deliverAddressList.size() <= position) return ;

        deliverAddressList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void edit(int position) {
        if (deliverAddressList == null || deliverAddressList.size() <= position) return ;

    }

    private void toEditAddress(UserDeliverAddress address){
        Intent intent = new Intent(this , AddAddressActivity.class);
        intent
    }
}
