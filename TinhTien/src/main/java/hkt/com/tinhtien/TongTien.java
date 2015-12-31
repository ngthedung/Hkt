/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hkt.com.tinhtien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private final JPanel panelRoot, panelTop, panelBotton, panelBotton_L, panelBotton_R, panelCente;
    private static JTable tbList_1, tbList_2;
    private final JScrollPane scrTable_1, scrTable_2;
    private final JLabel lbMaBienLai, lbTongTien_1, lbTongTien_2;
    private static JTextField txtMaBienLai, txtTongTien_1, txtTongTien_2;
    private final JButton btnNew;
    private static final String CO = "CÓ";
    private static final String NO = "NỢ";
    static String tempValue = "";
    final String sql = "INSERT INTO tinhtien(stt, ten, ma, tien, loai) VALUES (null, ";
    private static int[] arr_tb1 = new int[1000];   //Lưu giá trị trước khi chỉnh sửa
    private static int[] arr_tb2 = new int[1000];   //Lưu giá trị trước khi chỉnh sửa
    //End các phần tử trên mảng
    
    //Khởi tạo bảng
    private final String[] columnNames = {"STT", "Tên", "Mã", "Tiền", "Loại"};
//    private final static Object[][] data = {{"1", "", "", "0", CO}};
    MyTable tb_1 = new MyTable(new Object[][]{{"1", "", "", "0", CO}}, columnNames);
    MyTable tb_2 = new MyTable(new Object[][]{{"1", "", "", "0", NO}}, columnNames);
    //End khởi tạo bảng
    
    //Thêm dòng mới mỗi khi click dòng cuối cho table_1
    private static void addRow_tb1(){
        tbList_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt){
                if (tbList_1.rowAtPoint(evt.getPoint())==tbList_1.getRowCount()-1) {
                    MyTable addModel = (MyTable) tbList_1.getModel();
                    addModel.addRow(new Object[]{tbList_1.getRowCount() + 1, "", "", 0, CO});
                }
            }
        });
    }
    //End thêm dòng mới mỗi khi click dòng cuối cho table_1

    //Thêm dòng mới mỗi khi click dòng cuối cho table_2
    private static void addRow_tb2(){
        tbList_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt){
                if (tbList_2.rowAtPoint(evt.getPoint())==tbList_2.getRowCount()-1) {
                    MyTable addModel = (MyTable) tbList_2.getModel();
                    addModel.addRow(new Object[]{tbList_2.getRowCount() + 1, "", "", 0, NO});
                }
            }
        });
    }
    //End thêm dòng mới mỗi khi click dòng cuối cho table_2

    //Tính tổng tiền cho table_1
    private static void Sum_tb1(){
        tbList_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt){
                MyTable changeMode = (MyTable) tbList_1.getModel();
                if(tbList_1.columnAtPoint(evt.getPoint())<5){
                    func_Sum(tbList_1, changeMode, txtTongTien_1, arr_tb1);
                }
            }
        });
    }
    //End tính tổng tiền cho table_1
    
    //Tính tổng tiền cho table_2
    private static void Sum_tb2(){
        tbList_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt){
                MyTable changeMode = (MyTable) tbList_2.getModel();
                if(tbList_2.columnAtPoint(evt.getPoint())<5){
                    func_Sum(tbList_2, changeMode, txtTongTien_2, arr_tb2);
                }
            }
        });
    }
    //End tính tổng tiền cho table_2
    
    private static void Sum_keyboar_tb1(){
        tbList_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                MyTable changeMode_tb1 = (MyTable) tbList_1.getModel();
                if(!(e.getKeyChar() + "").equals("")){
                    func_Sum(tbList_1, changeMode_tb1, txtTongTien_1, arr_tb1);
                }
            }
        });
    }
    
        private static void Sum_keyboar_tb2(){
        tbList_2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                MyTable changeMode_tb2 = (MyTable) tbList_2.getModel();
                if(!(e.getKeyChar() + "").equals("")){
                    func_Sum(tbList_2, changeMode_tb2, txtTongTien_2, arr_tb2);
                }
            }
        });
    }   
       
        
    private static void setTableValue(){
        tbList_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tempValue += e.getKeyChar();
            }
        });
//        tbList_1.setValueAt(tempValue, tbList_1.getSelectedRow(), tbList_1.getSelectedRow());
    }   
        
    private static void func_Sum(JTable table, MyTable changeMode, JTextField txtTongTien, int[] arr){
            int sum_tb1 = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            arr[i] = 0;
        try{
            sum_tb1 += Integer.parseInt(changeMode.getValueAt(i, 3).toString()) - arr[i];
            arr[i] = Integer.parseInt(changeMode.getValueAt(i, 3).toString());
            String temp = sum_tb1 + " VNĐ";
            txtTongTien.setText(temp);
        }catch(Exception e){
                JOptionPane.showMessageDialog(table, "Xin hãy nhập lại số tiền", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                changeMode.setValueAt(0, i, 3);
        }
        }
    }
    
    //Resize kích thước các cột trong bảng
    private static void reSizeTable(JTable table){
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
    
    //Tạo mới
    public static void createNew(JTable table, String coNo){
        MyTable deleteModel = (MyTable) table.getModel();
        try{
        while(table.getRowCount()!=0) {
            txtTongTien_2.setText("0 VNĐ");
        }
        }catch(Exception e){
            System.out.println("Error   " + table.getRowCount());
        }
        deleteModel.addRow(new Object[]{"1", "", "", "0", coNo});
    }
    //End tạo mới
    
    //Chèn vào CSDL
    public void insertMySQL(String ten, String ma, String tien, String loai) throws SQLException, ClassNotFoundException{
        Connection connect = MySqlConnect.connectMySql();
        Statement statement = connect.createStatement();
        int result = statement.executeUpdate(sql + "'" + ten + "', '" + ma + "', '" + tien + "', '" +loai + "')");
    }
    //End chèn vào CSDL
    
    
    //Button Thêm Mới
    public void btnThemMoi(){
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            MyTable deleteModel = (MyTable) tbList_1.getModel();
//                for (int i = 0; i < tbList_1.getRowCount(); i++) {
                while(tbList_1.getRowCount()!=0) {
                    txtTongTien_1.setText("0 VNĐ");
                    try {
                        insertMySQL(tbList_1.getValueAt(0, 1).toString(), tbList_1.getValueAt(0, 2).toString(), tbList_1.getValueAt(0, 3).toString(), tbList_1.getValueAt(0, 4).toString());
                        deleteModel.removeRow(0);
                    } catch (SQLException ex) {
                        Logger.getLogger(TongTien.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TongTien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                deleteModel.addRow(new Object[]{"1", "", "", "0", CO});
//                createNew(tbList_1, CO);
//                createNew(tbList_2, NO);
            }
        });
    }
    //End Button Thêm Mới
    
    public TongTien(){
        super("Tính tiền - HKT");
        //Top chứa mã biên lai kiểu GridBag căn giữa
        panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(10, 10, 10));
        lbMaBienLai = new JLabel("Mã biên lai: ");
        lbMaBienLai.setBounds(40, 40, 300, 40);
        lbMaBienLai.setFont(new Font("Times New Roman", Font.BOLD, 14));
        txtMaBienLai = new JTextField();
        txtMaBienLai.setPreferredSize(new java.awt.Dimension(275, 20));
        btnNew = new JButton("Thêm Mới");
        panelTop.add(lbMaBienLai);
        panelTop.add(txtMaBienLai);
        panelTop.add(btnNew);
        //End Top
        
        //panelBotton chứa tổng tiền dùng Gid chia 2 cột
        panelBotton = new JPanel();
        panelBotton_L = new JPanel();
        panelBotton_R = new JPanel();
        panelBotton.setLayout(new GridLayout(1, 2));
        panelBotton_L.setLayout(new FlowLayout(10, 10, 10));
        panelBotton_R.setLayout(new FlowLayout(10, 10, 10));
        lbTongTien_1 = new JLabel("Tổng tiền: ");
        lbTongTien_1.setFont(new Font("Times New Roman", Font.TRUETYPE_FONT, 14));
        lbTongTien_2 = new JLabel("Tổng tiền: ");
        lbTongTien_2.setFont(new Font("Times New Roman", Font.TRUETYPE_FONT, 14));
        txtTongTien_1 = new JTextField("0 VNĐ");
        txtTongTien_2 = new JTextField("0 VNĐ");
        txtTongTien_1.setPreferredSize(new java.awt.Dimension(175, 20));
        txtTongTien_1.setEditable(false);
        txtTongTien_2.setPreferredSize(new java.awt.Dimension(175, 20));
        txtTongTien_2.setEditable(false);
        panelBotton_L.add(lbTongTien_1);
        panelBotton_L.add(txtTongTien_1);
        panelBotton_R.add(lbTongTien_2);
        panelBotton_R.add(txtTongTien_2);
        panelBotton.add(panelBotton_L);
        panelBotton.add(panelBotton_R);
        //End panelBotton
        
        //panelCente hiện thị table_1 và table_2
        panelCente = new JPanel();
        panelCente.setLayout(new GridLayout(1, 2));
        tbList_1 = new JTable();
        tbList_1.setModel(tb_1);
        tbList_1.setRowHeight(22);
        tbList_1.getColumnModel().getColumn(0).setCellRenderer(new MyRender());
        tbList_1.getColumnModel().getColumn(2).setCellRenderer(new MyRender());
        tbList_1.getColumnModel().getColumn(4).setCellRenderer(new MyRender());
        scrTable_1 = new JScrollPane(tbList_1);
        reSizeTable(tbList_1);
        addRow_tb1();
        Sum_tb1();
        Sum_keyboar_tb1();
        setTableValue();
        panelCente.add(scrTable_1);
        tbList_2 = new JTable();
        tbList_2.setModel(tb_2);
        tbList_2.setRowHeight(22);
        tbList_2.getColumnModel().getColumn(0).setCellRenderer(new MyRender());
        tbList_2.getColumnModel().getColumn(2).setCellRenderer(new MyRender());
        tbList_2.getColumnModel().getColumn(4).setCellRenderer(new MyRender());
//        TableColumnModel tcmCn_2 = tbList_2.getColumnModel();
//        TableColumn tcCN_2 = tcmCn_2.getColumn(4);
//        tcCN_2.setCellRenderer(new MyRender());
//        tcCN_2.setCellEditor(new MyEdit());
        scrTable_2 = new JScrollPane(tbList_2);
        reSizeTable(tbList_2);
        addRow_tb2();
        Sum_tb2();
        Sum_keyboar_tb2();
        panelCente.add(scrTable_2);
        //End panelCente
        
        //panel chính dạng Border
        panelRoot = new JPanel();
        panelRoot.setLayout(new BorderLayout());
        panelRoot.add(panelTop, BorderLayout.PAGE_START);
        panelRoot.add(panelBotton, BorderLayout.PAGE_END);
        panelRoot.add(panelCente, BorderLayout.CENTER);
        add(panelRoot);
        btnThemMoi();
        //End panel 
    }
        
    public static void main(String[] args) {
        TongTien demo = new TongTien();
        demo.setVisible(true);
        demo.setSize(950, 500);
        demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

