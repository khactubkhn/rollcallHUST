package com.example.rollcallhust.views;

import com.example.rollcallhust.models.ClassDetailResponse;

public interface ClassInfoView {
    void onLoadClassSuccess(ClassDetailResponse classDetailResponse);

    void onLoadClassFail();
}
