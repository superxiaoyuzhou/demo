package com.piter.multipledatasources.mapper.loan;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author LiXun
 * @date 2019-11-08 10:57
 */
public interface CustNoRepairMapper {
    List<Map<String,String>> selectCreditInfo();

    int updateCreditInfo(@Param("id") String id, @Param("custNo") String custNo);

    int updateCreditStatus(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateImage(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateBankCard(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateCompanyInfo(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateApply(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateContract(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateOrder(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateRepay(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateRepayPlan(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);

    int updateRepaySelf(@Param("creditInfoId") String creditInfoId, @Param("custNo") String custNo);



}
