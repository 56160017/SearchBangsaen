package com.buu.se.searchbangsaen.add_categories.dao;

/**
 * Created by Dell on 26/04/2560.
 */

public class RelaxDao {
    private boolean Swim; //สระว่ายน้ำ
    private boolean Fitness; //
    private boolean Playground; //
    private boolean Golf;  //

    public boolean isSwim() {
        return Swim;
    }

    public void setSwim(boolean swim) {
        Swim = swim;
    }

    public boolean isFitness() {
        return Fitness;
    }

    public void setFitness(boolean fitness) {
        Fitness = fitness;
    }

    public boolean isPlayground() {
        return Playground;
    }

    public void setPlayground(boolean playground) {
        Playground = playground;
    }

    public boolean isGolf() {
        return Golf;
    }

    public void setGolf(boolean golf) {
        Golf = golf;
    }
}
