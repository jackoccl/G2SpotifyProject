package Connectors;

public class images {
    int height;
    int width;
    String URL;

    public images(int h, int w,String url){
        height=h;
        width=w;
        URL=url;
    }

    public String getURL() {
        return URL;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
