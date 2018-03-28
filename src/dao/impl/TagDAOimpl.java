package dao.impl;

import dao.TagDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagDAOimpl implements TagDAO
{
    PreparedStatement preparedStatement;
    Connection con;

    //TODO 文章删除时，标签还未删除
    public TagDAOimpl(Connection con)
    {
        this.con = con;
    }

    /**
     * 根据文章id返回其对应的标签
     *
     * @param id 文章id
     * @return 返回的标签数组
     */
    @Override
    public ArrayList<String> getTagById(Integer id) throws SQLException
    {
        ArrayList<String> arrayList = new ArrayList<>();

        String sql = "SELECT tag FROM tags WHERE id IN (SELECT tag_id FROM A_MAP_T" +
                "  WHERE article_id=?)";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, id.toString());
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getString("tag"));
        }
        return arrayList;
    }

    /**
     * 根据标签返回其对应的文章id列表
     *
     * @param tag 标签
     * @return 文章id数组
     */
    @Override
    public ArrayList<Integer> getIdByTag(String tag) throws SQLException
    {

        ArrayList<Integer> arrayList = new ArrayList<>();
        String sql = "SELECT article_id FROM A_MAP_T WHERE tag_id IN ( SELECT id FROM tags\n" +
                "  WHERE tag LIKE ?)";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, tag);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next())
        {
            arrayList.add(resultSet.getInt("article_id"));
        }
        return arrayList;
    }

    /**
     * 给一篇文章设置一个标签
     *
     * @param id
     * @param tag
     * @return
     */
    @Override
    public boolean setTag(Integer id, String tag) throws SQLException
    {
        String sql = "SELECT COUNT(*) FROM tags WHERE tag LIKE ?";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, tag);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        int num = 0;
        if (resultSet.next())
        {
            num = resultSet.getInt(1);
        }
        int tagId = tag.hashCode();

        //当标签为新标签时,更新tags表
        if (num == 0)
        {
            //其实有bug。。。。几十万分之一的几率。。暂时不处理
            sql = "INSERT INTO tags(id,tag) VALUES(?,?)";
            this.preparedStatement = this.con.prepareStatement(sql);
            this.preparedStatement.setString(1, String.valueOf(tagId));
            this.preparedStatement.setString(2, tag);
            if (this.preparedStatement.executeUpdate() <= 0)
            {
                return false;
            }
        }
        //更新a_map_t表
        sql = "INSERT INTO a_map_t(tag_id,article_id) VALUES(?,?)";
        this.preparedStatement = this.con.prepareStatement(sql);
        this.preparedStatement.setString(1, String.valueOf(tagId));
        this.preparedStatement.setString(2, String.valueOf(id));
        if (this.preparedStatement.executeUpdate() <= 0)
        {
            return false;
        }
        return true;
    }
}
