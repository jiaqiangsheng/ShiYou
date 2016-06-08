package com.jqs.lookbook;

/**
 * Created by qiangsheng on 2016/4/20.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.jqs.lookbook.bean.Chapter;
import com.jqs.lookbook.util.BookPage;
import com.jqs.lookbook.util.IOHelper;
import com.jqs.lookbook.view.PageWidget;
import com.yanghuan.R;

public class ViewBookActivity extends Activity{
    /*private TextView booktitleTv;
    private TextView bookcontentTv;*/
    private PageWidget pageWidget;//浏览方式操作
    private Bitmap curBitmap, nextBitmap; //压缩图片
    private Canvas curCanvas, nextCanvas; //画布
    private BookPage bookpage ;//（工具）阅读实现
    private Chapter chapter; //章节对象

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);             // 属性为FEATURE_NO_TITLE：无标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);	//设置窗体全屏

        initChapter();
        DisplayMetrics dm = new DisplayMetrics();	//获取手机分辨率
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;	//获取屏幕的宽和高
        System.out.println(w + "\t" + h);
        curBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);//按宽高压（裁剪/压缩）（不是太确定）图片
        nextBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        curCanvas = new Canvas(curBitmap);
        nextCanvas = new Canvas(nextBitmap);
        bookpage = new BookPage(w, h, chapter);
            //阅读界面背景
        /*bookpage.setBgBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.bg));*/

        bookpage.draw(curCanvas);

        pageWidget = new PageWidget(this, w, h);
        setContentView(pageWidget);
        pageWidget.setBitmaps(curBitmap, curBitmap);

        pageWidget.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                // TODO Auto-generated method stub

                boolean ret = false;
                boolean toAnotherChapter = true;
                if (v == pageWidget) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        pageWidget.abortAnimation();
                        pageWidget.calcCornerXY(e.getX(), e.getY());

                        bookpage.draw(curCanvas);
                        if (pageWidget.DragToRight()) {
                            if(bookpage.prePage()){
                                bookpage.draw(nextCanvas);
                            } else
                                return false;
                        } else {
                            if (bookpage.nextPage()){
                                bookpage.draw(nextCanvas);
                            }
                            else
                                return false;
                        }
                        pageWidget.setBitmaps(curBitmap, nextBitmap);
                    }

                    ret = pageWidget.doTouchEvent(e);
                    return ret;
                }
                return false;
            }

        });
    }

    private void init(){
        initChapter();
		/*booktitleTv.setText(chapter.getTitle());
		bookcontentTv.setText(chapter.getContent());*/
    }

    private void initChapter(){
        Intent intent = getIntent();//得到传递的意图
        int order = intent.getIntExtra("listorder", -1);
        chapter = IOHelper.getChapter(order);
    }
}

