package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    // guiInitialize() = Oluşturduğumuz JFrame'lere boyut verdiğimiz metot.
    public void guiInitialize(int width, int height){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Tourism Agency");
        this.setSize(width,height);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()), Helper.getLocationPoint("y",this.getSize()));
        this.setVisible(true);
    }

    //createTable() = JTable oluştururken kullandığımız sutun yapısı için kullanılan metot.
    public void createTable(DefaultTableModel model , JTable table, Object[] columns, ArrayList<Object[]> rows){
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if(rows == null){
            rows = new ArrayList<>();
        }

        for(Object[] row : rows){
            model.addRow(row);
        }

    }

    //  getTableSelectedRow() = Seçtiğimiz satırda ekleme silme güncellem işlemi yapmak için gerekli metot
    public int getTableSelectedRow(JTable table, int index){
        return Integer.parseInt(table.getValueAt(table.getSelectedRow() , index).toString());
    }

    //  tableRowSelect() = Seçtiğimiz satırda ekleme silme güncellem işleminin mouseEventi eklemek için gerekli metot
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selecte_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selecte_row, selecte_row);
            }
        });
    }
}