package dao;

import VO.Article;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 文章类DAO接口
 */
public interface ArticleDAO
{
    /**
     * 添加新文章
     *
     * @param vo
     * @return 插入成功返回true
     */
    boolean doCreate(Article vo) throws SQLException;

    /**
     * 通过文章id得到文章所有信息
     *
     * @param id 文章id
     * @return 该文章的值对象
     */
    Article getArticle(Integer id) throws SQLException;

    /**
     * 取得所有文章列表，考虑到文章肯定不会超过10k。。。所以直接暴力
     *
     * @return 返回所有文章id
     */
    ArrayList<Integer> getAllArticleList() throws SQLException;

    /**
     * 取得已审核的文章列表....state参数待定
     *
     * @return 文章id列表
     */
    ArrayList<Integer> getArticleListByState() throws SQLException;

    /**
     * 取得已审核的文章列表....state参数待定
     *
     * @return 文章id列表
     */
    ArrayList<Integer> getArticleListByState(String state) throws SQLException;

    /**
     * 取得某位作者的文章列表
     *
     * @param author 作者名
     * @return 某位作者的所有文章
     * @throws SQLException
     */
    ArrayList<Integer> getArticleListByAuthor(String author) throws SQLException;

    /**
     * 根据作者和文章状态返回对应列表
     *
     * @param author 作者名
     * @param state  文章状态
     * @return 文章列表
     */
    ArrayList<Integer> getArticleListBy(String author, String state) throws SQLException;

    /**
     * 该id是否可用
     *
     * @param id 传入文章id
     * @return 可用返回true
     */
    boolean IdIsAbailable(Integer id) throws SQLException;


    /**
     * @param id 文章id
     * @return 是否删除成功
     */
    boolean deleteArticle(Integer id) throws SQLException;


}
