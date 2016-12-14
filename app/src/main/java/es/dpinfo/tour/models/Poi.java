package es.dpinfo.tour.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dprimenko on 12/12/16.
 */

public class Poi implements Parcelable {

    // Point of interest

    private int mId;
    private String mName;
    private String mCategory;
    private String mDescription;
    private float mRating;

    protected Poi(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mCategory = in.readString();
        mDescription = in.readString();
        mRating = in.readFloat();
    }

    public static final Creator<Poi> CREATOR = new Creator<Poi>() {
        @Override
        public Poi createFromParcel(Parcel in) {
            return new Poi(in);
        }

        @Override
        public Poi[] newArray(int size) {
            return new Poi[size];
        }
    };

    public int getmId() {
        return mId;
    }
    public void setmId(int mId) {
        this.mId = mId;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmDescription() {
        return mDescription;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public String getmCategory() {
        return mCategory;
    }
    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }
    public float getmRating() {
        return mRating;
    }
    public void setmRating(int mRating) {
        this.mRating = mRating;
    }


    public Poi(int mId, String mName, String mCategory, String mDescription, float mRating) {
        this.mId = mId;
        this.mName = mName;
        this.mCategory = mCategory;
        this.mDescription = mDescription;
        this.mRating = mRating;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof Poi) {
            Poi poi = (Poi) o;

            if (this.mId == poi.getmId()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "mId : '" + mId + '\'' +
                ",mName : '" + mName + '\'' +
                ", mCategory : '" + mCategory + '\'' +
                ", mDescription : '" + mDescription + '\'' +
                ", mRating : " + mRating +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mCategory);
        dest.writeString(mDescription);
        dest.writeFloat(mRating);
    }
}
