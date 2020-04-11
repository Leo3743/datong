package domain;

import java.io.Serializable;

public class SenseImg implements Serializable {

    private int ssid;//商品图片id
    private int sid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public SenseImg(int ssid, int sid, String bigPic, String smallPic) {
        this.ssid = ssid;
        this.sid = sid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public SenseImg() {
    }
}
