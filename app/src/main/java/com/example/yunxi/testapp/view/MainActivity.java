package com.example.yunxi.testapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunxi.testapp.global.*;

import com.example.yunxi.testapp.R;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * Created by Coder-pig on 2015/8/29 0028.
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener{

    private RadioGroup  rg_tab_bar;
    private RadioButton rb_channel;
    private RadioButton rb_message;
    private RadioButton rb_better;
    private RadioButton rb_setting;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;
    //Fragment Object
    private TextView txt_topbar;
    private SharedPreferences sharedPreferences; // 记录用户名和密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_channel.setChecked(true);
        sharedPreferences = getSharedPreferences(SysConfig.SP_NAME, MODE_PRIVATE);
        checkToken();
    }

    private void bindViews() {
        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);
        rb_message = (RadioButton) findViewById(R.id.rb_message);
        rb_better = (RadioButton) findViewById(R.id.rb_better);
        rb_setting = (RadioButton) findViewById(R.id.rb_setting);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_channel:
                txt_topbar.setText(R.string.tab_menu_alert);
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_message:
                txt_topbar.setText(R.string.tab_menu_profile);
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_better:
                txt_topbar.setText(R.string.tab_menu_pay);
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                txt_topbar.setText(R.string.tab_menu_setting);
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    private void checkToken() {
        Log.e("checktToken Error","check token error");
        final SharedPreferences sharedPreferences = getSharedPreferences(SysConfig.SP_NAME, MODE_PRIVATE);
        String token = sharedPreferences.getString(SPEnum.Token.getKey(),null);
        if(token == null){
            Toast.makeText(this,"请登录", LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_message.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_better.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_setting.setChecked(true);
                    break;
            }
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }
}
