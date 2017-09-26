package com.myf.baokuqzz.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.map.MassTransitRouteOverlay;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoutePlanActivity extends AppCompatActivity implements OnGetRoutePlanResultListener,View.OnClickListener {
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    private LatLng currLocation;
    private LatLng projectLocation;
    private RoutePlanSearch mapSearch;
    private PlanNode startNode;
    private PlanNode endNode;
    BitmapDescriptor bdCurrent = BitmapDescriptorFactory.fromResource(R.drawable.icon_location);

    BDAbstractLocationListener locationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation == null){
                Toast.makeText(BKApplication.getInstance(),"无法获取当前位置信息",Toast.LENGTH_SHORT).show();
                return;
            }
            currLocation = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            locationClient.stop();
            MarkerOptions ooA = new MarkerOptions().position(currLocation).icon(bdCurrent)
                    .zIndex(9).draggable(false);
            mapSearch = RoutePlanSearch.newInstance();
            mapSearch.setOnGetRoutePlanResultListener(RoutePlanActivity.this);
            startNode = PlanNode.withLocation(currLocation);
            endNode = PlanNode.withLocation(projectLocation);
            mapSearch.masstransitSearch(new MassTransitRoutePlanOption().from(startNode).to(endNode));
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_plan);
        ButterKnife.bind(this);
        double X = getIntent().getDoubleExtra("X",0);
        double Y = getIntent().getDoubleExtra("Y",0);
        projectLocation = new LatLng(X,Y);

        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(locationListener);
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType("bd09ll");
        locationClientOption.setScanSpan(0);
        locationClientOption.setIsNeedAddress(true);
        locationClient.setLocOption(locationClientOption);
        requestLocationPermission();

        mapView = (MapView) findViewById(R.id.mapView);
        baiduMap = mapView.getMap();
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
        if(mapSearch!=null){
            mapSearch.destroy();
        }
        mapView.onDestroy();
    }

    @OnClick({R.id.img_themeBack})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_themeBack:
                finish();
                break;
        }
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {
        if(result!=null && result.error == SearchResult.ERRORNO.NO_ERROR && result.getRouteLines().size() > 0){
            MassTransitRouteOverlay overlay = new MassTransitRouteOverlay(baiduMap);
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }else {
            Toast.makeText(BKApplication.getInstance(),"未找到结果",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult result) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {

    }
}
