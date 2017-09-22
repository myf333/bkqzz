package com.myf.baokuqzz.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myf.baokuqzz.R;
import com.myf.baokuqzz.adapter.ProjectDetailAdapter;
import com.myf.baokuqzz.model.ProjectView;
import com.myf.baokuqzz.presenter.ProjectPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectActivity extends BaseActivity<ProjectPresenter> {
    private int projectId;
    private ProjectDetailAdapter adapter;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.recycler_project)
    RecyclerView recyclerView;
    @Bind(R.id.btn_rent_box)
    LinearLayout btnRentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);

        title.setText(R.string.project_detail);
        projectId = getIntent().getIntExtra("id",0);
        if(projectId>0){
            presenter.getProjectDetail(projectId);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new ProjectDetailAdapter(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }

    @OnClick(R.id.btn_rent_box)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_rent_box:
                break;
        }
    }

    public void getProjectDetail(ProjectView project){
        title.setText(project.getName());
    }
}
