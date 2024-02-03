package view;

import business.UserManager;
import entity.User;

import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JLabel lbll_welcome_2;
    private JPasswordField fld_password;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_pass;
    private final UserManager userManager;


    // Login işlemlerini yönetildiği sınıf.
    public LoginView() {

        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(500, 600);

        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_password.getText());
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                }else if(loginUser.getRole().toString().equals("EMP")){
                    EmployeeView employeeView = new EmployeeView(loginUser);
                    dispose();
                    System.out.println("Employee Ekranı");
                }
                else if(loginUser.getRole().toString().equals("ADM")) {
                    AdminView adminView = new AdminView(loginUser);
                    dispose();
                }
            }
        });
    }
}