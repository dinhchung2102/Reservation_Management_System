package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class TaoTaiKhoan_GUI extends JFrame implements ActionListener, MouseListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Font fontMenu;
    private Font fontMenuItem;
    private JMenuBar mnuMenuBar;
    private JMenu mnuPhieuDatBan;
    private JMenuItem mniDatBan;
    private JMenuItem mniDSPhieuDatBan;
    private JMenuItem mniTimKiemPhieuDatBan;
    private JMenu mnuHoaDon;
    private JMenuItem mniXuatHoaDon;
    private JMenuItem mniDSHoaDon;
    private JMenu mnuKhuyenMai;
    private JMenuItem mniDSKhuyenMai;
    private JMenuItem mniThemKhuyenMai;
    private JMenu mnuKhachHang;
    private JMenuItem mniQuanLiKhachHang;
    private JMenu mnuBan;
    private JMenuItem mniQuanLiBan;
    private JMenu mnuMonAn;
    private JMenuItem mniDSMonAn;
    private JMenuItem mniThemMonAn;
    private JMenu mnuTaiKhoan;
    private JMenuItem mniThongKeDoanhThu;
    private JMenuItem mniThemNhanVien;
    private JMenuItem mniTaoTaiKhoan;
    private JMenuItem mniThongTinTaiKhoan;
    private JMenuItem mniDangXuat;

    private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
    private TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();

    private TaiKhoan taiKhoan;
    private Font fontTieuDe;
    private Font fontGiaoDien;
    private JPanel pnlTieuDe;
    private JPanel pnlTaoTaiKhoan;
    private JPanel pnlDanhSachTaiKhoản;
    private Font fontGiaoDienInDam;
    private JLabel lblBangTaiKhoan;
    private Container pnlBangTaiKhoan;
    private DefaultTableModel modelDanhSachTaiKhoan;
    private JTable tblDanhSachTaiKhoan;
    private JScrollPane scrDanhSachTaiKhoan;
    private JPanel pnlTenDangNhap;
    private JLabel lblTenDangNhap;
    private JTextField txtTenDangNhap;
    private JPanel pnlMatKhau;
    private JLabel lblMatKhau;
    private JTextField txtMatKhau;
    private JPanel pnlLoaiTaiKhoan;
    private JLabel lblLoaiTaiKhoan;
    private JComboBox cmbLoaiTaiKhoan;
    private Component txtLoaiTaiKhoan;
    private JPanel pnlMaNhanVien;
    private JLabel lblMaNhanVien;
    private JTextField txtMaNhanVien;
    private JPanel pnlTenNhanVien;
    private JLabel lblTenNhanVien;
    private JTextField txtTenNhanVien;
    private JLabel lblDanhSachTaiKhoản;
    private JPanel pnlDanhSachTaiKhoan;
    private JLabel lblDanhSachTaiKhoan;
    private JPanel pnlChucNang;
    private Button btnThem;
    private Button btnUpdate;
    private Button btnXoa;

    public TaoTaiKhoan_GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setTitle("CHƯƠNG TRÌNH QUẢN LÍ ĐẶT BÀN TRONG NHÀ HÀNG");

        // tạo font cho JMenu
        fontMenu = new Font(Font.SERIF, Font.BOLD, 30);
        // tạo font cho JMenuItem
        fontMenuItem = new Font(Font.SERIF, Font.PLAIN, 30);

        // Tạo menubar
        mnuMenuBar = new JMenuBar();
        mnuMenuBar.setBackground(Color.white);
        this.setJMenuBar(mnuMenuBar);

        // Tạo menu phiếu đặt bàn
        mnuPhieuDatBan = new JMenu("   Phiếu đặt bàn   ");
        mnuPhieuDatBan.setFont(fontMenu);
        mniDatBan = new JMenuItem("Đặt bàn");
        mniDatBan.setFont(fontMenuItem);
        mniDSPhieuDatBan = new JMenuItem("Danh sách phiếu đặt");
        mniDSPhieuDatBan.setFont(fontMenuItem);
        mniTimKiemPhieuDatBan = new JMenuItem("Tìm kiếm phiếu đặt");
        mniTimKiemPhieuDatBan.setFont(fontMenuItem);
        mnuPhieuDatBan.add(mniDatBan);
        mnuPhieuDatBan.addSeparator();
        mnuPhieuDatBan.add(mniDSPhieuDatBan);
        mnuPhieuDatBan.addSeparator();
        mnuPhieuDatBan.add(mniTimKiemPhieuDatBan);

        mniDatBan.addActionListener(this);
        mniDSPhieuDatBan.addActionListener(this);
        mniTimKiemPhieuDatBan.addActionListener(this);

        // Tạo menu hóa đơn
        mnuHoaDon = new JMenu("   Hóa đơn   ");
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
        mnuKhuyenMai = new JMenu("   Khuyến mãi   ");
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
        mniQuanLiBan = new JMenuItem("Quản lí bàn");
        mniQuanLiBan.setFont(fontMenuItem);
        mnuBan.add(mniQuanLiBan);

        mniQuanLiBan.addActionListener(this);

        // Tạo menu món ăn
        mnuMonAn = new JMenu("   Món ăn   ");
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

        // Tạo menu tài khoản
        mnuTaiKhoan = new JMenu();
        ImageIcon iconTaiKhoan = new ImageIcon("img//tai_khoan.png");
        iconTaiKhoan.setImage(iconTaiKhoan.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        mnuTaiKhoan.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        mnuTaiKhoan.setIcon(iconTaiKhoan);
        mniThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
        mniThongKeDoanhThu.setFont(fontMenuItem);
        mniThemNhanVien = new JMenuItem("Thêm nhân viên");
        mniThemNhanVien.setFont(fontMenuItem);
        mniTaoTaiKhoan = new JMenuItem("Tạo tài khoản");
        mniTaoTaiKhoan.setFont(fontMenuItem);
        mniThongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
        mniThongTinTaiKhoan.setFont(fontMenuItem);
        mniDangXuat = new JMenuItem("Đăng xuất");
        mniDangXuat.setFont(fontMenuItem);
        mnuTaiKhoan.add(mniThongKeDoanhThu);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniThemNhanVien);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniTaoTaiKhoan);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniThongTinTaiKhoan);
        mnuTaiKhoan.addSeparator();
        mnuTaiKhoan.add(mniDangXuat);

        mniThongKeDoanhThu.addActionListener(this);
        mniThemNhanVien.addActionListener(this);
        mniTaoTaiKhoan.addActionListener(this);
        mniThongTinTaiKhoan.addActionListener(this);
        mniDangXuat.addActionListener(this);

        mnuMenuBar.add(mnuPhieuDatBan);
        mnuMenuBar.add(mnuHoaDon);
        mnuMenuBar.add(mnuKhuyenMai);
        mnuMenuBar.add(mnuKhachHang);
        mnuMenuBar.add(mnuBan);
        mnuMenuBar.add(mnuMonAn);
        mnuMenuBar.add(mnuTaiKhoan);

        // Giao diện
        // Tạo font
        Font font = new Font("Times New Roman", 1, 30);
        // Sử dụng Map để thêm thuộc tính gạch chân
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        // Áp dụng font với gạch chân
        fontTieuDe = new Font(attributes);

        fontGiaoDien = new Font(Font.SERIF, Font.PLAIN, 25);
        fontGiaoDienInDam = new Font(Font.SERIF, Font.BOLD, 25);

        // JPanel tiêu đề
        pnlTieuDe = new JPanel();
        add(pnlTieuDe, BorderLayout.NORTH);

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("Tạo tài khoản", JLabel.CENTER);
        lblTieuDe.setFont(fontTieuDe);
        pnlTieuDe.add(lblTieuDe, BorderLayout.CENTER);

        // JPanel tạo tài khoản
        pnlTaoTaiKhoan = new JPanel();
        add(pnlTaoTaiKhoan, BorderLayout.CENTER);
        pnlTaoTaiKhoan.setLayout(new BoxLayout(pnlTaoTaiKhoan, BoxLayout.Y_AXIS));

        // Đặt khoảng cách lề trái 200 pixel, trên và dưới 0 pixel, lề phải 200 pixel
        pnlTaoTaiKhoan.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        // Tên đăng nhập
        pnlTenDangNhap = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblTenDangNhap = new JLabel("Tên đăng nhập: ");
        lblTenDangNhap.setFont(fontGiaoDienInDam);
        lblTenDangNhap.setPreferredSize(new Dimension(200, 20));
        txtTenDangNhap = new JTextField(20);
        txtTenDangNhap.setFont(fontGiaoDien);
        pnlTenDangNhap.add(lblTenDangNhap);
        pnlTenDangNhap.add(txtTenDangNhap);
        // Mật khẩu
        pnlMatKhau = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblMatKhau = new JLabel("Mật khẩu: ");
        lblMatKhau.setFont(fontGiaoDienInDam);
        lblMatKhau.setPreferredSize(new Dimension(200, 20));
        txtMatKhau = new JTextField(20);
        txtMatKhau.setFont(fontGiaoDien);
        pnlMatKhau.add(lblMatKhau);
        pnlMatKhau.add(txtMatKhau);
        // Loại tài khoản
        pnlLoaiTaiKhoan = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblLoaiTaiKhoan = new JLabel("Loại tài khoản: ");
        lblLoaiTaiKhoan.setFont(fontGiaoDienInDam);
        lblLoaiTaiKhoan.setPreferredSize(new Dimension(200, 20));
        String[] loaiTaiKhoan = { "nhanvien", "quanli" };
        cmbLoaiTaiKhoan = new JComboBox<>(loaiTaiKhoan);
        cmbLoaiTaiKhoan.setFont(fontGiaoDien);
        pnlLoaiTaiKhoan.add(lblLoaiTaiKhoan);
        pnlLoaiTaiKhoan.add(cmbLoaiTaiKhoan);
        // Mã nhân viên
        pnlMaNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblMaNhanVien = new JLabel("Mã nhân viên: ");
        lblMaNhanVien.setFont(fontGiaoDienInDam);
        lblMaNhanVien.setPreferredSize(new Dimension(200, 20));
        txtMaNhanVien = new JTextField(20);
        txtMaNhanVien.setFont(fontGiaoDien);
        pnlMaNhanVien.add(lblMaNhanVien);
        pnlMaNhanVien.add(txtMaNhanVien);
        // Tên nhân viên
        pnlTenNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(fontGiaoDienInDam);
        lblTenNhanVien.setPreferredSize(new Dimension(200, 20));
        txtTenNhanVien = new JTextField(20);
        txtTenNhanVien.setFont(fontGiaoDien);
        txtTenNhanVien.setEditable(false);
        pnlTenNhanVien.add(lblTenNhanVien);
        pnlTenNhanVien.add(txtTenNhanVien);
        // Phím chức năng
        pnlChucNang = new JPanel();
        btnThem = new Button("Tạo tài khoản");
        btnThem.setFont(fontGiaoDienInDam);
        btnThem.setBackground(Color.GREEN);
        btnUpdate = new Button("Cập nhật");
        btnUpdate.setFont(fontGiaoDienInDam);
        btnXoa = new Button("Xóa tài khoản");
        btnXoa.setBackground(Color.GRAY);
        btnXoa.setFont(fontGiaoDienInDam);
        pnlChucNang.add(btnThem);
        pnlChucNang.add(btnUpdate);
        pnlChucNang.add(btnXoa);

        // Thêm sự kiện cho các buttom
        btnThem.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnXoa.addActionListener(this);

        // Thêm tất cả các panel vào pnlTaoTaiKhoan
        pnlTaoTaiKhoan.add(pnlTenDangNhap);
        pnlTaoTaiKhoan.add(pnlMatKhau);
        pnlTaoTaiKhoan.add(pnlLoaiTaiKhoan);
        pnlTaoTaiKhoan.add(pnlMaNhanVien);
        pnlTaoTaiKhoan.add(pnlTenNhanVien);
        pnlTaoTaiKhoan.add(pnlChucNang);

        // Danh sách tài khoản hiện có
        pnlDanhSachTaiKhoan = new JPanel(new BorderLayout());
        pnlDanhSachTaiKhoan.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        add(pnlDanhSachTaiKhoan, BorderLayout.SOUTH);
        lblDanhSachTaiKhoan = new JLabel("Danh sách tài khoản hiện có:");
        lblDanhSachTaiKhoan.setFont(fontGiaoDienInDam);
        pnlDanhSachTaiKhoan.add(lblDanhSachTaiKhoan, BorderLayout.NORTH);
        String[] colsTaiKhoan = { "Tên đăng nhập", "Mật khẩu", "Loại tài khoản", "Mã nhân viên", "Tên nhân viên" };
        modelDanhSachTaiKhoan = new DefaultTableModel(colsTaiKhoan, 0);
        tblDanhSachTaiKhoan = new JTable(modelDanhSachTaiKhoan);
        tblDanhSachTaiKhoan.setFont(fontGiaoDien);
        tblDanhSachTaiKhoan.setRowHeight(40);
        tblDanhSachTaiKhoan.getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 18));
        scrDanhSachTaiKhoan = new JScrollPane(tblDanhSachTaiKhoan);
        scrDanhSachTaiKhoan.setPreferredSize(new Dimension(1500, 600));
        pnlDanhSachTaiKhoan.add(scrDanhSachTaiKhoan, BorderLayout.CENTER);

        tblDanhSachTaiKhoan.addMouseListener(this);
        txtMaNhanVien.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTenNhanVien();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTenNhanVien();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTenNhanVien();
            }
        });

        // Cập nhật dữ liệu vào bảng tài khoản
        updateDanhSachTaiKhoan();
        setVisible(true);
    }

    public TaoTaiKhoan_GUI(TaiKhoan tk) {
        this();
        this.taiKhoan = tk;
        mnuTaiKhoan.setText("Nhân viên: " + taiKhoan.getNhanVien().getTenNV());

        if(!taiKhoan.getLoaiTaiKhoan().equals("quanli")) {
            mniThemNhanVien.setEnabled(false);
            mniTaoTaiKhoan.setEnabled(false);
        }
    }

    public void updateDanhSachTaiKhoan() {
        modelDanhSachTaiKhoan.setRowCount(0);
        List<TaiKhoan> listTaiKhoan = taiKhoan_DAO.getAllTaiKhoan();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            modelDanhSachTaiKhoan.addRow(taiKhoan.getObjTaiKhoan());
        }
    }

    private void updateTenNhanVien() {
        int maNhanVien = 0;
        try {
            maNhanVien = Integer.parseInt(txtMaNhanVien.getText());
        } catch (NumberFormatException e) {
            System.err.println("Mã nhân viên phải là một số nguyên hợp lệ.");
        }
        NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
        if (nhanVien != null) {
            txtTenNhanVien.setText(nhanVien.getTenNV());
        } else {
            txtTenNhanVien.setText("");
        }
    }

    private TaiKhoan getObjectFields() {
        String tenDangNhap = txtTenDangNhap.getText();
        String matKhau = txtMatKhau.getText();
        String loaiTaiKhoan = cmbLoaiTaiKhoan.getSelectedItem().toString();
        int maNhanVien = Integer.parseInt(txtMaNhanVien.getText());
        NhanVien nhanVien = nhanVien_DAO.getNhanVienTheoMa(maNhanVien);
        if (nhanVien != null) {
            return new TaiKhoan(tenDangNhap, matKhau, loaiTaiKhoan, nhanVien);
        } else {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(mniDatBan)) {
            this.dispose();
            new FormManHinhChinh(taiKhoan.getNhanVien());
        } else if (o.equals(mniDSPhieuDatBan)) {
            this.dispose();
            new FormPhieuDatBan(taiKhoan.getNhanVien());
        } else if (o.equals(mniTimKiemPhieuDatBan)) {

        } else if (o.equals(mniXuatHoaDon)) {
            this.dispose();
            new XuatHoaDon_GUI(taiKhoan);
        } else if (o.equals(mniDSHoaDon)) {
            this.dispose();
            new DanhSachHoaDon_GUI(taiKhoan);
        } else if (o.equals(mniDSKhuyenMai)) {
            this.dispose();
            new KhuyenMaiGUI(taiKhoan.getNhanVien());
        } else if (o.equals(mniThemKhuyenMai)) {
            this.dispose();
            new KhuyenMaiGUI(taiKhoan.getNhanVien());
        } else if (o.equals(mnuKhachHang)) {

        } else if (o.equals(mnuBan)) {

        } else if (o.equals(mniDSMonAn)) {

        } else if (o.equals(mniThemMonAn)) {

        } else if (o.equals(mniThongKeDoanhThu)) {

        } else if (o.equals(mniThemNhanVien)) {
            this.dispose();
        } else if (o.equals(mniTaoTaiKhoan)) {
            this.dispose();
            new TaoTaiKhoan_GUI(taiKhoan);
        } else if (o.equals(mniThongTinTaiKhoan)) {
            this.dispose();
            new ThongTinTaiKhoan_GUI(taiKhoan);
        } else if (o.equals(mniDangXuat)) {
            this.dispose();
            new DangNhap_GUI();
        }
        /// Các chức năng
        else if (o.equals(btnThem)) {
            TaiKhoan taiKhoan = getObjectFields();
            if (taiKhoan != null) {
                if (taiKhoan_DAO.taoTaiKhoan(taiKhoan)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công.");
                    updateDanhSachTaiKhoan();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản null");
            }
        } else if (o.equals(btnUpdate)) {
            TaiKhoan taiKhoan = getObjectFields();
            if (taiKhoan != null) {
                if (taiKhoan_DAO.capNhatTaiKhoan(taiKhoan)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
                    updateDanhSachTaiKhoan();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản null");
            }
        } else if (o.equals(btnXoa)) {
            int row = tblDanhSachTaiKhoan.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Chưa chọn tài khoản để xoá!");
                return;
            }
            String tenDangNhap = txtTenDangNhap.getText();
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá tài khoản " + tenDangNhap + " ?",
                    "Xác nhận xoá", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (taiKhoan_DAO.xoaTaiKhoan(tenDangNhap)) {
                    updateDanhSachTaiKhoan();
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi!!!!!!!");
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int row = tblDanhSachTaiKhoan.getSelectedRow();
        txtTenDangNhap.setText(modelDanhSachTaiKhoan.getValueAt(row, 0).toString());
        txtMatKhau.setText(modelDanhSachTaiKhoan.getValueAt(row, 1).toString());
        cmbLoaiTaiKhoan.setSelectedItem(modelDanhSachTaiKhoan.getValueAt(row, 2).toString());
        txtMaNhanVien.setText(modelDanhSachTaiKhoan.getValueAt(row, 3).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
