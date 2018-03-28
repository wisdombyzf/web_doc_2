package servlet;

import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResumeArticle",urlPatterns = {"/ResumeArticle"})
public class ResumeArticle extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //TODO 对权限进行检测
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("articleId"));
        ArticleService articleService = new ArticleService();
        try
        {
            if (articleService.alterArtclePermission(id, "待审核"))
            {
                response.getWriter().print("successful");
            } else
            {
                response.getWriter().print("fail");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request,response);
    }
}
