package com.jqs.lookbook.util;

/**
 * Created by qiangsheng on 2016/4/20.
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.text.format.Time;


import com.jqs.lookbook.bean.Chapter;

import java.text.DecimalFormat;
import java.util.Vector;

/**
 * 这个类的目的是为在看书翻页时，需要进行的动作提供接口。
 * 包括翻向下一页，翻向上一页。在翻到每章最后一页时，如果后面还有章节就继续翻向下一章节，没有就向用户显示已读完。
 * 在翻向上一章节时，如果前面还有章节，就翻到上一章节，没有就向用户显示，这已经是第一章节。
 *
 * 在直觉上认为这个应该只设置成一个接口，因为只需向视图层提供动作接口，也就是本类应属于模型层。则其设置为一个借口可能也合适。
 * 但是如果设置成一个接口，那么接口的实现类，有多个都要保存的数据。那么为了代码重用，抽象类可能比接口更加合适。 上面是个人分析，可能不是很合适。
 *
 * @author MJZ
 *
 */
public class BookPage {
    // configuration information
    private int screenWidth; // 屏幕宽度
    private int screenHeight; // 屏幕高度
    private int fontSize; // 字体大小
    private int lineHgight;	//每行的高度
    private int marginWidth = 15; // 左右与边缘的距离
    private int marginHeight = 15; // 上下与边缘的距离
    private int textColor; // 字体颜色
    private Bitmap bgBitmap; // 背景图片
    private int bgColor; // 背景颜色

    private Paint paint;
    private Paint paintBottom;
    private int visibleWidth; // 屏幕中可显示文本的宽度
    private int visibleHeight;
    private Chapter chapter; // 需要处理的章节对象
    private Vector<String> linesVe; // 将章节內容分成行，并将每页按行存储到vector对象中
    private int lineCount; // 一个章节在当前配置下一共有多少行

    private String content;
    private int chapterLen; // 章节的长度
    // private int curCharPos; // 当前字符在章节中所在位置
    private int charBegin; // 每一页第一个字符在章节中的位置
    private int charEnd; // 每一页最后一个字符在章节中的位置
    private boolean isfirstPage;
    private boolean islastPage;

    private Vector<Vector<String>> pagesVe;
    int pageNum;

    /**
     * 在新建一个BookPage对象时，需要向其提供数据，以支持屏幕翻页功能。
     *
     * @param screenWidth
     *            屏幕宽度，用来计算每行显示多少字
     * @param screenHeight
     *            屏幕高度，用来计算每页显示多少行
     * @param chapter
     *            章节对象
     */
    public BookPage(int screenWidth, int screenHeight, Chapter chapter) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.chapter = chapter;
        init();
    }

    /**
     * 初始最好按照定义变量的顺序来初始化，统一。在将来需要修改某个变量的时候，容易找到。 对代码维护应该也很有用吧。
     */
    protected void init() {
        bgBitmap = null;
        bgColor = 0xfff2f1f0;
        textColor = Color.BLACK;
        content = chapter.getContent();//得到输入流
        chapterLen = content.length();//章节长度
        // curCharPos = 0;
        charBegin = 0;
        charEnd = 0;
        fontSize = 30;
        lineHgight = fontSize + 8;//行高
        linesVe = new Vector<String>();//

        // 实例化画笔并打开抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//绘画，重写paint。Paint.ANTI_ALIAS_FLAG是使位图抗锯齿的标志
        paint.setTextAlign(Align.LEFT);//设置文本的对齐方式，可供选的方式有三种：CENTER,LEFT和RIGHT。
        paint.setTextSize(fontSize);
        paint.setColor(textColor);

        paintBottom = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBottom.setTextAlign(Align.LEFT);
        paintBottom.setTextSize(fontSize / 2);
        paintBottom.setColor(textColor);

        visibleWidth = screenWidth - marginWidth * 2;  // 屏幕中可显示文本的宽度
        visibleHeight = screenHeight - marginHeight * 2;
        lineCount = visibleHeight / lineHgight - 2;
        isfirstPage = true;
        islastPage = false;
        pagesVe = new Vector<Vector<String>>();
        pageNum = -1;
        slicePage();
    }

    public Vector<String> getCurPage() {
        return linesVe;
    }

    protected void slicePage() {
        pagesVe.clear();
        int curPos = 0;
        while (curPos < chapterLen) {
            Vector<String> lines = new Vector<String>();
            charBegin = curPos;//当前页面第一个字符所在章节中位置
            while (lines.size() < lineCount && curPos < chapterLen) {

                //返回字符中indexof（string）中字串string在父串中首次出现的位置，从0开始！没有返回-1；方便判断和截取字符串！
                //stringObject.indexOf(searchvalue,fromindex);该方法将从头到尾地检索字符串 stringObject，看它是否含有子串 searchvalue。
                //开始检索的位置在字符串的 fromindex 处或字符串的开头（没有指定 fromindex 时）。如果找到一个 searchvalue，则返回 searchvalue 的
                //第一次出现的位置。stringObject 中的字符位置是从 0 开始的。
                //eg：String str="a2dfcfar1bzvb";System.out.println(str.indexOf(97,2));看这个例子，输出：6

                //a的ASCII为97，就从d开始找a找到了输出a所在字符串的确切位置，找不到就输出-1！（java中位置第一个从0开始）

                int  i = content.indexOf("\n", curPos); //从curPos开始查找“\n”，返回所在位置

                //substring这个函数返回第一个参数中从第二个参数指定的位置开始、第三个参数指定的长度的子字符串。
                //该字符串中的每个字符都被认为具有数字位置：第一个字符的位置是 1，第二个字符的位置是 2，依此类推。
                //如果未指定第三个参数，将返回从第二个参数指定的位置开始直到字符串结尾的子字符串。
                //如：以下函数调用返回“234”： substring("12345",2,3)

                String paragraphStr = content.substring(curPos, i);//返回一段文字字符串
                // curCharPos += i;
                if (curPos == i)
                    lines.add("");

                while (paragraphStr.length() > 0) {
                    //public int breakText(String text,  boolean measureForwards,float maxWidth, float[] measuredWidth)
                    //测量的文本,早期如果测量宽度超过maxWidth停止。返回的字符数量来衡量,如果measuredWidth不是null,返回实际宽度测量。
                    int horSize = paint.breakText(paragraphStr, true,
                            visibleWidth, null);
                    lines.add(paragraphStr.substring(0, horSize));
                    paragraphStr = paragraphStr.substring(horSize);
                    curPos += horSize;
                    if (lines.size() > lineCount)
                        break;
                }
                // 如果是把一整段读取完的话，需要给当前位置加1
                if (paragraphStr.length() == 0)
                    curPos += "\n".length();
            }
            pagesVe.add(lines);
        }

    }

    /**
     * 翻到下一页
     */
    public boolean nextPage() {
        if (isLastPage()) {
            if (!nextChapter()) // 如果已经到本书末尾，那么不能继续执行翻页代码
                return false;
        }
		/*
		 * Vector<String> lines = new Vector<String>(); charBegin = charEnd;
		 * while (lines.size() < lineCount && charEnd < chapterLen) { int i =
		 * content.indexOf("\n", charEnd); String paragraphStr =
		 * content.substring(charEnd, i); // curCharPos += i; if (charEnd == i)
		 * lines.add("");
		 *
		 * while (paragraphStr.length() > 0) { int horSize =
		 * paint.breakText(paragraphStr, true, visibleWidth, null);
		 * lines.add(paragraphStr.substring(0, horSize)); paragraphStr =
		 * paragraphStr.substring(horSize); charEnd += horSize; if (lines.size()
		 * > lineCount) break; } // 如果是把一整段读取完的话，需要给当前位置加1 if
		 * (paragraphStr.length() == 0) charEnd += "\n".length(); } linesVe =
		 * lines;
		 */
        linesVe = pagesVe.get(++pageNum);
        return true;
    }

    /**
     * 翻到上一页
     */
    public boolean prePage() {
        if (isFirstPage()) {
            if (!preChapter()) // 如果已经到本书第一章，就不能继续执行翻页代码
                return false;
        }
		/*
		 * Vector<String> lines = new Vector<String>(); String backStr =
		 * content.substring(0, charBegin); charEnd = charBegin;
		 *
		 * while (lines.size() < lineCount && charBegin > 0) { int i =
		 * backStr.lastIndexOf("\n"); if(i == -1) i = 0; String paragraphStr =
		 * backStr.substring(i, charBegin); Vector<String> vet = new
		 * Vector<String>(lines);
		 *
		 * // if(charBegin == i)lines.add("");
		 *
		 * while (paragraphStr.length() > 0) { int horSize =
		 * paint.breakText(paragraphStr, true, visibleWidth, null);
		 * lines.add(paragraphStr.substring(0, horSize)); paragraphStr =
		 * paragraphStr.substring(horSize); charBegin -= horSize; if
		 * (lines.size() > lineCount) break; }
		 *
		 * backStr = content.substring(0, charBegin); int j = -1; for (String
		 * line : vet) lines.insertElementAt(line, ++j); } linesVe = lines;
		 */
        linesVe = pagesVe.get(--pageNum);
        return true;
    }

    /**
     * 跳到下一章，若返回值为false，则当前章节已经为最后一章
     */
    public boolean nextChapter() {
        int order = chapter.getOrder();//章节顺序
        Chapter tempChapter = IOHelper.getChapter(order + 1);
        if (tempChapter == null)
            return false;
        chapter = tempChapter;
        content = chapter.getContent();
        chapterLen = content.length();
        // curCharPos = 0;
        charBegin = 0;
        charEnd = 0;
        slicePage();
        pageNum = -1;
        return true;
    }

    /**
     * 跳到上一章,若返回值为false，则当前章节已经为第一章
     */
    public boolean preChapter() {
        int order = chapter.getOrder();
        Chapter tempChapter = IOHelper.getChapter(order - 1);
        if (tempChapter == null)
            return false;
        chapter = tempChapter;
        content = chapter.getContent();
        chapterLen = content.length();
        // curCharPos = chapterLen;
        charBegin = chapterLen;
        charEnd = chapterLen;
        slicePage();
        pageNum = pagesVe.size();
        return true;
    }

    public boolean isFirstPage() {
        if (pageNum <= 0)
            return true;
        return false;
    }

    public boolean isLastPage() {
        if (pageNum >= pagesVe.size() - 1)
            return true;
        return false;
    }

    public void draw(Canvas c) {		//在Android中既然把Canvas当做画布
        if (linesVe.size() == 0)
            nextPage();
        if (linesVe.size() > 0) {
            if (bgBitmap == null)
                c.drawColor(bgColor);
            else
                c.drawBitmap(bgBitmap, 0, 0, null);

            int y = marginHeight;
            for (String line : linesVe) {
                y += lineHgight;
                c.drawText(line, marginWidth, y, paint);
            }
        }

        // float percent = (float) (charBegin * 1.0 / chapterLen);
        float percent = (float) ((pageNum + 1) * 1.0 / pagesVe.size());
        DecimalFormat df = new DecimalFormat("#0.0");
        String percetStr = df.format(percent * 100) + "%";

        Time time = new Time();
        time.setToNow();
        String timeStr;
        if (time.minute < 10)
            timeStr = "" + time.hour + " : 0" + time.minute;
        else
            timeStr = "" + time.hour + " : " + time.minute;

        int pSWidth = (int) paintBottom.measureText("99.9%") + 2;
        int titWidth = (int) paintBottom.measureText(chapter.getTitle());


        c.drawText(timeStr, marginWidth / 2, screenHeight - 5, paintBottom);
        c.drawText(chapter.getTitle(), screenWidth / 2 - titWidth / 2,
                screenHeight - 5, paintBottom);
        c.drawText(percetStr, screenWidth - pSWidth, screenHeight - 5, paintBottom);
    }

    public void setBgBitmap(Bitmap bMap) {
        bgBitmap = Bitmap.createScaledBitmap(bMap, screenWidth, screenHeight,
                true);//按比例压缩图片
    }

}

