package com.piter.multipledatasources.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piter.multipledatasources.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Update("update usersys.user_info set cust_no = #{custNo} where user_no = #{userNo}")
    int updateCustNoByUserNo(@Param("custNo") String custNo, @Param("userNo") String userNo);
}