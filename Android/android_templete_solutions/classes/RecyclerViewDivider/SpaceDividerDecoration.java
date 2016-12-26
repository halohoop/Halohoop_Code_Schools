/*
 * Copyright (C) 2016, TP-LINK TECHNOLOGIES CO., LTD.
 *
 * SpaceDividerDecoration.java
 *
 * 
 *
 * Author huanghaiqi, Created at 2016-12-26
 *
 * Ver 1.0, 2016-12-26, huanghaiqi, Create file.
 */

package com.halohoop.recyclerviewdragsortswipedemo.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceDividerDecoration extends RecyclerView.ItemDecoration {
    private int mSpaceSize;

    public SpaceDividerDecoration(int spaceSize) {
        this.mSpaceSize = spaceSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSpaceSize;
    }
}
