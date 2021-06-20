public class MonHoc {
    private String maMH,tenMH;
    private float heSo;
    public MonHoc() {
        this.maMH = "";
        this.tenMH = "";
        this.heSo = 0;
    }

    public MonHoc(String maMH, String tenMH, float heSo) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.heSo = heSo;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public float getHeSo() {
        return heSo;
    }

    public void setHeSo(float heSo) {
        this.heSo = heSo;
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMH='" + maMH + '\'' +
                ", tenMH='" + tenMH + '\'' +
                ", heSo=" + heSo +
                '}';
    }
}
