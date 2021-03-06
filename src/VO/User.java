package VO;

import java.io.Serializable;

public class User implements Serializable
{
    private String id;
    private String name;
    private String password;
    private Integer admin;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setAdmin(Integer admin)
    {
        this.admin = admin;
    }

    public Integer getAdmin()
    {
        return admin;
    }
}
