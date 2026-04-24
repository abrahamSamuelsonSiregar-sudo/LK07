# LK07 Pemrograman Lanjut TIF-C angkatan 2025
Latihan Kerja 07 Mata Kuliah Pemrograman Lanjut TIF-C angkatan 2025.
Deskripsi:
Membuat program Create, Read, Update, Delete (CRUD) basis Java Graphical User Interface (GUI)
untuk data siswa yang disimpan pada file siswa.csv.

Anggota :
1. Hamam Yusuf Abdulloh
2. Abraham Samullson Siregar
3. Ridho Alfarizi
4. Muhammad Dzaky Nuril Mubin
5. Hanif Maulana

Class Siswa.java
- variable (private): String nis, String nama, String alamat.
- constructor
- Getter & Setter
- toCSV: memberi return String dengan format "nis,nama,alamat".

Class FileHandler.java
- import: java.io.* & java.util.*.
- variable (private static final): String FILE_NAME = "siswa.csv"
  (menyimpan nama file .csv)
- static list<Siswa> readFile(): untuk membaca file "siswa.csv". Jika file belum
  ada, buat file bernama "siswa.csv".
- static void writeFile(List<Siswa> list): memasukkan data ke file "siswa.csv".

Class MainFrame.java  
deskripsi: berisi tampilan GUI yang dapat dijalankan.
- void loadData(): merefresh tampilan saat aksi (create, update, delete) dilakukan.
- void tambahData(): menambahkan data yang sudah diketik di panel input.
- void updateData(): meng-edit data yang dipilih.
- void deleteData(): menghapus data yang dipilih.
- void clearForm(): mengosongkan isi panel input.
- void isiForm(): mengisi panel input dengan nilai pada baris yang sedang dipilih.
- static void main(String[] args): menampilkan GUI ke layar.
