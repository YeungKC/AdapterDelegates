package com.yeungkc.itemdelegate.sample.bean;

public class Post {

    private final int mBgId;
    private final String mContent;

    public Post(int bgId, String content) {
        mBgId = bgId;
        mContent =  content;
    }

    public int getBgId() {
        return mBgId;
    }

    public String getContent() {
        return mContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (mBgId != post.mBgId) return false;
        return mContent != null ? mContent.equals(post.mContent) : post.mContent == null;

    }

    @Override
    public int hashCode() {
        int result = mBgId;
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        return result;
    }
}
