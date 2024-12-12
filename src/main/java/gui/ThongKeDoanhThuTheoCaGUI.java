package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.PhieuDatBan_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDatBan;

public class ThongKeDoanhThuTheoCaGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable statisticsTable;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;
    private JLabel totalRevenueLabel;
    private JLabel totalShiftsLabel;
    private JLabel avgRevenueLabel;
    private String currentUser = "Phạm Văn Khang";


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
    
    private class ShiftData {
        LocalDate date;
        String shift;
        String timeIn;
        String timeOut;
        int hoursWorked;
        double revenue;
        String note;
        
        public ShiftData(LocalDate date, String shift, String timeIn, String timeOut, 
                        int hoursWorked, double revenue, String note) {
            this.date = date;
            this.shift = shift;
            this.timeIn = timeIn;
            this.timeOut = timeOut;
            this.hoursWorked = hoursWorked;
            this.revenue = revenue;
            this.note = note;
        }
    }

    public ThongKeDoanhThuTheoCaGUI(NhanVien nhanVien) {
        initializeGUI(nhanVien);
        loadDataFromDatabase();
        updateSummaryStats();
    }
    
    private void initializeGUI( NhanVien nhanVien) {
        setTitle("Thống Kê Doanh Thu Theo Ca");
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
        JMenuItem mniThongKeDoanhThuTheoCa = new JMenuItem(("Thống kê doanh thu theo ca"));
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
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel titlePanel = createTitlePanel();
        JPanel summaryPanel = createSummaryPanel();
        
        createTable();
        JScrollPane scrollPane = new JScrollPane(statisticsTable);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.add(titlePanel, BorderLayout.NORTH);
        topPanel.add(summaryPanel, BorderLayout.CENTER);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        
        titleLabel = new JLabel("THỐNG KÊ DOANH THU THEO NHÂN VIÊN", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        
        JLabel employeeLabel = new JLabel("Nhân viên: " + nhanVien.getTenNV() , JLabel.CENTER);
        employeeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        employeeLabel.setForeground(new Color(51, 51, 51));
        
        titlePanel.setLayout(new GridLayout(2, 1, 0, 10));
        titlePanel.add(titleLabel);
        titlePanel.add(employeeLabel);
        
        return titlePanel;
    }
    
    private JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        summaryPanel.setBackground(new Color(240, 240, 240));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        totalRevenueLabel = createInfoPanel("", "0 VdđNĐ");
        totalShiftsLabel = createInfoPanel("Hôm Nay", "0 ca");
        avgRevenueLabel = createInfoPanel("Tháng " , "0 VNĐ");

        summaryPanel.add(totalRevenueLabel);
        summaryPanel.add(totalShiftsLabel);
        summaryPanel.add(avgRevenueLabel);

        return summaryPanel;
    }
    
    private JLabel createInfoPanel(String title, String value) {
        JLabel label = new JLabel("<html><div style='text-align: center'>" +
                                "<span style='font-size: 16px; color: #666'>" + title + "</span><br>" +
                                "<span style='font-size: 20px; color: #000'>" + value + "</span></div></html>",
                                JLabel.CENTER);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return label;
    }
    
    private void createTable() {
        String[] columnNames = {
            "STT", "Ngày", "Col3", "Col4", "Col5",
            "Col6", "Tổng tiền", "Doanh thu/giờ", "Ghi chú"
        };
        
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        statisticsTable = new JTable(tableModel);
        
        statisticsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        statisticsTable.setRowHeight(35);
        statisticsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        statisticsTable.getTableHeader().setBackground(new Color(51, 153, 255));
        statisticsTable.getTableHeader().setForeground(Color.WHITE);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        
        for (int i = 0; i < columnNames.length; i++) {
            if (i == 6 || i == 7) {
                statisticsTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            } else {
                statisticsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        
        statisticsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        statisticsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        statisticsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        statisticsTable.getColumnModel().getColumn(3).setPreferredWidth(80);
        statisticsTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        statisticsTable.getColumnModel().getColumn(5).setPreferredWidth(80);
        statisticsTable.getColumnModel().getColumn(6).setPreferredWidth(120);
        statisticsTable.getColumnModel().getColumn(7).setPreferredWidth(120);
        statisticsTable.getColumnModel().getColumn(8).setPreferredWidth(200);
    }

    private void loadDataFromDatabase() {
        // Sử dụng dữ liệu mẫu gán cứng
        List<ShiftData> sampleDataList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        List<Double> revenues = new ArrayList<>();
        List<LocalDateTime> dateTimeList = new ArrayList<>();



        List<PhieuDatBan> listPhieuByNV = new PhieuDatBan_DAO().getPhieuDatBanTheoMaNV(nhanVien.getMaNV());
        for (PhieuDatBan phieuDatBan : listPhieuByNV) {
            List<ChiTietHoaDon>  listCTHDBymaPhieuDatBan= new ChiTietHoaDon_DAO().getChiTietHoaDonTheoMaPhieuDatBan(phieuDatBan.getMaPhieuDatBan());
            for (ChiTietHoaDon chiTietHoaDon : listCTHDBymaPhieuDatBan){
                HoaDon hoaDon = new HoaDon_DAO().getHoaDonTheoMa(chiTietHoaDon.getHoaDon().getMaHoaDon());
                dateTimeList.add(hoaDon.getThoiGianThanhToan());
                revenues.add(chiTietHoaDon.getTongTienCuoi());
            }
        }
        List<LocalDate> dateList = new ArrayList<>();
        for(LocalDateTime lcDT : dateTimeList){
            dateList.add(lcDT.toLocalDate());
        }

        int index = 0;
        for(Double tongTienCuoi : revenues){
            sampleDataList.add(new ShiftData(
                    dateList.get(index),
                    "Sáng",
                    "07:00",
                    "11:00",
                    4,
                    tongTienCuoi,
                    ""
            ));
        }

        // Cập nhật bảng với dữ liệu mẫu
        updateTableData(sampleDataList);
    }


    private void updateTableData(List<ShiftData> dataList) {
        tableModel.setRowCount(0);
        
        DecimalFormat moneyFormat = new DecimalFormat("###,###,###");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (int i = 0; i < dataList.size(); i++) {
            ShiftData shift = dataList.get(i);
            double revenuePerHour = shift.revenue / shift.hoursWorked;
            
            Object[] row = {
                String.valueOf(i + 1),
                shift.date.format(dateFormatter),
                shift.shift,
                shift.timeIn,
                shift.timeOut,
                String.valueOf(shift.hoursWorked),
                moneyFormat.format(shift.revenue) + " VNĐ",
                moneyFormat.format(revenuePerHour) + " VNĐ",
                shift.note
            };
            tableModel.addRow(row);
        }
    }
    
    private void updateSummaryStats() {
        DecimalFormat moneyFormat = new DecimalFormat("###,###,###");
        double totalRevenue = 0;
        int totalShifts = tableModel.getRowCount();
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String revenueStr = ((String) tableModel.getValueAt(i, 6))
                                .replace(" VNĐ", "")
                                .replace(",", "");
            totalRevenue += Double.parseDouble(revenueStr);
        }
        
        double avgRevenue = totalShifts > 0 ? totalRevenue / totalShifts : 0;
        
        totalRevenueLabel.setText("<html><div style='text-align: center'>" +
                                "<span style='font-size: 16px; color: #666'>Tổng doanh thu</span><br>" +
                                "<span style='font-size: 20px; color: #000'>" + 
                                moneyFormat.format(totalRevenue) + " VNĐ</span></div></html>");
        
        totalShiftsLabel.setText("<html><div style='text-align: center'>" +
                               "<span style='font-size: 16px; color: #666'>Doanh thu hôm nay</span><br>" +
                               "<span style='font-size: 20px; color: #000'>" + 
                               totalShifts + " ca</span></div></html>");
        
        avgRevenueLabel.setText("<html><div style='text-align: center'>" +
                              "<span style='font-size: 16px; color: #666'>Doanh thu trung bình/tháng</span><br>" +
                              "<span style='font-size: 20px; color: #000'>" + 
                              moneyFormat.format(avgRevenue) + " VNĐ</span></div></html>");
    }

}