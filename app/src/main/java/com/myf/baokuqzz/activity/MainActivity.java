package com.myf.baokuqzz.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.drawee.view.SimpleDraweeView;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.fragment.BKCommunityFragment;
import com.myf.baokuqzz.fragment.MapFragment;
import com.myf.baokuqzz.fragment.NewsFragment;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.presenter.MainPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends BaseActivity<MainPresenter> implements View.OnClickListener{
    private BKCommunityFragment bkCommunityFragment;
    private MapFragment mapFragment;
    private NewsFragment newsFragment;

    @Bind(R.id.img_themeBack)
    ImageView mBack;
    @Bind(R.id.tv_themeTitle)
    TextView title;
    @Bind(R.id.img_right)
    ImageView imgRight;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.fragment_layout)
    FrameLayout frameLayout;
    @Bind(R.id.menu_header)
    SimpleDraweeView headerPic;

    RelativeLayout relative_title;
    public LocationClient locationClient=null;
    private boolean isFirstLaunch = true;

    public BDAbstractLocationListener locationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation == null){
                Toast.makeText(BKApplication.getInstance(),"无法获取当前定位",Toast.LENGTH_SHORT).show();
                return;
            }
            bkCommunityFragment.setLocation(bdLocation);
            mapFragment.setLocation(bdLocation);
            locationClient.stop();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        relative_title = (RelativeLayout)findViewById(R.id.title);
        imgRight.setVisibility(View.VISIBLE);
        title.setText(R.string.app_name);
        if(title.getVisibility()!=View.VISIBLE){
            title.setVisibility(View.VISIBLE);
        }
        mBack.setImageDrawable(getResources().getDrawable(R.drawable.icon_menu));
        if(savedInstanceState == null){
            bkCommunityFragment = new BKCommunityFragment();
            mapFragment = new MapFragment();
            newsFragment = new NewsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.relative,bkCommunityFragment,"home");
            ft.add(R.id.relative,mapFragment,"map");
            ft.add(R.id.relative,newsFragment,"news");
            ft.hide(mapFragment);
            ft.hide(newsFragment);
            ft.show(bkCommunityFragment);
            ft.commit();
        }else{
            bkCommunityFragment = (BKCommunityFragment)getSupportFragmentManager().findFragmentByTag("home");
            mapFragment = (MapFragment)getSupportFragmentManager().findFragmentByTag("map");
            newsFragment = (NewsFragment)getSupportFragmentManager().findFragmentByTag("news");
            getSupportFragmentManager().beginTransaction()
                    .hide(mapFragment)
                    .hide(newsFragment)
                    .show(bkCommunityFragment).commit();
        }
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                ViewHelper.setTranslationX(frameLayout,drawerView.getMeasuredWidth()*slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //百度地图定位设置
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(locationListener);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(0);
        locationClientOption.setIsNeedAddress(true);
        locationClient.setLocOption(locationClientOption);
    }

    @Override
    protected MainPresenter createPresenter() {
        presenter = new MainPresenter(MainActivity.this);
        return presenter;
    }

    @OnClick({R.id.img_themeBack,R.id.img_right,R.id.menu_index,R.id.menu_map,R.id.menu_news})
    @Override
    public void onClick(View view) {
        drawerLayout.closeDrawers();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.img_themeBack:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.img_right:
                break;
            case R.id.menu_index:
                ft.hide(mapFragment);
                ft.hide(newsFragment);
                ft.show(bkCommunityFragment);
                ft.commit();
                title.setText(R.string.app_name);
                break;
            case R.id.item_project_more:
            case R.id.menu_map:
                ft.hide(bkCommunityFragment);
                ft.hide(newsFragment);
                ft.show(mapFragment);
                ft.commit();
                mapFragment.loadDate();
                title.setText(R.string.menu_map_text);
                break;
            case R.id.item_news_more:
            case R.id.menu_news:
                ft.hide(bkCommunityFragment);
                ft.hide(mapFragment);
                ft.show(newsFragment);
                ft.commit();
                title.setText(R.string.menu_news_text);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFirstLaunch){
            if(!locationClient.isStarted()){
                requestLocationPermission();
            }
            isFirstLaunch = false;
        }
    }

    private void requestLocationPermission(){
        //android 6.0以上需要申请运行时权限
        if(Build.VERSION.SDK_INT>=23){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                    ||ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else {
                locationClient.start();
            }
        }else {
            locationClient.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    locationClient.start();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
