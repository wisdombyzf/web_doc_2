package servlet;

import VO.Article;
import VO.User;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddArticleServlet",urlPatterns = {"/AddArticleServlet"})
public class AddArticleServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        Article vo=new Article();

        //初始化
        HttpSession httpSession=request.getSession();
        User user= (User) httpSession.getAttribute("user");
        vo.setAuthor(user.getId());
        vo.setState("待审核");
        //TODO 对传入数据进行检测
        vo.setTitle(request.getParameter("title"));
        vo.setBody(request.getParameter("body"));
        String type=request.getParameter("type");
        String[] strings=type.split(",");
        ArticleService articleService=new ArticleService();
        try
        {
            if (articleService.addArticle(vo)&&articleService.setTagForArticle(vo.getId(),strings))
            {
                response.getWriter().print("successful");
                //跳转到修改成功界面
            }else
            {
                response.getWriter().print("failed");
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
