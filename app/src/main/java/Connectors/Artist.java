package Connectors;

import java.util.ArrayList;

public class Artist {
    public Followers followers ;
    public String href ;
    public String id ;
    public String name ;
    public ArrayList<images> images;
    public int popularity ;


    public Artist(Followers f, String HREF,String ID,String Name,int Pop,ArrayList<images> img){
        followers = f;
        href = HREF;
        id = ID;
        name = Name;
        popularity = Pop;
        images = img;
    }

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

    public ArrayList<Connectors.images> getImages(){
        return images;
    }



    public void setImages(ArrayList<images> list){
        images = list;
    }
}
