import java.io.*;
import java.util.ArrayList;

import static java.lang.Float.parseFloat;

public class FileProcessing {
    FileWriter fileWriter ;
    BufferedWriter bufferedWriter;
    FileReader fileReader;
    BufferedReader bufferedReader;
    public void openFileToWrite (String fileName) {
        fileWriter = null;
        bufferedWriter = null;
        try {
            fileWriter = new FileWriter(new File(fileName),true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeAfterWrite (String fileName) {
        try {
            if (bufferedWriter != null){
                bufferedWriter.close();
                fileReader.close();}
        }catch (Exception e) {
        }
    }

    public void openFileToRead (String fileName) {
        fileReader = null;
        bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFileAfterRead (String fileName) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSinhVienOnFile (String fileName,ArrayList<SinhVien> sinhViens) throws IOException {
        openFileToWrite(fileName);
        for (int i=0;i< sinhViens.size();i++) {
            SinhVien sv = sinhViens.get(i);
            String sinhVien = sv.getMasv()+"/"+sv.getHoDem()+"/"+sv.getTen()+"/"+sv.getGioiTinh()+"/"+sv.getNgaySinh();
            bufferedWriter.write(sinhVien);
            bufferedWriter.newLine();
        }
        closeAfterWrite(fileName);
    }

    public void addDiemOnFile (String fileDiem,ArrayList<Diem> diems) throws IOException {
        openFileToWrite(fileDiem);
        for (int i=0;i<diems.size();i++) {
            Diem diem = diems.get(i);
            String diemSo = diem.getMamh()+"/"+diem.getMasv()+"/"+diem.getDiemSo();
            bufferedWriter.write(diemSo);
            bufferedWriter.newLine();
        }
        closeAfterWrite(fileDiem);
    }

    public void addMonHocOnFile (String fileDiem,ArrayList<MonHoc> monHocs) throws IOException {
        openFileToWrite(fileDiem);
        for (int i=0;i<monHocs.size();i++) {
            MonHoc diem = monHocs.get(i);
            String diemSo = diem.getMaMH()+"/"+diem.getTenMH()+"/"+diem.getHeSo();
            bufferedWriter.write(diemSo);
            bufferedWriter.newLine();
        }
        closeAfterWrite(fileDiem);
    }

    public ArrayList<SinhVien> getSinhVien (String fileName) {
        openFileToRead(fileName);
        ArrayList<SinhVien> sinhViens = new ArrayList<SinhVien>();
            String line;
                try {
                   String first = bufferedReader.readLine();
                    do {
                        line = bufferedReader.readLine();
                        if (line == null) break;
                        String sv[] = line.split("/");
                        SinhVien sinhVien = new SinhVien(sv[0],sv[1],sv[2],sv[3],sv[4]);
                        sinhViens.add(sinhVien);
                    }while (line != null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            closeFileAfterRead(fileName);
        return sinhViens;
    }

    public ArrayList<MonHoc> getMonHoc (String fileMonHoc) {
        openFileToRead(fileMonHoc);
        ArrayList<MonHoc> monHocs = new ArrayList<MonHoc>();
        String line="";
            try {
                String first = bufferedReader.readLine();
                do {
                line = bufferedReader.readLine();
                if (line == null) break;
                String mh[] = line.split(";");
                MonHoc monHoc = new MonHoc(mh[0],mh[1],Float.valueOf(mh[2]));
                monHocs.add(monHoc);
                }while (line != null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        closeFileAfterRead(fileMonHoc);
            return monHocs;
    }

    public ArrayList<Diem> getDiem (String fileDiem) {
        openFileToRead(fileDiem);
        ArrayList<Diem> diems = new ArrayList<Diem>();
        String line;
        try {
            String first = bufferedReader.readLine();
            do {
                line = bufferedReader.readLine();
                if (line == null) break;
                String sv[] = line.split(";");
                Diem diem = new Diem(sv[0],sv[1],Float.valueOf(sv[2]));
                diems.add(diem);
            }while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeFileAfterRead(fileDiem);
        return diems;
    }

    public ArrayList<Diem> getDiem (String fileDiem,String fileName,String fileMonHoc) {
        ArrayList<SinhVien> sinhViens= getSinhVien(fileName);
        ArrayList<MonHoc> monHocs = getMonHoc(fileMonHoc);
        openFileToRead(fileDiem);
        ArrayList<Diem> diems = new ArrayList<Diem>();
        String line;
        try {
            String first = bufferedReader.readLine();
            do {
                line = bufferedReader.readLine();
                if (line == null) break;
                String sv[] = line.split(";");
                String masv = sv[0];
                SinhVien sinhVien = new SinhVien();
                for (int i=0;i<sinhViens.size();i++) {
                    if (sinhViens.get(i).getMasv().compareToIgnoreCase(masv)==0) {
                        sinhVien = sinhViens.get(i);
                        break;
                    }
                }
                String mamh = sv[1];
                MonHoc monHoc = new MonHoc();
                for (int j=0;j<monHocs.size();j++) {
                    if (monHocs.get(j).getMaMH().compareToIgnoreCase(mamh)==0) {
                        monHoc = monHocs.get(j);
                        break;
                    }
                }
                Diem diem = new Diem(sinhVien,monHoc,Float.valueOf(sv[2]));
                diems.add(diem);
            }while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeFileAfterRead(fileDiem);
        return diems;
    }
    public void repairFile (String fileName, ArrayList<SinhVien> sv) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            addSinhVienOnFile(fileName,sv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
