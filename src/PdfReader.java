import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfReader {

    static String ambilNama(String teks) {
        Pattern pattern = Pattern.compile(
                "Nama lengkap\\s*:\\s*([\\s\\S]*?);"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).replaceAll("\\s+", " ").trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilTempatLahir(String teks) {
        Pattern pattern = Pattern.compile(
                "Tempat lahir\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilUmur(String teks) {
        Pattern pattern = Pattern.compile(
                "Umur/ Tanggal lahir\\s*:\\s*(\\d+) Tahun"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "Tidak ditemukan";
    }

    static String ambilTanggalLahir(String teks) {
        Pattern pattern = Pattern.compile(
                "Umur/ Tanggal lahir\\s*:\\s*\\d+ Tahun\\s*/\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilJenisKelamin(String teks) {
        Pattern pattern = Pattern.compile(
                "Jenis kelamin\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilKebangsaan(String teks) {
        Pattern pattern = Pattern.compile(
                "Kebangsaan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilPekerjaan(String teks) {
        Pattern pattern = Pattern.compile(
                "Pekerjaan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static String ambilPendidikan(String teks) {
        Pattern pattern = Pattern.compile(
                "Pendidikan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    static void tampilkanData(Data<?> data) {
        System.out.println("Data : " + data.getNilai());
    }

    public static void main(String[] args) throws Exception {

        File file = new File("Data/putusan.pdf");

        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper stripper = new PDFTextStripper();

        String teks = stripper.getText(document);

        String nama = ambilNama(teks);
        String tempatLahir = ambilTempatLahir(teks);
        String umur = ambilUmur(teks);
        String tanggalLahir = ambilTanggalLahir(teks);
        String jenisKelamin = ambilJenisKelamin(teks);
        String kebangsaan = ambilKebangsaan(teks);
        String pekerjaan = ambilPekerjaan(teks);
        String pendidikan = ambilPendidikan(teks);

        JenisKelamin jk;

        if (jenisKelamin.equalsIgnoreCase("Laki-laki")) {
            jk = JenisKelamin.LAKI_LAKI;
        } else {
            jk = JenisKelamin.PEREMPUAN;
        }

        Data<String> dataNama = new Data<>(nama);
        Data<Integer> dataUmur = new Data<>(Integer.parseInt(umur));

        System.out.println("=== DATA TERDAKWA ===");
        System.out.println("Nama          : " + dataNama.getNilai());
        System.out.println("Tempat Lahir  : " + tempatLahir);
        System.out.println("Tanggal Lahir : " + tanggalLahir);
        System.out.println("Umur          : " + dataUmur.getNilai());
        System.out.println("Jenis Kelamin : " + jk);
        System.out.println("Kebangsaan    : " + kebangsaan);
        System.out.println("Pekerjaan     : " + pekerjaan);
        System.out.println("Pendidikan    : " + pendidikan);

        System.out.println("\n=== WILDCARD ===");
        tampilkanData(dataNama);
        tampilkanData(dataUmur);

        document.close();
    }
}