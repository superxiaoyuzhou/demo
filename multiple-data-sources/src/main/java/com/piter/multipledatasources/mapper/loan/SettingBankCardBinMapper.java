package com.piter.multipledatasources.mapper.loan;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piter.multipledatasources.entity.loan.SettingBankCardBin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 银行卡bin表 Mapper 接口
 * </p>
 *
 * @author LX
 * @since 2019-11-13
 */
public interface SettingBankCardBinMapper extends BaseMapper<SettingBankCardBin> {

    List<Map<String, Object>> selectAll();
}
