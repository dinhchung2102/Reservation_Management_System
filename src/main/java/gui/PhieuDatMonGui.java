package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import dao.MonAnDAO;
import entity.MonAn;

public class PhieuDatMonGui extends JFrame {
    private JPanel panelTop, panelMenu, panelCart;
    private JTextField txtSearch;
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private List<MonAn> menuItems;

    public PhieuDatMonGui() {
        setTitle("Quản lý món ăn");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel top
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        JButton btnOrder = new JButton("Order");
        JButton btnMap = new JButton("Sơ đồ");
        JButton btnReturn = new JButton("Trả món");

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelButtons.add(btnOrder);
        panelButtons.add(btnMap);
        panelButtons.add(btnReturn);

        panelTop.add(panelButtons, BorderLayout.WEST);

        // Search bar
        txtSearch = new JTextField("Nhập mã/Tên món cần tìm", 30);
        panelTop.add(txtSearch, BorderLayout.CENTER);

        add(panelTop, BorderLayout.NORTH);

        // Panel for menu items
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(0, 1, 10, 10));
        menuItems = loadMenuItems(); // Load các món ăn từ CSDL

        for (MonAn item : menuItems) {
            JPanel itemPanel = createMenuItemPanel(item);
            panelMenu.add(itemPanel);
        }

        // Scroll pane for menu items
        JScrollPane menuScrollPane = new JScrollPane(panelMenu);
        menuScrollPane.setPreferredSize(new Dimension(700, 600));
        add(menuScrollPane, BorderLayout.CENTER);

        // Cart panel
        panelCart = new JPanel();
        panelCart.setLayout(new BorderLayout());
        panelCart.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));

        String[] columnNames = {"Tên món", "SL", "Thành tiền"};
        cartTableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        panelCart.add(cartScrollPane, BorderLayout.CENTER);

        JButton btnCalculate = new JButton("Tính tiền");
        panelCart.add(btnCalculate, BorderLayout.SOUTH);

        add(panelCart, BorderLayout.EAST);

        setVisible(true);
    }

    private JPanel createMenuItemPanel(MonAn item) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblName = new JLabel(item.getTenMon());
        JLabel lblPrice = new JLabel(item.getGiaTien() + "K");
        JButton btnAdd = new JButton("Thêm món");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToCart(item);
            }
        });

        panel.add(lblName, BorderLayout.NORTH);
        panel.add(lblPrice, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        return panel;
    }

    private void addItemToCart(MonAn item) {
        int rowCount = cartTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if (cartTableModel.getValueAt(i, 0).equals(item.getTenMon())) {
                int quantity = (int) cartTableModel.getValueAt(i, 1);
                cartTableModel.setValueAt(quantity + 1, i, 1);
                cartTableModel.setValueAt((quantity + 1) * item.getGiaTien(), i, 2);
                return;
            }
        }
        cartTableModel.addRow(new Object[]{item.getTenMon(), 1, item.getGiaTien()});
    }

    private List<MonAn> loadMenuItems() {
        MonAnDAO daoMon = new MonAnDAO();
        List<MonAn> menuList = daoMon.getAllMonAn();
        return menuList;
    }

}


