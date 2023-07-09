// Bookmark.java
package org.example.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bookmark {
    private int histNo;
    private String mainNm;
    private String name;
    private Date createDate;

    public Bookmark(int histNo, String mainNm, String name, Date createDate) {
        this.histNo = histNo;
        this.mainNm = mainNm;
        this.name = name;
        this.createDate = createDate;
    }

    public int getHistNo() {
        return histNo;
    }

    public void setHistNo(int histNo) {
        this.histNo = histNo;
    }

    public String getMainNm() {
        return mainNm;
    }

    public void setMainNm(String mainNm) {
        this.mainNm = mainNm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
