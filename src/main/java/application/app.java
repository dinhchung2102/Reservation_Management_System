package application;

import javax.swing.SwingUtilities;

import gui.DangNhap_GUI;

public class app {
    public static void main(String[] args) {
//		new Login();
        SwingUtilities.invokeLater(() -> {
            new DangNhap_GUI();
            //new Login();
        });
    }
}