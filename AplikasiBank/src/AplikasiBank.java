import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Definisikan kelas RekeningBank
class RekeningBank {
    private String namaPemilik;
    private String jenisBank;
    private double saldo;

    public RekeningBank(String namaPemilik, String jenisBank) {
        this.namaPemilik = namaPemilik;
        this.jenisBank = jenisBank;
        this.saldo = 0;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public String getJenisBank() {
        return jenisBank;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void tambahSaldo(double jumlah) {
        saldo += jumlah;
    }

    public void tarikSaldo(double jumlah) {
        saldo -= jumlah;
    }
}

// Definisikan kelas AplikasiBank
public class AplikasiBank {
    private Map<String, RekeningBank> daftarRekening;

    public AplikasiBank() {
        this.daftarRekening = new HashMap<>();
    }

    public void buatRekening(String namaPemilik, String jenisBank) {
        if (!daftarRekening.containsKey(namaPemilik)) {
            daftarRekening.put(namaPemilik, new RekeningBank(namaPemilik, jenisBank));
            System.out.println("Rekening bank untuk " + namaPemilik + " dengan jenis " + jenisBank + " berhasil dibuat.");
        } else {
            System.out.println("Rekening bank untuk " + namaPemilik + " sudah ada.");
        }
    }

    public void cekSaldo(String namaPemilik) {
        if (daftarRekening.containsKey(namaPemilik)) {
            RekeningBank rekening = daftarRekening.get(namaPemilik);
            System.out.println("Saldo untuk " + namaPemilik + " di " + rekening.getJenisBank() + " adalah " + rekening.getSaldo());
        } else {
            System.out.println("Rekening bank untuk " + namaPemilik + " tidak ditemukan.");
        }
    }

    public void inputSaldo(String namaPemilik, double jumlah) {
        if (daftarRekening.containsKey(namaPemilik)) {
            RekeningBank rekening = daftarRekening.get(namaPemilik);
            rekening.tambahSaldo(jumlah);
            System.out.println("Saldo berhasil ditambahkan.");
        } else {
            System.out.println("Rekening bank untuk " + namaPemilik + " tidak ditemukan.");
        }
    }

    public void rekapTransaksiPerBulan(String namaPemilik) {
        if (daftarRekening.containsKey(namaPemilik)) {
            RekeningBank rekening = daftarRekening.get(namaPemilik);
            // Implementasi rekap transaksi per bulan di sini
            System.out.println("Rekap transaksi per bulan untuk " + namaPemilik);
        } else {
            System.out.println("Rekening bank untuk " + namaPemilik + " tidak ditemukan.");
        }
    }

    public void transfer(String namaPemilikAsal, String namaPemilikTujuan, double jumlah) {
        if (daftarRekening.containsKey(namaPemilikAsal) && daftarRekening.containsKey(namaPemilikTujuan)) {
            RekeningBank rekeningAsal = daftarRekening.get(namaPemilikAsal);
            RekeningBank rekeningTujuan = daftarRekening.get(namaPemilikTujuan);
            double biayaTransfer = (rekeningAsal.getJenisBank().equals(rekeningTujuan.getJenisBank())) ? 0.0 : 5.0; // Biaya transfer antar bank
            if (rekeningAsal.getSaldo() >= jumlah + biayaTransfer) {
                rekeningAsal.tarikSaldo(jumlah + biayaTransfer);
                rekeningTujuan.tambahSaldo(jumlah);
                System.out.println("Transfer berhasil dilakukan.");
            } else {
                System.out.println("Saldo tidak mencukupi untuk melakukan transfer.");
            }
        } else {
            System.out.println("Salah satu atau kedua rekening tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        AplikasiBank aplikasi = new AplikasiBank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Buat Rekening Bank");
            System.out.println("2. Cek Saldo");
            System.out.println("3. Input Saldo");
            System.out.println("4. Rekap Transaksi Per Bulan");
            System.out.println("5. Transfer");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("Masukkan nama pemilik rekening: ");
                    String namaPemilikBaru = scanner.next();
                    System.out.print("Masukkan jenis bank: ");
                    String jenisBankBaru = scanner.next();
                    aplikasi.buatRekening(namaPemilikBaru, jenisBankBaru);
                    break;
                case 2:
                    System.out.print("Masukkan nama pemilik rekening: ");
                    String namaPemilikCek = scanner.next();
                    aplikasi.cekSaldo(namaPemilikCek);
                    break;
                case 3:
                    System.out.print("Masukkan nama pemilik rekening: ");
                    String namaPemilikInput = scanner.next();
                    System.out.print("Masukkan jumlah saldo yang ingin ditambahkan: ");
                    double jumlahInput = scanner.nextDouble();
                    aplikasi.inputSaldo(namaPemilikInput, jumlahInput);
                    break;
                case 4:
                    System.out.print("Masukkan nama pemilik rekening: ");
                    String namaPemilikRekap = scanner.next();
                    aplikasi.rekapTransaksiPerBulan(namaPemilikRekap);
                    break;
                case 5:
                    System.out.print("Masukkan nama pemilik rekening pengirim: ");
                    String namaPemilikAsal = scanner.next();
                    System.out.print("Masukkan nama pemilik rekening penerima: ");
                    String namaPemilikTujuan = scanner.next();
                    System.out.print("Masukkan jumlah transfer: ");
                    double jumlahTransfer = scanner.nextDouble();
                    aplikasi.transfer(namaPemilikAsal, namaPemilikTujuan, jumlahTransfer);
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.exit(0);
                default:
                    System.out.println("Menu tidak valid.");
            }
        }
    }
}
