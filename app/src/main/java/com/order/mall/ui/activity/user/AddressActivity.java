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
import com.order.mall.data.network.login.UserRespDTO;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.ui.adapter.AddressAdapter;
import com.order.mall.util.RetrofitUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.order.mall.ui.activity.pay.QiangdanPayActivity.INTENT_KEY_ADDRESS;
import static com.order.mall.ui.activity.pay.QiangdanPayActivity.RESULT_ADDRESS;

public class AddressActivity extends BaseActivity implements AddressAdapter.AddressListener {
    public static final String INTENT_KEY_EDIT_ADDRESS = "INTENT_KEY_EDIT_ADDRESS";
    public static final String INTENT_KEY_ADDRESS_TYPE = "INTENT_KEY_ADDRESS_TYPE";
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;

    AddressAdapter adapter;

    private IUserApi userApi;

    private UserRespDTO user;

    private List<UserDeliverAddress> deliverAddressList;

    public int type;

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
        user = SharedPreferencesHelp.getInstance(this).getUser();
        setUp();
        initRecy();
    }

    @Override
    protected void setUp() {
        super.setUp();
        if (getIntent() != null) {
            type = getIntent().getIntExtra(INTENT_KEY_ADDRESS, -1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();

    }

    private void initData() {
        if (userApi == null)
            userApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        long userId = SharedPreferencesHelp.getInstance(this).getUser().getId();
        addObserver(userApi.getUserAddress(userId), new NetworkObserver<ApiResult<List<UserDeliverAddress>>>() {

            @Override
            public void onReady(ApiResult<List<UserDeliverAddress>> userDeliverAddressApiResult) {
                if (userDeliverAddressApiResult.getData() != null) {

                    List<UserDeliverAddress> addressList = userDeliverAddressApiResult.getData();
                    if (addressList != null && addressList.size() > 0) {
                        deliverAddressList.clear();
                        deliverAddressList.addAll(addressList);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void initRecy() {
        deliverAddressList = new ArrayList<>();
        adapter = new AddressAdapter(this, R.layout.item_address, deliverAddressList);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                edit(deliverAddressList.get(position), position);
                if (type != -1) {
                    Intent intent = new Intent();
                    intent.putExtra(RESULT_ADDRESS , deliverAddressList.get(position));
                    AddressActivity.this.finish();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        adapter.setListener(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void edit(UserDeliverAddress address, final int position) {
        if (address == null) return;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", address.getId());
        hashMap.put("userId", address.getUserId());
        hashMap.put("reciver", address.getReciver());
        hashMap.put("mobile", address.getMobile());
        hashMap.put("address", address.getAddress());
        hashMap.put("addressDetail", address.getAddressDetail());
        hashMap.put("isDefault", !address.isIsDefault());
        addObserver(userApi.mergeAddress(hashMap), new NetworkObserver<ApiResult<Boolean>>() {
            @Override
            public void onReady(ApiResult<Boolean> booleanApiResult) {
                if (booleanApiResult.getData()) {
                    address.setIsDefault(!address.isIsDefault());
                    deliverAddressList.set(position, address);
                    adapter.notifyItemChanged(position);
                    if (type != -1) {
                        Intent intent = new Intent();
                        intent.putExtra(RESULT_ADDRESS , address);
                        AddressActivity.this.finish();
                    }
                } else {
                    showToast("修改失败");
                }
            }
        });
    }

    @OnClick(R.id.add)
    public void add() {
        Intent intent = new Intent(this, AddAddressActivity.class);
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
        if (deliverAddressList == null || deliverAddressList.size() <= position || user == null)
            return;
        addObserver(userApi.delUserDelivery(deliverAddressList.get(position).getId(), (int) user.getId()), new NetworkObserver<ApiResult<Boolean>>() {

            @Override
            public void onReady(ApiResult<Boolean> stringApiResult) {
                if (stringApiResult.getData() != null && stringApiResult.getData()) {
                    showToast("删除成功");
                    initData();
                } else {
                    showToast(stringApiResult.getMessage());
                }
            }
        });

    }

    @Override
    public void edit(int position) {
        if (deliverAddressList == null || deliverAddressList.size() <= position) return;
        toEditAddress(deliverAddressList.get(position));
    }

    @Override
    public void edit(int position, boolean isCheck) {
        UserDeliverAddress address = deliverAddressList.get(position);
        if (address == null) return;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", address.getId());
        hashMap.put("userId", address.getUserId());
        hashMap.put("reciver", address.getReciver());
        hashMap.put("mobile", address.getMobile());
        hashMap.put("address", address.getAddress());
        hashMap.put("addressDetail", address.getAddressDetail());
        hashMap.put("isDefault", isCheck);
        addObserver(userApi.mergeAddress(hashMap), new NetworkObserver<ApiResult<Boolean>>() {
            @Override
            public void onReady(ApiResult<Boolean> booleanApiResult) {
                if (booleanApiResult.getData()) {
                    address.setIsDefault(!address.isIsDefault());
                    deliverAddressList.set(position, address);
                    adapter.notifyItemChanged(position);
                } else {
                    showToast("修改失败");
                }
            }
        });
    }

    private void toEditAddress(UserDeliverAddress address) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        intent.putExtra(INTENT_KEY_ADDRESS_TYPE, 1);
        intent.putExtra(INTENT_KEY_EDIT_ADDRESS, address);
        startActivity(intent);
    }
}
