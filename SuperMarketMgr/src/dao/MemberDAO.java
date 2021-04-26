package dao;

import bean.MemberInfo;
import common.ServerResponse;

import java.math.BigDecimal;

public interface MemberDAO<T> extends BaseDAO<T> {
    ServerResponse addMember(MemberInfo member);

    ServerResponse deleteMemberById(int id);

    ServerResponse selectMemberById(int id);

    ServerResponse selectAllMember();

    ServerResponse updateMemberById(MemberInfo member);

    /**
     * 充值余额
     *
     * @param
     * @param bigMoney：要充值的金额
     * @param id：充值的用户id
     * @return ： 操作是否成功，0 不成功，>0 则成功
     */
    int addMoney(int id, BigDecimal bigMoney);

    ServerResponse selectMemberByIdAndPass(int id, String pass);
}
