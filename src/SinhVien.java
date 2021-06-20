public class SinhVien {
    private String maSV,hoDem,ten,gioiTinh,ngaySinh;
    private int soMonHoc;

    public SinhVien () {
        this.maSV = "";
        this.hoDem = "";
        this.ten = "";
        this.gioiTinh = "";
        this.ngaySinh = "";
    }
    public SinhVien (String maSV,String hoDem,String ten,String ngaySinh,String gioiTinh) {
        this.maSV = maSV;
        this.hoDem = hoDem;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public String getMasv() {
        return maSV;
    }

    public void setMasv(String masv) {
        this.maSV = masv;
    }

    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSoMonHoc() {
        return soMonHoc;
    }

    public void setSoMonHoc(int soMonHoc) {
        this.soMonHoc = soMonHoc;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    @Override
    public String toString() {
        return "SinhVien{" +
                "maSV='" + maSV + '\'' +
                ", hoDem='" + hoDem + '\'' +
                ", ten='" + ten + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                '}';
    }
}
