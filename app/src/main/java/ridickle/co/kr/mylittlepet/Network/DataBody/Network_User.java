package ridickle.co.kr.mylittlepet.Network.DataBody;

import java.util.ArrayList;

/**
 * Created by ridickle on 2017. 10. 8..
 */

public class Network_User {
    String _id;
    String uTokenId;
    String uNickname;
    String uImageURL;
    String uIntroduce;
    Boolean uGender;
    int uAge;
    String uSpecify;
    int uWeight;
    String uAddress;
    float uLatitude;
    float uLongitude;
    ArrayList<String> uFollowingList;
    ArrayList<String> uFollowerList;
    ArrayList<String> uTagList;

    public Network_User(String _id, String uTokenId, String uNickname, String uImageURL, String uIntroduce, Boolean uGender, int uAge, String uSpecify, int uWeight, String uAddress, float uLatitude, float uLongitude, ArrayList<String> uFollowingList, ArrayList<String> uFollowerList, ArrayList<String> uTagList) {
        this._id = _id;
        this.uTokenId = uTokenId;
        this.uNickname = uNickname;
        this.uImageURL = uImageURL;
        this.uIntroduce = uIntroduce;
        this.uGender = uGender;
        this.uAge = uAge;
        this.uSpecify = uSpecify;
        this.uWeight = uWeight;
        this.uAddress = uAddress;
        this.uLatitude = uLatitude;
        this.uLongitude = uLongitude;
        this.uFollowingList = uFollowingList;
        this.uFollowerList = uFollowerList;
        this.uTagList = uTagList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getuTokenId() {
        return uTokenId;
    }

    public void setuTokenId(String uTokenId) {
        this.uTokenId = uTokenId;
    }

    public String getuNickname() {
        return uNickname;
    }

    public void setuNickname(String uNickname) {
        this.uNickname = uNickname;
    }

    public String getuImageURL() {
        return uImageURL;
    }

    public void setuImageURL(String uImageURL) {
        this.uImageURL = uImageURL;
    }

    public String getuIntroduce() {
        return uIntroduce;
    }

    public void setuIntroduce(String uIntroduce) {
        this.uIntroduce = uIntroduce;
    }

    public Boolean getuGender() {
        return uGender;
    }

    public void setuGender(Boolean uGender) {
        this.uGender = uGender;
    }

    public int getuAge() {
        return uAge;
    }

    public void setuAge(int uAge) {
        this.uAge = uAge;
    }

    public String getuSpecify() {
        return uSpecify;
    }

    public void setuSpecify(String uSpecify) {
        this.uSpecify = uSpecify;
    }

    public int getuWeight() {
        return uWeight;
    }

    public void setuWeight(int uWeight) {
        this.uWeight = uWeight;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public float getuLatitude() {
        return uLatitude;
    }

    public void setuLatitude(float uLatitude) {
        this.uLatitude = uLatitude;
    }

    public float getuLongitude() {
        return uLongitude;
    }

    public void setuLongitude(float uLongitude) {
        this.uLongitude = uLongitude;
    }

    public ArrayList<String> getuFollowingList() {
        return uFollowingList;
    }

    public void setuFollowingList(ArrayList<String> uFollowingList) {
        this.uFollowingList = uFollowingList;
    }

    public ArrayList<String> getuFollowerList() {
        return uFollowerList;
    }

    public void setuFollowerList(ArrayList<String> uFollowerList) {
        this.uFollowerList = uFollowerList;
    }

    public ArrayList<String> getuTagList() {
        return uTagList;
    }

    public void setuTagList(ArrayList<String> uTagList) {
        this.uTagList = uTagList;
    }
}
