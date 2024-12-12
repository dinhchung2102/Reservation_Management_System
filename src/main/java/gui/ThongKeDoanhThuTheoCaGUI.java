package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connectDB.ConnectDB;

public class ThongKeDoanhThuTheoCaGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable statisticsTable;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;
    private JLabel totalRevenueLabel;
    private JLabel totalShiftsLabel;
    private JLabel avgRevenueLabel;
    private String currentUser = "Phạm Văn Khang";
    
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

    public ThongKeDoanhThuTheoCaGUI() {
        initializeGUI();
        loadDataFromDatabase();
        updateSummaryStats();
    }
    
    private void initializeGUI() {
        setTitle("Thống Kê Doanh Thu Theo Ca");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
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
        
        titleLabel = new JLabel("THỐNG KÊ DOANH THU THEO CA", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 51, 102));
        
        JLabel employeeLabel = new JLabel("Nhân viên: " + currentUser + " (Ca Sáng: 07:00 - 11:00)", JLabel.CENTER);
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
        
        totalRevenueLabel = createInfoPanel("Tổng doanh thu", "0 VNĐ");
        totalShiftsLabel = createInfoPanel("Tổng số ca làm", "0 ca");
        avgRevenueLabel = createInfoPanel("Doanh thu trung bình/ca", "0 VNĐ");
        
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
            "STT", "Ngày", "Ca làm việc", "Giờ vào", "Giờ ra", 
            "Số giờ làm", "Doanh thu ca", "Doanh thu/giờ", "Ghi chú"
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
        List<ShiftData> shiftDataList = new ArrayList<>();
        String query = """
            SELECT 
                HD.ngayLap AS ngay,
                'Sáng' AS ca,
                '07:00' AS gioVao,
                '11:00' AS gioRa,
                4 AS soGioLam,
                SUM(CTHD.thanhTien) AS doanhThu,
                'Ca làm việc bình thường' AS ghiChu
            FROM HoaDon HD
            JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD
            JOIN NhanVien NV ON HD.maNV = NV.maNV
            WHERE NV.tenNV = ? 
                AND CAST(HD.ngayLap AS TIME) BETWEEN '07:00' AND '11:00'
            GROUP BY HD.ngayLap
            ORDER BY HD.ngayLap DESC
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, currentUser);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ShiftData shift = new ShiftData(
                    rs.getDate("ngay").toLocalDate(),
                    rs.getString("ca"),
                    rs.getString("gioVao"),
                    rs.getString("gioRa"),
                    rs.getInt("soGioLam"),
                    rs.getDouble("doanhThu"),
                    rs.getString("ghiChu")
                );
                shiftDataList.add(shift);
            }

            if (shiftDataList.isEmpty()) {
                loadSampleData();
            } else {
                updateTableData(shiftDataList);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Lỗi truy vấn dữ liệu: " + e.getMessage() + "\nHiển thị dữ liệu mẫu thay thế.",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            loadSampleData();
        }
    }

    private void loadSampleData() {
        List<ShiftData> sampleDataList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        
        double[] revenues = {
            2800000, 2100000, 2400000, 1900000, 2000000,
            2600000, 1800000, 1700000, 2500000, 1600000
        };
        
        for (int i = 0; i < revenues.length; i++) {
            sampleDataList.add(new ShiftData(
                currentDate.minusDays(i),
                "Sáng",
                "07:00",
                "11:00",
                4,
                revenues[i],
                "Ca làm việc bình thường (Dữ liệu mẫu)"
            ));
        }
        
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
                               "<span style='font-size: 16px; color: #666'>Tổng số ca làm</span><br>" +
                               "<span style='font-size: 20px; color: #000'>" + 
                               totalShifts + " ca</span></div></html>");
        
        avgRevenueLabel.setText("<html><div style='text-align: center'>" +
                              "<span style='font-size: 16px; color: #666'>Doanh thu trung bình/ca</span><br>" +
                              "<span style='font-size: 20px; color: #000'>" + 
                              moneyFormat.format(avgRevenue) + " VNĐ</span></div></html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThongKeDoanhThuTheoCaGUI frame = new ThongKeDoanhThuTheoCaGUI();
            frame.setVisible(true);
        });
    }
}