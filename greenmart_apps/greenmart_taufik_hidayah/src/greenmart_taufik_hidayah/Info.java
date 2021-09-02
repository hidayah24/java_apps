/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greenmart_taufik_hidayah;

import Config.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.io.File;
import java.awt.Image;
import java.sql.Connection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Taufik
 */
public class Info extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    String level, id;
    private String getBarang(int id_barang){
        String barang = null;
        try {
            String sql = "SELECT * FROM `taufik_barang` WHERE id_barang='"+id_barang+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            if(res.next()){
               barang = res.getString("nama_brg");
            }
        } catch (Exception e) {
            System.out.println(e);
        }   
            return barang;
    }
    private String getNama(int id_cust){
        String barang = null;
        try {
            String sql = "SELECT * FROM `taufik_customer` WHERE id_cust='"+id_cust+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            if(res.next()){
               barang = res.getString("nama");
            }
        } catch (Exception e) {
            System.out.println(e);
        }   
            return barang;
    }
    private String getNama2(int id_seller){
        String barang1 = null;
        try {
            String sql = "SELECT * FROM `taufik_seller` WHERE id_seller='"+id_seller+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            if(res.next()){
               barang1 = res.getString("nama");
            }
        } catch (Exception e) {
            System.out.println(e);
        }   
            return barang1;
    }
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Produk");
        model.addColumn("Total Bayar");
        model.addColumn("Tanggal Bayar");
        model.addColumn("Pembeli");
        
        //menampilkan data database kedalam tabel
         try {
            int no=1;
            String sql = "SELECT * FROM `taufik_pembayaran` WHERE id_seller='"+this.id+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,getBarang(res.getInt("id_barang")),res.getInt("total_bayar"),res.getDate("tgl_bayar"),getNama(res.getInt("id_cust"))});
            }
            jTable2.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void load_table2(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Produk");
        model.addColumn("Total Bayar");
        model.addColumn("Tanggal Bayar");
        model.addColumn("Penjual");
        
        //menampilkan data database kedalam tabel
         try {
            int no=1;
            String sql = "SELECT * FROM `taufik_pembayaran` WHERE id_cust='"+this.id+"'";
            java.sql.Connection conn=(Connection)Config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{no++,getBarang(res.getInt("id_barang")),res.getInt("total_bayar"),res.getDate("tgl_bayar"),getNama2(res.getInt("id_seller"))});
            }
            jTable2.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }   
    }
    public void cek(){
        if(this.level.equals("admin")){
            user_btn.setVisible(true);
            user_btn.setText("New User");
        } else if(this.level.equals("customer")){
            user_btn.setVisible(true);
            btn3.setText("Trolli");
            try {
            File FileToRead = new File("src/gambar/trolli2.png");
            Image Picture = ImageIO.read(FileToRead);
            btn3.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            try {
            File FileToRead = new File("src/gambar/File.png");
            Image Picture = ImageIO.read(FileToRead);
            btn4.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            btn4.setText("Riwayat");
            btn5.setVisible(true);
            btn6.setVisible(false);
            try {
            File FileToRead = new File("src/gambar/File.png");
            Image Picture = ImageIO.read(FileToRead);
            judul.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            judul.setText("RIWAYAT PEMBELIAN");
            load_table2();
        } else if(this.level.equals("seller")){
            btn3.setText("List Barang");
            btn4.setText("Barang Baru");
            try {
            File FileToRead = new File("src/gambar/baru.png");
            Image Picture = ImageIO.read(FileToRead);
            btn4.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            try {
            File FileToRead = new File("src/gambar/Chart.png");
            Image Picture = ImageIO.read(FileToRead);
            btn5.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            btn5.setText("Info");
            load_table();
        }
    }
    public Info(String id, String level) {
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
        this.level = level;
        this.id = id;
        cek();
        
    }

    private Info() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        user_btn = new javax.swing.JButton();
        home_btn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();

        jPanel6.setBackground(new java.awt.Color(255, 51, 51));
        jPanel6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel6AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel5.setText("Hai");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel5)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(84, 92, 100));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(26, 34, 43));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/delete1.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logo2.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Green Mart");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 572, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(26, 34, 43));

        user_btn.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        user_btn.setForeground(new java.awt.Color(255, 255, 255));
        user_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/User2.png"))); // NOI18N
        user_btn.setText("Profil");
        user_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_btnActionPerformed(evt);
            }
        });
        user_btn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                user_btnPropertyChange(evt);
            }
        });

        home_btn.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        home_btn.setForeground(new java.awt.Color(255, 255, 255));
        home_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/House.png"))); // NOI18N
        home_btn.setText("Home");
        home_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_btnActionPerformed(evt);
            }
        });
        home_btn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                home_btnPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Door.png"))); // NOI18N
        jLabel2.setText("Logout");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/list.png"))); // NOI18N
        btn3.setText("Barang");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        btn3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btn3PropertyChange(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        btn4.setForeground(new java.awt.Color(255, 255, 255));
        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        btn4.setText("About");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        btn4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btn4PropertyChange(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        btn5.setForeground(new java.awt.Color(255, 255, 255));
        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        btn5.setText("About");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        btn5.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btn5PropertyChange(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        btn6.setForeground(new java.awt.Color(255, 255, 255));
        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/search.png"))); // NOI18N
        btn6.setText("About");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        btn6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btn6PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(home_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(home_btn)
                .addGap(18, 18, 18)
                .addComponent(user_btn)
                .addGap(18, 18, 18)
                .addComponent(btn3)
                .addGap(18, 18, 18)
                .addComponent(btn4)
                .addGap(18, 18, 18)
                .addComponent(btn5)
                .addGap(18, 18, 18)
                .addComponent(btn6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(26, 34, 43));

        jPanel5.setBackground(new java.awt.Color(116, 119, 126));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(116, 119, 126));

        judul.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        judul.setForeground(new java.awt.Color(255, 255, 255));
        judul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Chart2.png"))); // NOI18N
        judul.setText("INFO PENJUALAN");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(judul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        int selectedOption = JOptionPane.showConfirmDialog(null,
            "Apakah anda ingin keluar dari aplikasi?", "Peringatan", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        // TODO add your handling code here:
        int koordinatX = evt.getXOnScreen();
        int koordinatY = evt.getYOnScreen();

        this.setLocation(koordinatX, koordinatY);
    }//GEN-LAST:event_jPanel2MouseDragged

    private void user_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_btnActionPerformed
        // TODO add your handling code here:
        new Profil(this.id, this.level).show();
        this.dispose();
    }//GEN-LAST:event_user_btnActionPerformed

    private void user_btnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_user_btnPropertyChange
        // TODO add your handling code here:
        user_btn.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_user_btnPropertyChange

    private void home_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_btnActionPerformed
        // TODO add your handling code here:
        new Dashboard(this.id, this.level).show();
        this.dispose();
    }//GEN-LAST:event_home_btnActionPerformed

    private void home_btnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_home_btnPropertyChange
        // TODO add your handling code here:
        home_btn.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_home_btnPropertyChange

    private void jPanel6AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel6AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel6AncestorAdded

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
        if(this.level.equals("seller")){
            new List(this.id, this.level).show();
            this.dispose();
        } else if(this.level.equals("customer")){
            new Trolli(this.id, this.level).show();
            this.dispose();
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btn3PropertyChange
        // TODO add your handling code here:
        btn3.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_btn3PropertyChange

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        // TODO add your handling code here:
        if(this.level.equals("seller")){
            new BarangBaru(this.id, this.level).show();
            this.dispose();
        } else if(this.level.equals("customer")){
            new Info(this.id,this.level).show();
            this.dispose();
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btn4PropertyChange
        // TODO add your handling code here:
        btn4.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_btn4PropertyChange

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
        if(this.level.equals("seller")){
            new Info(this.id, this.level).show();
            this.dispose();
        } else if(this.level.equals("customer")){
            new About(this.id, this.level).show();
            this.dispose();
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn5PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btn5PropertyChange
        // TODO add your handling code here:
        btn5.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_btn5PropertyChange

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new Login().show();
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
        new About(this.id, this.level).show();
        this.dispose();
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btn6PropertyChange
        // TODO add your handling code here:
        btn6.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_btn6PropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Info().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton home_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel judul;
    private javax.swing.JButton user_btn;
    // End of variables declaration//GEN-END:variables
}
