package dao;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 标签DAO接口
 */
public interface TagDAO
{
    /**
     * 根据文章id返回其对应的标签
     * @param id 文章id
     * @return 返回的标签数组
     */
    ArrayList<String> getTagById(Integer id) throws SQLException;

    /**
     * 根据标签返回其对应的文章id列表
     * @param tag 标签
     * @return 文章id数组
     */
    ArrayList<Integer> getIdByTag(String tag) throws SQLException;

    /**
     * 给一篇文章设置一个标签
     * @param id
     * @param tag
     * @return
     */
    boolean setTag(Integer id, String tag) throws SQLException;
}
