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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author MRG
 */
public class MyEdit extends JComboBox implements TableCellEditor{
    
    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);

    public MyEdit(){
        super();
        addItem("Có");
        addItem("Nợ");
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public void addCellEditorListener(CellEditorListener listener) {
        listenerList.add(CellEditorListener.class, listener);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener listener) {
        listenerList.remove(CellEditorListener.class, listener);
    }
    
    protected void fireEditingStopped(){
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for(int i=0; i<listeners.length; i++){
            if(listeners[i] == CellEditorListener.class){
                listener = (CellEditorListener) listeners[i+1];
                listener.editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled(){
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for(int i=0; i< listeners.length; i++){
            if(listeners[i] == CellEditorListener.class){
                listener = (CellEditorListener) listeners[i+1];
                listener.editingCanceled(changeEvent);
            }
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        boolean isNo = ((Boolean) value).booleanValue();
        setSelectedIndex(isNo?0:1);
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return new Boolean(getSelectedIndex() == 0 ? true : false);
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }
 }
