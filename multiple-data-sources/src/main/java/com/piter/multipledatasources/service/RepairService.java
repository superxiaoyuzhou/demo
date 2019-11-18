package com.piter.multipledatasources.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chinaums.commons.utils.BeanUtils;
import com.chinaums.dataencrypt.util.DataSecurityUtil;
import com.piter.multipledatasources.dto.SaveIdentityDto;
import com.piter.multipledatasources.entity.CustIdentity;
import com.piter.multipledatasources.entity.UserInfo;
import com.piter.multipledatasources.mapper.ccs.CustIdentityMapper;
import com.piter.multipledatasources.mapper.user.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author lyq
 * @time 2019/10/22 10:27
 */
@Service
@Slf4j
public class RepairService {

    @Autowired(required = false)
    private CustIdentityMapper custIdentityMapper;

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    /**
     * 不同的SqlSessionFactory，事务不起作用
     */
    @Transactional
    public String testTransaction() throws Exception {
//        CustIdentity custIdentity = custIdentityMapper.selectById(488);
//        log.info("身份信息->{}", custIdentity);
//        UserInfo userInfo = userInfoMapper.selectById("2c9050b26660cd260166622618cd001b");
//        log.info("user_info信息->{}",userInfo);

//        CustIdentity custIdentity = new CustIdentity();
//        custIdentity.setIdCard("111111111111111111")
//                .setIdCardFuzzy(DataSecurityUtil.fuzzyIdCard(custIdentity.getIdCard()))
//                .setName("测试")
//                .setNameFuzzy(DataSecurityUtil.fuzzyName(custIdentity.getName()))
//                .setCustNo("1")
//                .setId(1L);
//        custIdentityMapper.insert(custIdentity);
//        if(custIdentity.getIdCard().equals("111111111111111111")){
//            throw new Exception("chucuole");
//        }
//
//        UserInfo userInfo = new UserInfo();
//        Date date = new Date();
//        userInfo.setId("11111")
//                .setUserNo("22222")
//                .setPhone("33333")
//                .setUserStatus("01")
//                .setCreateTime(date)
//                .setUpdateTime(date);
//        userInfoMapper.insert(userInfo);

//        UserInfo userInfo = new UserInfo();
//        userInfo.setPhone("18202813706");
//        int update = userInfoMapper.update(userInfo, new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserNo, "157112490014500129"));
//        log.info("更新数：{}",update);


        return "success";
    }

    /**
     * 修复客户中心身份证号小写x
     * @return
     */
    public String idCard() {
        log.info("修复客户中心身份证号小写x ---> 开始");
        //1.查出所有小写x结尾的身份证号
        List<CustIdentity> lows = custIdentityMapper.selectLowerIdCard();
        log.info("需要修复的数据条数：{}",lows.size());
        lows.forEach(low -> {
            String upIdCard = low.getIdCard().toUpperCase();
            //2.判断大写X的身份证存不存在
            CustIdentity up = custIdentityMapper.selectOne(new LambdaQueryWrapper<CustIdentity>()
                    .eq(CustIdentity::getIdCard, DataSecurityUtil.encrypt(upIdCard)));
            if(Objects.isNull(up)){
                //3.只需要将小写的这一条更新为大写
                low.setIdCard(upIdCard)
                        .setIdCardFuzzy(DataSecurityUtil.fuzzyIdCard(upIdCard));
                custIdentityMapper.updateById(low);
            } else {
                //4.将用户中心user_info关联到小写这条的custNo更新为大写这条的custNo
                UserInfo userInfo = new UserInfo();
                userInfo.setCustNo(up.getCustNo());
                userInfoMapper.update(userInfo,new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getCustNo,low.getCustNo()));

                //5.判断这两条数据哪一条是新的，如果小的新，则将小的信息复制给大的
                if(low.getUpdateTime().after(up.getUpdateTime())){
                    SaveIdentityDto saveIdentityDto = BeanUtils.beanToBean(low, SaveIdentityDto.class);
                    BeanUtils.copy(up,saveIdentityDto);
                    up.setNameFuzzy(DataSecurityUtil.fuzzyName(up.getName()));
                }
                //6.删除小的，更新大的
                custIdentityMapper.updateById(up);
                custIdentityMapper.deleteById(low.getId());
            }
        });

        log.info("修复客户中心身份证号小写x ---> 结束");
        return "success";
    }
}
