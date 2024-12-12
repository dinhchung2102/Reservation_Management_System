package gui;

import entity.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.table.DefaultTableCellRenderer;

public class ThongKeDoanhThuGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable statisticsTable;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;
    private Font fontMenu;
    private Font fontMenuItem;
    private JMenuBar mnuMenuBar;
    private JMenu mnuDatBan;
    private JMenu mnuPhieuDatBan;
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
    private NhanVien nhanVien;
    
    public ThongKeDoanhThuGUI(NhanVien nhanVien) {
        // Thiết lập cơ bản cho frame
        setTitle("Thống Kê Doanh Thu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.nhanVien = nhanVien;
        /***
         *
         *
         *
         * FORM MENU CREATED BY PHAM VAN
         * KHANG/////////////////////////////////////////////////////////////
         * T//////////////////////////////////////////////////////////////////////////////////////////////
         *
         */

        // tạo font cho JMenu
        fontMenu = new Font(Font.SERIF, Font.BOLD, 25);
        // tạo font cho JMenuItem
        fontMenuItem = new Font(Font.SERIF, Font.PLAIN, 25);

        // Tạo menubar
        mnuMenuBar = new JMenuBar();
        mnuMenuBar.setBackground(Color.white);
        this.setJMenuBar(mnuMenuBar);

        // Tạo menu đặt bàn
        mnuDatBan = new JMenu("     Đặt bàn     ");
        mnuDatBan.setFont(fontMenu);

        JMenuItem mniManHinhChinhItem = new JMenuItem("Màn hình chính");
        mniManHinhChinhItem.setFont(fontMenuItem);
        mnuDatBan.add(mniManHinhChinhItem);

        mniManHinhChinhItem.addActionListener(e->{
            FormManHinhChinh newFormManHinhChinh = new FormManHinhChinh(nhanVien);
            newFormManHinhChinh.setVisible(true);
            this.dispose();
        });

        // mnuDatBan.addActionListener(this);

        // Tạo menu phiếu đặt bàn
        mnuPhieuDatBan = new JMenu("   Phiếu đặt bàn   ");
        mnuPhieuDatBan.setFont(fontMenu);
        mniDSPhieuDatBan = new JMenuItem("Danh sách phiếu đặt");
        mniDSPhieuDatBan.setFont(fontMenuItem);
        mniTimKiemPhieuDatBan = new JMenuItem("Tìm kiếm phiếu đặt");
        mniTimKiemPhieuDatBan.setFont(fontMenuItem);
        mnuPhieuDatBan.add(mniDSPhieuDatBan);
        mnuPhieuDatBan.addSeparator();
        mnuPhieuDatBan.add(mniTimKiemPhieuDatBan);

//		mniDSPhieuDatBan.addActionListener(this);
//		mniTimKiemPhieuDatBan.addActionListener(this);

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

//		mniXuatHoaDon.addActionListener(this);
//		mniDSHoaDon.addActionListener(this);

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
        mniDSKhuyenMai.addActionListener(e->{
            this.dispose();
            KhuyenMaiGUI newKhuyenMaiGUI = new KhuyenMaiGUI(nhanVien);
            newKhuyenMaiGUI.setVisible(true);
        });

//		mniDSKhuyenMai.addActionListener(this);
//		mniThemKhuyenMai.addActionListener(this);

        // Tạo menu khách hàng
        mnuKhachHang = new JMenu("   Khách hàng   ");
        mnuKhachHang.setFont(fontMenu);
        mniQuanLiKhachHang = new JMenuItem("Quản lí khách hàng");
        mniQuanLiKhachHang.setFont(fontMenuItem);
        mnuKhachHang.add(mniQuanLiKhachHang);

        mniQuanLiKhachHang.addActionListener(e -> {
            this.dispose();
            KhachHangGUI khachHangGUI = new KhachHangGUI(nhanVien);
            khachHangGUI.setVisible(true);
        });

        // Tạo menu bàn
        mnuBan = new JMenu("   Bàn   ");
        mnuBan.setFont(fontMenu);
        mniQuanLiBan = new JMenuItem("Quản lí bàn");
        mniQuanLiBan.setFont(fontMenuItem);
        mnuBan.add(mniQuanLiBan);

//		mniQuanLiBan.addActionListener(this);

        // Tạo menu món ăn
        mnuMonAn = new JMenu("   Món ăn   ");
        mnuMonAn.setFont(fontMenu);

        mniDSMonAn = new JMenuItem("Danh sách món ăn");
        mniDSMonAn.setFont(fontMenuItem);
        mniDSMonAn.addActionListener(e->{
            dispose();
            MonAnGUI newMonAnGUI = new MonAnGUI(nhanVien);
            newMonAnGUI.setVisible(true);
        });
        mniThemMonAn = new JMenuItem("Thêm món ăn");
        mniThemMonAn.setFont(fontMenuItem);
        mnuMonAn.add(mniDSMonAn);
        mnuMonAn.addSeparator();
        mnuMonAn.add(mniThemMonAn);


//		mniDSMonAn.addActionListener(this);
//		mniThemMonAn.addActionListener(this);

        // Tạo menu tài khoản
        mnuTaiKhoan = new JMenu();
        mnuTaiKhoan.setOpaque(true);
        mnuTaiKhoan.setBackground(Color.green);

        ImageIcon iconTaiKhoan = new ImageIcon("image//userIcon.png");
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
        mnuTaiKhoan.setText(nhanVien.getTenNV());

//		mniThongKeDoanhThu.addActionListener(this);
//		mniThongTinTaiKhoan.addActionListener(this);
        mniDangXuat.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                this.dispose();
                DangNhap_GUI dangNhap_GUI = new DangNhap_GUI();
                dangNhap_GUI.setVisible(true);
            }
        });

        mnuMenuBar.add(mnuDatBan);
        mnuMenuBar.add(mnuPhieuDatBan);
        mnuMenuBar.add(mnuHoaDon);
        mnuMenuBar.add(mnuKhuyenMai);
        mnuMenuBar.add(mnuKhachHang);
        mnuMenuBar.add(mnuBan);
        mnuMenuBar.add(mnuMonAn);
        mnuMenuBar.add(mnuTaiKhoan);
        /***
         *
         *
         *
         *
         *
         *
         *
         * */
        setTitle("Dish Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Tạo panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(173, 216, 230)); // Light blue background
        
        // Tạo tiêu đề
        titleLabel = new JLabel("THỐNG KÊ DOANH THU", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102)); // Dark blue text
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(173, 216, 230));
        titlePanel.add(titleLabel);
        
        // Tạo bảng
        createTable();
        JScrollPane scrollPane = new JScrollPane(statisticsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Thêm các component vào panel chính
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Thêm panel chính vào frame
        add(mainPanel);
        
        // Load dữ liệu mẫu
        loadSampleData();
    }
    
    private void createTable() {
        // Tạo các cột cho bảng
        String[] columnNames = {
            "Ngày", "Số Hóa Đơn", "Tổng Tiền", "Thanh Toán Tiền Mặt", 
            "Thanh Toán Banking", "Thanh Toán Khác", "Ghi Chú"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa cell
            }
        };
        
        statisticsTable = new JTable(tableModel);
        
        // Tùy chỉnh giao diện bảng
        statisticsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        statisticsTable.setRowHeight(30);
        statisticsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        statisticsTable.getTableHeader().setBackground(new Color(51, 153, 255));
        statisticsTable.getTableHeader().setForeground(Color.WHITE);
        
        // Căn giữa header
        ((DefaultTableCellRenderer)statisticsTable.getTableHeader()
            .getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        // Tùy chỉnh căn lề cho các cột
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        // Áp dụng căn lề cho từng cột
        statisticsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Ngày
        statisticsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // Số hóa đơn
        for (int i = 2; i <= 5; i++) { // Các cột tiền
            statisticsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
    }
    
    private void loadSampleData() {
        // Tạo DecimalFormat để định dạng số tiền
        DecimalFormat moneyFormat = new DecimalFormat("###,###,###");
        LocalDate currentDate = LocalDate.now();
        
        // Dữ liệu mẫu cố định cho nhà hàng
        // Mỗi ngày có khoảng 20-30 hóa đơn, doanh thu từ 5-10 triệu
        Object[][] sampleData = {
            {30, 8500000, 5100000, 2975000, 425000}, // Cuối tuần (doanh thu cao)
            {25, 6500000, 3900000, 2275000, 325000}, // Ngày thường
            {28, 7200000, 4320000, 2520000, 360000}, // Cuối tuần
            {22, 5800000, 3480000, 2030000, 290000}, // Ngày thường
            {24, 6200000, 3720000, 2170000, 310000}, // Ngày thường
            {29, 8000000, 4800000, 2800000, 400000}, // Cuối tuần
            {23, 5500000, 3300000, 1925000, 275000}, // Ngày thường
            {21, 5200000, 3120000, 1820000, 260000}, // Ngày thường
            {27, 7800000, 4680000, 2730000, 390000}, // Cuối tuần
            {20, 5000000, 3000000, 1750000, 250000}  // Ngày thường
        };
        
        // Thêm dữ liệu vào bảng
        for (int i = 0; i < 10; i++) {
            LocalDate date = currentDate.minusDays(i);
            Object[] sampleRow = sampleData[i];
            
            Object[] row = {
                date.toString(),
                String.valueOf(sampleRow[0]), // Số hóa đơn
                moneyFormat.format(sampleRow[1]) + " VNĐ", // Tổng tiền
                moneyFormat.format(sampleRow[2]) + " VNĐ", // Tiền mặt (60%)
                moneyFormat.format(sampleRow[3]) + " VNĐ", // Banking (35%)
                moneyFormat.format(sampleRow[4]) + " VNĐ", // Khác (5%)
                "Doanh thu ngày " + date.toString()
            };
            tableModel.addRow(row);
        }
    }

}
