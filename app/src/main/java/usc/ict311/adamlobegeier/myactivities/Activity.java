package usc.ict311.adamlobegeier.myactivities;

import java.util.Date;
import java.util.UUID;

public class Activity {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mPlace;
    private String mDuration;
    private int mType;
    private String mComment;


    public Activity() {
        this(UUID.randomUUID());
    }

    public Activity(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getPlace() { return mPlace; }

    public void setPlace(String place) {
        mPlace = place;
    }

    public String getDuration() { return mDuration; }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public String getComments() {
        return mComment;
    }

    public void setComments(String comment) {
        mComment = comment;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

}

