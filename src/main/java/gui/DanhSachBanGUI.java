package gui;

import dao.DAO_Ban;
import dao.TaiKhoan_DAO;
import entity.Ban;
import dao.DAO_KhuVuc;
import entity.KhuVuc;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DanhSachBanGUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton addButton, deleteButton, updateButton;
    private JTextField loaiBanField, soGheNgoiField, moTaField;
    private JComboBox<String> maKhuVucComboBox;
    private JTable tableTable;
    private DefaultTableModel tableModel;
    private int currentKhuVuc = 1; // Mặc định là Lầu 1

    private  JMenu mnuDatBan;
    private  JMenuItem mniDSPhieuDatBan;
    private  JMenuItem mniTimKiemPhieuDatBan;
    private  JMenuItem mniXuatHoaDon;
    private  JMenuItem mniDSHoaDon;
    private  JMenuItem mniDSKhuyenMai;
    private  JMenuItem mniThemKhuyenMai;
    private  JMenu mnuKhachHang;
    private  JMenuItem mniQuanLiKhachHang;
    private  JMenu mnuBan;
    private  JMenuItem mniDSMonAn;
    private  JMenuItem mniThemMonAn;
    private  JMenuItem mniThongKeDoanhThu;
    private  JMenuItem mniThongTinTaiKhoan;
    private  JMenuItem mniDangXuat;

    private  NhanVien nhanVien;
    public DanhSachBanGUI(NhanVien nhanVien) {
        setTitle("Danh Sách Bàn Nhà Hàng");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.nhanVien = nhanVien;
        // tạo font cho JMenu
        /*
         *
         *
         *
         *
         *
         *
         *
         * */
        Font fontMenu = new Font(Font.SERIF, Font.BOLD, 25);
        // tạo font cho JMenuItem
        Font fontMenuItem = new Font(Font.SERIF, Font.PLAIN, 25);

        // Tạo menubar
        JMenuBar mnuMenuBar = new JMenuBar();
        mnuMenuBar.setBackground(Color.white);
        this.setJMenuBar(mnuMenuBar);

        // Tạo menu đặt bàn
        mnuDatBan = new JMenu("     Đặt bàn     ");
        mnuDatBan.setFont(fontMenu);
        mnuDatBan.setBorder(new LineBorder(Color.white, 1));

        mnuDatBan.addActionListener(this);

        // Tạo menu phiếu đặt bàn
        JMenu mnuPhieuDatBan = new JMenu("   Phiếu đặt bàn   ");
        mnuPhieuDatBan.setFont(fontMenu);
        mniDSPhieuDatBan = new JMenuItem("Danh sách phiếu đặt");
        mniDSPhieuDatBan.setFont(fontMenuItem);
        mniTimKiemPhieuDatBan = new JMenuItem("Tìm kiếm phiếu đặt");
        mniTimKiemPhieuDatBan.setFont(fontMenuItem);
        mnuPhieuDatBan.add(mniDSPhieuDatBan);
        mnuPhieuDatBan.addSeparator();
        mnuPhieuDatBan.add(mniTimKiemPhieuDatBan);

        mniDSPhieuDatBan.addActionListener(this);
        mniTimKiemPhieuDatBan.addActionListener(this);

        // Tạo menu hóa đơn
        JMenu mnuHoaDon = new JMenu("   Hóa đơn   ");
        mnuHoaDon.setFont(fontMenu);
        mniXuatHoaDon = new JMenuItem("Xuất hóa đơn");
        mniXuatHoaDon.setFont(fontMenuItem);
        mniDSHoaDon = new JMenuItem("Danh sách hóa đơn");
        mniDSHoaDon.setFont(fontMenuItem);
        mnuHoaDon.add(mniXuatHoaDon);
        mnuHoaDon.addSeparator();
        mnuHoaDon.add(mniDSHoaDon);

        mniXuatHoaDon.addActionListener(this);
        mniDSHoaDon.addActionListener(this);

        // Tạo menu khuyến mãi
        JMenu mnuKhuyenMai = new JMenu("   Khuyến mãi   ");
        mnuKhuyenMai.setFont(fontMenu);
        mniDSKhuyenMai = new JMenuItem("Danh sách khuyến mãi");
        mniDSKhuyenMai.setFont(fontMenuItem);
        mniThemKhuyenMai = new JMenuItem("Thêm khuyến mãi");
        mniThemKhuyenMai.setFont(fontMenuItem);
        mnuKhuyenMai.add(mniDSKhuyenMai);
        mnuKhuyenMai.addSeparator();
        mnuKhuyenMai.add(mniThemKhuyenMai);

        mniDSKhuyenMai.addActionListener(this);
        mniThemKhuyenMai.addActionListener(this);

        // Tạo menu khách hàng
        mnuKhachHang = new JMenu("   Khách hàng   ");
        mnuKhachHang.setFont(fontMenu);
        mniQuanLiKhachHang = new JMenuItem("Quản lí khách hàng");
        mniQuanLiKhachHang.setFont(fontMenuItem);
        mnuKhachHang.add(mniQuanLiKhachHang);

        mniQuanLiKhachHang.addActionListener(this);

        // Tạo menu bàn
        mnuBan = new JMenu("   Bàn   ");
        mnuBan.setFont(fontMenu);
        JMenuItem mniQuanLiBan = new JMenuItem("Quản lí bàn");
        mniQuanLiBan.setFont(fontMenuItem);
        mniQuanLiBan.addActionListener(e->{
            new DanhSachBanGUI(nhanVien).setVisible(true);
            this.dispose();
        });
        mnuBan.add(mniQuanLiBan);

        mniQuanLiBan.addActionListener(this);

        // Tạo menu món ăn
        JMenu mnuMonAn = new JMenu("   Món ăn   ");
        mnuMonAn.setFont(fontMenu);
        mniDSMonAn = new JMenuItem("Danh sách món ăn");
        mniDSMonAn.setFont(fontMenuItem);
        mniThemMonAn = new JMenuItem("Thêm món ăn");
        mniThemMonAn.setFont(fontMenuItem);
        mnuMonAn.add(mniDSMonAn);
        mnuMonAn.addSeparator();
        mnuMonAn.add(mniThemMonAn);

        mniDSMonAn.addActionListener(this);
        mniThemMonAn.addActionListener(this);

        mnuBan.setOpaque(true);
        mnuBan.setBackground(Color.GREEN);

        // Tạo menu tài khoản
        JMenu mnuTaiKhoan = new JMenu();
        ImageIcon iconTaiKhoan = new ImageIcon("image//userIcon.png");
        iconTaiKhoan.setImage(iconTaiKhoan.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        mnuTaiKhoan.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        mnuTaiKhoan.setIcon(iconTaiKhoan);
        mniThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
        mniThongKeDoanhThu.setFont(fontMenuItem);
        mniThongKeDoanhThu.addActionListener(e->{
            ThongKeDoanhThuGUI tkNew = new ThongKeDoanhThuGUI(nhanVien);
            tkNew.setVisible(true);
            this.dispose();
        });
        JMenuItem mniThongKeDoanhThuTheoCa = new JMenuItem(("Thống kê doanh thu theo ca"));
        mniThongKeDoanhThuTheoCa.setFont(fontMenuItem);
        mniThongKeDoanhThuTheoCa.addActionListener(e->{
            ThongKeDoanhThuTheoCaGUI tkNew = new ThongKeDoanhThuTheoCaGUI(nhanVien);
            tkNew.setVisible(true);
            this.dispose();
        });
        JMenuItem mniThemNhanVien = new JMenuItem("Thêm nhân viên");
        mniThemNhanVien.setFont(fontMenuItem);
        mniThemNhanVien.addActionListener(e->{
            new ThemNhanVien_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV()));
            this.dispose();
        });
        JMenuItem mniTaoTaiKhoan = new JMenuItem("Tạo tài khoản");
        mniTaoTaiKhoan.setFont(fontMenuItem);
        mniThongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
        mniThongTinTaiKhoan.setFont(fontMenuItem);
        mniDangXuat = new JMenuItem("Đăng xuất");
        mniDangXuat.setFont(fontMenuItem);

        mniThongKeDoanhThuTheoCa = new JMenuItem(("Thống kê doanh thu theo ca"));
        mniThongKeDoanhThuTheoCa.setFont(fontMenuItem);
        mniThongKeDoanhThuTheoCa.addActionListener(e->{
            ThongKeDoanhThuTheoCaGUI tkNew = new ThongKeDoanhThuTheoCaGUI(nhanVien);
            tkNew.setVisible(true);
            this.dispose();
        });

        mnuTaiKhoan.add(mniThongKeDoanhThu);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniThongKeDoanhThuTheoCa);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniThemNhanVien);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniTaoTaiKhoan);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniThongTinTaiKhoan);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniDangXuat);

        mniTaoTaiKhoan.addActionListener(e->{
            new TaoTaiKhoan_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV())).setVisible(true);
        });
        mniThongKeDoanhThu.addActionListener(this);
        mniThongTinTaiKhoan.addActionListener(this);
        mniDangXuat.addActionListener(this);

        mnuMenuBar.add(mnuDatBan);
        mnuMenuBar.add(mnuPhieuDatBan);
        mnuMenuBar.add(mnuHoaDon);
        mnuMenuBar.add(mnuKhuyenMai);
        mnuMenuBar.add(mnuKhachHang);
        mnuMenuBar.add(mnuBan);
        mnuMenuBar.add(mnuMonAn);
        mnuMenuBar.add(mnuTaiKhoan);




        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230)); // Màu xanh nhạt

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(173, 216, 230));
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);

        JLabel loaiBanLabel = new JLabel("Loại Bàn:");
        loaiBanLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(loaiBanLabel, gbc);

        loaiBanField = new JTextField(20);
        loaiBanField.setFont(fieldFont);
        gbc.gridx = 1;
        inputPanel.add(loaiBanField, gbc);

        JLabel soGheNgoiLabel = new JLabel("Số Ghế Ngồi:");
        soGheNgoiLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(soGheNgoiLabel, gbc);

        soGheNgoiField = new JTextField(20);
        soGheNgoiField.setFont(fieldFont);
        gbc.gridx = 1;
        inputPanel.add(soGheNgoiField, gbc);

        JLabel moTaLabel = new JLabel("Mô Tả:");
        moTaLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(moTaLabel, gbc);

        moTaField = new JTextField(20);
        moTaField.setFont(fieldFont);
        gbc.gridx = 1;
        inputPanel.add(moTaField, gbc);

        JLabel maKhuVucLabel = new JLabel("Khu Vực:");
        maKhuVucLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(maKhuVucLabel, gbc);

        maKhuVucComboBox = new JComboBox<>(new String[]{"Lầu 1", "Lầu 2", "Lầu 3"});
        maKhuVucComboBox.setFont(fieldFont);
        gbc.gridx = 1;
        inputPanel.add(maKhuVucComboBox, gbc);

        addButton = new JButton("Thêm mới");
        deleteButton = new JButton("Xóa bàn");
        updateButton = new JButton("Thay đổi thông tin");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(173, 216, 230));
        addButton.setPreferredSize(new Dimension(150, 40));
        deleteButton.setPreferredSize(new Dimension(150, 40));
        updateButton.setPreferredSize(new Dimension(150, 40));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(173, 216, 230));
        JLabel titleLabel = new JLabel("Thông tin bàn", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        String[] columnNames = {"Mã Bàn", "Loại Bàn", "Số Ghế Ngồi", "Mô Tả", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableTable = new JTable(tableModel);
        tableTable.setFont(new Font("Arial", Font.PLAIN, 18));
        tableTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(tableTable);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(titlePanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        tableTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tableTable.getSelectedRow();
                    if (selectedRow != -1) {
                        // Lấy dữ liệu từ dòng được chọn
                        String loaiBan = tableModel.getValueAt(selectedRow, 1).toString();
                        String soGheNgoi = tableModel.getValueAt(selectedRow, 2).toString();
                        String moTa = tableModel.getValueAt(selectedRow, 3).toString();
                        String trangThai = tableModel.getValueAt(selectedRow, 4).toString();

                        // Điền dữ liệu vào các trường nhập liệu
                        loaiBanField.setText(loaiBan);
                        soGheNgoiField.setText(soGheNgoi);
                        moTaField.setText(moTa);
                    }
                }
            }
        });

        addButton.addActionListener(e -> {
            String loaiBan = loaiBanField.getText();
            String soGheNgoi = soGheNgoiField.getText();
            String moTa = moTaField.getText();
            String selectedKhuVuc = (String) maKhuVucComboBox.getSelectedItem();

            if (!loaiBan.isEmpty() && !soGheNgoi.isEmpty() && !moTa.isEmpty() && selectedKhuVuc != null) {
                int maKhuVuc = maKhuVucComboBox.getSelectedIndex() + 1;
                Ban ban = new Ban(loaiBan, Integer.parseInt(soGheNgoi), moTa, false, new KhuVuc());
                KhuVuc khuVuc = new DAO_KhuVuc().getKhuVucById(maKhuVuc);
                new DAO_Ban().themBan(ban);
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = tableTable.getSelectedRow();
            if (selectedRow != -1) {
                int maBan = (int) tableModel.getValueAt(selectedRow, 0);
                new DAO_Ban().xoaBan(maBan);
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedRow = tableTable.getSelectedRow();
            if (selectedRow != -1) {
                int maBan = (int) tableModel.getValueAt(selectedRow, 0);
                String loaiBan = loaiBanField.getText();
                String soGheNgoi = soGheNgoiField.getText();
                String moTa = moTaField.getText();
                String selectedKhuVuc = (String) maKhuVucComboBox.getSelectedItem();

                if (!loaiBan.isEmpty() && !soGheNgoi.isEmpty() && !moTa.isEmpty() && selectedKhuVuc != null) {
                    Ban ban = new Ban(loaiBan, Integer.parseInt(soGheNgoi), moTa, false, new KhuVuc());
                    new DAO_Ban().capNhatBan(ban);
                    loadData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        maKhuVucComboBox.addActionListener(e -> {
            currentKhuVuc = maKhuVucComboBox.getSelectedIndex() + 1;
            loadData();
        });

        loadData();
    }
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(mniDSPhieuDatBan)) {
            FormPhieuDatBan newFormPhieuDatBan = new FormPhieuDatBan(nhanVien);
            newFormPhieuDatBan.setVisible(true);
            this.dispose();
        } else if (o.equals(mniTimKiemPhieuDatBan)) {
            this.dispose();
            FormPhieuDatBan newFormPhieuDatBan = new FormPhieuDatBan(nhanVien);
            newFormPhieuDatBan.setVisible(true);
        } else if (o.equals(mniDSHoaDon)) {
            DanhSachHoaDon_GUI newFrm = new DanhSachHoaDon_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV()));
        } else if (o.equals(mniXuatHoaDon)) {
            XuatHoaDon_GUI newFrm = new XuatHoaDon_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV()));
            newFrm.setVisible(true);
            this.dispose();
        } else if (o.equals(mniDSKhuyenMai)) {
            this.dispose();
            KhuyenMaiGUI khuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            khuyenMaiGUI.setVisible(true);
        } else if (o.equals(mniThemKhuyenMai)) {
            this.dispose();
            KhuyenMaiGUI khuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            khuyenMaiGUI.setVisible(true);
        } else if (o.equals(mnuKhachHang)) {
            KhachHangGUI newKhachHangGUI = new KhachHangGUI(nhanVien);
            newKhachHangGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mnuBan)) {


        } else if (o.equals(mniDSMonAn)) {
            dispose();
            MonAnGUI monAnGUI = new MonAnGUI(nhanVien);
            monAnGUI.setVisible(true);

        } else if (o.equals(mniThemMonAn)) {
            dispose();
            MonAnGUI monAnGUI = new MonAnGUI(nhanVien);
            monAnGUI.setVisible(true);
        } else if (o.equals(mniQuanLiKhachHang)) {
            this.dispose();
            KhachHangGUI khachHang = new KhachHangGUI(nhanVien);
            khachHang.setVisible(true);
        } else if (o.equals(mniThongKeDoanhThu)) {
            ThongKeDoanhThuGUI newFrm = new ThongKeDoanhThuGUI(nhanVien);
            newFrm.setVisible(true);
            this.dispose();
        } else if (o.equals(mniThongTinTaiKhoan)) {
            TaiKhoan_DAO daoTK = new TaiKhoan_DAO();
            TaiKhoan taiKhoan = daoTK.getTaiKhoanByMaNhanVien(nhanVien.getMaNV());
            ThongTinTaiKhoan_GUI newTT = new ThongTinTaiKhoan_GUI(taiKhoan);
            newTT.setVisible(true);
            this.dispose();
        }else if (o.equals(mniDangXuat)) {
            int response = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn đăng xuất?",
                    "Xác nhận đăng xuất",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                this.dispose();
                DangNhap_GUI dangNhap_GUI = new DangNhap_GUI();
                dangNhap_GUI.setVisible(true);
            }
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<Ban> banList = new DAO_Ban().getBansByKhuVuc(maKhuVucComboBox.getSelectedItem().toString());
        for (Ban ban : banList) {
            tableModel.addRow(new Object[]{ban.getMaBan(), ban.getLoaiBan(), ban.getSoGheNgoi(), ban.getMoTa(), ban.isTrangThai() ? "Có" : "Không"});
        }
    }

    private void clearFields() {
        loaiBanField.setText("");
        soGheNgoiField.setText("");
        moTaField.setText("");
    }


}
