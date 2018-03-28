package servlet;

import VO.User;
import dao.DatabaseConnection;
import dao.UserDAO;
import factory.DAOFactory;
import service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet",urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Connection con= DatabaseConnection.getConnection();
        UserDAO userDAO= DAOFactory.getUserDAOInstance(con);
        User vo=new User();
        vo.setId(request.getParameter("id"));
        vo.setPassword(request.getParameter("password"));
        try
        {
            if (userDAO.isLogin(vo))
            {
                //登陆成功时返回主页面
                if (userDAO.isAdmin(vo))
                {
                    //取得管理员能见到的所有文章
                    ArticleService articleService=new ArticleService();
                    ArrayList<Integer> list=articleService.getAllArticleList();

                    //管理员页面
                    HttpSession httpSession=request.getSession();
                    httpSession.setAttribute("user",vo);

                    //设置初始页面
                    httpSession.setAttribute("page",0);
                    httpSession.setAttribute("articleList",list);

                    request.getRequestDispatcher("/check_pending.jsp").forward(request,response);
                }else
                {
                    //取得普通用户能见到的所有文章
                    ArticleService articleService=new ArticleService();
                    ArrayList<Integer> list=articleService.getArticleList();
                    //普通用户页面,设置session
                    HttpSession httpSession=request.getSession();
                    httpSession.setAttribute("user",vo);
                    //设置初始页面
                    httpSession.setAttribute("page",0);
                    httpSession.setAttribute("articleList",list);

                    request.getRequestDispatcher("/content_user.jsp").forward(request,response);
                }
            }else
            {
                request.getRequestDispatcher("/404.html").forward(request,response);
                /*
                PrintWriter out=response.getWriter();
                out.println("");
                out.close();
                */
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
