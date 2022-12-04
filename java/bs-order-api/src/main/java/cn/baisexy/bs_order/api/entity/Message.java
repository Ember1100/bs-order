package cn.baisexy.bs_order.api.entity;



import java.io.Serializable;

public class Message implements Serializable {
    private Integer mId;

    private Integer mUId;

    private String mContent;

    private String mDate;

    private Integer mFId;

    private static final long serialVersionUID = 1L;

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Integer getmUId() {
        return mUId;
    }

    public void setmUId(Integer mUId) {
        this.mUId = mUId;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate == null ? null : mDate.trim();
    }

    public Integer getmFId() {
        return mFId;
    }

    public void setmFId(Integer mFId) {
        this.mFId = mFId;
    }
}