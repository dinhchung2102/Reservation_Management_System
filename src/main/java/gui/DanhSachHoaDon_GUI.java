package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.TaiKhoan_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.TaiKhoan;

public class DanhSachHoaDon_GUI extends JFrame implements ActionListener {
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
    private HoaDon_DAO hoaDon_DAO = new HoaDon_DAO();
    private ChiTietHoaDon_DAO chiTietHoaDon_DAO = new ChiTietHoaDon_DAO();

    private TaiKhoan taiKhoan;
    private Font fontTieuDe;
    private Font fontGiaoDien;
    private Font fontGiaoDienInDam;
    private JPanel pnlTieuDe;
    private JPanel pnlDanhSachHoaDon;
    private DefaultTableModel modelDanhSachHoaDon;
    private JTable tblDanhSachHoaDon;
    private JScrollPane scrDanhSachHoaDon;

    public DanhSachHoaDon_GUI() {
        setTitle("Quản lý đặt bàn");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // tạo font cho JMenu
        fontMenu = new Font(Font.SERIF, Font.BOLD, 25);
        // tạo font cho JMenuItem
        fontMenuItem = new Font(Font.SERIF, Font.PLAIN, 25);

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
        Font font = new Font("Times New Roman", 1, 25);
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
        JLabel lblTieuDe = new JLabel("Danh sách hóa đơn", JLabel.CENTER);
        lblTieuDe.setFont(fontTieuDe);
        pnlTieuDe.add(lblTieuDe, BorderLayout.CENTER);

        /////////////////////
        // JPanel danh sách hóa đơn
        pnlDanhSachHoaDon = new JPanel(new BorderLayout());
        pnlDanhSachHoaDon.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        add(pnlDanhSachHoaDon, BorderLayout.CENTER);
        String[] colsHoaDon = { "Mã hóa đơn", "Thanh toán lúc", "Khách hàng", "Số diện thoại", "Mã phiếu đặt", "Bàn", "Lầu", "Tổng thành tiền", "Tổng tiền cuối" };
        modelDanhSachHoaDon = new DefaultTableModel(colsHoaDon, 0);
        tblDanhSachHoaDon = new JTable(modelDanhSachHoaDon);
        tblDanhSachHoaDon.setFont(fontGiaoDien);
        tblDanhSachHoaDon.setRowHeight(40);
        tblDanhSachHoaDon.getTableHeader().setFont(fontGiaoDienInDam);
        scrDanhSachHoaDon = new JScrollPane(tblDanhSachHoaDon);
        pnlDanhSachHoaDon.add(scrDanhSachHoaDon, BorderLayout.CENTER);

        updateTableHoaDon();

        setVisible(true);
    }

    public DanhSachHoaDon_GUI(TaiKhoan tk) {
        this();
        this.taiKhoan = tk;
        mnuTaiKhoan.setText("Nhân viên: " + taiKhoan.getNhanVien().getTenNV());

        if (!taiKhoan.getLoaiTaiKhoan().equals("quanli")) {
            mniThemNhanVien.setEnabled(false);
            mniTaoTaiKhoan.setEnabled(false);
        }
    }
    public void updateTableHoaDon() {
        modelDanhSachHoaDon.setRowCount(0);
        ArrayList<HoaDon> listHoaDon = hoaDon_DAO.getAllHoaDon();
        for (HoaDon hoaDon : listHoaDon) {
            ChiTietHoaDon chiTietHoaDon = chiTietHoaDon_DAO.getChiTietHoaDonTheoMa(hoaDon.getMaHoaDon());
            modelDanhSachHoaDon.addRow(new Object[] {
                    hoaDon.getMaHoaDon(), hoaDon.getThoiGianThanhToan(), chiTietHoaDon.getPhieuDatBan().getKhachHang().getTenKH(),
                    chiTietHoaDon.getPhieuDatBan().getKhachHang().getSoDT(), chiTietHoaDon.getPhieuDatBan().getMaPhieuDatBan(),
                    chiTietHoaDon.getPhieuDatBan().getBan().getMaBan(), chiTietHoaDon.getPhieuDatBan().getBan().getKhuVuc().getTenKhuVuc(),
                    chiTietHoaDon.getTongThanhTien(), chiTietHoaDon.getTongTienCuoi(),
            });
        }
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
                DangNhap_GUI dangNhap_GUI = new DangNhap_GUI();
                dangNhap_GUI.setVisible(true);
                this.dispose();
            }
        }

    }
}
