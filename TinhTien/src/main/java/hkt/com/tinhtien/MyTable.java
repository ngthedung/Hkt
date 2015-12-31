/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import javax.swing.table.DefaultTableModel;


/**
 *
 * @author MRG
 * MyTable kế thừa DefaultTableModel.
 * isCellEdittable không cho chỉnh sửa cột có index < 1.
 * getColumnClass bắt buộc cột tiền phải nhập vào định dạng số.
 */
public class MyTable extends DefaultTableModel {
    public MyTable(Object[][]  data, Object[] columnNames) {
        super(data, columnNames);
    }
    
      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex >= 1;
    }
      
      @Override
      public Class getColumnClass(int columnIndex) {
        if (columnIndex == 3)
            return Integer.class;
        else return String.class;
      }
}
