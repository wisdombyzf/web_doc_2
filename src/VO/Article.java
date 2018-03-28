package VO;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable
{
    private Integer id;
    private String author;
    private String title;
    private String body;
    private String state;
    private String tag;
    //TODO 时间待完成
    private Date date;
    public Article()
    {
        id=0;
        author="";
        title="";
        body="";
        state="见鬼了";
        tag="";
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setBody(String body)
    {
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public String getTag()
    {
        return tag;
    }
}
