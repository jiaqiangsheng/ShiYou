package com.jqs.example.utils;

/**
 * Created by qiangsheng on 2016/5/2.
 */
import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by qiangsheng on 2016/4/16.
 */
public class ImageViewDemo extends ImageView {
    public ImageViewDemo(Context context) {
        super(context);
    }
    public ImageViewDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewDemo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b =  ((BitmapDrawable)drawable).getBitmap();

        if(null == b)
        {
            return;
        }

        Bitmap bitmap = b.copy(Config.ARGB_8888, true);

        int w = getWidth(), h = getHeight();


        Bitmap roundBitmap =  getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0,0, null);

    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if(bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
                sbmp.getWidth() / 2+0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }

    //随手指移动图片imageview
    public void setLocation(int x, int y) {
        this.setFrame(x, y - this.getHeight(), x + this.getWidth(), y);
    }

    // 移动
    public boolean autoMouse(MotionEvent event) {
        boolean rb = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                this.setLocation((int) event.getX(), (int) event.getY());
                rb = true;
                break;
        }
        return rb;
    }

}

