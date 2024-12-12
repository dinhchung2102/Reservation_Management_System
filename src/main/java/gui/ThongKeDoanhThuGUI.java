package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.table.DefaultTableCellRenderer;

public class ThongKeDoanhThuGUI extends FormMenu {
    private static final long serialVersionUID = 1L;
    private JTable statisticsTable;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;
    
    public ThongKeDoanhThuGUI() {
        // Thiết lập cơ bản cho frame
        setTitle("Thống Kê Doanh Thu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThongKeDoanhThuGUI frame = new ThongKeDoanhThuGUI();
            frame.setVisible(true);
        });
    }
}