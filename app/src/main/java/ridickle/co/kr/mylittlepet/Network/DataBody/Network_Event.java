package ridickle.co.kr.mylittlepet.Network.DataBody;

/**
 * Created by ridickle on 2017. 10. 12..
 */

public class Network_Event {
    String _id;
    String eName;
    String eIntroduce;
    String eImageURL;
    long eStartDate;
    long eEndDate;
    String[] eAward;
    String[] eContentList;

    public Network_Event(String _id, String eName, String eIntroduce, String eImageURL, long eStartDate, long eEndDate, String[] eAward, String[] eContentList) {
        this._id = _id;
        this.eName = eName;
        this.eIntroduce = eIntroduce;
        this.eImageURL = eImageURL;
        this.eStartDate = eStartDate;
        this.eEndDate = eEndDate;
        this.eAward = eAward;
        this.eContentList = eContentList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteIntroduce() {
        return eIntroduce;
    }

    public void seteIntroduce(String eIntroduce) {
        this.eIntroduce = eIntroduce;
    }

    public String geteImageURL() {
        return eImageURL;
    }

    public void seteImageURL(String eImageURL) {
        this.eImageURL = eImageURL;
    }

    public long geteStartDate() {
        return eStartDate;
    }

    public void seteStartDate(long eStartDate) {
        this.eStartDate = eStartDate;
    }

    public long geteEndDate() {
        return eEndDate;
    }

    public void seteEndDate(long eEndDate) {
        this.eEndDate = eEndDate;
    }

    public String[] geteAward() {
        return eAward;
    }

    public void seteAward(String[] eAward) {
        this.eAward = eAward;
    }

    public String[] geteContentList() {
        return eContentList;
    }

    public void seteContentList(String[] eContentList) {
        this.eContentList = eContentList;
    }
}
