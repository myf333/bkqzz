package com.myf.baokuqzz.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.adapter.ProjectPicAdapter;
import com.myf.baokuqzz.adapter.ProjectServiceAdapter;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.presenter.ProjectPresenter;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectActivity extends BaseActivity<ProjectPresenter> {
    private int projectId;
    private ProjectPicAdapter adapter;
    private ProjectServiceAdapter serviceAdapter;

    @Bind(R.id.tv_themeTitle)
    TextView title;
    @Bind(R.id.recycler_pic_list)
    RecyclerView recyclerPicList;
    @Bind(R.id.recycler_service_list)
    RecyclerView recyclerServiceList;
    @Bind(R.id.btn_rent_box)
    LinearLayout btnRentBox;
    @Bind(R.id.txt_project_name)
    TextView txt_project_name;
    @Bind(R.id.txt_project_address)
    TextView txt_project_address;
    @Bind(R.id.txt_project_phone)
    TextView txt_project_phone;
    @Bind(R.id.txt_project_type)
    TextView txt_project_type;
    @Bind(R.id.txt_project_desc)
    TextView txt_project_desc;
    @Bind(R.id.project_bottom)
    RelativeLayout project_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);

        title.setText(R.string.project_detail);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerPicList.setLayoutManager(gridLayoutManager);
        adapter = new ProjectPicAdapter();
        recyclerPicList.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerServiceList.setLayoutManager(linearLayoutManager);
        serviceAdapter = new ProjectServiceAdapter();
        recyclerServiceList.setAdapter(serviceAdapter);
        projectId = getIntent().getIntExtra("id",0);
        if(projectId>0){
            presenter.getProjectDetail(projectId);
        }
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @OnClick({R.id.btn_rent_box,R.id.img_themeBack})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_rent_box:
                break;
            case R.id.img_themeBack:
                finish();
                break;
        }
    }

    public void getProjectDetail(ProjectView project){
        title.setText(project.getName());
        txt_project_address.setText(project.getAddress());
        txt_project_desc.setText(project.getMemo());
        txt_project_name.setText(project.getName());
        txt_project_type.setText(project.getProjecttype());
        txt_project_phone.setText(project.getPhone());
        serviceAdapter.setServiceList(Arrays.asList(project.getServicecontent().split(",")));
        serviceAdapter.notifyDataSetChanged();
        if(project.getProjecttype().equals("地标宝库")){
            project_bottom.setVisibility(View.GONE);
        }
        if(project.getPiclist()!=null&&project.getPiclist().size()>0) {
            adapter.setPicList(project.getPiclist());
            adapter.notifyDataSetChanged();
        }
    }
}
