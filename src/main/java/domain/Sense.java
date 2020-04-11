package domain;

import java.util.List;

/*
*
* 风景实体类
* */
public class Sense {

    private int sid;
    private String sname;
    private String sIntroduce;
    private String simage;

    private List<SenseImg> senseImgList;//商品详情图片列表

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getsIntroduce() {
        return sIntroduce;
    }

    public void setsIntroduce(String sIntroduce) {
        this.sIntroduce = sIntroduce;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }

    public List<SenseImg> getSenseImgList() {
        return senseImgList;
    }

    public void setSenseImgList(List<SenseImg> senseImgList) {
        this.senseImgList = senseImgList;
    }

    public Sense(int sid, String sname, String sIntroduce, String simage, List<SenseImg> senseImgList) {
        this.sid = sid;
        this.sname = sname;
        this.sIntroduce = sIntroduce;
        this.simage = simage;
        this.senseImgList = senseImgList;
    }

    public Sense() {
    }
}
