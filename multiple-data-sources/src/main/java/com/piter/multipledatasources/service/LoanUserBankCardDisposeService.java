package com.piter.multipledatasources.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.piter.multipledatasources.entity.loan.LoanUserBankCardDispose;
import com.piter.multipledatasources.mapper.loan.LoanUserBankCardDisposeMapper;
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
 * @author LiXun
 * @date 2019-10-11 14:31
 */
@Service
@Slf4j
public class LoanUserBankCardDisposeService extends ServiceImpl<LoanUserBankCardDisposeMapper, LoanUserBankCardDispose> {
    public static final String SUCCESS_RESP_CODE = "0000";

    @Autowired
    private SettingBankCardBinMapper settingBankCardBinMapper;

    //获取所有的卡bin信息
    private List<Map<String, Object>>  cardBinList;

    //第一次清洗数据失败集合
    private List<LoanUserBankCardDispose> bankCardList = new LinkedList<>();
    //第二次清洗数据失败集合
    private LinkedList<LoanUserBankCardDispose> errorList = new LinkedList<>();


    public void dispose() {
        cardBinList = settingBankCardBinMapper.selectAll();
        //获取所有的LOAN_USER_BANK_CARD
        List<LoanUserBankCardDispose> list = this.list();
        log.info("共查询到{}条LOAN_USER_BANK_CARD数据", list.size());
        int sum = 0;
        for (Iterator<LoanUserBankCardDispose> iterator = list.iterator(); iterator.hasNext(); ){
            LoanUserBankCardDispose loanUserBankCard = null;
            try {
                loanUserBankCard = iterator.next();
                String bankCode = getBankCode(loanUserBankCard.getCardNo());
                if (bankCode == null) {
                    log.error("银行卡错误,卡bin识别不存在:ID={} ,cardNo={}", loanUserBankCard.getId(), loanUserBankCard.getCardNo());
                    bankCardList.add(loanUserBankCard);
                    iterator.remove();
                } else if (bankCode.equals(loanUserBankCard.getBankCode())) {
                    iterator.remove();
                } else {
                    loanUserBankCard.setBankCode(bankCode);
                }
                sum++;
                if (sum % 5000 == 0) {
                    log.info("已更新银行卡list中{}条数据", sum);
                }
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                bankCardList.add(loanUserBankCard);
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
            for (LoanUserBankCardDispose loanUserBankCard : errorList) {
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

    private void twoDispose(List<LoanUserBankCardDispose> list) {
        log.info("对失败数据二次尝试处理,总{}条", list.size());
        for (Iterator<LoanUserBankCardDispose> iterator = list.iterator(); iterator.hasNext(); ){
            LoanUserBankCardDispose loanUserBankCard = null;
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


    /**
     * 判断调用是否成功
     *
     * @param resp
     */
//    private BankCarBinQueryVo isSuccess(RestResp<BankCarBinQueryVo> resp) {
//        if (resp == null) {
//            throw new BusinessException(RespCode.OPERATE_FAIL, new Object[]{"基础数据服务卡bin识别调用失败,无法收到响应"});
//        }
//        if (resp.getRespCode().equals("2001")) {
//            return null;
//        }
//        if (!SUCCESS_RESP_CODE.equals(resp.getRespCode())) {
//            throw new BusinessException(RespCode.OPERATE_FAIL, new Object[]{"基础数据服务调用失败响应：" + JSON.toJSONString(resp)});
//        }
//        return resp.getData();
//    }

}
