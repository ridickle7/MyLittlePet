package yapp.co.kr.mlp.Item_item;

import android.graphics.drawable.Drawable;

/**
 * Created by hayoung on 2015-08-29.
 */
public class ListItem02 {
    private Drawable detailimage;
    private String nickname;

    public ListItem02(Drawable detailimage, String nickname){
        this.detailimage = detailimage;
        this.nickname = nickname;
    }

    public Drawable getDetailimage(){
        return  detailimage;
    }

    public void setDetailimage(Drawable detailimage){
        this.detailimage = detailimage;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(){
        this.nickname = nickname;
    }
}


