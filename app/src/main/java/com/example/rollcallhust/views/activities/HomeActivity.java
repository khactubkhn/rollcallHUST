package com.example.rollcallhust.views.activities;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.managers.AppPreference;
import com.example.rollcallhust.views.fragments.MainFragment;
import com.example.rollcallhust.views.fragments.StudentClassFragment;
import com.example.rollcallhust.views.fragments.TeacherClassFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ứng dụng điểm danh");

        initTabHost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_logout:
                AppPreference.INSTANCE.setUser(null);
                AppPreference.INSTANCE.setToken(null);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabHost(){
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Trang chủ"),
                MainFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Danh sách lớp"),
                AppPreference.INSTANCE.getUser().getStudentCode().length() == 0? TeacherClassFragment.class: StudentClassFragment.class, null);
    }
}
