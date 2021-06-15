package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService {
    /**
     * 根据用户名获取会员
     */
    UmsMember getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 根据会员id修改会员积分
     */
    void updateIntegration(Long id,Integer integration);

    /**
     * 根据会员id修改会员历史积分
     * @param id
     * @param hitoryIntegration
     */
    void updateHistoryIntegration(Long id,Integer hitoryIntegration);

    /**
     * 根据会员id修改会员历史消费和本月消费
     * @param id
     * @param historyConsumption
     * @param currentMonthConsumption
     */
    void updateHistoryConsumptionAndCurrentMonthConsumption(Long id,BigDecimal historyConsumption, BigDecimal currentMonthConsumption);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    String login(String username, String password);

    /**
     * 刷新token
     */
    String refreshToken(String token);

    /**
     * 微信注册
     */
    void wxRegister(String openid);

    /**
     * 保存用户信息并对加密信息进行解密
     */
    void saveUserInfo(UserInfo userInfo, String rawData, String signature, String encryptedData, String iv);

    /**
     * 获取达到最高消费级别的用户
     */
    List<UmsMember> getMaxSegmentMember(String startTime, String endTime, BigDecimal amount);

    /**
     * 根据月份和id查找消费总额
     * @param id
     * @return
     */
    BigDecimal countAmountGroupByMonthAndId(String startTime, String endTime, Long id);

    /**
     * 获取所有用户
     * @return
     */
    List<UmsMember> getAllMember();
}
