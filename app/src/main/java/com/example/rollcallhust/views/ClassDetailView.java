package com.example.rollcallhust.views;

import com.example.rollcallhust.models.ClassDetailResponse;

public interface ClassDetailView {
    void onLoadClassDetail(ClassDetailResponse classDetailResponse);

    void onLoadFail();

    void onCreateRollCallFail();

    void onCreateRollCallSuccess(int rollCallId);
}
