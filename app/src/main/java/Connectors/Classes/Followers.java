package Connectors.Classes;

public class Followers {
    public String href;
    public int total;

    public Followers(String Href, int Tot){
        href = Href;
        total = Tot;
    }

    public void setHref(String href) {
        href = href;
    }
    public void setTotal(int total){
        total = total;
    }

    public int getTotal() {
        return total;
    }

    public String getHref() {
        return href;
    }
}
