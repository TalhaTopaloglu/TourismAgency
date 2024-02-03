package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private UserManager userManager;

    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu;
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel w_top;
    private JTable tbl_user;
    private JScrollPane scrl_user;
    private JComboBox cmb_filter_user;
    private JButton btn_search_user;
    private JButton btn_clear_user;
    private User user;
    private Object[] col_user;

    public AdminView(User user) {
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(800, 600);
        this.user = user;


        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername() + " || Admin olarak giriş yaptınız.");

        //General Cod
        loadComponent();

        loadUserTable(null);
        loadUserFilter();
        loadUserComponent();

        this.btn_search_user.addActionListener(e -> {

            ArrayList<User> userListBySearch = this.userManager.searchForTable((User.Role) cmb_filter_user.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(col_user.length,userListBySearch);

            loadUserTable(userRowListBySearch);
        });


        btn_clear_user.addActionListener(e -> {
            loadUserTable(null);
        });
    }

    public void loadUserTable(ArrayList<Object[]> userList) {

        this.col_user =new Object[]{"Kullanıcı ID", "Kullanıcı Adı","Kullanıcı Şifresi","Kullanıcı Rölü"};

        if(userList == null){
           userList= this.userManager.getForTable(col_user.length,this.userManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_user, col_user, userList);
    }

    public void loadUserFilter() {
        this.cmb_filter_user.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_filter_user.setSelectedItem(null);
    }


    public void loadUserComponent(){
        tableRowSelect(this.tbl_user);
        this.user_menu = new JPopupMenu();
        this.user_menu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectedUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedUserId = this.getTableSelectedRow(tbl_user, 0);
                if (this.userManager.delete(selectedUserId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(user_menu);
    }

    private void loadComponent(){
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }

}
