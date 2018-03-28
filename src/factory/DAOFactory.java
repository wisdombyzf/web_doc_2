package factory;

import dao.ArticleDAO;
import dao.TagDAO;
import dao.UserDAO;
import dao.impl.ArticleDAOimpl;
import dao.impl.TagDAOimpl;
import dao.impl.UserDAOimpl;

import java.sql.Connection;

/**
 * 工厂类
 */
public class DAOFactory
{
    public static UserDAO getUserDAOInstance(Connection connection)
    {
        return new UserDAOimpl(connection);
    }
    public static ArticleDAO getArticleInstance(Connection connection)
    {
        return new ArticleDAOimpl(connection);
    }
    public static TagDAO getTagDAOInstance(Connection connection)
    {
        return new TagDAOimpl(connection);
    }
}
