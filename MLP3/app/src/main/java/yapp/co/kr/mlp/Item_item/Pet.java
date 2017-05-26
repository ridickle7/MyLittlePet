package yapp.co.kr.mlp.Item_item;

/**
 * Created by home on 2015-08-28.
 */
public class Pet {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public String getIntroduction_write() {
        return introduction_write;
    }

    public void setIntroduction_write(String introduction_write) {
        this.introduction_write = introduction_write;
    }

    private int id;
    private String name;
    private String gender;
    private String birthday;
    private String specific;
    private String introduction_write;

}
