package com.example.notev2;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Random;

public class Note implements Parcelable {
    String title;
    String description;
    long createdTime;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public Note(String title, String description, int id){
        this.title = title;
        this.id = id;
        this.description = description;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public int getId(){
        return  id;
    }

    // Parcelable implementation
    protected Note(Parcel in) {
        title = in.readString();
        description = in.readString();
        createdTime = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(createdTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
