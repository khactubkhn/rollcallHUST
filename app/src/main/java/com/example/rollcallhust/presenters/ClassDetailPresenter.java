package com.example.rollcallhust.presenters;

import com.example.rollcallhust.views.ClassDetailView;

public interface ClassDetailPresenter {
    void loadDetailOfClass(String classCode);
    void createRollCall(String classCode);
}
