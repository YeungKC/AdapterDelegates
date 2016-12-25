package com.yeungkc.itemdelegate.sample.bean;

public class Episode {

    private final String mName;
    private boolean mSelected;

    public Episode(String name) {
        mName = name;
    }

    public String getNum() {
        return mName;
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

        if (mSelected != episode.mSelected) return false;
        return mName != null ? mName.equals(episode.mName) : episode.mName == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSelected ? 1 : 0);
        return result;
    }
}
