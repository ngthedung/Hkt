/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MRG
 */
public class MyRender extends JComboBox implements TableCellRenderer{
    
    public MyRender(){
        super();
        addItem("Có");
        addItem("Nợ");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(isSelected){
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        }else{
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        boolean isNo = ((Boolean) value).booleanValue();
        setSelectedIndex(isNo?0:1);

        return this;
    }
}
