package com.jqs.example.beans;


import java.io.Serializable;

/**
 * Created by qiangsheng on 2016/5/31.
 */
public class NovelsChapter implements Serializable {
    private String bookId;
    private String cid;
    private String cname;

    public NovelsChapter(String bookId, String cid, String cname) {
        this.bookId = bookId;
        this.cid = cid;
        this.cname = cname;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
