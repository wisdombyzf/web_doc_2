package dao.impl;

import VO.Article;
import dao.ArticleDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleDAOimpl implements ArticleDAO
{
    private Connection con;
    private PreparedStatement preparedStatement;

    public ArticleDAOimpl(Connection con)
    {
        this.con = con;
    }

    /**
     * 添加新文章
     *
     * @param vo
     * @return 插入成功返回true
     */
    @Override
    public boolean doCreate(Article vo) throws SQLException
    {
        String sql = "INSERT INTO article(id,author,title,body,state)" +
                "VALUE (?,?,?,?,?)";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, vo.getId().toString());
        this.preparedStatement.setString(2, vo.getAuthor());
        this.preparedStatement.setString(3, vo.getTitle());
        this.preparedStatement.setString(4, vo.getBody());
        //this.preparedStatement.setString(5, vo.getDate().toString());
        this.preparedStatement.setString(5, vo.getState());
        if (this.preparedStatement.executeUpdate() > 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 通过文章id得到文章所有信息
     *
     * @param id 文章id
     * @return 该文章的值对象
     */
    @Override
    public Article getArticle(Integer id) throws SQLException
    {
        Article vo = new Article();
        String sql = "SELECT id,title,author,body,date,state" +
                " FROM article WHERE id=?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, id.toString());
        ResultSet resultSet = this.preparedStatement.executeQuery();
        if (resultSet.next())
        {
            vo.setId(id);
            vo.setAuthor(resultSet.getString("author"));
            vo.setBody(resultSet.getString("body"));
            vo.setTitle(resultSet.getString("title"));
            vo.setDate(resultSet.getDate("date"));
            vo.setState(resultSet.getString("state"));
        }
        return vo;
    }

    /**
     * 取得所有文章列表，考虑到文章肯定不会超过10k。。。所以直接暴力
     *
     * @return 返回所有文章id
     */
    @Override
    public ArrayList<Integer> getAllArticleList() throws SQLException
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT id FROM article";
        this.preparedStatement = this.con.prepareStatement(sql);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("id"));
        }
        return arrayList;
    }

    /**
     * 取得已审核的文章列表
     *
     * @return 文章id列表
     */
    @Override
    public ArrayList<Integer> getArticleListByState() throws SQLException
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM article WHERE state LIKE '已审核'";
        this.preparedStatement = this.con.prepareStatement(sql);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("id"));
        }
        return arrayList;
    }

    /**
     * 取得已审核的文章列表....state参数待定
     *
     * @param state
     * @return 文章id列表
     */
    @Override
    public ArrayList<Integer> getArticleListByState(String state) throws SQLException
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT id FROM article WHERE state LIKE ?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, state);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("id"));
        }
        return arrayList;
    }

    /**
     * 取得某位作者的文章列表
     *
     * @param author 作者名
     * @return 某位作者的所有文章
     * @throws SQLException
     */
    @Override
    public ArrayList<Integer> getArticleListByAuthor(String author) throws SQLException
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT id FROM article WHERE author LIKE ?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, author);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("id"));
        }
        return arrayList;
    }

    /**
     * 根据作者和文章状态返回对应列表
     *
     * @param author 作者名
     * @param state  文章状态
     * @return 文章列表
     */
    @Override
    public ArrayList<Integer> getArticleListBy(String author, String state) throws SQLException
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT id FROM article WHERE author LIKE ? AND state LIKE ?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, author);
        this.preparedStatement.setString(2, state);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("id"));
        }
        return arrayList;
    }

    /**
     * 该id是否可用
     *
     * @param id 传入文章id
     * @return 可用返回true
     */
    @Override
    public boolean IdIsAbailable(Integer id) throws SQLException
    {
        String sql = "SELECT COUNT(*) FROM article WHERE id=?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, id.toString());
        ResultSet resultSet = this.preparedStatement.executeQuery();
        int num = 0;
        while (resultSet.next())
        {
            num = resultSet.getInt(1);
        }
        if (num == 0)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 根据文章id删除对应文章
     *
     * @param id 文章id
     * @return 是否删除成功
     */
    @Override
    public boolean deleteArticle(Integer id) throws SQLException
    {
        Article vo = new Article();
        String sql = "DELETE  FROM `article` WHERE id = ? ";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, id.toString());
        if (this.preparedStatement.executeUpdate() > 0)
        {
            return true;
        } else
        {
            return false;
        }
    }


}
