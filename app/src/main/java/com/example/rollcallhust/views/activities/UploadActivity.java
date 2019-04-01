package com.example.rollcallhust.views.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.models.GeneralResponse;
import com.example.rollcallhust.networks.ApiClient;
import com.example.rollcallhust.utils.ReadPathUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {
    public final String TAG = "UPLOAD_ACTIVITY";
    @BindView(R.id.ivRollCall)
    ImageView ivRollCall;

    private final int REQUEST_SELECT_IMAGE_CODE = 1996;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSelectImage)
    public void selectImage(){
        checkPermistion();
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_SELECT_IMAGE_CODE);
    }

    private void checkPermistion() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SELECT_IMAGE_CODE){
            if(resultCode == RESULT_OK){
                String path = ReadPathUtil.getPath(this, data.getData());
                File file= new File(path);
                Uri uri = Uri.fromFile(new File(path));
                try {
                    InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                    Bitmap bm = BitmapFactory.decodeStream(inputStream);
                    ivRollCall.setImageBitmap(bm);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                uploadImage(path);
            }
        }
    }

    private void uploadImage(String path){
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        ApiClient.getService().uploadImageRollCall(body).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        Toast.makeText(UploadActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Toast.makeText(UploadActivity.this, "Upload fail", Toast.LENGTH_SHORT).show();
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
