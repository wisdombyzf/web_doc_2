<%@ page import="java.util.ArrayList" %>
<%@ page import="service.ArticleService" %>
<%@ page import="VO.Article" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理员界面||文章审核</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link href='http://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet' type='text/css'>
    <!-- jQuery file -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.tabify.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/query.js" type="text/javascript" charset="UTF-8"></script>
    <script type="text/javascript">
        var $ = jQuery.noConflict();
        $(function () {
            $('#tabsmenu').tabify();
            $(".toggle_container").hide();
            $(".trigger").click(function () {
                $(this).toggleClass("active").next().slideToggle("slow");
                return false;
            });
        });
    </script>

    <script type=text/javascript src=js/query.js></script>
</head>

<body>
<div id="panelwrap">

    <%@include file="header.jsp"%>


    <div class="center_content">

        <div id="right_wrap">
            <div id="right_content">
                <h2>文档列表</h2>


                <table id="rounded-corner">
                    <thead>
                    <tr>
                        <th></th>
                        <th>文档编号</th>
                        <th>标题</th>
                        <th>发布日期</th>
                        <th>文档类别</th>
                        <th>作者</th>
                        <th>文档详情</th>
                        <th>文章状态</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <td colspan="12">以上是所有的待审核文档</td>
                    </tr>
                    </tfoot>
                    <tbody>
                    <%
                        ArticleService articleService=new ArticleService();
                        ArrayList<Integer> arrayList=articleService.getArticleListByState("待审核");
                        for (int i = 0; i < arrayList.size(); i++) {
                            Integer tem = arrayList.get(i);
                            Article vo=articleService.getArticle(tem);
                            ArrayList<String> tag=articleService.getArticleTag(tem);
                    %>
                    <tr class="odd">
                        <td><input type="checkbox" name=""/></td>
                        <td><%=vo.getId()%>
                        </td>
                        <td><%=vo.getTitle()%>
                        </td>
                        <td><%=vo.getDate()%>
                        </td>
                        <td><%
                            for (String s:tag)
                            {
                                out.print(s+" ");
                            }
                        %>
                        </td>
                        <td><%=vo.getAuthor()%>
                        </td>
                        <td><a href="#"><img src="images/edit.png" alt="" title="" border="0"
                                             onclick="queryInfo('<%=vo.getId()%>','check_pending')"/></a>
                        </td>

                        <td>
                            <%=vo.getState()%>
                        </td>
                        <td><a href="#"><img src="images/trash.gif" alt="" title="" border="0"
                                             onclick="deleteArticleByAdmin('<%=vo.getId().toString()%>')"/></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>


                <ul id="tabsmenu" class="tabsmenu">
                    <li class="active"><a href="#tab1">文档详情</a></li>

                </ul>
                <div id="tab1" class="tabcontent">
                    <h3>详细信息审核</h3>
                    <div class="form">


                        <div class="form_row">
                            <label>编号:</label>
                            <input type="text" class="form_input" id="check_id" value=""/>
                        </div>

                        <div class="form_row">
                            <label>标题:</label>
                            <input type="text" class="form_input" id="check_head" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>作者:</label>
                            <input type="text" class="form_input" id="check_author" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>时间:</label>
                            <input type="text" class="form_input" id="check_time" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>类型:</label>
                            <input type="text" class="form_input" id="check_type" value="" readonly/>
                        </div>

                        <div class="form_row">
                            <label>内容:</label>
                            <textarea rows="100" class="form_textarea" id="check_content"  value="" readonly></textarea>
                        </div>

                        <div class="form_row">
                            <input type="submit" class="form_submit" value="审核通过" onclick="checkpass()">
                        </div>

                        <div class="clear"></div>
                    </div>
                </div>
                <%@include file="help.html"%>

            </div>
        </div><!-- end of right content-->


        <%@include file="leftSlider.jsp"%>


        <div class="clear"></div>
    </div> <!--end of center_content-->



</div>


</body>
</html>