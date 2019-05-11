package com.order.mall.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.order.mall.R;
import com.order.mall.util.ScreenUtils;

public class ProgressView  extends View {
    private int width ;
    private int height ;

    private int smallWidth ;
    private int lineHeight ;

    private Paint paint ;

    public ProgressView(Context context) {
        this(context , null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        smallWidth = ScreenUtils.dpToPxInt(getContext() , 10);
        lineHeight = ScreenUtils.dpToPxInt(getContext() , 2);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineHeight);
        paint.setColor(getContext().getResources().getColor(R.color.colorFullRed));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas) ;
        drawLine(canvas);
    }

    /**
     * drawLine
     * @param canvas
     */
    private void drawLine(Canvas canvas){
        int centerY = height / 2 ;
        // drawLine
        canvas.drawLine( 0 ,centerY ,smallWidth , centerY ,paint);
        // drawCircle
        canvas.drawCircle(smallWidth + lineHeight * 2 ,centerY , 2* lineHeight ,paint);
        // drawLine
        canvas.drawLine(smallWidth + 4 * lineHeight ,centerY , width - smallWidth - 4 * lineHeight ,centerY , paint);
        // drawCircle
        canvas.drawCircle(width - smallWidth - 2 * lineHeight   ,centerY , 2* lineHeight ,paint);
//        // drawLine
        canvas.drawLine(width - smallWidth ,centerY , width  ,centerY , paint);
    }
}
