package com.example.rentamate;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_RATING = "rating";
    public static final String KEY_NUM_OF_RATES = "numOfRates";
    public static final String KEY_SELECTED_BY = "selectedBy";
    public static final String KEY_CREATED_KEY = "createdAt";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public int getRating(){
        return getInt(KEY_RATING);
    }

    public void setRating(int rating){
        put(KEY_RATING, rating);
    }

    public int getNumOfRates(){
        return getInt(KEY_NUM_OF_RATES);
    }

    public void setNumOfRates(int numOfRates){
        put(KEY_NUM_OF_RATES, numOfRates);
    }

    public ParseUser getSelectedBy(){
        return getParseUser(KEY_SELECTED_BY);
    }

    public void setSelectedBy(ParseUser user){
        put(KEY_SELECTED_BY, user);
    }

}

