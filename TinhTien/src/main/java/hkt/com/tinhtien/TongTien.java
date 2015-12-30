/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

/**
 *
 * @author MRG
 */
public class TongTien extends JFrame{
    //Các phần tử trên mảng
    private JPanel panelRoot, panelTop, panelBotton, panelBotton_L, panelBotton_R, panelLeft, panelRight;
    private static JTable tbList_1, tbList_2;
    private JScrollPane scrTable_1, scrTable_2;
    private JLabel lbMaBienLai, lbTongTien_1, lbTongTien_2;
    private static JTextField txtMaBienLai, txtTongTien_1, txtTongTien_2;
    private static int countRow_tb1 = 0; //Biến đếm dùng tăng giần giá trị Stt
    private static int countRow_tb2 = 0; //Biến đếm dùng tăng giần giá trị Stt
    //End các phần tử trên mảng
    
    //Khởi tạo bảng
    private final String[] columnNames = {"STT", "Tên", "Mã", "Tiền", "Loại"};
    private Object[][] data = {{"1", "", "", "", ""}};
    MyTable tb_1 = new MyTable(data, columnNames);
    MyTable tb_2 = new MyTable(data, columnNames);
    //End khởi tạo bảng
    
    //Thêm dòng mới mỗi khi click cột cuối cho table_1
    private static void addRow_tb1(){
        tbList_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
                if (tbList_1.columnAtPoint(evt.getPoint())==4) {
                    MyTable addModel = (MyTable) tbList_1.getModel();
                    int temp_tb2 = Integer.parseInt(addModel.getValueAt(countRow_tb1, 0).toString());
                    addModel.addRow(new Object[]{temp_tb2 + 1, "", "", "", ""});
                    countRow_tb1 ++;
                }
            }
        });
    }
    //End thêm dòng mới mỗi khi click cột cuối cho table_1

    //Thêm dòng mới mỗi khi click cột cuối cho table_2
    private static void addRow_tb2(){
        tbList_2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
                if (tbList_2.columnAtPoint(evt.getPoint())==4) {
                    MyTable addModel = (MyTable) tbList_2.getModel();
                    int temp_tb2 = Integer.parseInt(addModel.getValueAt(countRow_tb2, 0).toString());
                    addModel.addRow(new Object[]{temp_tb2 + 1, "", "", "", ""});
                    countRow_tb2 ++;
                }
            }
        });
    }
    //End thêm dòng mới mỗi khi click cột cuối cho table_2

    //Tính tổng tiền cho table_1
    private static void Sum_tb1(){
        tbList_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
                MyTable changeMode = (MyTable) tbList_1.getModel();
                if(tbList_1.columnAtPoint(evt.getPoint())<5){
                    int sum_tb1 = 0;
                    for (int i = 0; i < tbList_1.getRowCount(); i++) {
                        if (changeMode.getValueAt(i, 3).toString().equals("")) {
                            sum_tb1 += 0;
                        }else{
                            sum_tb1 += Integer.parseInt(changeMode.getValueAt(i, 3).toString());
                        }
                    }
                    String temp = sum_tb1 + " VNĐ";
                    txtTongTien_1.setText(temp);
                }
            }
        });
    }
    //End tính tổng tiền cho table_1
    
    //Tính tổng tiền cho table_2
    private static void Sum_tb2(){
        tbList_2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt){
                MyTable changeMode = (MyTable) tbList_2.getModel();
                if(tbList_2.columnAtPoint(evt.getPoint())<5){
                    int sum_tb1 = 0;
                    for (int i = 0; i < tbList_2.getRowCount(); i++) {
                        if (changeMode.getValueAt(i, 3).toString().equals("")) {
                            sum_tb1 += 0;
                        }else{
                            sum_tb1 += Integer.parseInt(changeMode.getValueAt(i, 3).toString());
                        }
                    }
                    String temp = sum_tb1 + " VNĐ";
                    txtTongTien_2.setText(temp);
                }
            }
        });
    }
    //End tính tổng tiền cho table_2
    
    //Resize kích thước các cột trong bảng
    private void reSizeTable(JTable table){
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            switch(i){
                case 0:
                    column.setPreferredWidth(25);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 2:
                    column.setPreferredWidth(75);
                    break;
                case 3:
                    column.setPreferredWidth(100);
                    break;
                case 4:
                    column.setPreferredWidth(100);
            }
        }
    }
    //End resize
    
    public TongTien(){
        //panelTop chứa mã biên lai kiểu GridBag căn giữa
        panelTop = new JPanel();
        panelTop.setLayout(new GridBagLayout());
        lbMaBienLai = new JLabel("Mã biên lai: ");
        txtMaBienLai = new JTextField();
        txtMaBienLai.setPreferredSize(new java.awt.Dimension(75, 20));
        panelTop.add(lbMaBienLai);
        panelTop.add(txtMaBienLai);
        //End panelTop
        
        //panelBotton chứa tổng tiền dùng Gid chia 2 cột
        panelBotton = new JPanel();
        panelBotton_L = new JPanel();
        panelBotton_R = new JPanel();
        panelBotton.setLayout(new GridLayout(1, 2));
        panelBotton_L.setLayout(new GridBagLayout());
        panelBotton_R.setLayout(new GridBagLayout());
        lbTongTien_1 = new JLabel("Tổng tiền: ");
        lbTongTien_2 = new JLabel("Tổng tiền: ");
        txtTongTien_1 = new JTextField();
        txtTongTien_2 = new JTextField();
        txtTongTien_1.setPreferredSize(new java.awt.Dimension(175, 20));
        txtTongTien_1.setEditable(false);
        txtTongTien_2.setPreferredSize(new java.awt.Dimension(175, 20));
        panelBotton_L.add(lbTongTien_1);
        panelBotton_L.add(txtTongTien_1);
        panelBotton_R.add(lbTongTien_2);
        panelBotton_R.add(txtTongTien_2);
        panelBotton.add(panelBotton_L);
        panelBotton.add(panelBotton_R);
        //End panelBotton
        
        //panelLeft hiện thị table_1
        panelLeft = new JPanel();
        tbList_1 = new JTable(tb_1);
        scrTable_1 = new JScrollPane(tbList_1);
        reSizeTable(tbList_1);
        addRow_tb1();
        Sum_tb1();
        panelLeft.add(scrTable_1);
        //End panelLeft
        
        //panelRight hiện thị table_2
        panelRight = new JPanel();
        tbList_2 = new JTable(tb_2);
        scrTable_2 = new JScrollPane(tbList_2);
        reSizeTable(tbList_2);
        addRow_tb2();
        Sum_tb2();
        panelLeft.add(scrTable_2);
//        End panelRight
        
        //panel chính dạng Border
        panelRoot = new JPanel();
        panelRoot.setLayout(new BorderLayout());
        panelRoot.add(panelTop, BorderLayout.PAGE_START);
        panelRoot.add(panelBotton, BorderLayout.PAGE_END);
        panelRoot.add(panelLeft, BorderLayout.LINE_START);
        panelRoot.add(panelRight, BorderLayout.LINE_END);
        add(panelRoot);
        //End panel 
    }
    
    public static void main(String[] args) {
        TongTien demo = new TongTien();
        demo.setVisible(true);
        demo.setSize(950, 500);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

