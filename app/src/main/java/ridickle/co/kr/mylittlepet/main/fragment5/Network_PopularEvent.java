package ridickle.co.kr.mylittlepet.main.fragment5;

/**
 * Created by ridickle on 2017. 10. 3..
 */

class Network_PopularEvent {
    Boolean IsEnded;
    String ImageURL;
    String Title;
    String Term;

    public Network_PopularEvent(Boolean isEnded, String imageURL, String title, String term) {
        IsEnded = isEnded;
        ImageURL = imageURL;
        Title = title;
        Term = term;
    }

    public Boolean getEnded() {
        return IsEnded;
    }

    public void setEnded(Boolean ended) {
        IsEnded = ended;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }
}
