import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private JTextField txtNis, txtNama, txtAlamat;
    private JTable table;
    private DefaultTableModel model;

    private List<Siswa> list = new ArrayList<>();

    public MainFrame() {
        setTitle("Data Siswa SMP");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== Judul =====
        JLabel lblTitle = new JLabel("DATA SISWA", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

        // ===== Form Input =====
        JPanel panelInput = new JPanel(new GridLayout(3, 2));
        panelInput.add(new JLabel("NIS"));
        txtNis = new JTextField();
        panelInput.add(txtNis);

        panelInput.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Alamat"));
        txtAlamat = new JTextField();
        panelInput.add(txtAlamat);

        // ===== Tombol =====
        JPanel panelButton = new JPanel();

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnDelete);
        panelButton.add(btnClear);

        // ===== Table =====
        model = new DefaultTableModel(new String[]{"NIS", "Nama", "Alamat"}, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // ===== Layout =====
        setLayout(new BorderLayout());
        add(lblTitle, BorderLayout.NORTH);
        add(panelInput, BorderLayout.WEST);
        add(panelButton, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // ===== Load Data Awal =====
        loadData();

        // ===== Event =====
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());
        btnClear.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> isiForm());
    }

    private void loadData() {
        list = FileHandler.readFile();
        model.setRowCount(0);

        for (Siswa s : list) {
            model.addRow(new Object[]{
                s.getNis(), s.getNama(), s.getAlamat()
            });
        }
    }

    private void tambahData() {
        String nis = txtNis.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();

        // Validasi NIS unik
        for (Siswa s : list) {
            if (s.getNis().equals(nis)) {
                JOptionPane.showMessageDialog(this, "NIS sudah ada!");
                return;
            }
        }

        list.add(new Siswa(nis, nama, alamat));
        FileHandler.writeFile(list);
        loadData();
        clearForm();
    }

    private void updateData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        list.get(row).setNama(txtNama.getText());
        list.get(row).setAlamat(txtAlamat.getText());

        FileHandler.writeFile(list);
        loadData();
    }

    private void deleteData() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        list.remove(row);
        FileHandler.writeFile(list);
        loadData();
        clearForm();
    }

    private void clearForm() {
        txtNis.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
    }

    private void isiForm() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtNis.setText(model.getValueAt(row, 0).toString());
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtAlamat.setText(model.getValueAt(row, 2).toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}