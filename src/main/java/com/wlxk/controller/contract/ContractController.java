package com.wlxk.controller.contract;

import com.wlxk.controller.contract.vo.AddContractVo;
import com.wlxk.controller.contract.vo.DisuseContractVo;
import com.wlxk.controller.contract.vo.ReviewContractVo;
import com.wlxk.controller.contract.vo.UpdateContractVo;
import com.wlxk.service.contract.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by malin on 2016/7/26.
 */
@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired(required = false)
    private ContractService contractService;

    /**
     * 新增合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(@RequestBody AddContractVo vo) {
        try {
            return contractService.add(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 审核合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public Map review(@RequestBody ReviewContractVo vo) {
        try {
            return contractService.review(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 作废合同
     *
     * @param vo
     * @return
     */
    public Map disuse(@RequestBody DisuseContractVo vo) {
        try {
            return contractService.disuse(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改合同
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestBody UpdateContractVo vo) {
        try {
            return contractService.update(vo);
        } catch (Exception e) {
            throw e;
        }
    }
}
