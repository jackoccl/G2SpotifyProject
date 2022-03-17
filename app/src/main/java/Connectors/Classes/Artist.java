package Connectors.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Artist implements Parcelable {
    public Followers followers ;
    public String href ;
    public String id ;
    public String name ;
    public ArrayList<Connectors.Classes.images> images;
    public int popularity ;


    public Artist(Followers f, String HREF,String ID,String Name,int Pop,ArrayList<images> img){
        followers = f;
        href = HREF;
        id = ID;
        name = Name;
        popularity = Pop;
        images = img;
    }

    protected Artist(Parcel in) {
        href = in.readString();
        id = in.readString();
        name = in.readString();
        popularity = in.readInt();
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public int getFollowers() {
        return followers.total;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public ArrayList<Connectors.Classes.images> getImages(){
        return images;
    }



    public void setImages(ArrayList<images> list){
        images = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(href);
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeInt(popularity);
    }
}
