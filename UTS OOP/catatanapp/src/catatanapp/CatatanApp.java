package catatanapp;
/**
 * @author I Putu Eka Saputra
 * 10 mei 2025
 */

import java.util.Scanner;

class Catatan {
    private int id;
    private String isi;

    
    public Catatan(int id, String isi) {
        this.id = id;
        this.isi = isi;
    }

    
    public Catatan() {
        this.id = 0;
        this.isi = "";
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    
    @Override
    public String toString() {
        return "Catatan ID: " + id + ", Isi: " + isi;
    }
}

public class CatatanApp {
    private Catatan[] catatanArray;
    private int kapasitas;
    private int jumlahCatatan;
    private Scanner scanner;

    public CatatanApp(int kapasitas) {
        this.kapasitas = kapasitas;
        this.catatanArray = new Catatan[kapasitas];
        this.jumlahCatatan = 0;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CatatanApp app = new CatatanApp(100); // max 100 notes
        app.menu();
    }

    public void menu() {
        int pilihan = 0;
        do {
            System.out.println("\n=== Menu Aplikasi Catatan ===");
            System.out.println("1. Tambah Catatan");
            System.out.println("2. Tampilkan Semua Catatan");
            System.out.println("3. Ubah Catatan");
            System.out.println("4. Hapus Catatan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                pilihan = 0;
            }
            switch (pilihan) {
                case 1:
                    tambahCatatan();
                    break;
                case 2:
                    tampilkanCatatan();
                    break;
                case 3:
                    ubahCatatan();
                    break;
                case 4:
                    hapusCatatan();
                    break;
                case 5:
                    System.out.println("Terima kasih. Keluar dari aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);
    }

    private void tambahCatatan() {
        if (jumlahCatatan >= kapasitas) {
            System.out.println("Kapasitas catatan penuh!");
            return;
        }
        System.out.print("Masukkan isi catatan: ");
        String isi = scanner.nextLine();
        int idBaru = (jumlahCatatan == 0) ? 1 : catatanArray[jumlahCatatan-1].getId() + 1;
        Catatan catatan = new Catatan(idBaru, isi);
        catatanArray[jumlahCatatan] = catatan;
        jumlahCatatan++;
        System.out.println("Catatan berhasil ditambahkan.");
    }

    private void tampilkanCatatan() {
        if (jumlahCatatan == 0) {
            System.out.println("Belum ada catatan yang tersimpan.");
            return;
        }
        System.out.println("\nDaftar Catatan:");
        for (int i = 0; i < jumlahCatatan; i++) {
            System.out.println(catatanArray[i].toString());
        }
    }

    private void ubahCatatan() {
        if (jumlahCatatan == 0) {
            System.out.println("Belum ada catatan untuk diubah.");
            return;
        }
        System.out.print("Masukkan ID catatan yang akan diubah: ");
        int idUbah;
        try {
            idUbah = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return;
        }
        int index = cariIndexById(idUbah);
        if (index == -1) {
            System.out.println("Catatan dengan ID tersebut tidak ditemukan.");
            return;
        }
        System.out.print("Masukkan isi catatan baru: ");
        String isiBaru = scanner.nextLine();
        catatanArray[index].setIsi(isiBaru);
        System.out.println("Catatan berhasil diubah.");
    }

    private void hapusCatatan() {
        if (jumlahCatatan == 0) {
            System.out.println("Belum ada catatan untuk dihapus.");
            return;
        }
        System.out.print("Masukkan ID catatan yang akan dihapus: ");
        int idHapus;
        try {
            idHapus = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
            return;
        }
        int index = cariIndexById(idHapus);
        if (index == -1) {
            System.out.println("Catatan dengan ID tersebut tidak ditemukan.");
            return;
        }
        
        for (int i = index; i < jumlahCatatan - 1; i++) {
            catatanArray[i] = catatanArray[i + 1];
        }
        catatanArray[jumlahCatatan - 1] = null;
        jumlahCatatan--;
        System.out.println("Catatan berhasil dihapus.");
    }

    private int cariIndexById(int id) {
        for (int i = 0; i < jumlahCatatan; i++) {
            if (catatanArray[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}

