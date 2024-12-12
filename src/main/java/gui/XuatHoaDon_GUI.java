package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.*;
import entity.ChiTietHoaDon;
import entity.ChiTietPhieuDatBan;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.PhieuDatBan;
import entity.TaiKhoan;

public class XuatHoaDon_GUI extends JFrame implements ActionListener {
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

    private TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();

    private TaiKhoan taiKhoan;
    private Font fontTieuDe;
    private Font fontGiaoDien;
    private Font fontGiaoDienInDam;
    private JPanel pnlTieuDe;
    private JPanel pnlThongTinHoaDon;
    private JLabel lblMaPhieuDatBan;
    private JLabel lblNgayTaoPhieu;
    private JLabel lblTenKhachHang;
    private JLabel lblSDTKhachHang;
    private JLabel lblThoiGianDat;
    private JLabel lblBan;
    private JLabel lblKhuVuc;
    private JLabel lblTenNhanVien;
    private JLabel lblDanhSachDatMon;
    private DefaultTableModel modelDanhSachDatMon;
    private JTable tblDanhSachDatMon;
    private JScrollPane scrDanhSachDatMon;
    private JPanel pnlChiTietHoaDon;
    private JTextField txtMaPhieuDatBan;
    private JLabel lblKhuyenMai;

    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
    private ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();
    private PhieuDatBan_DAO phieuDatBan_DAO = new PhieuDatBan_DAO();
    private ChiTietPhieuDatBan_DAO chiTietPhieuDatBan_DAO = new ChiTietPhieuDatBan_DAO();
    private KhuyenMaiDAO khuyenMai_DAO = new KhuyenMaiDAO();
    private Thue_DAO thue_DAO = new Thue_DAO();

    private Double tongTien;
    private Double tongTienCuoi;
    private KhuyenMai khuyenMai;

    private JComboBox<String> cmbKhuyenMai;
    private JLabel lblThue;
    private JLabel lblGiamGia;
    private JLabel lblTongTien;
    private JLabel lblTongTienCuoi;
    private JPanel pnlChonKhuyenMai;
    private JPanel pnlMaPhieuDatBan;
    private JPanel pnlNgayTaoPhieu;
    private JPanel pnlTenKhachHang;
    private JPanel pnlSDTKhachHang;
    private JPanel pnlThoiGianDat;
    private JPanel pnlBan;
    private JPanel pnlKhuVuc;
    private JPanel pnlTenNhanVien;
    private JPanel pnlDanhSachDatMon;
    private JPanel pnlTongTien;
    private JPanel pnlGiamGia;
    private JPanel pnlThue;
    private JPanel pnlTongTienCuoi;
    private JPanel pnlButtomXuatHoaDon;
    private Button btnXuatHoaDon;
    private JButton btnTimKiem;
    private JTextField txtTongTienCuoi;
    private JTextField txtGiamGia;

    private JButton btnXacNhanKhuyenMai;

    public XuatHoaDon_GUI() {
        setTitle("Quản lý đặt bàn");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // tạo font cho JMenu
        fontMenu = new Font(Font.SERIF, Font.BOLD, 25);
        // tạo font cho JMenuItem
        fontMenuItem = new Font(Font.SERIF, Font.PLAIN, 25
        );

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
        mnuHoaDon.setOpaque(true);
        mnuHoaDon.setBackground(Color.GREEN);
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

        fontGiaoDien = new Font(Font.SERIF, Font.PLAIN, 15);
        fontGiaoDienInDam = new Font(Font.SERIF, Font.BOLD, 15);

        // JPanel tiêu đề
        pnlTieuDe = new JPanel();
        add(pnlTieuDe, BorderLayout.NORTH);

        // Tiêu đề
        JLabel lblTieuDe = new JLabel("Xuất hóa đơn", JLabel.CENTER);
        lblTieuDe.setFont(fontTieuDe);
        pnlTieuDe.add(lblTieuDe, BorderLayout.CENTER);

        /////////////////////
        // Tạo JPanel cha với BoxLayout
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        add(parentPanel, BorderLayout.CENTER);

        // Đặt khoảng cách lề trái 500 pixel
        parentPanel.setBorder(BorderFactory.createEmptyBorder(0, 550, 0, 550));

        // JPanel hiển thị chi tiết hóa đơn

        pnlChiTietHoaDon = new JPanel();
        pnlChiTietHoaDon.setMaximumSize(new Dimension(800, 1000));
        pnlChiTietHoaDon.setPreferredSize(new Dimension(800, 1000));

        parentPanel.add(pnlChiTietHoaDon);

        // Tạo viền
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Chi tiết hóa đơn: ");
        titledBorder.setTitleFont(new Font(Font.SERIF, Font.BOLD, 25));
        pnlChiTietHoaDon.setBorder(titledBorder);
        pnlChiTietHoaDon.setLayout(new BoxLayout(pnlChiTietHoaDon, BoxLayout.Y_AXIS));

        // Chi tiết phiếu đặt bàn
        pnlMaPhieuDatBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblMaPhieuDatBan = new JLabel("Mã phiếu đặt bàn: ");
        lblMaPhieuDatBan.setFont(fontGiaoDien);
        txtMaPhieuDatBan = new JTextField(10);
        txtMaPhieuDatBan.setFont(fontGiaoDien);
        btnTimKiem = new JButton("Tìm");
        btnTimKiem.setFont(fontGiaoDien);
        pnlMaPhieuDatBan.add(lblMaPhieuDatBan);
        pnlMaPhieuDatBan.add(txtMaPhieuDatBan);
        pnlMaPhieuDatBan.add(btnTimKiem);

        btnTimKiem.addActionListener(this);

        pnlNgayTaoPhieu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblNgayTaoPhieu = new JLabel("Ngày tạo phiếu: ");
        lblNgayTaoPhieu.setFont(fontGiaoDien);
        pnlNgayTaoPhieu.add(lblNgayTaoPhieu);

        pnlTenKhachHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTenKhachHang = new JLabel("Tên khách hàng: ");
        lblTenKhachHang.setFont(fontGiaoDien);
        pnlTenKhachHang.add(lblTenKhachHang);

        pnlSDTKhachHang = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblSDTKhachHang = new JLabel("Số điện thoại: ");
        lblSDTKhachHang.setFont(fontGiaoDien);
        pnlSDTKhachHang.add(lblSDTKhachHang);

        pnlThoiGianDat = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblThoiGianDat = new JLabel("Thời gian đặt: ");
        lblThoiGianDat.setFont(fontGiaoDien);
        pnlThoiGianDat.add(lblThoiGianDat);

        pnlBan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblBan = new JLabel("Bàn: ");
        lblBan.setFont(fontGiaoDien);
        pnlBan.add(lblBan);

        pnlKhuVuc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblKhuVuc = new JLabel("Khu vực: ");
        lblKhuVuc.setFont(fontGiaoDien);
        pnlKhuVuc.add(lblKhuVuc);

        pnlTenNhanVien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(fontGiaoDien);
        pnlTenNhanVien.add(lblTenNhanVien);

        pnlDanhSachDatMon = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblDanhSachDatMon = new JLabel("Danh sách món: ");
        lblDanhSachDatMon.setFont(fontGiaoDien);
        pnlDanhSachDatMon.add(lblDanhSachDatMon);

//				 Thêm các JLabel vào panel
        pnlChiTietHoaDon.add(pnlMaPhieuDatBan);
        pnlChiTietHoaDon.add(pnlNgayTaoPhieu);
        pnlChiTietHoaDon.add(pnlTenKhachHang);
        pnlChiTietHoaDon.add(pnlSDTKhachHang);
        pnlChiTietHoaDon.add(pnlThoiGianDat);
        pnlChiTietHoaDon.add(pnlBan);
        pnlChiTietHoaDon.add(pnlKhuVuc);
        pnlChiTietHoaDon.add(pnlTenNhanVien);
        pnlChiTietHoaDon.add(pnlDanhSachDatMon);


        String[] colsDatMon = { "Tên món", "Số lượng", "Thành tiền" };
        modelDanhSachDatMon = new DefaultTableModel(colsDatMon, 0);
        tblDanhSachDatMon = new JTable(modelDanhSachDatMon);
        tblDanhSachDatMon.setRowHeight(40);
        tblDanhSachDatMon.setFont(fontGiaoDien);
        tblDanhSachDatMon.getTableHeader().setFont(fontGiaoDienInDam);
        // căn giữa cho các cột

        // Tạo renderer cho căn lề giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tblDanhSachDatMon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblDanhSachDatMon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblDanhSachDatMon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        scrDanhSachDatMon = new JScrollPane(tblDanhSachDatMon);

        pnlTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTongTien = new JLabel("Tổng tiền: ");
        lblTongTien.setFont(fontGiaoDien);
        pnlTongTien.add(lblTongTien);

        pnlChonKhuyenMai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblKhuyenMai = new JLabel("Khuyến mãi: ");
        lblKhuyenMai.setFont(fontGiaoDien);
        cmbKhuyenMai = new JComboBox<String>();
        cmbKhuyenMai.setFont(fontGiaoDien);
        List<KhuyenMai> listKhuyenMai = khuyenMai_DAO.getAllKhuyenMai();
        for (KhuyenMai khuyenMai : listKhuyenMai) {
            cmbKhuyenMai.addItem(khuyenMai.getTenKM());
        }
        btnXacNhanKhuyenMai = new JButton("OK");
        btnXacNhanKhuyenMai.setFont(fontGiaoDien);
        pnlChonKhuyenMai.add(lblKhuyenMai);
        pnlChonKhuyenMai.add(cmbKhuyenMai);
        pnlChonKhuyenMai.add(btnXacNhanKhuyenMai);

        btnXacNhanKhuyenMai.addActionListener(this);

        pnlGiamGia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblGiamGia = new JLabel("Giảm giá: ");
        lblGiamGia.setFont(fontGiaoDien);
        txtGiamGia = new JTextField(10);
        txtGiamGia.setFont(fontGiaoDien);
        txtGiamGia.setEditable(false);
        pnlGiamGia.add(lblGiamGia);
        pnlGiamGia.add(txtGiamGia);
        pnlGiamGia.add(new JLabel("%"));

        pnlThue = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblThue = new JLabel("Thuế: " + thue_DAO.getThueTheoMa(1).getTenThue());
        lblThue.setFont(fontGiaoDien);
        pnlThue.add(lblThue);

        pnlTongTienCuoi = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTongTienCuoi = new JLabel("Tổng tiền cuối: ");
        lblTongTienCuoi.setFont(fontGiaoDien);
        txtTongTienCuoi = new JTextField(10);
        txtTongTienCuoi.setFont(fontGiaoDien);
        txtTongTienCuoi.setEditable(false);
        pnlTongTienCuoi.add(lblTongTienCuoi);
        pnlTongTienCuoi.add(txtTongTienCuoi);

        // Chức năng
        pnlButtomXuatHoaDon = new JPanel();
        btnXuatHoaDon = new Button("Xuất hóa đơn");
        btnXuatHoaDon.setFont(fontGiaoDien);
        btnXuatHoaDon.setBackground(Color.green);
        pnlButtomXuatHoaDon.add(btnXuatHoaDon);

        btnXuatHoaDon.addActionListener(this);

        pnlChiTietHoaDon.add(scrDanhSachDatMon);
        pnlChiTietHoaDon.add(pnlTongTien);
        pnlChiTietHoaDon.add(pnlChonKhuyenMai);
        pnlChiTietHoaDon.add(pnlGiamGia);
        pnlChiTietHoaDon.add(pnlThue);
        pnlChiTietHoaDon.add(pnlTongTienCuoi);
        pnlChiTietHoaDon.add(pnlButtomXuatHoaDon);

        setVisible(true);
    }

    public XuatHoaDon_GUI(TaiKhoan tk) {
        this();
        this.taiKhoan = tk;
        mnuTaiKhoan.setText("Nhân viên: " + taiKhoan.getNhanVien().getTenNV());

        if (!taiKhoan.getLoaiTaiKhoan().equals("quanli")) {
            mniThemNhanVien.setEnabled(false);
            mniTaoTaiKhoan.setEnabled(false);
        }
    }

    public XuatHoaDon_GUI(TaiKhoan tk, int maPhieuDatBanInput) {
        this();
        this.taiKhoan = tk;
        mnuTaiKhoan.setText("Nhân viên: " + taiKhoan.getNhanVien().getTenNV());

        if (!taiKhoan.getLoaiTaiKhoan().equals("quanli")) {
            mniThemNhanVien.setEnabled(false);
            mniTaoTaiKhoan.setEnabled(false);
        }
        updateDuLieuHoaDon(maPhieuDatBanInput);
    }

    public Double updateTableDanhSachDatMon(int maPhieuDatBan) {
        Double tongT = 0.0;
        modelDanhSachDatMon.setRowCount(0);
        ArrayList<ChiTietPhieuDatBan> listChiTietPhieuDatBan = chiTietPhieuDatBan_DAO
                .getAllChiTietPhieuDatBanBangMaPhieuDatBan(maPhieuDatBan);
        for (ChiTietPhieuDatBan chiTietPhieuDatBan : listChiTietPhieuDatBan) {
            modelDanhSachDatMon.addRow(new Object[] {
//					"Tên món", "Số lượng", "Thành tiền"
                    chiTietPhieuDatBan.getMonAn().getTenMon(), chiTietPhieuDatBan.getSoLuong(),
                    chiTietPhieuDatBan.getThanhTien() });
            tongT += chiTietPhieuDatBan.getThanhTien();
        }
        return tongT;
    }

    public void updateDuLieuHoaDon(int maPhieuDatBanInput) {
        PhieuDatBan phieuDatBan = phieuDatBan_DAO.getPhieuDatBanTheoMa(maPhieuDatBanInput);
        txtMaPhieuDatBan.setText("" + phieuDatBan.getMaPhieuDatBan());
        lblNgayTaoPhieu.setText("Ngày tạo phiếu: " + phieuDatBan.getNgayTaoPhieu());
        lblTenKhachHang.setText("Tên khách hàng: " + phieuDatBan.getKhachHang().getTenKH());
        lblSDTKhachHang.setText("Số điện thoại: " + phieuDatBan.getKhachHang().getSoDT());
        lblThoiGianDat.setText("Thời gian đặt: " + phieuDatBan.getThoiGianDatBan());
        lblBan.setText("Bàn: " + phieuDatBan.getBan().getMaBan());
        lblKhuVuc.setText("Khu vực: " + phieuDatBan.getBan().getKhuVuc().getTenKhuVuc());
        lblTenNhanVien.setText("Tên nhân viên: " + phieuDatBan.getNhanVien().getTenNV());


        tongTien = updateTableDanhSachDatMon(maPhieuDatBanInput);


        lblTongTien.setText("Tổng tiền: " + tongTien);
        CapNhatKhuyenMai();
    }

    public PhieuDatBan TimPhieuDatBan(int maPhieuDatBan) {
        try {
            maPhieuDatBan = Integer.parseInt(txtMaPhieuDatBan.getText());
            PhieuDatBan phieuDatBan = phieuDatBan_DAO.getPhieuDatBanTheoMa(maPhieuDatBan);
            if (phieuDatBan == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu đặt bàn!!!");
            } else {
                return phieuDatBan;
            }
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!!!");
        }
        return null;
    }
    public void CapNhatKhuyenMai() {
        khuyenMai = khuyenMai_DAO.getKhuyenMaiTheoTen(cmbKhuyenMai.getSelectedItem().toString());
        txtGiamGia.setText(Double.toString(khuyenMai.getGiamGia()));
        tongTienCuoi = tongTien - tongTien / 100 * (khuyenMai.getGiamGia() - thue_DAO.getThueTheoMa(1).getGiaTriThue());
        txtTongTienCuoi.setText(Double.toString(tongTienCuoi));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(mniDatBan)) {
            FormManHinhChinh newFrm = new FormManHinhChinh(taiKhoan.getNhanVien());
            newFrm.setVisible(true);
            this.dispose();
        } else if (o.equals(mniDSPhieuDatBan)) {
            FormPhieuDatBan newFrm = new FormPhieuDatBan(taiKhoan.getNhanVien());
            newFrm.setVisible(true);
            this.dispose();
        } else if (o.equals(mniTimKiemPhieuDatBan)) {
            FormPhieuDatBan newFrm = new FormPhieuDatBan(taiKhoan.getNhanVien());
            newFrm.setVisible(true);
            this.dispose();
        } else if (o.equals(mniXuatHoaDon)) {
            new XuatHoaDon_GUI(taiKhoan).setVisible(true);
            this.dispose();
        } else if (o.equals(mniDSHoaDon)) {
            new DanhSachHoaDon_GUI(taiKhoan).setVisible(true);
            this.dispose();
        } else if (o.equals(mniDSKhuyenMai)) {
            new KhuyenMaiGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThemKhuyenMai)) {
            new KhuyenMaiGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mnuKhachHang)) {
            new KhachHangGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mnuBan)) {

        } else if (o.equals(mniDSMonAn)) {
            new MonAnGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThemMonAn)) {
            new MonAnGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThongKeDoanhThu)) {
            new ThongKeDoanhThuGUI(taiKhoan.getNhanVien()).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThemNhanVien)) {
            this.dispose();
        } else if (o.equals(mniTaoTaiKhoan)) {
            new TaoTaiKhoan_GUI(taiKhoan).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThongTinTaiKhoan)) {
            this.dispose();
            new ThongTinTaiKhoan_GUI(taiKhoan).setVisible(true);
        } else if (o.equals(mniDangXuat)) {
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

        else if (o.equals(btnTimKiem)) {
            int maPhieuDatBan = Integer.parseInt(txtMaPhieuDatBan.getText());
            PhieuDatBan phieuDatBan = TimPhieuDatBan(maPhieuDatBan);
            if (phieuDatBan != null) {
                updateDuLieuHoaDon(maPhieuDatBan);
            }

        } else if (o.equals(btnXacNhanKhuyenMai)) {
            CapNhatKhuyenMai();
        } else if (o.equals(btnXuatHoaDon)) {
            int result = JOptionPane.showConfirmDialog(null, "Xác nhận xuất hóa đơn", "Xác nhận",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                PhieuDatBan phieuDatBan = new PhieuDatBan_DAO().getPhieuDatBanTheoMa(Integer.parseInt(txtMaPhieuDatBan.getText()));
                HoaDon hoaDon = new HoaDon(LocalDateTime.now());
                if(new HoaDon_DAO().themHoaDon(hoaDon)){
                    int maHD =  hoaDon.getMaHoaDon();
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(new HoaDon_DAO().getHoaDonTheoMa(maHD), phieuDatBan, khuyenMai, thue_DAO.getThueTheoMa(1), tongTien, tongTienCuoi);
                    new ChiTietHoaDon_DAO().themChiTietHoaDon(chiTietHoaDon);
                    JOptionPane.showMessageDialog(this, "ĐÃ TẠO HÓA ĐƠN");
                    this.dispose();
                }
            }

        }
    }
}
