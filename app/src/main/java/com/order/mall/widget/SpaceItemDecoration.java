package com.order.mall.widget;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space ;

    public SpaceItemDecoration(int space){
        this.space =space ;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        if (parent.getChildLayoutPosition(view) % 2 == 0){
            outRect.left = 0 ;
            outRect.bottom = space ;
        }else{
            outRect.left = space ;
            outRect.bottom = space ;
        }
    }
}
