package com.myf.baokuqzz.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.myf.baokuqzz.R;
import com.myf.baokuqzz.global.BKApplication;
import com.myf.baokuqzz.map.DrivingRouteOverlay;
import com.myf.baokuqzz.map.MassTransitRouteOverlay;
import com.myf.baokuqzz.map.OverlayManager;
import com.myf.baokuqzz.map.WalkingRouteOverlay;

import butterknife.Bind;
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
    private String projectType;
    BitmapDescriptor bdCurrent = BitmapDescriptorFactory.fromResource(R.drawable.icon_location);
    @Bind(R.id.route_bus)
    LinearLayout route_bus;
    @Bind(R.id.route_car)
    LinearLayout route_car;
    @Bind(R.id.route_walk)
    LinearLayout route_walk;
    @Bind(R.id.tv_themeTitle)
    TextView title;

    BDAbstractLocationListener locationListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation == null){
                Toast.makeText(BKApplication.getInstance(),"无法获取当前位置信息",Toast.LENGTH_SHORT).show();
                return;
            }
            currLocation = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            locationClient.stop();
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
        projectType = getIntent().getStringExtra("type");
        projectLocation = new LatLng(Y,X);
        title.setText(R.string.project_road);

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

    @OnClick({R.id.img_themeBack,R.id.route_bus,R.id.route_car,R.id.route_walk})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_themeBack:
                finish();
                break;
            case R.id.route_bus:
                if(currLocation == null){
                    Toast.makeText(getApplicationContext(),"无法获取当前位置",Toast.LENGTH_SHORT).show();
                    return;
                }
                route_bus.setBackgroundResource(R.drawable.shape_leftradius_red);
                route_car.setBackgroundResource(R.color.color_gray1);
                route_walk.setBackgroundResource(R.drawable.shape_rightradius_gray);
                mapSearch.masstransitSearch(new MassTransitRoutePlanOption().from(startNode).to(endNode));
                break;
            case R.id.route_car:
                if(currLocation == null){
                    Toast.makeText(getApplicationContext(),"无法获取当前位置",Toast.LENGTH_SHORT).show();
                    return;
                }
                route_bus.setBackgroundResource(R.drawable.shape_leftradius_gray);
                route_car.setBackgroundResource(R.color.color_red);
                route_walk.setBackgroundResource(R.drawable.shape_rightradius_gray);
                mapSearch.drivingSearch(new DrivingRoutePlanOption().from(startNode).to(endNode));
                break;
            case R.id.route_walk:
                if(currLocation == null){
                    Toast.makeText(getApplicationContext(),"无法获取当前位置",Toast.LENGTH_SHORT).show();
                    return;
                }
                route_bus.setBackgroundResource(R.drawable.shape_leftradius_gray);
                route_car.setBackgroundResource(R.color.color_gray1);
                route_walk.setBackgroundResource(R.drawable.shape_rightradius_red);
                mapSearch.walkingSearch(new WalkingRoutePlanOption().from(startNode).to(endNode));
                break;
        }
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        if(result!=null && result.error == SearchResult.ERRORNO.NO_ERROR && result.getRouteLines().size() > 0){
            baiduMap.clear();
            WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap){
                @Override
                public BitmapDescriptor getStartMarker() {
                    return BitmapDescriptorFactory.fromResource(R.drawable.icon_location);
                }

                @Override
                public BitmapDescriptor getTerminalMarker() {
                    if(projectType.equals("社区宝库")){
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker1);
                    }else {
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker2);
                    }
                }
            };
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }else {
            Toast.makeText(BKApplication.getInstance(),"未找到结果",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult result) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult result) {
        if(result!=null && result.error == SearchResult.ERRORNO.NO_ERROR && result.getRouteLines().size() > 0){
            baiduMap.clear();
            MassTransitRouteOverlay overlay = new MassTransitRouteOverlay(baiduMap){
                @Override
                public BitmapDescriptor getStartMarker() {
                    return BitmapDescriptorFactory.fromResource(R.drawable.icon_location);
                }

                @Override
                public BitmapDescriptor getTerminalMarker() {
                    if(projectType.equals("社区宝库")){
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker1);
                    }else {
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker2);
                    }
                }
            };
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
        if(result!=null && result.error == SearchResult.ERRORNO.NO_ERROR && result.getRouteLines().size() > 0){
            baiduMap.clear();
            DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap){
                @Override
                public BitmapDescriptor getStartMarker() {
                    return BitmapDescriptorFactory.fromResource(R.drawable.icon_location);
                }

                @Override
                public BitmapDescriptor getTerminalMarker() {
                    if(projectType.equals("社区宝库")){
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker1);
                    }else {
                        return BitmapDescriptorFactory.fromResource(R.drawable.icon_maker2);
                    }
                }
            };
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }else {
            Toast.makeText(BKApplication.getInstance(),"未找到结果",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult result) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult result) {

    }
}
