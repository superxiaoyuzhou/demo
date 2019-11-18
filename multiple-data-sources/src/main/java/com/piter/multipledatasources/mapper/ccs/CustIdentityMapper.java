package com.piter.multipledatasources.mapper.ccs;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piter.multipledatasources.entity.CustIdentity;

import java.util.List;

/**
 * <p>
 * 身份认证 Mapper 接口
 * </p>
 *
 * @author Piter
 * @since 2019-07-25
 */

public interface CustIdentityMapper extends BaseMapper<CustIdentity> {

    List<CustIdentity> selectLowerIdCard();

}
