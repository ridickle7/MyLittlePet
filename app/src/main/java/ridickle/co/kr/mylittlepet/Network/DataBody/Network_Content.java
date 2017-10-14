package ridickle.co.kr.mylittlepet.Network.DataBody;

/**
 * Created by ridickle on 2017. 10. 10..
 */

public class Network_Content {
    String _id;
    String cOwner;
    String cEid;
    long cDate;
    int cType;
    String cText;
    String cImageURL;
    String[] cTagList;
    String[] cGood;

    public Network_Content(String _id, String cOwner, String cEid, long cDate, int cType, String cText, String[] cTagList, String[] cGood, String cImageURL) {
        this._id = _id;
        this.cOwner = cOwner;
        this.cEid = cEid;
        this.cDate = cDate;
        this.cType = cType;
        this.cText = cText;
        this.cTagList = cTagList;
        this.cGood = cGood;
        this.cImageURL = cImageURL;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getcOwner() {
        return cOwner;
    }

    public void setcOwner(String cOwner) {
        this.cOwner = cOwner;
    }

    public String getcEid() {
        return cEid;
    }

    public void setcEid(String cEid) {
        this.cEid = cEid;
    }

    public long getcDate() {
        return cDate;
    }

    public void setcDate(long cDate) {
        this.cDate = cDate;
    }

    public int getcType() {
        return cType;
    }

    public void setcType(int cType) {
        this.cType = cType;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public String[] getcTagList() {
        return cTagList;
    }

    public void setcTagList(String[] cTagList) {
        this.cTagList = cTagList;
    }

    public String[] getcGood() {
        return cGood;
    }

    public void setcGood(String[] cGood) {
        this.cGood = cGood;
    }

    public String getcImageURL() {
        return cImageURL;
    }

    public void setcImageURL(String cImageURL) {
        this.cImageURL = cImageURL;
    }
}
