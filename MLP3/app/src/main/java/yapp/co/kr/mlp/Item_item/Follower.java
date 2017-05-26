package yapp.co.kr.mlp.Item_item;

import android.graphics.drawable.Drawable;

/**
 * Created by home on 2015-09-05.
 */
public class Follower {
    private Drawable profile;
    private String nickname;
    private boolean toggle_clicked;

    public Follower(Drawable profile, String nickname, boolean toggle_clicked) {
        this.profile = profile;
        this.nickname = nickname;
        this.toggle_clicked = false;
    }

    public Drawable getProfile() {
        return profile;
    }

    public void setProfile(Drawable profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isToggle_clicked() {
        return toggle_clicked;
    }
}
