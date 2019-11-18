package com.piter.multipledatasources.mapper.loan;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piter.multipledatasources.entity.loan.LoanUserCreditInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoanUserCreditInfoMapper extends BaseMapper<LoanUserCreditInfo> {

    @Select("select * from COOP_LOAN.LOAN_USER_CREDIT_INFO i \n" +
            "where i.REAL_NAME is not null \n" +
            "and i.CERT_ID is not null\n" +
            "and  EXISTS (\n" +
            "select 1 from COOP_LOAN.Loan_user_credit_status s where s.credit_info_id = i.id and s.PHASE_CODE = 'REAL_AUTH' and s.PHASE_STATUS = 'SU'\n" +
            ")")
    List<LoanUserCreditInfo> selectForCcsData();


    @Select("select * from COOP_LOAN.LOAN_USER_CREDIT_INFO i \n" +
            "where i.REAL_NAME is not null \n" +
            "and i.CERT_ID is not null\n" +
            "and i.PROD_CODE='MER-ELOAN'\n" +
            "and  EXISTS (\n" +
            "select 1 from COOP_LOAN.Loan_user_credit_status s\n" +
            "where s.credit_info_id = i.id\n" +
            "and s.PHASE_CODE = 'REAL_AUTH'\n" +
            "and s.PHASE_STATUS = 'SU'\n" +
            "and s.PHASE_TIME > to_date('2019-10-15 00:00:00','yyyy-mm-dd hh24:mi:ss')\n"+
            ")")
    List<LoanUserCreditInfo> selectForEloanCcs();
}