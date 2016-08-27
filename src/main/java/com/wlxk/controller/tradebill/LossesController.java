package com.wlxk.controller.tradebill;

import com.wlxk.service.tradebill.LossesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 马林 on 2016/8/27.
 */

@RestController
@RequestMapping("/losses")
public class LossesController {
    private static final Logger logger = LoggerFactory.getLogger(LossesController.class);
    @Autowired
    private LossesService lossesService;

    @RequestMapping(value = "/pageView", method = RequestMethod.POST)
    public Map pageView() {
        return null;
    }
}
