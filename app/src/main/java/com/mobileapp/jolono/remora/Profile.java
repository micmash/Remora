package com.mobileapp.jolono.remora;

/**
 * TODO: Move to separate package for organization.
 * Profile is public information about a user.
 * Account contains a profile but we don't want to pull account information if not user.
 *
 * Profile cannot directly update itself on database as the only person who can is the holder
 * of the account. i.e. account can be updated with new profile, profile cannot update itself.
 *
 * Created by Noah on 3/21/2015.
 */
public class Profile {
    public int mId;
    public String mName;
    public int mAge;
    public String mGender;
    public String mDescription;

    private Profile(int id, String name, int age, String gender, String description) {
        mId = id;
        mName = name;
        mAge = age;
        mGender = gender;
        mDescription = description;
    }

    public static Profile getSelectedProfile(int id) {
        //if profile with id in cache, get profile from cache and return.
        //else connect to database with separate thread, retrieve profile data, create new
        //profile instance and return.
        Profile profile = new Profile(id, "Noah Torrance", 23, "male", "I'm a pretty cool guy.");

        return profile;
    }
}
