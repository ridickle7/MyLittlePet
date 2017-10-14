package ridickle.co.kr.mylittlepet.main.fragment1.fragment1_4;

/**
 * Created by ridickle on 2017. 10. 3..
 */

class Network_Popular{
    String logoImageURL;
    String title;
    String name;

    public Network_Popular(String logoImageURL, String title, String name) {
        this.logoImageURL = logoImageURL;
        this.title = title;
        this.name = name;
    }

    public String getLogoImageURL() {
        return logoImageURL;
    }

    public void setLogoImageURL(String logoImageURL) {
        this.logoImageURL = logoImageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
