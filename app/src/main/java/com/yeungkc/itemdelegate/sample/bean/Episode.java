package com.yeungkc.itemdelegate.sample.bean;

public class Episode {

    private final int mNum;
    private boolean mSelected;

    public Episode(int num) {
        mNum = num;
    }

    public int getNum() {
        return mNum;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (mNum != episode.mNum) return false;
        return mSelected == episode.mSelected;

    }

    @Override
    public int hashCode() {
        int result = mNum;
        result = 31 * result + (mSelected ? 1 : 0);
        return result;
    }
}
