package Connectors.Classes;

public class images {
    int height;
    int width;
    String url;

    public images(int h, int w,String URL){
        height=h;
        width=w;
        url=URL;
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public void setUrl(String URL) {
        this.url = URL;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }
}
