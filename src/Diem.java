public class Diem {
    private String Mamh,Masv;
    private float diemSo;
    private SinhVien sinhVien;
    private MonHoc monHoc;

    public Diem(SinhVien sinhVien, MonHoc monHoc,float diemSo) {
        this.diemSo = diemSo;
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public Diem(String masv, String mamh, float diemSo) {
        Mamh = mamh;
        Masv = masv;
        this.diemSo = diemSo;
    }
    public Diem() {
        Mamh = "";
        Masv = "";
        this.diemSo = 0;
    }

    @Override
//    public String toString() {
//        return "Diem{" +
//                "Mamh='" + Mamh + '\'' +
//                ", Masv='" + Masv + '\'' +
//                ", diemSo=" + diemSo +
//                '}';
//    }
    public String toString() {
        return "Diem{" +
                "Mamh='" + monHoc.getMaMH() + '\'' +
                ", Masv='" + sinhVien.getMasv() + '\'' +
                ", diemSo=" + diemSo +
                '}';
    }
//    public SinhVien getSinhVien () {
//
//    }

    public String getMamh() {
        return Mamh;
    }
    public String getMamh1() {
        return monHoc.getMaMH();
    }

    public void setMamh(String mamh) {
        Mamh = mamh;
    }

    public String getMasv() {
        return Masv;
    }
    public String getMasv1() {
        return sinhVien.getMasv();
    }

    public void setMasv(String masv) {
        Masv = masv;
    }

    public float getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(float diemSo) {
        this.diemSo = diemSo;
    }
}
