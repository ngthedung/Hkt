/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MRG
 */
//public class MyRender extends JLabel implements TableCellRenderer{
public class MyRender extends DefaultTableCellRenderer{

    public MyRender(){
        super();
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setHorizontalAlignment(SwingConstants.CENTER);
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return this;
    }
    
    
//    
//    public MyRender(){
//        super();
//        addItem("Có");
//        addItem("Nợ");
//    }
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if(isSelected){
//            setForeground(table.getSelectionForeground());
//            super.setBackground(table.getSelectionBackground());
//        }else{
//            setForeground(table.getForeground());
//            setBackground(table.getBackground());
//        }
//
//        boolean isNo = ((Boolean) value).booleanValue();
//        setSelectedIndex(isNo?0:1);
//
//        return this;
//    }
}
