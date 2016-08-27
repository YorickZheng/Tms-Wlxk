package com.wlxk.controller.tradebill.vo;

import com.wlxk.domain.tradebill.Losses;

/**
 * Created by 马林 on 2016/8/27.
 */
public class LossesView {
    private Losses losses;

    public static LossesView newInstance(Losses losses) {
        LossesView view = new LossesView();
        view.setLosses(losses);
        return view;
    }

    public Losses getLosses() {
        return losses;
    }

    public void setLosses(Losses losses) {
        this.losses = losses;
    }
}
