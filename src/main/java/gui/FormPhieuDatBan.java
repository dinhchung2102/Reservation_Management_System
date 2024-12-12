package gui;

import com.toedter.calendar.JDateChooser;
import dao.*;
import entity.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private final JDateChooser dateChooserNgayDen;
    /*
     *
     *
     *
     *
     *
     *
     *
     * */
    Font txtFieldFont = new Font("Montserrat", Font.BOLD, 16);
    public FormPhieuDatBan(NhanVien nhanVien)  {
       this.nhanVien = nhanVien;
        setTitle("Quản lý đặt bàn");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font btnFont = new Font("Arial", Font.BOLD, 20);
        Color selectedColor = new Color(0,255,0);

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
        //mnuDatBan.setBackground(Color.GREEN);
        mnuDatBan.setBorder(new LineBorder(Color.white, 1));

        mnuDatBan.addActionListener(this);
        JMenuItem mniManHinhChinhItem = new JMenuItem("Màn hình chính");
        mniManHinhChinhItem.setFont(fontMenuItem);
        mnuDatBan.add(mniManHinhChinhItem);

        mniManHinhChinhItem.addActionListener(e->{
            this.dispose();
            FormManHinhChinh newFormManHinhChinh = new FormManHinhChinh(nhanVien);
            newFormManHinhChinh.setVisible(true);
        });

        // Tạo menu phiếu đặt bàn
        JMenu mnuPhieuDatBan = new JMenu("   Phiếu đặt bàn   ");
        mnuPhieuDatBan.setBackground(selectedColor);
        mnuPhieuDatBan.setOpaque(true);
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
        mniDSHoaDon.addActionListener(e->{
            new DanhSachHoaDon_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV())).setVisible(true);

            this.dispose();
        });
        mnuHoaDon.add(mniXuatHoaDon);
        mnuHoaDon.addSeparator();
        mnuHoaDon.add(mniDSHoaDon);

        mniXuatHoaDon.addActionListener(e->{
            new XuatHoaDon_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV()));
            this.dispose();
        });
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
        JMenu mnuTaiKhoan = new JMenu(nhanVien.getTenNV());
        ImageIcon iconTaiKhoan = new ImageIcon("image//userIcon.png");
        iconTaiKhoan.setImage(iconTaiKhoan.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        mnuTaiKhoan.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
        mnuTaiKhoan.setIcon(iconTaiKhoan);
        mniThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
        mniThongKeDoanhThu.setFont(fontMenuItem);
        mniThongKeDoanhThu.addActionListener(e->{
            new ThongKeDoanhThuGUI(nhanVien).setVisible(true);
            this.dispose();
        });
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
        pnlChiTietPhieu.setBorder(new EmptyBorder(0, 10,0, 0));

        pnlPhieuDatBan.add(pnlDanhSachPhieu);
        pnlPhieuDatBan.add(pnlChiTietPhieu);

        //Panel tìm kiếm thong tin đặt bàn của khách hàng theo số điện thoại
        JPanel pnlSearch = new JPanel();
        pnlSearch.setLayout(new BoxLayout(pnlSearch, BoxLayout.X_AXIS));
        pnlSearch.setBackground(backgroundColor);


        JLabel lblSearch = new JLabel("SỐ ĐIỆN THOẠI: ");
        lblSearch.setFont(txtFieldFont);
        JTextField txtSearch = new JTextField();
        txtSearch.setFont(txtFieldFont);


        JButton btnSearch = new JButton("TÌM");



        pnlSearch.add(lblSearch);
        pnlSearch.add(txtSearch);
        pnlSearch.add(btnSearch);

        //Panel hiển thị tên khách hàng sau khi tìm kiếm
        JPanel pnlCustomerName = new JPanel();
        pnlCustomerName.setLayout(new BoxLayout(pnlCustomerName, BoxLayout.X_AXIS));
        JLabel lblCusName = new JLabel("KHÁCH HÀNG: ");
        lblCusName.setFont(txtFieldFont);
        JTextField txtCusName = new JTextField();
        txtCusName.setBackground(Color.white);
        txtCusName.setFont(txtFieldFont);
        txtCusName.setEditable(false);
        pnlCustomerName.add(lblCusName);
        pnlCustomerName.add(Box.createHorizontalStrut(12));
        pnlCustomerName.add(txtCusName);
        pnlCustomerName.add(Box.createHorizontalStrut(63));
        pnlCustomerName.setBackground(backgroundColor);

        btnSearch.setFont(txtFieldFont);


        //Panel bảng danh sách phiếu:
        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(new BoxLayout(pnlTable, BoxLayout.Y_AXIS));

        String[] columnsName = {"STT", "Mã Phiếu", "Mã KH", "Ngày Tạo Phiếu", "Ngày Sử Dụng", "Trạng Thái"};
        Object[][] data = {

        };
        DefaultTableModel modelTable = new DefaultTableModel(data, columnsName);
        JTable tblDanhSachPhieu = new JTable(modelTable);
        tblDanhSachPhieu.setBackground(Color.white);
        tblDanhSachPhieu.setForeground(Color.blue);
        tblDanhSachPhieu.setFont(new Font("Montserrat", Font.ITALIC, 16));
        tblDanhSachPhieu.setDefaultEditor(Object.class, null);
        tblDanhSachPhieu.setRowHeight(30);

        JTableHeader tableHeader = tblDanhSachPhieu.getTableHeader();
        Font headerFont = new Font("Arial", Font.BOLD, 16);
        tableHeader.setFont(headerFont);
        tableHeader.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(tblDanhSachPhieu);
        scrollPane.setPreferredSize(new Dimension(830, 600));
        scrollPane.setBorder(new LineBorder(Color.black, 2));


        tblDanhSachPhieu.getColumnModel().getColumn(0).setPreferredWidth(2);
        tblDanhSachPhieu.getColumnModel().getColumn(1).setPreferredWidth(2);
        tblDanhSachPhieu.getColumnModel().getColumn(2).setPreferredWidth(2);
        tblDanhSachPhieu.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblDanhSachPhieu.getColumnModel().getColumn(4).setPreferredWidth(120);
        tblDanhSachPhieu.getColumnModel().getColumn(5).setPreferredWidth(70);

        loadDataToTableDSPhieu(tblDanhSachPhieu);



        //=================================Panel Filter============================
        JPanel pnlFilter = new JPanel();
        pnlFilter.setLayout(new BoxLayout(pnlFilter, BoxLayout.X_AXIS));
        pnlFilter.setMaximumSize(new Dimension(800, 10));
        pnlFilter.setBackground(backgroundColor);

        JButton btnAll = new JButton("TẤT CẢ");

        JButton btnHomNay = new JButton("HÔM NAY");

        JButton btnDaHuy = new JButton("ĐÃ HỦY");

        pnlFilter.add(btnAll);
        pnlFilter.add(btnHomNay);
        pnlFilter.add(btnDaHuy);
        pnlFilter.add(Box.createHorizontalStrut(500));

        //=============================================================
        pnlTable.add(pnlFilter);
        pnlTable.add(scrollPane);
        pnlTable.setPreferredSize(new Dimension(488,800));
        pnlTable.setBackground(backgroundColor);


        //Gom: panel search, panel Tên KH, panel lọc danh sch phiêu
        pnlDanhSachPhieu.add(pnlSearch);
        pnlDanhSachPhieu.add(Box.createVerticalStrut(5));
        pnlDanhSachPhieu.add(pnlCustomerName);
        pnlDanhSachPhieu.add(Box.createVerticalStrut(10));
        pnlDanhSachPhieu.add(pnlTable);
        //pnlDanhSachPhieu.add(pnlFilter);
        pnlDanhSachPhieu.setBackground(backgroundColor);


        //PANEL CHI TIẾT PHIẾU
        JPanel pnlMa_Time = new JPanel();
        pnlMa_Time.setLayout(new BoxLayout(pnlMa_Time, BoxLayout.X_AXIS));
        pnlMa_Time.setBackground(backgroundColor);

        JLabel lblMaPhieu = new JLabel("MÃ PHIẾU: ");
        lblMaPhieu.setFont(txtFieldFont);
        JTextField txtMaPhieu = new JTextField();
        txtMaPhieu.setFont(txtFieldFont);
        txtMaPhieu.setPreferredSize(new Dimension(30, 30));
        txtMaPhieu.setEditable(false);
        txtMaPhieu.setBackground(Color.WHITE);

        JLabel lblNhanVien = new JLabel("NHÂN VIÊN ĐẶT BÀN: ");
        lblNhanVien.setFont(txtFieldFont);
        JTextField txtNhanVien = new JTextField();
        txtNhanVien.setPreferredSize(new Dimension(120,30));
        txtNhanVien.setBackground(Color.WHITE);
        txtNhanVien.setFont(txtFieldFont);
        txtNhanVien.setEditable(false);

        pnlMa_Time.add(lblMaPhieu);
        pnlMa_Time.add(txtMaPhieu);
        pnlMa_Time.add(Box.createHorizontalStrut(28));
        pnlMa_Time.add(lblNhanVien);
        pnlMa_Time.add(Box.createHorizontalStrut(11));
        pnlMa_Time.add(txtNhanVien);

        //==================PANEL Vị trí bàn đã đt ==========================
        JPanel pnlViTri = new JPanel();
        pnlViTri.setLayout(new BoxLayout(pnlViTri, BoxLayout.X_AXIS));
        pnlViTri.setBackground(backgroundColor);

        JLabel lblKhuVuc = new JLabel("KHU VỰC: ");
        lblKhuVuc.setFont(txtFieldFont);
        JComboBox<String> comboBoxKhuVuc = new JComboBox<>();
        comboBoxKhuVuc.setPreferredSize(new Dimension(82,30));
        comboBoxKhuVuc.setFont(txtFieldFont);
        comboBoxKhuVuc.setBackground(Color.white);

        JLabel lblBan = new JLabel("BÀN: ");
        lblBan.setFont(txtFieldFont);
        JComboBox<Integer> comboBoxBan = new JComboBox<>();
        comboBoxBan.setFont(txtFieldFont);
        comboBoxBan.setBackground(Color.WHITE);

        JLabel lblSoLuong = new JLabel("SỐ LƯỢNG: ");
        lblSoLuong.setFont(txtFieldFont);
        JComboBox<Integer> comboBoxSoLuong = new JComboBox<>();
        comboBoxSoLuong.setFont(txtFieldFont);
        comboBoxSoLuong.setBackground(Color.white);


        pnlViTri.add(lblKhuVuc);
        pnlViTri.add(Box.createHorizontalStrut(2));
        pnlViTri.add(comboBoxKhuVuc);
        pnlViTri.add(Box.createHorizontalStrut(26));
        pnlViTri.add(lblBan);
        pnlViTri.add(Box.createHorizontalStrut(14));
        pnlViTri.add(comboBoxBan);
        pnlViTri.add(Box.createHorizontalStrut(29));
        pnlViTri.add(lblSoLuong);
        pnlViTri.add(comboBoxSoLuong);

        //=============================Xử lý sự kiện =============================
        //========================================================================

        //=================================GIOW DEN =====================================
        JPanel pnlGioDat = new JPanel();
        pnlGioDat.setLayout(new BoxLayout(pnlGioDat, BoxLayout.X_AXIS));
        pnlGioDat.setBackground(backgroundColor);
        JLabel lblGioDen = new JLabel("GIỜ ĐẾN: ");
        lblGioDen.setFont(txtFieldFont);

        JComboBox<Integer> comboBoxGio = new JComboBox<>();
        comboBoxGio.setFont(txtFieldFont);
        for (int i = 7; i <= 21; i++) {
            comboBoxGio.addItem(i);
        }
        JLabel lblGio = new JLabel("giờ");
        lblGio.setFont(txtFieldFont);

        JComboBox<Integer> comboBoxPhut = new JComboBox<>();
        comboBoxPhut.setFont(txtFieldFont);
        for (int i = 0; i <= 59; i++) {
            comboBoxPhut.addItem(i);
        }
        JLabel lblPhut = new JLabel("phút");
        lblPhut.setFont(txtFieldFont);

        JLabel lblNgayDen = new JLabel("NGÀY: ");
        lblNgayDen.setFont(txtFieldFont);
        dateChooserNgayDen = new JDateChooser();
        dateChooserNgayDen.setFont(txtFieldFont);
        dateChooserNgayDen.setBackground(Color.white);
        dateChooserNgayDen.setMinSelectableDate(new Date());

        pnlGioDat.add(lblGioDen);
        pnlGioDat.add(Box.createHorizontalStrut(8));
        pnlGioDat.add(comboBoxGio);
        pnlGioDat.add(Box.createHorizontalStrut(2));
        pnlGioDat.add(lblGio);
        pnlGioDat.add(Box.createHorizontalStrut(10));
        pnlGioDat.add(comboBoxPhut);
        pnlGioDat.add(Box.createHorizontalStrut(2));
        pnlGioDat.add(lblPhut);
        pnlGioDat.add(Box.createHorizontalStrut(34));
        pnlGioDat.add(lblNgayDen);
        pnlGioDat.add(Box.createHorizontalStrut(2));
        pnlGioDat.add(dateChooserNgayDen);
        //========================================
        //==================================================Tiền cọc==============
        JPanel pnlTienCoc = new JPanel();
        pnlTienCoc.setLayout(new BoxLayout(pnlTienCoc, BoxLayout.X_AXIS));
        pnlTienCoc.setBackground(backgroundColor);

        JLabel lblTienCoc = new JLabel("TIỀN CỌC: ");
        lblTienCoc.setFont(txtFieldFont);
        JTextField txtTienCoc = new JTextField();
        txtTienCoc.setFont(txtFieldFont);
        txtTienCoc.setPreferredSize(new Dimension(100, 30));

        pnlTienCoc.add(lblTienCoc);
        pnlTienCoc.add(txtTienCoc);
        //========================================Panel món ăn=======================
        JPanel pnlMonAn = new JPanel();
        pnlMonAn.setPreferredSize(new Dimension(500, 550));
        pnlMonAn.setBorder(new LineBorder(Color.BLACK, 2));

        String[] columnNamesTinhTien = { "STT", "Mã Món", "Tên", "Đơn giá", "Số lượng", "Thành tiền" };
        Object[][] dataTinhTien = {

        };

        DefaultTableModel modelMonAn = new DefaultTableModel(dataTinhTien, columnNamesTinhTien);
        JTable tblMonAn = new JTable(modelMonAn);

        Font txtFieldFont = new Font("Arial", Font.PLAIN, 14);
        tblMonAn.setBackground(Color.white);
        tblMonAn.setForeground(Color.blue);
        tblMonAn.setFont(txtFieldFont);
        tblMonAn.setRowHeight(30);

        tblMonAn.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblMonAn.getColumnModel().getColumn(1).setPreferredWidth(5);
        tblMonAn.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblMonAn.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblMonAn.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblMonAn.getColumnModel().getColumn(5).setPreferredWidth(50);

        JTableHeader tableHeaderMonAn = tblMonAn.getTableHeader();
        tableHeaderMonAn.setFont(headerFont);
        tableHeaderMonAn.setForeground(Color.BLACK);


        JScrollPane scrollPaneMonAn = new JScrollPane(tblMonAn);
        scrollPaneMonAn.setPreferredSize(new Dimension(750, 525));

        pnlMonAn.add(scrollPaneMonAn);
        //===================================Panel button Sử dụng, Thaydđổi, hủy===================
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

        JButton btnSuDung = new JButton("SỬ DỤNG");
        JButton btnThayDoi = new JButton("THAY ĐỔI");
        JButton btnHuyDat = new JButton("HỦY ĐẶT BÀN");

        pnlButton.add(btnSuDung);
        pnlButton.add(btnThayDoi);
        pnlButton.add(btnHuyDat);

        pnlMonAn.add(pnlButton);


        //==========================add cac panel======================
        pnlChiTietPhieu.add(pnlMa_Time);
        pnlChiTietPhieu.add(Box.createVerticalStrut(10));
        pnlChiTietPhieu.add(pnlViTri);
        pnlChiTietPhieu.add(Box.createVerticalStrut(10));
        pnlChiTietPhieu.add(pnlGioDat);
        pnlChiTietPhieu.add(Box.createVerticalStrut(10));
        pnlChiTietPhieu.add(pnlTienCoc);
        pnlChiTietPhieu.add(Box.createVerticalStrut(10));
        pnlChiTietPhieu.add(pnlMonAn);
        pnlChiTietPhieu.add(Box.createVerticalStrut(10));
        pnlChiTietPhieu.add(pnlButton);
        pnlChiTietPhieu.setBackground(backgroundColor);



        pnlPhieuDatBan.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(pnlPhieuDatBan);

        getDataToComboBox(comboBoxKhuVuc, comboBoxBan, comboBoxSoLuong, "Lầu 1", 1);

        btnThayDoi.addActionListener(e->{
            System.out.print("Thay đổi dat ban");
        });
        comboBoxKhuVuc.addActionListener(e -> {
            List<Ban> bans;
            bans = new DAO_Ban().getBansByKhuVuc((String) comboBoxKhuVuc.getSelectedItem());
            comboBoxBan.removeAllItems();
            for (Ban ban : bans) {
                comboBoxBan.addItem(ban.getMaBan());
            }

            comboBoxSoLuong.removeAllItems();
            int soGhe = new DAO_Ban().getSoGheByMaBan((Integer) comboBoxBan.getSelectedItem());

            for (int i = 1; i <= soGhe; i++) {
                comboBoxSoLuong.addItem(i);
            }
        });
        //===========================================Xu ly su kien=========================
        comboBoxBan.addActionListener(e -> {
            comboBoxSoLuong.removeAllItems();
            if (comboBoxBan.getItemCount() > 0) {
                int soGhe2 = new DAO_Ban().getSoGheByMaBan((Integer) comboBoxBan.getSelectedItem());
                for (int i = 1; i <= soGhe2; i++) {
                    comboBoxSoLuong.addItem(i);
                }
            }
        });

        btnSearch.addActionListener(e->{
            DAO_KhachHang daoKhachHang = new DAO_KhachHang();
            KhachHang khachHang = new KhachHang();
            khachHang = daoKhachHang.getKhachHangBySDT(txtSearch.getText());
            txtCusName.setText(khachHang.getTenKH());

            loadDataToTableDSPhieuByMaKH(tblDanhSachPhieu, khachHang.getMaKH());
        });

        btnAll.addActionListener(e->{
            loadDataToTableDSPhieu(tblDanhSachPhieu);
            txtSearch.setText("");
            txtCusName.setText("");
            txtMaPhieu.setText("");
            txtTienCoc.setText("");
            txtNhanVien.setText("");
            comboBoxGio.setSelectedItem(7);
            comboBoxPhut.setSelectedItem(0);
            dateChooserNgayDen.setDate(new Date());
        });

        btnSuDung.addActionListener(e->{
            PhieuDatBan phieuDatBan = new PhieuDatBan_DAO().getPhieuDatBanTheoMa(Integer.parseInt(txtMaPhieu.getText()));
            new DAO_Ban().capNhatTrangThaiBanById(phieuDatBan.getBan().getMaBan(), true);
            new PhieuDatBan_DAO().capNhatTrangThaiByMaPhieu(Integer.parseInt(txtMaPhieu.getText()), "Đã sử dụng");
            this.dispose();
            FormManHinhChinh formManHinhChinh = new FormManHinhChinh(nhanVien);
            formManHinhChinh.setVisible(true);
        });

        btnHuyDat.addActionListener(e->{
            PhieuDatBan phieuDatBan = new PhieuDatBan_DAO().getPhieuDatBanTheoMa(Integer.parseInt(txtMaPhieu.getText()));
            ThayDoiDatBan thayDoiDatBan = new ThayDoiDatBan(phieuDatBan, nhanVien, LocalDateTime.now() , "Hủy Đặt Bàn");

            if(new DAO_ThayDoiDatBan().huyDatBan(thayDoiDatBan)){
                new PhieuDatBan_DAO().capNhatTrangThaiByMaPhieu(phieuDatBan.getMaPhieuDatBan(), "Đã Hủy");
                JOptionPane.showMessageDialog(this, "Hủy Đặt Bàn thành công. MÃ ĐẶT BÀN: " + phieuDatBan.getMaPhieuDatBan());
                loadDataToTableDSPhieu(tblDanhSachPhieu);
            }
        });

        tblDanhSachPhieu.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // Kiểm tra nếu có một dòng được chọn
                int selectedRow = tblDanhSachPhieu.getSelectedRow();
                if (selectedRow >= 0) {
                    // Lấy dữ liệu từ dòng đã chọn
                    Object id = tblDanhSachPhieu.getValueAt(selectedRow, 1);
                    Object maKH = tblDanhSachPhieu.getValueAt(selectedRow, 2);
                    PhieuDatBan_DAO phieuDatBanDao = new PhieuDatBan_DAO();
                    PhieuDatBan phieuDatBan = phieuDatBanDao.getPhieuDatBanTheoMa((int) id);

                    // Cập nhật các JTextField
                    txtMaPhieu.setText(String.valueOf(phieuDatBan.getMaPhieuDatBan()));
                    txtTienCoc.setText(String.valueOf(phieuDatBan.getTienCoc()));

                    KhuVuc khuVuc = new DAO_Ban().getKhuVucByMaBan(phieuDatBan.getBan().getMaBan());

                    comboBoxKhuVuc.setSelectedItem(khuVuc.getTenKhuVuc());
                    comboBoxBan.setSelectedItem(phieuDatBan.getBan().getMaBan());
                    comboBoxSoLuong.setSelectedItem(phieuDatBan.getSoLuongKhach());
                    txtNhanVien.setText(phieuDatBan.getNhanVien().getTenNV());

                    KhachHang khachHang = new KhachHang();
                    khachHang = new DAO_KhachHang().getKhachHangTheoMa((Integer) maKH);
                    txtCusName.setText(khachHang.getTenKH());

                    LocalDateTime thoiGianDatBan = phieuDatBan.getThoiGianDatBan();
                    int gioDat = thoiGianDatBan.getHour();
                    comboBoxGio.setSelectedItem(gioDat);

                    int phutDat = thoiGianDatBan.getMinute();
                    comboBoxPhut.setSelectedItem(phutDat);

                    Date dateDatBan = Date.from(thoiGianDatBan.atZone(ZoneId.systemDefault()).toInstant());
                    dateChooserNgayDen.setDate(dateDatBan);

                    String trangThai = (String) tblDanhSachPhieu.getValueAt(selectedRow, 5);
                    if("Chưa sử dụng".equals(trangThai)){
                        btnSuDung.setEnabled(true);
                        btnHuyDat.setEnabled(true);
                        btnThayDoi.setEnabled(true);
                    }
                    else {
                        btnSuDung.setEnabled(false);
                        btnHuyDat.setEnabled(false);
                        btnThayDoi.setEnabled(false);
                    }
                    loadDataToTableCTPhieu(tblMonAn, (int) id);
                }
            }
        });
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
            this.dispose();
        }
        else if (o.equals(mniDSKhuyenMai)) {
            KhuyenMaiGUI khuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            khuyenMaiGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mniThemKhuyenMai)) {
            KhuyenMaiGUI khuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            khuyenMaiGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mnuKhachHang)) {
            KhachHangGUI newKhachHangGUI = new KhachHangGUI(nhanVien);
            newKhachHangGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mnuBan)) {

        } else if (o.equals(mniDSMonAn)) {
            MonAnGUI monAnGUI = new MonAnGUI(nhanVien);
            monAnGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mniThemMonAn)) {
            MonAnGUI monAnGUI = new MonAnGUI(nhanVien);
            monAnGUI.setVisible(true);
            this.dispose();
        } else if (o.equals(mniQuanLiKhachHang)) {
            KhachHangGUI khachHang = new KhachHangGUI(nhanVien);
            khachHang.setVisible(true);
            this.dispose();
        } else if (o.equals(mniThongKeDoanhThu)) {
            new ThongKeDoanhThuGUI(nhanVien).setVisible(true);
            this.dispose();
        } else if (o.equals(mniThongTinTaiKhoan)) {
            new ThongTinTaiKhoan_GUI(new TaiKhoan_DAO().getTaiKhoanByMaNhanVien(nhanVien.getMaNV())).setVisible(true);
            this.dispose();
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
    private void getDataToComboBox(JComboBox<String> cbbKhuVuc, JComboBox<Integer> cbbBan,JComboBox<Integer> cbbSoKhach, String khuVuc, int maBan) {

        DAO_KhuVuc dao_KhuVuc = new DAO_KhuVuc();
        List<KhuVuc> listKhuVuc = new ArrayList<KhuVuc>();
        listKhuVuc = dao_KhuVuc.getKhuVuc();

        List<String> listTenKV = new ArrayList<String>();
        for (KhuVuc kVuc : listKhuVuc) {
            listTenKV.add(kVuc.getTenKhuVuc());
        }
        // System.out.println(listTenKV);
        for (String tenKV : listTenKV) {
            cbbKhuVuc.addItem(tenKV);
        }
        cbbKhuVuc.setSelectedItem(khuVuc);
        DAO_Ban daoBan = new DAO_Ban();
        List<Ban> bans = daoBan.getBansByKhuVuc((String) cbbKhuVuc.getSelectedItem());

        for (Ban ban : bans) {
            cbbBan.addItem(ban.getMaBan());
        }
        cbbBan.setSelectedItem(maBan);

        DAO_Ban dao_Ban = new DAO_Ban();
        int soGhe = dao_Ban.getSoGheByMaBan(maBan);
        for (int i = 1; i <= soGhe; i++) {
            cbbSoKhach.addItem(i);
        }

    }
    public static void loadDataToTableDSPhieu(JTable table) {
        PhieuDatBan_DAO phieuDatBanDao = new PhieuDatBan_DAO();
        List<PhieuDatBan> listPhieu = phieuDatBanDao.getAllPhieuDatBan();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        int stt = 1;
        for (PhieuDatBan phieu : listPhieu) {
            String formattedNgayTaoPhieu = phieu.getNgayTaoPhieu().format(formatter);
            String formattedThoiGianDatBan = phieu.getThoiGianDatBan().format(formatter);

            model.addRow(new Object[] {
                    stt++,
                    phieu.getMaPhieuDatBan(),
                    phieu.getKhachHang().getMaKH(),
                    formattedNgayTaoPhieu,
                    formattedThoiGianDatBan,
                    phieu.getTrangThai()
            });
        }
    }
    public static void loadDataToTableCTPhieu(JTable table, int maPhieuDatBan) {
        List<ChiTietPhieuDatBan> listCTPhieu = new ArrayList<>();
        listCTPhieu = new ChiTietPhieuDatBan_DAO().getAllChiTietPhieuDatBanBangMaPhieuDatBan(maPhieuDatBan);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        int stt = 1;
        for (ChiTietPhieuDatBan chiTietPhieuDatBan : listCTPhieu) {
            model.addRow(new Object[] {
                    stt++,
                    chiTietPhieuDatBan.getMonAn().getMaMon(),
                    chiTietPhieuDatBan.getMonAn().getTenMon(),
                    chiTietPhieuDatBan.getDonGia(),
                    chiTietPhieuDatBan.getSoLuong(),
                    chiTietPhieuDatBan.getThanhTien(),
            });
        }
    }
    public static void loadDataToTableDSPhieuByMaKH(JTable table, int maKH) {
        PhieuDatBan_DAO phieuDatBanDao = new PhieuDatBan_DAO();
        // Lấy danh sách phiếu đặt bàn theo maKH
        List<PhieuDatBan> listPhieu = phieuDatBanDao.getAllPhieuDatBanByMaKH(maKH);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);  // Xóa toàn bộ dữ liệu trong bảng trước khi thêm mới

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  // Định dạng thời gian

        int stt = 1;  // Biến để đánh số thứ tự
        for (PhieuDatBan phieu : listPhieu) {
            // Định dạng thời gian tạo phiếu và thời gian đặt bàn
            String formattedNgayTaoPhieu = phieu.getNgayTaoPhieu().format(formatter);
            String formattedThoiGianDatBan = phieu.getThoiGianDatBan().format(formatter);

            // Thêm một dòng mới vào bảng với dữ liệu của phiếu đặt bàn
            model.addRow(new Object[] {
                    stt++,  // Số thứ tự
                    phieu.getMaPhieuDatBan(),  // Mã phiếu đặt bàn
                    phieu.getKhachHang().getMaKH(),  // Mã khách hàng
                    formattedNgayTaoPhieu,  // Ngày tạo phiếu
                    formattedThoiGianDatBan,  // Thời gian đặt bàn
                    phieu.getTrangThai()  // Trạng thái
            });
        }
    }
}

