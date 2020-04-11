package domain;

import java.io.Serializable;
import java.util.List;

/**
 * 新闻实体类
 */
public class News implements Serializable {


    private int nid;//新闻id
    private String nname;//新闻名称，必输
    private String nIntroduce;//新闻简介
    private String ndate;   //上架时间
    private String nimage;//缩略图
    private String ncontent;//新闻具体内容

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getnIntroduce() {
        return nIntroduce;
    }

    public void setnIntroduce(String nIntroduce) {
        this.nIntroduce = nIntroduce;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    public String getNimage() {
        return nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage;
    }

    public News(String nname, String nIntroduce, String ndate, String nimage) {
        this.nname = nname;
        this.nIntroduce = nIntroduce;
        this.ndate = ndate;
        this.nimage = nimage;
    }

    public News() {
    }
}
