/**
 * 构造异步ajax
 * @type {boolean}
 */
var xmlHttp=false;
function createXMLHttpRequest()
{
    if (window.ActiveXObject)  //在IE浏览器中创建XMLHttpRequest对象
    {
        try{
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch(e){
            try{
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            catch(ee){
                xmlHttp=false;
            }
        }
    }
    else if (window.XMLHttpRequest) //在非IE浏览器中创建XMLHttpRequest对象
    {
        try{
            xmlHttp = new XMLHttpRequest();
        }
        catch(e){
            xmlHttp=false;
        }
    }
}

/**
 * 取得文章信息
 * @param id 文章id
 * @param table
 */
function queryInfo( id,table)
{
    id=id.toString();
    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var url="getArticle?articleId="+id;

    alert("正在查询编号为："+id+"的文章详情");
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                //js真坑
                var temp= xmlHttp.responseText;
                var data = JSON.parse(temp);
                //alert(temp)
                document.getElementById("check_id").value=data[0].id;
                document.getElementById("check_head").value=data[0].title;
                document.getElementById("check_author").value=data[0].author;
                document.getElementById("check_time").value=data[0].data;
                document.getElementById("check_type").value=data[0].tag;
                document.getElementById("check_content").value=data[0].body;
            }
        }
    }
}

/**
 * 添加或修改文章
 * @param username
 * @param table
 * @param destination
 */
function addNews(username,table,destination)
{

    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var myDate = new Date();
    var nowtime=myDate.Format("yyyy-MM-dd hh:mm:ss");
    var idtime=myDate.Format("yyyyMMddhhmmss");
    alert("正在发布新文章，请稍后......"+nowtime);

    var newsid=idtime+username;
    var head=document.getElementById("check_head").value;
    var content=document.getElementById("check_content").value;
    var publish_time=nowtime;
    var author=username;
    var newstype=document.getElementById("check_type").value;
    var url="AddArticleServlet?body="+content+"&title="+head+"&publish_time="+publish_time+"&author="+author+"&type="+newstype;

    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send("content="+content);
    function callback()
    {
        //alert("四处打断点1"+nowtime);

        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                //alert("四处打断点2"+nowtime);

                var data= xmlHttp.responseText;
                data=data.toString();
                if(data=="successful")
                {

                    alert("发布成功");
                    window.location.href=destination;
                }
                else
                {
                    alert("发布失败，请重试");
                    location.reload();
                }
            }
        }
    }

}


/**
 * 普通用户删除文章
 * @param id
 */
function deleteArticleByUer(id,table)
{
    id=id.toString();
    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var url="deleteArticleUser?articleId="+id;

    alert("正在删除编号为："+id+"的文章详情");
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                //js真坑
                var temp= xmlHttp.responseText;
                if(temp.toString()=="successful")
                {
                    alert("删除文章成功");
                }else
                {
                    alert("删除文章失败");
                }
                /**
                 * 没有时间解释了。。。暴力暴力，直接重新向服务器请求页面………^_^
                 */
                window.location.replace("content_user.jsp");
                //location.reload();
            }
        }
    }
}


/**
 * 管理员删除文章
 * @param id
 */
function deleteArticleByAdmin(id)
{
    id=id.toString();
    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var url="deleteArticle?articleId="+id;

    alert("正在删除编号为："+id+"的文章详情");
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                //js真坑
                var temp= xmlHttp.responseText;
                if(temp.toString()=="successful")
                {
                    alert("删除文章成功");
                }else
                {
                    alert("删除文章失败");
                }
                /**
                 * 没有时间解释了。。。暴力暴力，直接重新向服务器请求页面………^_^
                 */
                location.reload();
            }
        }
    }
}

/**
 * 提交审核文章
 */
function checkpass() {//审核通过

    createXMLHttpRequest();   //调用创建XMLHttpRequest对象的方法
    xmlHttp.onreadystatechange=callback;   //设置回调函数
    var newsid=document.getElementById("check_id").value;
    var url="CheckArticle?articleId="+newsid;
    xmlHttp.open("post",url,true);      //向服务器端发送请求
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf8");
    xmlHttp.send(null);
    function callback()
    {
        if(xmlHttp.readyState==4)
        {
            if(xmlHttp.status==200)
            {
                var temp= xmlHttp.responseText;
                if(temp=="zf我同意了")
                {
                    alert("审核通过，已完成");
                }else
                {
                    alert("审核未通过，你怕是个假的管理员");
                }
                location.reload();
            }
        }
    }
}

Date.prototype.Format = function(format){

    var o = {

        "M+" : this.getMonth()+1, //month

        "d+" : this.getDate(), //day

        "h+" : this.getHours(), //hour

        "m+" : this.getMinutes(), //minute

        "s+" : this.getSeconds(), //second

        "q+" : Math.floor((this.getMonth()+3)/3), //quarter

        "S" : this.getMilliseconds() //millisecond

    }

    if(/(y+)/.test(format)) {

        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

    }

    for(var k in o) {

        if(new RegExp("("+ k +")").test(format)) {

            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));

        }

    }

    return format;

}