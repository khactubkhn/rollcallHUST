package com.example.rollcallhust.views.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.rollcallhust.R;
import com.example.rollcallhust.managers.AppPreference;
import com.example.rollcallhust.models.User;
import com.example.rollcallhust.views.HomeView;
import com.example.rollcallhust.views.fragments.MainFragment;
import com.example.rollcallhust.views.fragments.TeacherClassFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDrawerActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_drawer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        onNavigationItemChecked(menuItem);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        navigationView.getMenu().getItem(0).setChecked(true);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        replaceFragment(MainFragment.newInstance("", ""));
        getSupportActionBar().setTitle(R.string.home);

        User user = AppPreference.INSTANCE.getUser();
        onInitUserHeader(user.getUsername(), user.getEmail());
    }

    @Override
    public void onNavigationItemChecked(MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                fragment = MainFragment.newInstance("", "");
                replaceFragment(fragment);
                getSupportActionBar().setTitle(R.string.home);
                break;
            case R.id.nav_study_class:
                fragment = TeacherClassFragment.newInstance("", "");
                replaceFragment(fragment);
                getSupportActionBar().setTitle(R.string.class_study);
                break;
            case R.id.nav_logout:
                AppPreference.INSTANCE.setUser(null);
                AppPreference.INSTANCE.setToken(null);
                this.finish();
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void onInitUserHeader(String username, String email) {
        View headerView = navigationView.getHeaderView(0);
        TextView tvUsername = headerView.findViewById(R.id.tvUsername);
        TextView tvEmail = headerView.findViewById(R.id.tvEmail);
        TextView tvLetter = headerView.findViewById(R.id.tvLetter);
        tvUsername.setText("Tài khoản: " + username);
        tvEmail.setText("Email: " + email);

        String usrnameUpper = username.toUpperCase();
        tvLetter.setText(String.valueOf(usrnameUpper.charAt(0)));
    }
}
