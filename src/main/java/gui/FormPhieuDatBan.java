package gui;

import entity.NhanVien;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormPhieuDatBan extends JFrame implements ActionListener {
    private final JMenu mnuDatBan;
    private final JMenuItem mniDSKhuyenMai;
    private final JMenuItem mniThemKhuyenMai;
    private final JMenu mnuKhachHang;
    private final JMenuItem mniQuanLiKhachHang;
    private final JMenu mnuBan;
    private final JMenuItem mniDSMonAn;
    private final JMenuItem mniThemMonAn;
    private final JMenuItem mniThongKeDoanhThu;
    private final JMenuItem mniThongTinTaiKhoan;
    private final JMenuItem mniDangXuat;
    private final NhanVien nhanVien;
    /*
     *
     *
     *
     *
     *
     *
     *
     * */

    public FormPhieuDatBan(NhanVien nhanVien)  {
       this.nhanVien = nhanVien;
        setTitle("Quản lý đặt bàn");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        mnuDatBan.setOpaque(true);
        mnuDatBan.setBackground(Color.GREEN);
        mnuDatBan.setBorder(new LineBorder(Color.white, 1));

        mnuDatBan.addActionListener(this);

        // Tạo menu phiếu đặt bàn
        JMenu mnuPhieuDatBan = new JMenu("   Phiếu đặt bàn   ");
        mnuPhieuDatBan.setFont(fontMenu);
        JMenuItem mniDSPhieuDatBan = new JMenuItem("Danh sách phiếu đặt");
        mniDSPhieuDatBan.setFont(fontMenuItem);
        JMenuItem mniTimKiemPhieuDatBan = new JMenuItem("Tìm kiếm phiếu đặt");
        mniTimKiemPhieuDatBan.setFont(fontMenuItem);
        mnuPhieuDatBan.add(mniDSPhieuDatBan);
        mnuPhieuDatBan.addSeparator();
        mnuPhieuDatBan.add(mniTimKiemPhieuDatBan);

        mniDSPhieuDatBan.addActionListener(this);
        mniTimKiemPhieuDatBan.addActionListener(this);

        // Tạo menu hóa đơn
        JMenu mnuHoaDon = new JMenu("   Hóa đơn   ");
        mnuHoaDon.setFont(fontMenu);
        JMenuItem mniXuatHoaDon = new JMenuItem("Xuất hóa đơn");
        mniXuatHoaDon.setFont(fontMenuItem);
        JMenuItem mniDSHoaDon = new JMenuItem("Danh sách hóa đơn");
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

        // Tạo menu tài khoản
        JMenu mnuTaiKhoan = new JMenu();
        ImageIcon iconTaiKhoan = new ImageIcon("image//userIcon.png");
        iconTaiKhoan.setImage(iconTaiKhoan.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        mnuTaiKhoan.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        mnuTaiKhoan.setIcon(iconTaiKhoan);
        mniThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
        mniThongKeDoanhThu.setFont(fontMenuItem);
        JMenuItem mniThemNhanVien = new JMenuItem("Thêm nhân viên");
        mniThemNhanVien.setFont(fontMenuItem);
        JMenuItem mniTaoTaiKhoan = new JMenuItem("Tạo tài khoản");
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
//=========================================
        //==============================================
        //==============================================
        Color backgroundColor =  Color.CYAN;
        JPanel pnlPhieuDatBan = new JPanel();
        pnlPhieuDatBan.setLayout(new BoxLayout(pnlPhieuDatBan, BoxLayout.X_AXIS));
        pnlPhieuDatBan.setBackground(backgroundColor);

        JPanel pnlDanhSachPhieu = new JPanel();
        pnlDanhSachPhieu.setLayout(new BoxLayout(pnlDanhSachPhieu, BoxLayout.Y_AXIS));

        JPanel pnlChiTietPhieu = new JPanel();
        pnlChiTietPhieu.setLayout(new BoxLayout(pnlChiTietPhieu, BoxLayout.Y_AXIS));

        pnlPhieuDatBan.add(pnlDanhSachPhieu);
        pnlPhieuDatBan.add(pnlChiTietPhieu);

        //Panel tìm kiếm thong tin đặt bàn của khách hàng theo số điện thoại
        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new BoxLayout(pnlSearch, BoxLayout.X_AXIS));
        JTextField txtSearch = new JTextField();
        JButton btnSearch = new JButton("TÌM");
        pnlSearch.add(txtSearch);
        pnlSearch.add(btnSearch);
        //Panel hiển thị tên khách hàng sau khi tìm kiếm
        JPanel pnlCustomerName = new JPanel();
        pnlCustomerName.setLayout(new BoxLayout(pnlCustomerName, BoxLayout.X_AXIS));
        JLabel lblCusName = new JLabel("Tên khách hàng: ");
        JTextField txtCusName = new JTextField();
        txtCusName.setEditable(false);
        pnlCustomerName.add(lblCusName);
        pnlCustomerName.add(txtCusName);
        //Panel lọc các thông tin danh sách phiếu
        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new BoxLayout(pnlFilter, BoxLayout.X_AXIS));
        JButton btnToday = new JButton("Hôm nay");
        btnToday.setBackground(Color.GREEN);
        pnlFilter.add(btnToday);

        //Panel bảng danh sách phiếu:
        JPanel pnlTable = new JPanel();
        String[] columnsName = {"STT", "Mã Phiếu", "Ngày Tạo Phiếu", "Ngày dùng", };


        //Gom: panel search, panel Tên KH, panel lọc danh sch phiêu
        pnlDanhSachPhieu.add(pnlSearch);
        pnlDanhSachPhieu.add(pnlCustomerName);
        pnlDanhSachPhieu.add(pnlFilter);


        getContentPane().add(pnlPhieuDatBan);

    }

    /**
     *
     *
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(mnuDatBan)) {
            FormManHinhChinh newFrmManHinhChinh = new FormManHinhChinh(nhanVien);
            newFrmManHinhChinh.setVisible(true);
        }
        else if (o.equals(mniDSKhuyenMai)) {
            this.dispose();
            KhuyenMaiGUI khuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            khuyenMaiGUI.setVisible(true);
        } else if (o.equals(mniThemKhuyenMai)) {

        } else if (o.equals(mnuKhachHang)) {
            KhachHangGUI newKhachHangGUI = new KhachHangGUI(nhanVien);
            newKhachHangGUI.setVisible(true);
        } else if (o.equals(mnuBan)) {


        } else if (o.equals(mniDSMonAn)) {
            dispose();
            MonAnGUI monAnGUI = new MonAnGUI(nhanVien);
            monAnGUI.setVisible(true);

        } else if (o.equals(mniThemMonAn)) {

        } else if (o.equals(mniQuanLiKhachHang)) {
            this.dispose();
            KhachHangGUI khachHang = new KhachHangGUI(nhanVien);
            khachHang.setVisible(true);
        } else if (o.equals(mniThongKeDoanhThu)) {

        } else if (o.equals(mniThongTinTaiKhoan)) {

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
    }

}

