package com.piter.multipledatasources.controller;

import com.chinaums.commons.RestResp;
import com.chinaums.commons.utils.RestRespUtil;
import com.piter.multipledatasources.service.CcsCardBinRepairService;
import com.piter.multipledatasources.service.LoanUserBankCardDisposeService;
import com.piter.multipledatasources.service.RepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @time 2019/10/21 17:44
 */
@Slf4j
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private LoanUserBankCardDisposeService loanUserBankCardDisposeService;

    @Autowired
    private CcsCardBinRepairService ccsCardBinRepairService;

    @GetMapping("/test")
    public String testTransaction() throws Exception {
        return repairService.testTransaction();
    }

    @GetMapping("/idCard")
    public String idCard(){
        return repairService.idCard();
    }

    @RequestMapping("/cardBin")
    public RestResp cardBin() {
        long startTime = System.currentTimeMillis();
        loanUserBankCardDisposeService.dispose();
        log.info("银行卡bin清洗耗时:{}秒",(System.currentTimeMillis() - startTime)/1000);
        return RestRespUtil.success();
    }

    @RequestMapping("/ccsCardBin")
    public RestResp ccsCardBin() {
        long startTime = System.currentTimeMillis();
        ccsCardBinRepairService.dispose();
        log.info("银行卡bin清洗耗时:{}秒",(System.currentTimeMillis() - startTime)/1000);
        return RestRespUtil.success();
    }
}
