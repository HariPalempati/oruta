package model;

import java.util.List;

/**
 * Created by nallurip on 08/03/15.
 */
public class Users {
    private String loginid;
    private String password;
    private int userid;
    private Users tpaverficationUser;
    private List<Users> tpaverficationUserList;
    private String logintype;
    private String location;
    private String mailid;
    private String registereddate;
    private String filename;
    private String fileblockpositionstatus;

    public String getRegistereddate() {
        return registereddate;
    }

    public void setRegistereddate(String registereddate) {
        this.registereddate = registereddate;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String mailid) {
        this.mailid = mailid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Users getTpaverficationUser() {
        return tpaverficationUser;
    }

    public void setTpaverficationUser(Users tpaverficationUser) {
        this.tpaverficationUser = tpaverficationUser;
    }

    public List<Users> getTpaverficationUserList() {
        return tpaverficationUserList;
    }

    public void setTpaverficationUserList(List<Users> tpaverficationUserList) {
        this.tpaverficationUserList = tpaverficationUserList;
    }

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileblockpositionstatus() {
        return fileblockpositionstatus;
    }

    public void setFileblockpositionstatus(String fileblockpositionstatus) {
        this.fileblockpositionstatus = fileblockpositionstatus;
    }
}
