<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="service.ArticleService" %>
<%@ page import="VO.Article" %>
<%@ page import="VO.User" %>
<!DOCTYPE html>
<html lang="zh-cn">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    ///禁用浏览器页面缓存
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    ///
    <title>用户空间</title>

    <link rel="stylesheet" href="http://www.pintuer.com/css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="http://www.pintuer.com/js/jquery.js"></script>
    <script src="http://www.pintuer.com/js/pintuer.js"></script>

    <script type=text/javascript src=js/query.js></script>
    <script type=text/javascript src=js/Resume.js></script>
    <script src="js/admin.js"></script>
    <link type="image/x-icon" href="http://www.pintuer.com/favicon.ico" rel="shortcut icon"/>
    <link href="http://www.pintuer.com/favicon.ico" rel="bookmark icon"/>
</head>


<body>
<div class="lefter">
    <div class="logo">
        <a href="content_user.jsp" target="_blank"><img src="images/logo.png" alt="后台管理系统"/></a>
    </div>
</div>
<div class="righter nav-navicon" id="admin-nav">
    <div class="mainer">
        <div class="admin-navbar">
					<span class="float-right">
                    <a class="button button-little bg-main" href="content_user.jsp">回到首页</a>
                    <a class="button button-little bg-yellow" href="index.html">注销登录</a>
                </span>
            <ul class="nav nav-inline admin-nav">
                <li >
                    <a href="content_user.jsp" class="icon-file-text">查看文章</a>

                </li>


                <li>
                    <a href="AddNews_user.jsp" class="icon-cog">发布文章</a>

                </li>

                <li class="active"><a href="Dustbin_user.jsp" class="icon-th-list">回收站</a></li>

            </ul>
        </div>
        <div class="admin-bread">
            <span>您好，${username}，欢迎您的光临。</span>
            <ul class="bread">
                <li><a href="content.html"> </a></li>
            </ul>
        </div>
    </div>
</div>

<div class="admin">
    <form method="post">
        <div class="panel admin-panel">
            <div class="panel-head"><strong>回收站文章列表</strong></div>
            <div class="padding border-bottom">

            </div>
            <table class="table table-hover">
                <tr>
                    <th width="300">标题</th>
                    <th width="100">时间</th>
                    <th width="100">类别</th>
                    <th width="150">操作</th>
                </tr>

                <%
                    Article clickArtcle = null;
                    User user= (User) session.getAttribute("user");
                    ArticleService articleService=new ArticleService();
                    ///设定普通用户不能在本页面看到已被删除的文章
                    ArrayList<Integer> temp=articleService.getDeleteArticle(user.getId());
                    for (int i = 0; i < temp.size(); i++) {
                        Article article=articleService.getArticle(temp.get(i));
                %>
                <tr>

                    <td><%=article.getId()%>
                    </td>
                    <td><%=article.getDate()%>
                    </td>
                    <td><%
                        ArrayList<String> tag=articleService.getArticleTag(temp.get(i));
                        for (String s:tag)
                        {
                            out.print(s);
                        }
                    %>
                    </td>
                    <td>
                        <a class="button border-green button-little" href="#"
                           onclick="queryInfo('<%=article.getId()%>','dustbin')">查看详情</a>

                    </td>
                </tr>
                <%
                    }
                %>

            </table>
            <div class="panel-foot text-center">
                <ul class="pagination">
                    <li><a href="#">上一页</a></li>
                </ul>
                <ul class="pagination pagination-group">
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                </ul>
                <ul class="pagination">
                    <li><a href="#">下一页</a></li>
                </ul>
            </div>

        </div>

        <br>
        <br>


        <div class="panel admin-panel">
            <div class="panel-head"><strong>文档详情</strong></div>
            <div class="tab">

                <div class="tab-body">
                    <br/>
                    <div class="tab-panel active" id="tab-set">



                            <div class="form-group">

                                <div class="field">
                                    <label>文章编号</label> <input type="text" class="input" id="check_id" size="50"
                                                               value="" readonly/>
                                </div>
                                <br>

                                <div class="field">
                                    <label>文章标题</label> <input type="text" class="input" id="check_head" size="50"
                                                               value="" readonly/>
                                </div>
                                <br>
                                <div class="field">
                                    <label>文章作者</label> <input type="text" class="input" id="check_author" size="50"
                                                               value="" readonly/>
                                </div>
                                <br>
                                <div class="field">
                                    <label>发布时间</label> <input type="text" class="input" id="check_time" size="50"
                                                               value="" readonly/>
                                </div>
                                <br>
                                <div class="field">
                                    <label>文章类型</label> <input type="text" class="input" id="check_type" size="50"
                                                               value="" readonly/>
                                </div>
                                <br>


                            </div>


                            <div class="form-group">
                                <div class="label">
                                    <label>内容详情</label>
                                </div>
                                <div class="field">
                                    <textarea class="input" id="check_content" rows="5" cols="50" value="" readonly></textarea>
                                </div>
                            </div>
                            <div class="form-button" align="center">
                                <button class="button bg-main" onclick="ResumeArticle()">恢复文章</button>
                            </div>
                    </div>

                </div>
            </div>


        </div>
    </form>
    <br/>
</div>


</body>

</html>