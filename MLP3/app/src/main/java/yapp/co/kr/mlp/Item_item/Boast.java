package yapp.co.kr.mlp.Item_item;

import android.graphics.drawable.Drawable;

/**
 * Created by hayoung on 2015-08-29.
 */
public class Boast {
    private Drawable firstrank;
    private String mission;
    private String term;
    private Drawable progressing;


    public Boast(Drawable firstrank, String mission, String term, Drawable progressing) {
        this.firstrank = firstrank;
        this.mission = mission;
        this.term = term;
        this.progressing = progressing;
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Drawable getProgressing() {
        return progressing;
    }

    public void setProgressing(Drawable progressing) {
        this.progressing = progressing;
    }

    public Drawable getFirstrank() {
        return firstrank;
    }

    public void setFirstrank(Drawable firstrank) {
        this.firstrank = firstrank;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
}
