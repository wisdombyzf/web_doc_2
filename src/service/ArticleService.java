package service;

import VO.Article;
import dao.ArticleDAO;
import dao.DatabaseConnection;
import dao.TagDAO;
import factory.DAOFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 文章服务类
 */
public class ArticleService
{
    private ArticleDAO articleDAO;
    private Article vo;
    private TagDAO tagDAO;

    public ArticleService()
    {
        Connection con = DatabaseConnection.getConnection();
        Connection con2=DatabaseConnection.getConnection();
        articleDAO = DAOFactory.getArticleInstance(con);
        tagDAO= DAOFactory.getTagDAOInstance(con2);
    }

    /**
     * @return 所有文章id列表
     * @throws SQLException
     */
    public ArrayList<Integer> getAllArticleList() throws SQLException
    {
        return articleDAO.getAllArticleList();
    }

    /**
     * @return 已审核的文章id列表
     * @throws SQLException
     */
    public ArrayList<Integer> getArticleList() throws SQLException
    {
        return articleDAO.getArticleListByState();
    }


    /**
     * 通过文章id得到文章所有信息
     * @param id 文章id
     * @return 该文章的值对象
     */
    public Article getArticle(Integer id) throws SQLException
    {
        return articleDAO.getArticle(id);
    }

    /**
     * 添加文章，因为文章数不多，直接以文章的hashcode作为id，若冲突，则循环+1
     * @param vo
     * @return 添加成功，返回true
     */
    public boolean addArticle(Article vo) throws SQLException
    {
        Integer hash = vo.getBody().hashCode();
        while (!articleDAO.IdIsAbailable(hash))
        {
            hash = (hash.intValue() + 1);
        }
        vo.setId(hash);
        if (articleDAO.doCreate(vo))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * @param id
     * @return 文章对应的标签数组
     * @throws SQLException
     */
    public ArrayList<String> getArticleTag(Integer id) throws SQLException
    {
        Connection con = DatabaseConnection.getConnection();
        TagDAO tagDAO = DAOFactory.getTagDAOInstance(con);
        return tagDAO.getTagById(id);
    }

    /**
     * 根据标签返回对应文章的id数组
     * @param tag 标签
     * @return 该标签对应所有文章的id
     * @throws SQLException
     */
    public ArrayList<Integer> getIdByArticle(String tag) throws SQLException
    {
        Connection con = DatabaseConnection.getConnection();
        TagDAO tagDAO = DAOFactory.getTagDAOInstance(con);
        return tagDAO.getIdByTag(tag);
    }

    /**
     * 暴力暴力。。。直接删掉，再添加
     * @param id 文章id
     * @return 是否修改成功
     */
    public boolean alterArtclePermission(Integer id,String state) throws SQLException
    {
        Article vo=articleDAO.getArticle(id);
        vo.setState(state);
        if (articleDAO.deleteArticle(id)&&articleDAO.doCreate(vo))
        {
            return true;
        }else
        {
            return false;
        }
    }


    /**
     * 取得某位作者的文章列表
     *
     * @param author 作者名
     * @return 某位作者的所有文章
     * @throws SQLException
     */
    public ArrayList<Integer> getArticleListByAuthor(String author) throws SQLException
    {
        return articleDAO.getArticleListByAuthor(author);
    }

    /**
     * 取得已审核的文章列表....state参数待定
     *
     * @param state
     * @return 文章id列表
     */
    public ArrayList<Integer> getArticleListByState(String state) throws SQLException
    {
        return articleDAO.getArticleListByState(state);
    }

    /**
     * 删除对应id的文章
     * @param id
     * @return 删除成功返回true
     * @throws SQLException
     */
    public boolean deleteArticle(Integer id) throws SQLException
    {
        return articleDAO.deleteArticle(id);
    }

    /**
     * 获取用户能看到的文章列表
     * @param author
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getUserArticle(String author) throws SQLException
    {
        ArrayList<Integer> list= articleDAO.getArticleListBy(author,"已审核");
        ArrayList<Integer> list2= articleDAO.getArticleListBy(author,"待审核");
        list.addAll(list2);
        return list;
    }

    /**
     * 获取用户已删除的文章
     * @param author
     * @return
     * @throws SQLException
     */
    public ArrayList<Integer> getDeleteArticle(String author) throws SQLException
    {
        return articleDAO.getArticleListBy(author,"删除");
    }

    /**
     * 根据标签返回对应文章列表
     * @param tag 标签
     * @return 文章列表
     * @throws SQLException
     */
    public ArrayList<Integer> getArticleByTag(String tag) throws SQLException
    {
        return tagDAO.getIdByTag(tag);
    }

    public boolean setTagForArticle(Integer id,String []tags) throws SQLException
    {
        for (String s:tags)
        {
            if(!tagDAO.setTag(id,s))
            {
                return false;
            }
        }
        return true;
    }

}
