package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JTextField fld_password;
    private JButton btn_user_save;
    private JComboBox cmb_user_role;
    private JLabel fld_header;
    private User user;

    private UserManager userManager;

    public UserView(User user) {  // Değerlendirme Formu 7
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(300, 500);
        this.user = user;


        this.cmb_user_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        if(user != null){
            this.fld_user_name.setText(this.user.getUsername());
        }

        btn_user_save.addActionListener(e -> {  // Değerlendirme Formu 9
            if (Helper.isFieldListEmpty((new JTextField[]{this.fld_user_name,this.fld_password}))) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                this.user.setUsername(fld_user_name.getText());
                this.user.setPassword(fld_password.getText());
                this.user.setRole((User.Role) cmb_user_role.getSelectedItem());
                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }
                if(result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}
