package com.order.mall.ui.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.order.mall.R;
import com.order.mall.data.SharedPreferencesHelp;
import com.order.mall.data.base.JsonBean;
import com.order.mall.data.network.IUserApi;
import com.order.mall.data.network.user.Address;
import com.order.mall.data.network.user.UserDeliverAddress;
import com.order.mall.model.netword.ApiResult;
import com.order.mall.ui.BaseActivity;
import com.order.mall.util.GetJsonDataUtil;
import com.order.mall.util.InputUtils;
import com.order.mall.util.RetrofitUtils;
import com.order.mall.widget.MyDialog;
import com.suke.widget.SwitchButton;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.order.mall.ui.activity.user.AddressActivity.INTENT_KEY_EDIT_ADDRESS;

public class AddAddressActivity extends BaseActivity implements MyDialog.OkDialogListener,
        MyDialog.CancelDialogListener {

    public static final int ADD_ADDRESS = 1;

    public static final int EDIT_ADDRESS = 2;
    @BindView(R.id.sys_title)
    View sysTitle;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.line1)
    View line1;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.et_address_detail)
    EditText etAddressDetail;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private MyDialog dialog;

    private UserDeliverAddress userDeliverAddress;

    private IUserApi userApi ;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    Unbinder unbinder ;

    int userId ;
    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        unbinder = ButterKnife.bind(this);
        setUp();
        init();
    }

    String name;
    String mobile;
    String addressDetails;
    String address;

    @Override
    protected void setUp() {
        if (getIntent() != null) {
            userDeliverAddress = getIntent().getParcelableExtra(INTENT_KEY_EDIT_ADDRESS);
            if (userDeliverAddress != null) {
                name = userDeliverAddress.getReciver();
                mobile = userDeliverAddress.getMobile();
                addressDetails = userDeliverAddress.getAddressDetail();
                address = userDeliverAddress.getAddress();
                etName.setText(name);
                etMobile.setText(mobile);
                etAddressDetail.setText(addressDetails);
                tvAddress.setText(address);
                showDelete();
            } else {
                hideDelete();
                etName.setText("");
                etMobile.setText("");
                etAddressDetail.setText("");
                tvAddress.setText("");
            }
        }
    }
    private void showDate() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程中解析省市区数据
                initJsonData();
            }
        });
        thread.start();
    }
    OptionsPickerView pvOptions ;
    private void init() {
        dialog = new MyDialog(this);
        dialog.setCancelDialogListener(this);
        dialog.setOkDialogListener(this);
        userApi = RetrofitUtils.getInstance().getRetrofit().create(IUserApi.class);
        userId = (int) SharedPreferencesHelp.getInstance(this).getUser().getId();
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                isDefault = isChecked ;
            }
        });

        showDate();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                tvAddress.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
    }

    @OnClick(R.id.rl_address)
    public void addAddress(){
        InputUtils.hideSoftInputFromWindow(this , etMobile);
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    boolean isDefault ;

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    private void showDelete() {
        delete.setVisibility(View.VISIBLE);
    }

    private void hideDelete() {
        delete.setVisibility(View.GONE);
    }

    @OnClick(R.id.tv_ok)
    public void tvOk() {
        String content = etName.getText().toString() +" ," + etMobile.getText().toString() +
                " ," + tvAddress.getText().toString() +  etAddressDetail.getText().toString() ;
        dialog.setContent(content);
        dialog.show();

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
    public void cancel() {

    }

    @Override
    public void ok() {
        String name = etName.getText().toString();
        String mobile = etMobile.getText().toString();
        String address = tvAddress.getText().toString();
        String addressDetail = etAddressDetail.getText().toString();
        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put("id" , "");
        hashMap.put("userId" , userId);
        hashMap.put("reciver" , name);
        hashMap.put("mobile" , mobile);
        hashMap.put("address" , address);
        hashMap.put("addressDetail" , addressDetail);
        hashMap.put("isDefault" ,isDefault);
        addObserver(userApi.mergeAddress(hashMap), new NetworkObserver<ApiResult<Boolean>>(){

                    @Override
                    public void onReady(ApiResult<Boolean> booleanApiResult) {
                        if (booleanApiResult.getData()){
                            showToast("添加地址成功");
                            back();
                        }else{
                            showToast("添加地址失败");
                        }
                    }
                });
    }
}
