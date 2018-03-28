package dao;

import VO.User;

import java.sql.SQLException;

public interface UserDAO
{
    /**
     * 创建一个新用户
     * @param vo 要增加内容的vo对象
     * @return 成功返回true
     */
    boolean doCreate(User vo) throws SQLException;

    /**
     * 修改一位用户的信息
     * @param vo 要修改内容的vo对象
     * @return 成功返回true
     */
    boolean doUpdata(User vo) throws SQLException;

    /**
     * @param id 要删除的对象
     * @return 成功返回true
     * @brief 删除一位用户的信息
     */
    boolean doRemove(String id);

    /**
     * 根据id查询用户信息
     * @param id 要查询的对象
     * @return 返回该对象
     */
    User findById(String id) throws SQLException;

    /**
     * @param vo 传入user值对象
     * @return 登陆成功返回true
     */
    boolean isLogin(User vo) throws SQLException;

    /**
     * 判断管理员权限
     *
     * @param id
     * @return 是管理员则返回true
     */
    boolean isAdmin(String id) throws SQLException;

    /**
     * 判断管理员权限
     *
     * @param vo
     * @return 是管理员则返回true
     */
    boolean isAdmin(User vo) throws SQLException;
}
