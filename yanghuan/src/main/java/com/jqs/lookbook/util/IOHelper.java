package com.jqs.lookbook.util;

/**
 * Created by qiangsheng on 2016/4/20.
 */

import android.content.Context;
import android.content.res.Resources;

import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.lookbook.bean.Book;
import com.jqs.lookbook.bean.Chapter;
import com.yanghuan.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 设计此类的目的是便于统一管理，从资源文件中读取数据。 app中的所有数据来源都可以通过这个类来提供，这样在将来重用代码的时候，也方便修改。
 * 而且在各个类的逻辑任务上也清晰可见。 其目前所做的任务包括，初始化Book类，从资源文件中读取章节内容来初始化Chapter对象。
 *
 * @author MJZ
 *
 */
public class IOHelper {
    private static Book book;
    // private static Chapter chapter;
    private static String[] chapterPaths;
    private static Resources res;//资源

    /**
     * 初始化Book类的唯一对象。 这个函数一般只会调用一次。
     *
     * @param context
     *            由于从文件中读取资源，则需要通过Activity 来提供。因此在Activity调用此函数的时候，会传入 this。
     * @return
     */
    public static Book getBook(Context context, MyStoreItemBean storeItemBean) {
        book = Book.getInstance();

        res = context.getResources();    // 获取应用程序包的Resources实例
        String booklists[] = res.getStringArray(R.array.booklists);
        chapterPaths = res.getStringArray(R.array.content_path);

        // 设置Book 对象的信息
        book.setAuthor(storeItemBean.getXsAuthor());
        book.setBookname(storeItemBean.getXsName());

        //下面這個if语句是因为出现个bug而写的，它其实不应该存在。
        //猜测的原因可能是在软件退出的时候，Book类对象没有被销毁，则再次启动软件的时候
        //又给它添加了一次章节信息
        if (book.getChapterList().size() != booklists.length)
            for (int i = 0; i < booklists.length; ++i)
                book.addChapter(booklists[i]);

        return book;
    }

    /**
     * 得到章节内容。
     */
    public static Chapter getChapter(int order) {
        // Resources res = context.getResources();
        if (order < 0 || order >= chapterPaths.length)//顺序位置超过章节数，返回null
            return null;
        InputStream is;//输入流
        InputStreamReader isr; //InputStreamReader：从字节到字符的桥梁。
        BufferedReader br;
        StringBuffer strBuffer = new StringBuffer();
        try {
            String path = chapterPaths[order];
            is = res.getAssets().open(path);//文件读取(字节输入流)

            isr = new InputStreamReader(is, "UTF-8");  //从字节到字符的桥梁
            br = new BufferedReader(isr);  //从字节变为字符，打印显示了

            String str;
            while ((str = br.readLine()) != null)
                strBuffer.append(str + '\n');
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Chapter chapter = new Chapter();
        chapter.setOrder(order);
        chapter.setTitle(book.getChapterName(order));
        chapter.setContent(strBuffer.toString());

        return chapter;
    }
}

