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
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Taufik
 */
public class Profil extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    String level, id;
    DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    public void getBiodata() {
        if(this.level.equals("customer")){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                java.sql.Connection conn =(java.sql.Connection)Config.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT * FROM taufik_customer where id_cust='"+this.id+"'");
                if(sql.next()){
                    nama.setText(sql.getString("nama"));
                    alamat.setText(sql.getString("alamat"));
                    tgl_lahir.setText(String.valueOf(sql.getDate("tgl_lahir")));
                    bio.setText(sql.getString("bio"));
                }
            } catch (Exception e) { 
                JOptionPane.showMessageDialog(null, "Biodata Masih Kosong");
            }
        } else if(this.level.equals("seller")){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                java.sql.Connection conn =(java.sql.Connection)Config.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql = stm.executeQuery("SELECT * FROM taufik_seller where id_seller='"+this.id+"'");
                if(sql.next()){
                    nama.setText(sql.getString("nama"));
                    alamat.setText(sql.getString("alamat"));
                    tgl_lahir.setText(String.valueOf(sql.getDate("tgl_lahir")));
                    bio.setText(sql.getString("bio"));
                    member2.setText(String.valueOf(sql.getDate("member_since")));
                }
            } catch (Exception e) { 
                JOptionPane.showMessageDialog(null, "Biodata Masih Kosong");
            }
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
            member1.setVisible(false);
            member2.setVisible(false);
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
            member1.setVisible(true);
            member2.setVisible(true);
            try {
            File FileToRead = new File("src/gambar/Chart.png");
            Image Picture = ImageIO.read(FileToRead);
            btn5.setIcon(new ImageIcon(Picture));
            } catch (Exception e) {
            JOptionPane.showMessageDialog( null, e.toString() );
            }
            btn5.setText("Info");
        }
    }
    public Profil(String id, String level) {
        initComponents();
        Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();

        // membuat titik x dan y
        int x = layar.width / 2  - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;

        this.setLocation(x, y);
        this.level = level;
        this.id = id;
        cek();
        getBiodata();
    }

    private Profil() {
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
        jLabel7 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        alamat = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tgl_lahir = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bio = new javax.swing.JTextArea();
        save = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        member1 = new javax.swing.JLabel();
        member2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

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
        setResizable(false);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(home_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama:");

        nama.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Alamat:");

        alamat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        alamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal Lahir:");

        tgl_lahir.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        tgl_lahir.setText("yyyy-mm-dd");
        tgl_lahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_lahirActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Bio:");

        bio.setColumns(20);
        bio.setRows(5);
        jScrollPane2.setViewportView(bio);

        save.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        save.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                savePropertyChange(evt);
            }
        });

        edit.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        edit.setForeground(new java.awt.Color(255, 255, 255));
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        edit.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                editPropertyChange(evt);
            }
        });

        member1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        member1.setForeground(new java.awt.Color(255, 255, 255));
        member1.setText("Member Since:");

        member2.setEditable(false);
        member2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        member2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                member2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7))
                            .addGap(71, 71, 71)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(alamat)
                                .addComponent(nama)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tgl_lahir)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(member1)
                        .addGap(18, 18, 18)
                        .addComponent(member2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(edit)
                .addGap(29, 29, 29)
                .addComponent(save)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(alamat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tgl_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(member2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(member1)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edit)
                            .addComponent(save))))
                .addGap(19, 19, 19))
        );

        jPanel7.setBackground(new java.awt.Color(116, 119, 126));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/info.png"))); // NOI18N
        jLabel6.setText("Profil");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
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
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            new Info(this.id, this.level).show();
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

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void alamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatActionPerformed

    private void tgl_lahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_lahirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_lahirActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if(this.level.equals("customer")){
         Date date1 = Date.valueOf(tgl_lahir.getText());
            try {
                String sql = "INSERT INTO `taufik_customer`(`id_cust`, `nama`, `alamat`, `tgl_lahir`, `bio`) VALUES (?,?,?,?,?)";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, parseInt(this.id));
                pst.setString(2, nama.getText());
                pst.setString(3, alamat.getText());
                pst.setDate(4, date1);
                pst.setString(5, bio.getText());
                pst.execute(); 
                JOptionPane.showMessageDialog(null, "Penyimpanan berhasil");
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }   
        } else if(this.level.equals("seller")){
            Date date1 = Date.valueOf(tgl_lahir.getText());
            java.util.Date ys = new java.util.Date();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date date2 = java.sql.Date.valueOf(s.format(ys));
            try {
                String sql = "INSERT INTO `taufik_seller`(`id_seller`, `nama`, `alamat`, `tgl_lahir`, `bio`, `member_since`) VALUES (?,?,?,?,?,?)";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, parseInt(this.id));
                pst.setString(2, nama.getText());
                pst.setString(3, alamat.getText());
                pst.setDate(4, date1);
                pst.setString(5, bio.getText());
                pst.setDate(6, date2);
                pst.execute(); 
                JOptionPane.showMessageDialog(null, "Penyimpanan berhasil");
                new Profil(this.id, this.level).show();
                this.dispose();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if(this.level.equals("customer")){
         Date date1 = Date.valueOf(tgl_lahir.getText());
            try {
                String sql = "UPDATE `taufik_customer` SET `id_cust`=?,`nama`=?,`alamat`=?,`tgl_lahir`=?, `bio`=? WHERE id_cust='"+this.id+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, parseInt(this.id));
                pst.setString(2, nama.getText());
                pst.setString(3, alamat.getText());
                pst.setDate(4, date1);
                pst.setString(5, bio.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Update Berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }   
        } else if(this.level.equals("seller")){
            Date date1 = Date.valueOf(tgl_lahir.getText());
            Date date2 = Date.valueOf(member2.getText());
            try {
                String sql = "UPDATE `taufik_seller` SET `id_seller`=?,`nama`=?,`alamat`=?,`tgl_lahir`=?, `bio`=?, `member_since`=? WHERE id_seller='"+this.id+"'";
                java.sql.Connection conn=(Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, parseInt(this.id));
                pst.setString(2, nama.getText());
                pst.setString(3, alamat.getText());
                pst.setDate(4, date1);
                pst.setString(5, bio.getText());
                pst.setDate(6, date2);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Update Berhasil");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void savePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_savePropertyChange
        // TODO add your handling code here:
        save.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_savePropertyChange

    private void editPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_editPropertyChange
        // TODO add your handling code here:
        edit.setBackground(new java.awt.Color(65, 89, 240));
    }//GEN-LAST:event_editPropertyChange

    private void member2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_member2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_member2ActionPerformed

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
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamat;
    private javax.swing.JTextArea bio;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton edit;
    private javax.swing.JButton home_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JLabel member1;
    private javax.swing.JTextField member2;
    private javax.swing.JTextField nama;
    private javax.swing.JButton save;
    private javax.swing.JTextField tgl_lahir;
    private javax.swing.JButton user_btn;
    // End of variables declaration//GEN-END:variables
}
