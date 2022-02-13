package Connectors;

public class Artist {
    public Followers followers ;
    public String href ;
    public String id ;
    public String name ;
    public int popularity ;


    public Artist(Followers f, String HREF,String ID,String Name,int Pop){
        followers = f;
        href = HREF;
        id = ID;
        name = Name;
        popularity = Pop;
    }

    public Followers getFollowers() {
        return followers;
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
}
