package servlet;

import VO.Article;
import net.sf.json.JSONArray;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 根据传入的请求文章的id，返回对应文章
 */
@WebServlet(name = "getArticle",urlPatterns = {"/getArticle"})
public class getArticle extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        //TODO 真奇怪为啥数据库中加入data变量后，object转json就会挂掉呢？
        Article vo=null;
        int page= Integer.parseInt(request.getParameter("articleId"));
        JSONArray jsonArray=new JSONArray();
        ArticleService articleService=new ArticleService();
        try
        {
            String type=" ";
            ArrayList<String> strings=articleService.getArticleTag(page);
            for (String s:strings)
            {
                type=type+s+" ";
            }
            vo=articleService.getArticle(page);
            vo.setTag(type);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        jsonArray.add(vo);
        //第二次了。。。乱码...坑死
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonArray.toString());
    }
}
