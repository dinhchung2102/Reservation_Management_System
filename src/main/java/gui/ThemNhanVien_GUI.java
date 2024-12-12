package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;

public class ThemNhanVien_GUI extends JFrame implements ActionListener, MouseListener {
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private TaiKhoan taiKhoan;
    private JPanel pnlThemNhanVien;
    private Font fontTieuDe;
    private Font fontGiaoDien;
    private JPanel pnlTieuDe;
    private DefaultTableModel modelDanhSachNhanVien;
    private JTable tblDanhSachNhanVien;
    private JScrollPane scrDanhSachNhanVien;
    private JLabel lblTenNhanVien;
    private JLabel lblGioiTinh;
    private JLabel lblNgaySinh;
    private JLabel lblSoDienThoai;
    private JLabel lblEmail;
    private JLabel lblDiaChi;
    private JPanel pnlTenNhanVien;
    private JPanel pnlGioiTinh;
    private JPanel pnlNgaySinh;
    private JPanel pnlSoDienThoai;
    private JPanel pnlEmail;
    private JPanel pnlDiaChi;
    private JPanel pnlBangNhanVien;
    private JLabel lblBangNhanVien;
    private JTextField txtTenNhanVien;
    private JComboBox cmbGioiTinh;
    private JTextField txtNgaySinh;
    private JTextField txtSoDienThoai;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private Font fontGiaoDienInDam;
    private JPanel pnlChucNang;
    private Button btnThem;
    private Button btnXoa;
    private Button btnUpdate;

    public ThemNhanVien_GUI() {
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

        // Tạo giao diện chính

        Font font = new Font("Times New Roman", 1, 25);
        // Sử dụng Map để thêm thuộc tính gạch chân
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        // Áp dụng font với gạch chân
        fontTieuDe = new Font(attributes);

        fontGiaoDien = new Font(Font.SERIF, Font.PLAIN, 20);
        fontGiaoDienInDam = new Font(Font.SERIF, Font.BOLD, 20);

        // JPanel tiêu đề
        pnlTieuDe = new JPanel();
        add(pnlTieuDe, BorderLayout.NORTH);

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("Thêm nhân viên", JLabel.CENTER);
        lblTieuDe.setFont(fontTieuDe);
        pnlTieuDe.add(lblTieuDe, BorderLayout.CENTER);

        // JPannel thêm nhân viên mới
        pnlThemNhanVien = new JPanel();
        add(pnlThemNhanVien, BorderLayout.CENTER);
        pnlThemNhanVien.setLayout(new BoxLayout(pnlThemNhanVien, BoxLayout.Y_AXIS));

        // Đặt khoảng cách lề trái 200 pixel, trên và dưới 0 pixel, lề phải 200 pixel
        pnlThemNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));

        // Tên nhân viên
        pnlTenNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(fontGiaoDienInDam);
        lblTenNhanVien.setPreferredSize(new Dimension(200, 20));
        txtTenNhanVien = new JTextField(20);
        txtTenNhanVien.setFont(fontGiaoDien);// Ô nhập tên nhân viên
        pnlTenNhanVien.add(lblTenNhanVien);
        pnlTenNhanVien.add(txtTenNhanVien);

        // Giới tính
        pnlGioiTinh = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblGioiTinh = new JLabel("Giới tính: ");
        lblGioiTinh.setFont(fontGiaoDienInDam);
        lblGioiTinh.setPreferredSize(new Dimension(200, 20));
        String[] gioiTinhOptions = { "Nam", "Nữ" };
        cmbGioiTinh = new JComboBox<>(gioiTinhOptions);
        cmbGioiTinh.setFont(fontGiaoDien);// Ô chọn giới tính
        pnlGioiTinh.add(lblGioiTinh);
        pnlGioiTinh.add(cmbGioiTinh);

        // Ngày sinh
        pnlNgaySinh = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(fontGiaoDienInDam);
        lblNgaySinh.setPreferredSize(new Dimension(200, 20));
        txtNgaySinh = new JTextField(10);// Ô nhập ngày sinh
        txtNgaySinh.setFont(fontGiaoDien);
        txtNgaySinh.setText("yyyy-MM-dd");
        pnlNgaySinh.add(lblNgaySinh);
        pnlNgaySinh.add(txtNgaySinh);

        // Số Điện thoại
        pnlSoDienThoai = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblSoDienThoai = new JLabel("Số điện thoại: ");
        lblSoDienThoai.setFont(fontGiaoDienInDam);
        lblSoDienThoai.setPreferredSize(new Dimension(200, 20));
        txtSoDienThoai = new JTextField(15); // Ô nhập số điện thoại
        txtSoDienThoai.setFont(fontGiaoDien);
        pnlSoDienThoai.add(lblSoDienThoai);
        pnlSoDienThoai.add(txtSoDienThoai);

        // Email
        pnlEmail = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblEmail = new JLabel("Email: ");
        lblEmail.setFont(fontGiaoDienInDam);
        lblEmail.setPreferredSize(new Dimension(200, 20));
        txtEmail = new JTextField(20);// Ô nhập email
        txtEmail.setFont(fontGiaoDien);
        pnlEmail.add(lblEmail);
        pnlEmail.add(txtEmail);

        // Địa chỉ
        pnlDiaChi = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Căn lề trái
        lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(fontGiaoDienInDam);
        lblDiaChi.setPreferredSize(new Dimension(200, 20));
        txtDiaChi = new JTextField(25); // Ô nhập địa chỉ
        txtDiaChi.setFont(fontGiaoDien);
        pnlDiaChi.add(lblDiaChi);
        pnlDiaChi.add(txtDiaChi);

        // Các phím chức năng
        pnlChucNang = new JPanel();
        btnThem = new Button("Thêm nhân viên");
        btnThem.setFont(fontGiaoDienInDam);
        btnThem.setBackground(Color.GREEN);
        btnUpdate = new Button("Sửa thông tin nhân viên");
        btnUpdate.setFont(fontGiaoDienInDam);
        btnXoa = new Button("Xóa nhân viên");
        btnXoa.setBackground(Color.GRAY);
        btnXoa.setFont(fontGiaoDienInDam);
        pnlChucNang.add(btnThem);
        pnlChucNang.add(btnUpdate);
        pnlChucNang.add(btnXoa);

        // Thêm sự kiện cho các buttom
        btnThem.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnXoa.addActionListener(this);

        // Thêm tất cả các panel vào pnlThemNhanVien
        pnlThemNhanVien.add(pnlTenNhanVien);
        pnlThemNhanVien.add(pnlGioiTinh);
        pnlThemNhanVien.add(pnlNgaySinh);
        pnlThemNhanVien.add(pnlSoDienThoai);
        pnlThemNhanVien.add(pnlEmail);
        pnlThemNhanVien.add(pnlDiaChi);
        pnlThemNhanVien.add(pnlChucNang);

        // Bảng nhân viên hiện có
        pnlBangNhanVien = new JPanel(new BorderLayout());
        pnlBangNhanVien.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 200));
        add(pnlBangNhanVien, BorderLayout.SOUTH);
        lblBangNhanVien = new JLabel("Danh sách nhân viên hiện có:");
        lblBangNhanVien.setFont(fontGiaoDienInDam);
        pnlBangNhanVien.add(lblBangNhanVien, BorderLayout.NORTH);
        String[] colsNhanVien = { "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email",
                "Địa chỉ" };
        modelDanhSachNhanVien = new DefaultTableModel(colsNhanVien, 0);
        tblDanhSachNhanVien = new JTable(modelDanhSachNhanVien);
        tblDanhSachNhanVien.setFont(fontGiaoDien);
        tblDanhSachNhanVien.setRowHeight(40);
        tblDanhSachNhanVien.getTableHeader().setFont(new Font(Font.SERIF, Font.BOLD, 18));
        scrDanhSachNhanVien = new JScrollPane(tblDanhSachNhanVien);
        scrDanhSachNhanVien.setPreferredSize(new Dimension(1000, 400));
        pnlBangNhanVien.add(scrDanhSachNhanVien, BorderLayout.CENTER);

        tblDanhSachNhanVien.addMouseListener(this);

        // Cập nhật dữ liệu vào bảng nhân viên
        updateTableNhanVien();

        setVisible(true);
    }

    public ThemNhanVien_GUI(TaiKhoan tk) {
        this();
        this.taiKhoan = tk;
        mnuTaiKhoan.setText("Nhân viên: " + taiKhoan.getNhanVien().getTenNV());


        if(!taiKhoan.getLoaiTaiKhoan().equals("quanli")) {
            mniThemNhanVien.setEnabled(false);
            mniTaoTaiKhoan.setEnabled(false);
        }
    }

    public void updateTableNhanVien() {
        modelDanhSachNhanVien.setRowCount(0);
        ArrayList<NhanVien> listNhanVien = nhanVien_DAO.getAllNhanVien();
        for (NhanVien nhanVien : listNhanVien) {
            modelDanhSachNhanVien.addRow(nhanVien.getObjNhanVien());
        }
    }

    private NhanVien getObjectFields() {
        try {
            String tenNV = txtTenNhanVien.getText();
            if (tenNV.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tên bị rỗng!");
                return null;
            }
            if (!tenNV.matches("^[A-Za-zÀ-ỹ][A-Za-zÀ-ỹ\\s]*$")) {
                JOptionPane.showMessageDialog(null,
                        "Tên nhân viên phải bắt đầu bằng chữ cái viết hoa và mỗi từ phải cách nhau bằng khoảng trắng.");
                return null;
            }
            String gioiTinh = cmbGioiTinh.getSelectedItem().toString();

            dateFormat.setLenient(false);
            Date ngaySinh = (Date) dateFormat.parse(txtNgaySinh.getText());

            String soDT = txtSoDienThoai.getText();
            if (soDT.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Số điện thoại rỗng!");
                return null;
            }
            if (!soDT.matches("^0\\d{8,11}$")) {
                JOptionPane.showMessageDialog(null, "Số điện thoại bắt đầu bằng 0 và có từ 9 đến 12 ký tự!");
                return null;
            }
            String email = txtEmail.getText();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email rỗng!");
                return null;
            }
            String diaChi = txtDiaChi.getText();
            if (diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Địa chỉ rỗng!");
                return null;
            }
            return new NhanVien(tenNV, gioiTinh, (java.sql.Date) ngaySinh, soDT, email, diaChi);
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, e.getMessage());
            return null;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(mniDatBan)) {
            this.dispose();
        } else if (o.equals(mniDSPhieuDatBan)) {
            this.dispose();
        } else if (o.equals(mniTimKiemPhieuDatBan)) {

        } else if (o.equals(mniXuatHoaDon)) {
            this.dispose();
            new XuatHoaDon_GUI(taiKhoan);
        } else if (o.equals(mniDSHoaDon)) {
            this.dispose();
            new DanhSachHoaDon_GUI(taiKhoan);
        } else if (o.equals(mniDSKhuyenMai)) {
            this.dispose();
        } else if (o.equals(mniThemKhuyenMai)) {
            this.dispose();
        } else if (o.equals(mnuKhachHang)) {

        } else if (o.equals(mnuBan)) {

        } else if (o.equals(mniDSMonAn)) {

        } else if (o.equals(mniThemMonAn)) {

        } else if (o.equals(mniThongKeDoanhThu)) {

        } else if (o.equals(mniThemNhanVien)) {
            this.dispose();
            new ThemNhanVien_GUI(taiKhoan);
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
            NhanVien nhanVien = getObjectFields();
            if (nhanVien != null) {
//				JOptionPane.showMessageDialog(null, "Nhân viên không null");
//				System.out.println(nhanVien.toString());
                if (nhanVien_DAO.addNhanVien(nhanVien)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công.");
                    updateTableNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nhân viên null");
            }
        } else if (o.equals(btnUpdate)) {
            int row = tblDanhSachNhanVien.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Chọn nhân viên để sửa thông tin");
                return;
            }
            int maNhanVien = (int) modelDanhSachNhanVien.getValueAt(row, 0);
            NhanVien nhanVien = getObjectFields();
            if (nhanVien != null) {
//				JOptionPane.showMessageDialog(null, "Nhân viên không null");
//				System.out.println(nhanVien.toString());
                if (nhanVien_DAO.capNhatNhanVien(nhanVien, maNhanVien)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công.");
                    updateTableNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nhân viên null");
            }
        } else if (o.equals(btnXoa)) {
            int row = tblDanhSachNhanVien.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Chưa chọn dòng để xoá!");
                return;
            }
            int maNhanVien = (int) modelDanhSachNhanVien.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá nhân viên " + maNhanVien + " ?",
                    "Xác nhận xoá", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (nhanVien_DAO.xoaNhanVien(maNhanVien)) {
                    updateTableNhanVien();
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi! Đang có tài khoản được liên kết.");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int row = tblDanhSachNhanVien.getSelectedRow();
        txtTenNhanVien.setText(modelDanhSachNhanVien.getValueAt(row, 1).toString());
        cmbGioiTinh.setSelectedItem(modelDanhSachNhanVien.getValueAt(row, 2).toString());
        txtNgaySinh.setText(modelDanhSachNhanVien.getValueAt(row, 3).toString());
        txtSoDienThoai.setText(modelDanhSachNhanVien.getValueAt(row, 4).toString());
        txtEmail.setText(modelDanhSachNhanVien.getValueAt(row, 5).toString());
        txtDiaChi.setText(modelDanhSachNhanVien.getValueAt(row, 6).toString());
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
