package Connectors;

public class Followers {
    public String Href;
    public int Total;

    public Followers(String href, int tot){
        Href = href;
        Total = tot;
    }

    public void setHref(String href) {
        Href = href;
    }
    public void setTotal(int total){
        Total = total;
    }

    public int getTotal() {
        return Total;
    }

    public String getHref() {
        return Href;
    }
}
