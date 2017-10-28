package usc.ict311.adamlobegeier.myactivities.user;

import java.util.UUID;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class User {
    private UUID mId;
    private int mGender;
    private String mWholeName;
    private String mEmail;
    private String mComments;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID id) {
        mId = id;

    }

    public UUID getId() {
        return mId;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public String getWholeName() {
        return mWholeName;
    }

    public void setWholeName(String wholeName) {
        mWholeName = wholeName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comment) {
        mComments = comment;
    }
}

