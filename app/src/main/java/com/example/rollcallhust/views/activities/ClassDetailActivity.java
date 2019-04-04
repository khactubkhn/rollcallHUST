package com.example.rollcallhust.views.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.presenters.ClassDetailPresenter;
import com.example.rollcallhust.presenters.ClassDetailPresenterImpl;
import com.example.rollcallhust.views.ClassDetailView;
import com.example.rollcallhust.views.ClassInfoView;
import com.example.rollcallhust.views.fragments.ClasInfoFragment;
import com.example.rollcallhust.views.fragments.ListRollCallFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassDetailActivity extends AppCompatActivity implements ClassDetailView {
    @BindView(R.id.toolbarDetailClass)
    Toolbar toolbar;

    String classCode = "";

    ClassDetailPresenter classDetailPresenter;

    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;

    ClassDetailResponse classDetailResponse = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết lớp học");

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white_color), PorterDuff.Mode.SRC_ATOP);

        classCode = getIntent().getStringExtra("classCode");
        //Toast.makeText(this, classCode, Toast.LENGTH_SHORT).show();
        classDetailPresenter = new ClassDetailPresenterImpl(this);
        classDetailPresenter.loadDetailOfClass(classCode);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_class_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.create_rollcall:
                classDetailPresenter.createRollCall(classCode);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadClassDetail(ClassDetailResponse classDetailResponse) {
        this.classDetailResponse = classDetailResponse;
        initTabHost();
    }

    @Override
    public void onLoadFail() {
        Toast.makeText(this, "Đã xảy ra vấn đề khi tải lớp. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateRollCallFail() {
        Toast.makeText(this, "Gặp vấn đề khi điểm danh. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateRollCallSuccess(int rollCallId) {
        Intent intent = new Intent(this, RollCallDetailActivity.class);
        intent.putExtra("rollCallId", rollCallId);
        startActivity(intent);
    }

    private void initTabHost(){
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Thông tin lớp học"),
                ClasInfoFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("danh sách điểm danh"),
                ListRollCallFragment.class, null);

        TabWidget tabWidget = tabHost.getTabWidget();
        for(int i = 0; i< tabWidget.getChildCount(); i++){
            View v = tabWidget.getChildTabViewAt(i);
            TextView tv = v.findViewById(android.R.id.title);
            if(tv == null) continue;
            tv.setTextColor(Color.RED);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if(fragment instanceof ListRollCallFragment){
            ListRollCallFragment listRollCallFragment = (ListRollCallFragment)fragment;
            listRollCallFragment.onLoadRollCall(classDetailResponse.getRollCall());
        }else if(fragment instanceof ClasInfoFragment){
            ClasInfoFragment clasInfoFragment = (ClasInfoFragment)fragment;
            clasInfoFragment.onLoadDate(classDetailResponse);
        }
        super.onAttachFragment(fragment);
    }
}
