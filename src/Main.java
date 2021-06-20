import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        FileProcessing fileProcessing = new FileProcessing();
        String fileName = "C:\\Users\\Admin\\Desktop\\HocJavaDev\\BaiTapCuoiKhoa2\\data\\sinhvien.txt";
        String fileMonHoc = "C:\\Users\\Admin\\Desktop\\HocJavaDev\\BaiTapCuoiKhoa2\\src\\monhoc.txt";
        String fileDiem = "C:\\Users\\Admin\\Desktop\\HocJavaDev\\BaiTapCuoiKhoa2\\data\\diem.txt";
        ArrayList<SinhVien> sinhViens = new ArrayList<SinhVien>();
        sinhViens = fileProcessing.getSinhVien(fileName);
        ArrayList<MonHoc> monHocs = new ArrayList<MonHoc>();
        monHocs = fileProcessing.getMonHoc(fileMonHoc);
        ArrayList<Diem> diems = new ArrayList<Diem>();
        diems = fileProcessing.getDiem(fileDiem);
        ArrayList<Diem> diems1 = new ArrayList<Diem>();
        diems1 = fileProcessing.getDiem(fileDiem,fileName,fileMonHoc);
        int option;
        do {
            System.out.println("┌──────────────────────────────────────────────────────┐");
            System.out.println("│===========>Chức năng cập nhận danh sách<=============│");
            System.out.println("│1. Thêm một sinh viên mới                             │");
            System.out.println("│2. Sửa thông tin của một sinh viên                    │");
            System.out.println("│3. Xóa thông tin của một sinh viên                    │");
            System.out.println("│4. Hiển thị danh sách sinh viên được sắp xếp theo tên │");
            System.out.println("│5. Hiện thị danh sách môn học được sắp xếp theo tên   │");
            System.out.println("│0. Quay lại                                           │");
            System.out.println("└──────────────────────────────────────────────────────┘");
            System.out.print("Vui lòng chọn chức năng: ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    SinhVien sv = new SinhVien();
                    sv = createSinhVien(sv);
                    sinhViens.add(sv);
                    try {
                        fileProcessing.addSinhVienOnFile(fileName, sinhViens);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    displaySVs(sinhViens,diems,monHocs);
                    System.out.println("Nhap ma sinh vien ban muon sua : ");
                    sc.nextLine();
                    String masv = sc.nextLine();
                    if (checkMaSV(masv, sinhViens) != -1) {
                        repairSinhVien(masv, sinhViens);
                        fileProcessing.repairFile(fileName, sinhViens);
                    } else {
                        System.out.println("Khong ton tai MaSV");
                    }
                    break;
                case 3:
                    displaySVs(sinhViens,diems,monHocs);
                    sc.nextLine();
                    System.out.println("Nhap vao ma sinh vien can xoa : ");
                    String delMaSv = sc.nextLine();
                    int ck = checkMaSV(delMaSv, sinhViens);
                    if (ck != -1 && checkDiem(delMaSv, diems)) {
                        sinhViens.remove(ck);
                        fileProcessing.repairFile(fileName, sinhViens);
                    } else {
                        System.out.println("Khong the xoa");
                    }
                    displaySinhVien(sinhViens);
                    break;
                case 4 :
                    sortSVByName(sinhViens);
                    displaySVs(sinhViens,diems,monHocs);
                        sc.nextLine();
                        System.out.println("Nhap ma sinh vien : ");
                        String masinhv = sc.nextLine();
                        SinhVien sinhVien = returnMaSV(masinhv,sinhViens);
                        if (checkMaSV(masinhv,sinhViens) != -1) {
                            displayBangDiem(sinhVien,diems,monHocs);
                            break;}
                        else {
                            System.out.println("Khong ton tai ma sinh vien ");
                        }
                    break;
                case 5:
                    sortMonHocByName(monHocs);
                    displayMon(monHocs);
                    System.out.println("1.Hiển thị bảng điểm theo danh sách môn học");
                    System.out.println("0.Để thoát");
                    System.out.println("Nhap lua chon : ");
                    int op = sc.nextInt();
                    switch (op) {
                        case 1:
                            sc.nextLine();
                            System.out.println("Nhap ma mon hoc : ");
                            String mamh = sc.nextLine();
                            if (checkMaMH(mamh,monHocs))
                            {
                                MonHoc monHoc = returnMaMH(mamh,monHocs);
                                displayBangMH(diems1,monHoc);
                            }
                            else {
                            System.out.println("Khong ton tai mon hoc");
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }

        }
            while (option != 0) ;
    }

    public static String displaySV (SinhVien sv) {
        String str = String.format("│ %-10s │ %-20s | %-10s | %-10s | %-10s |",
                sv.getMasv(),sv.getHoDem(),sv.getTen(),sv.getNgaySinh(),sv.getGioiTinh());
        return str;
    }
    public static void displayByPage (ArrayList<SinhVien> sv,int page) {
        int start = 20*page;
        int end = start+20;
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│    DIEM    |       TEN  DEM       |    TEN     |  NGAY SINH |  GIOI TINH |");
        abc:for (int i= start;i<end;i++) {
            if (i > sv.size()-1) break abc;
            System.out.println(displaySV(sv.get(i)));
        }
        System.out.println("└──────────────────────────────────────────────────────────────────────────┘");
    }
    public static void displayByPage (ArrayList<SinhVien> sv,int start,int end) {
        System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│    DIEM    |       TEN  DEM       |    TEN     |  NGAY SINH |  GIOI TINH |");
        abc:for (int i= start;i<end;i++) {
            if (i > sv.size()-1) break abc;
            System.out.println(displaySV(sv.get(i)));
        }
        System.out.println("└──────────────────────────────────────────────────────────────────────────┘");
    }
    public static void displaySVs (ArrayList<SinhVien> sinhViens,ArrayList<Diem> diems,ArrayList<MonHoc> monHocs) {
        int op = 0;
        int pageOp;
        displayByPage(sinhViens, op);
        try{
            do {
                System.out.println("1.Xem trang tiep theo     2.Tro lai trang truoc     3.Xem trang cu the");
                System.out.println("4.Xem trang dau tien      5.Xem trang cuoi cung     0.Tro ve MENU");
                System.out.println("6.Xem chi tiet bang diem");
                pageOp = sc.nextInt();
                switch (pageOp) {
                    case 1:
                        op = op+1;
                        displayByPage(sinhViens, op);
                        break;
                    case 2:
                        op = op-1;
                        if (op<0) {System.out.println("Hien dang ơ trang 1"); op = 0;}
                        displayByPage(sinhViens, op);
                        break;
                    case 3:
                        System.out.println("Nhap lua chon cua ban :");
                        sc.nextLine();
                        op = sc.nextInt();
                        displayByPage(sinhViens, op);
                        break;
                    case 4 :
                        op = 0;
                        displayByPage(sinhViens,op);
                        break;
                    case 5:
                        op = sinhViens.size()/20;
                        displayByPage(sinhViens,op);
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.println("Nhap ma sinh vien : ");
                        String masinhv = sc.nextLine();
                        SinhVien sinhVien = returnMaSV(masinhv,sinhViens);
                        if (checkMaSV(masinhv,sinhViens) != -1) {
                            displayBangDiem(sinhVien,diems,monHocs); }
                        else {
                            System.out.println("Khong ton tai ma sinh vien ");
                        }
                        displayByPage(sinhViens,op);
                        break;
                    case 0:
                        break;

                }
            }
            while (pageOp >= 1 && pageOp < 7);
        }catch (Exception e) {
            System.out.println("nhap sai");
        }
    }
    public static void displayMonHocs (ArrayList<SinhVien> sinhViens,ArrayList<Diem> diems,ArrayList<MonHoc> monHocs) {
        int op = 0;
        int pageOp;
        displayByPage(sinhViens, op);
        try{
            do {
                System.out.println("1.Xem trang tiep theo     2.Tro lai trang truoc     3.Xem trang cu the");
                System.out.println("4.Xem trang dau tien      5.Xem trang cuoi cung     0.Tro ve MENU");
                System.out.println("6.Xem chi tiet bang diem");
                pageOp = sc.nextInt();
                switch (pageOp) {
                    case 1:
                        op = op+1;
                        displayByPage(sinhViens, op);
                        break;
                    case 2:
                        op = op-1;
                        if (op<0) {System.out.println("Hien dang ơ trang 1"); op = 0;}
                        displayByPage(sinhViens, op);
                        break;
                    case 3:
                        System.out.println("Nhap lua chon cua ban :");
                        sc.nextLine();
                        op = sc.nextInt();
                        displayByPage(sinhViens, op);
                        break;
                    case 4 :
                        op = 0;
                        displayByPage(sinhViens,op);
                        break;
                    case 5:
                        op = sinhViens.size()/20;
                        displayByPage(sinhViens,op);
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.println("Nhap ma sinh vien : ");
                        String masinhv = sc.nextLine();
                        SinhVien sinhVien = returnMaSV(masinhv,sinhViens);
                        if (checkMaSV(masinhv,sinhViens) != -1) {
                            displayBangDiem(sinhVien,diems,monHocs); }
                        else {
                            System.out.println("Khong ton tai ma sinh vien ");
                        }
                        displayByPage(sinhViens,op);
                        break;
                    case 0:
                        break;

                }
            }
            while (pageOp >= 1 && pageOp < 7);
        }catch (Exception e) {
            System.out.println("nhap sai");
        }
    }

    private static int checkMaSV(String masv, ArrayList<SinhVien> sinhViens) {
        for (int i=0;i<sinhViens.size();i++) {
            if (sinhViens.get(i).getMasv().equals(masv)) return i;
        }
        return -1;
    }
    private static SinhVien returnMaSV(String masv, ArrayList<SinhVien> sinhViens) {
        for (int i=0;i<sinhViens.size();i++) {
            if (sinhViens.get(i).getMasv().equals(masv)) return sinhViens.get(i);
        }
        return null;
    }
    private static boolean checkMaMH(String mamh, ArrayList<MonHoc> monHocs) {
        for (int i=0;i<monHocs.size();i++) {
            if (monHocs.get(i).getMaMH().equals(mamh)) return true;
        }
        return false;
    }
    private static MonHoc returnMaMH(String mamh, ArrayList<MonHoc> monHocs) {
        for (int i=0;i<monHocs.size();i++) {
            if (monHocs.get(i).getMaMH().equals(mamh)) return monHocs.get(i);
        }
        return null;
    }
    private static boolean checkDiem(String masv, ArrayList<Diem> diems) {
        for (int i=0;i<diems.size();i++) {
            if (diems.get(i).getMasv().equals(masv)) return true;
        }
        return false;
    }
    private static Diem returnDiem(String masv, ArrayList<Diem> diems) {
        for (int i=0;i<diems.size();i++) {
            if (diems.get(i).getMasv().equals(masv)) return diems.get(i);
        }
        return null;
    }

    public static SinhVien createSinhVien (SinhVien sv) {

       // Scanner sc = new Scanner(System.in);
        String maSV,hoDem,ten,gioiTinh,ngaySinh;
        gioiTinh = "";
        sc.nextLine();
        System.out.println("Nhap vao ma Sinh Vien : ");
        maSV = sc.nextLine();
        System.out.println("Nhap vao ho dem Sinh Vien : ");
        hoDem = sc.nextLine();
        System.out.println("Nhap vao ten Sinh Vien : ");
        ten = sc.nextLine();
        int option;
        do {
            System.out.println("Nhap vao gioi tinh (1.Nam 2.Nu) : ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    gioiTinh = "Nam";
                    break;
                case 2:
                    gioiTinh = "Nu";
                    break;
                default:
                    System.out.println("Nhap sai!Vui long nhap lai");
            }
        }while (option<1 || option>2);
        System.out.println("Nhap cho ngay - thang - nam :");
        int day = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();
        ngaySinh = day+"-"+month+"-"+year;
        sv = new SinhVien(maSV,hoDem,ten,gioiTinh,ngaySinh);
        return sv;
    }

    public static void repairSinhVien (String maSV,ArrayList<SinhVien> sinhViens) {
            for (int i=0;i<sinhViens.size();i++) {
                if (sinhViens.get(i).getMasv().equals(maSV)) {
                    System.out.println("Ban Muon Sua (1.TenDem 2.Ten 3.GioiTinh 4.NgaySinh");
                    int option = sc.nextInt();
                    System.out.println("Ban muon sua thanh : ");
                    sc.nextLine();
                    String repair = sc.nextLine();
                    switch (option) {
                        case 1:
                            sinhViens.get(i).setHoDem(repair);
                            break;
                        case 2:
                            sinhViens.get(i).setTen(repair);
                            break;
                        case 3:
                            sinhViens.get(i).setGioiTinh(repair);
                            break;
                        case 4:
                            sinhViens.get(i).setNgaySinh(repair);
                            break;
                        default:
                            System.out.println("Bạn nhập sai");
                            break;
                    }
                }
            }
    }

    public static void displaySinhVien (ArrayList<SinhVien> sinhViens) {
        for (int i=0;i<sinhViens.size();i++) {
            System.out.println(sinhViens.get(i));
        }
    }
    public static void displayMon (ArrayList<MonHoc> monHocs) {
        for (int i=0;i<monHocs.size();i++) {
            System.out.println(monHocs.get(i));
        }
    }
    public static void displayDiem (ArrayList<Diem> diems) {
        for (int i=0;i<diems.size();i++) {
            System.out.println(diems.get(i));
        }
    }

    public static void sortSVByName (ArrayList<SinhVien> sinhViens) {
        Comparator<SinhVien> com = new Comparator<SinhVien>() {
            @Override
            public int compare(SinhVien o1, SinhVien o2) {
                return o1.getTen().compareToIgnoreCase(o2.getTen());
            }
        };
        sinhViens.sort(com);
    }
    public static void sortDiemByMASV (ArrayList<Diem> diems) {
        Comparator<Diem> com = new Comparator<Diem>() {
            @Override
            public int compare(Diem o1, Diem o2) {
                return o1.getMasv().compareToIgnoreCase(o2.getMasv());
            }
        };
        diems.sort(com);
    }
    public static void sortMonHocByName (ArrayList<MonHoc> monHocs) {
        Comparator<MonHoc> com = new Comparator<MonHoc>() {
            @Override
            public int compare(MonHoc o1, MonHoc o2) {
                return o1.getTenMH().compareToIgnoreCase(o2.getTenMH());
            }
        };
        monHocs.sort(com);
    }

    public static void displayBangDiem (SinhVien sv,ArrayList<Diem> diems,ArrayList<MonHoc> monHocs) {
        System.out.println("┌───────────────────────────────────────────────────────┐");
        String name = sv.getHoDem()+ " "+sv.getTen();
        String str = "chua co diem";
        System.out.format("| %-10s %40s   |",sv.getMasv(),name);
        System.out.println();
        System.out.println(" ───────────────────────────────────────────────────────");
        ArrayList<Diem> diems1 = new ArrayList<Diem>();
        Diem diem = new Diem();
        for (Diem i : diems) {
            if (i.getMasv().equalsIgnoreCase(sv.getMasv())) {
                diems1.add(i);
            }
        }
        if (diems1.size()==0) {System.out.format("|%54s |",str);
        System.out.println();}
        else {
            for (int i=0;i<diems1.size();i++) {

                String mamh = diems1.get(i).getMamh();
                MonHoc monHoc = returnMaMH(mamh,monHocs);
                if (monHoc == null) continue;
                System.out.format("| %-10s  %-30s %10s |",monHoc.getMaMH(),monHoc.getTenMH(),diems1.get(i).getDiemSo());
                System.out.println();
            }
        }
        System.out.println("└───────────────────────────────────────────────────────┘");
    }
    public static void findNams (ArrayList<SinhVien> sinhViens,String name) {
        ArrayList<SinhVien> sinhViens1 = new ArrayList<SinhVien>();
        for (SinhVien i : sinhViens) {
            if (i.getTen().compareToIgnoreCase(name)==0) {
                sinhViens1.add(i);
            }
        }
        if (sinhViens1.size()==0) System.out.println("Khong co ket qua");
       displayByPage(sinhViens1,0,sinhViens1.size());
    }
    public static SinhVien findByName (ArrayList<SinhVien> sinhViens,String name) {
        for (int i=0;i<sinhViens.size();i++) {
            if(sinhViens.get(i).getTen().compareToIgnoreCase(name) == 0) {
                return sinhViens.get(i);
            }
        }
        return null;
    }

    public static void displayBangMH (ArrayList<Diem> diems,MonHoc monHoc) {
        ArrayList<Diem> diems1 = new ArrayList<Diem>();
        for (Diem diem : diems) {
            if (diem.getMamh1().compareToIgnoreCase(monHoc.getMaMH())==0) {
                diems1.add(diem);
            }
        }
        System.out.println("┌────────────────────────────────────────────────────┐");
        System.out.format("|%-10s %41s|",monHoc.getMaMH(),monHoc.getTenMH());
        System.out.println();
        displayMHs(diems1);
        System.out.println("└────────────────────────────────────────────────────┘");
    }
    public static String displayMH (Diem diem) {
        String name = diem.getSinhVien().getHoDem()+" "+diem.getSinhVien().getTen();
        String masv = diem.getSinhVien().getMasv();
        String str = String.format("|%-10s %30s %10s|",masv,name,diem.getDiemSo());
        return str;
    }
    public static void displayMHByPage (ArrayList<Diem> diems,int page) {
        int start = 20*page;
        int end = start+20;
        System.out.println(" ────────────────────────────────────────────────────");
        abc:for (int i= start;i<end;i++) {
            if (i > diems.size()-1) break abc;
            System.out.println(displayMH(diems.get(i)));
        }
        System.out.println("└────────────────────────────────────────────────────┘");
    }
    public static void displayMHs (ArrayList<Diem> diems) {
        int op = 0;
        int pageOp;
        displayMHByPage(diems, op);
        try{
            do {
                System.out.println("1.Xem trang tiep theo     2.Tro lai trang truoc     3.Xem trang cu the");
                System.out.println("4.Xem trang dau tien      5.Xem trang cuoi cung     0.Tro ve MENU");
                System.out.println("6.Xem chi tiet bang diem");
                pageOp = sc.nextInt();
                switch (pageOp) {
                    case 1:
                        op = op+1;
                        displayMHByPage(diems, op);
                        break;
                    case 2:
                        op = op-1;
                        if (op<0) {System.out.println("Hien dang ơ trang 1"); op = 0;}
                        displayMHByPage(diems, op);
                        break;
                    case 3:
                        System.out.println("Nhap lua chon cua ban :");
                        sc.nextLine();
                        op = sc.nextInt();
                        displayMHByPage(diems, op);
                        break;
                    case 4 :
                        op = 0;
                        displayMHByPage(diems, op);
                        break;
                    case 5:
                        op = diems.size()/20;
                        displayMHByPage(diems, op);
                        break;
                    case 0:
                        break;

                }
            }
            while (pageOp >= 1 && pageOp < 6);
        }catch (Exception e) {
            System.out.println("nhap sai");
        }
    }

}

