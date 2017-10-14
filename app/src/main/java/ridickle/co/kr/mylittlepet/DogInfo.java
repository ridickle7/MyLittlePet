package ridickle.co.kr.mylittlepet;

/**
 * Created by ridickle on 2017. 6. 3..
 */

public class DogInfo {
    String name;    // 이름
    String address; // 주소
    int gender;     // 성 (0:암컷 / 1:수컷)
    String imgUrl;  // 강아지 이미지 URL
    int isGood;     // 좋아요 유무 (좋아요 안했을 시 0 / 했을 시 1)
    int distance;   // 거리

    public DogInfo(String name, String address, int gender, String imgUrl, int isGood, int distance) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.isGood = isGood;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIsGood() {
        return isGood;
    }

    public void setIsGood(int isGood) {
        this.isGood = isGood;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
