package com.example.rollcallhust.views.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rollcallhust.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RollCallDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbarRollCallDetail)
    Toolbar toolbar;
    int rollCallId = -1;

    public final int REQUEST_IMAGE_CODE = 1996;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_call_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chi tiết điểm danh");

        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white_color), PorterDuff.Mode.SRC_ATOP);

        rollCallId = getIntent().getIntExtra("rollCallId", -1);
        Toast.makeText(this, rollCallId+ "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_upload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.upload_image:
                selectImage();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CODE && resultCode == RESULT_OK){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        }
    }
}
