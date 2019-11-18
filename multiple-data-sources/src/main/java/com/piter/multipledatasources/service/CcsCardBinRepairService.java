package com.piter.multipledatasources.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piter.multipledatasources.entity.ccs.CustBankCardRepair;
import com.piter.multipledatasources.mapper.ccs.CustBankCardRepairMapper;
import com.piter.multipledatasources.mapper.loan.SettingBankCardBinMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ccs银行卡bin信息修护
 * @author Piter
 */
@Service
@Slf4j
public class CcsCardBinRepairService extends ServiceImpl<CustBankCardRepairMapper, CustBankCardRepair> {

    @Autowired
    private SettingBankCardBinMapper settingBankCardBinMapper;

    //获取所有的卡bin信息
    private List<Map<String, Object>> cardBinList;

    //第一次清洗数据失败集合
    private List<CustBankCardRepair> bankCardList = new LinkedList<>();
    //第二次清洗数据失败集合
    private LinkedList<CustBankCardRepair> errorList = new LinkedList<>();


    public void dispose() {
        cardBinList = settingBankCardBinMapper.selectAll();
        //获取所有的LOAN_USER_BANK_CARD
        List<CustBankCardRepair> list = this.list();
        log.info("共查询到{}条CUST_BANK_CARD数据", list.size());
        int sum = 0;
        for (Iterator<CustBankCardRepair> iterator = list.iterator(); iterator.hasNext(); ){
            CustBankCardRepair custBankCard = null;
            try {
                custBankCard = iterator.next();
                String bankCode = getBankCode(custBankCard.getCardNo());
                if (bankCode == null) {
                    log.error("银行卡错误,卡bin识别不存在:ID={} ,cardNo={}", custBankCard.getId(), custBankCard.getCardNo());
                    bankCardList.add(custBankCard);
                    iterator.remove();
                } else if (bankCode.equals(custBankCard.getBankCode())) {
                    iterator.remove();
                } else {
                    custBankCard.setBankCode(bankCode);
                }
                sum++;
                if (sum % 10000 == 0) {
                    log.info("已更新银行卡list中{}条数据", sum);
                }
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                bankCardList.add(custBankCard);
                iterator.remove();
            }
        }
        log.info("更新银行卡list----------完成---------实际需要更新的数据为:{}",list.size());
        log.info("银行卡数据批量保存-----------开始-----------");
        if (list.size() > 0) {
            this.updateBatchById(list);
        }
        log.info("[清洗银行卡编码数据]------------处理完成-------------");

        if (bankCardList.size() > 0 ) {
            //二次数据清洗
            twoDispose(bankCardList);
        }

        if (errorList.size() > 0) {
            log.info("最终未能处理的数据为:");
            for (CustBankCardRepair loanUserBankCard : errorList) {
                log.info("ID={} ,cardNo={}", loanUserBankCard.getId(), loanUserBankCard.getCardNo());
            }
        }
    }

    /**
     * 获取银行卡bin
     * @param cardNo
     * @return
     */
    public String getBankCode(String cardNo) {
        for (Map<String, Object> map: cardBinList) {
            int length = ((BigDecimal)map.get("LENGTH")).intValue();
            int cardBinLen = ((BigDecimal)map.get("CARD_BIN_LEN")).intValue();
            String cardBin = (String) map.get("CARD_BIN");
            if (cardNo.length() == length && cardBin.equals(cardNo.substring(0,cardBinLen))){
                return (String) map.get("BANK_CATEGORY");
            }
        }
        return null;
    }

    private void twoDispose(List<CustBankCardRepair> list) {
        log.info("对失败数据二次尝试处理,总{}条", list.size());
        for (Iterator<CustBankCardRepair> iterator = list.iterator(); iterator.hasNext(); ){
            CustBankCardRepair loanUserBankCard = null;
            try {
                loanUserBankCard = iterator.next();
                String bankCode = getBankCode(loanUserBankCard.getCardNo());
                if (bankCode == null) {
                    log.error("银行卡错误,卡bin识别不存在:ID={} ,cardNo={}", loanUserBankCard.getId(), loanUserBankCard.getCardNo());
                    errorList.add(loanUserBankCard);
                    iterator.remove();
                } else if (bankCode.equals(loanUserBankCard.getBankCode())) {
                    errorList.remove();
                } else {
                    loanUserBankCard.setBankCode(bankCode);
                }
            } catch (Exception e) {
                log.error(e.toString());
                errorList.add(loanUserBankCard);
                iterator.remove();
            }
        }
        log.info("二次处理,更新银行卡list----------完成---------实际需要更新的数据为:{}",list.size());
        log.info("二次处理,银行卡数据批量保存-----------开始-----------");
        if (list.size() > 0) {
            this.updateBatchById(list);
        }
        log.info("[二次处理,清洗银行卡编码数据]------------处理完成-------------");
    }
}
