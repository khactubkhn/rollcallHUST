package com.example.rollcallhust.views.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.adapters.RVClassDetailAdapter;
import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.presenters.ClassDetailPresenter;
import com.example.rollcallhust.presenters.ClassDetailPresenterImpl;
import com.example.rollcallhust.views.ClassDetailView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassDetailActivity extends AppCompatActivity implements ClassDetailView {
    @BindView(R.id.toolbarDetailClass)
    Toolbar toolbar;
    ClassDetailPresenter classDetailPresenter;
    @BindView(R.id.tvClassCodeDetail)
    TextView tvClassCode;
    @BindView(R.id.tvTermClassDetail)
    TextView tvTerm;
    @BindView(R.id.tvClassNameDetail)
    TextView tvClassName;
    @BindView(R.id.tvSizeClassDetail)
    TextView tvSize;
    @BindView(R.id.tvFromDetail)
    TextView tvFrom;
    @BindView(R.id.tvToDetail)
    TextView tvTo;

    @BindView(R.id.rvRollCall)
    RecyclerView rvRollCall;
    RVClassDetailAdapter rvClassDetailAdapter;

    String classCode = "";

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
        tvClassCode.setText(classDetailResponse.getClassCode());
        tvTerm.setText(classDetailResponse.getTerm());
        tvClassName.setText(classDetailResponse.getClassName());
        tvSize.setText(classDetailResponse.getSize() + "");
        tvFrom.setText(classDetailResponse.getFrom());
        tvTo.setText(classDetailResponse.getTo());

        rvClassDetailAdapter = new RVClassDetailAdapter(classDetailResponse.getRollCall(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRollCall.setLayoutManager(layoutManager);
        rvRollCall.setAdapter(rvClassDetailAdapter);
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
}
