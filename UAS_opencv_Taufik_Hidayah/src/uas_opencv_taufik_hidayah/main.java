/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uas_opencv_taufik_hidayah;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Taufik
 */
public class main extends javax.swing.JFrame {
     private Dimension screensize;
    private static Mat doCanny(Mat frame) {
        double threshold = 0;
        // init
        Mat grayImage = new Mat();
        Mat detectedEdges = new Mat();

        // convert to grayscale
        Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);

        // reduce noise with a 3x3 kernel
        Imgproc.blur(grayImage, detectedEdges, new Size(3, 3));

        // canny detector, with ratio of lower:upper threshold of 3:1
       Imgproc.Canny(detectedEdges, detectedEdges, threshold, threshold * 3, 3, false);

        // using Canny's output as a mask, display the result
        Mat dest = new Mat();
        Core.add(dest, Scalar.all(0), dest);
        frame.copyTo(dest, detectedEdges);

        return dest;
    }
    private static double getHistAverage(Mat hsvImg, Mat hueValues) {
            // init
            double average = 0.0;
            Mat hist_hue = new Mat();
            MatOfInt histSize = new MatOfInt(180);
            List<Mat> hue = new ArrayList<>();
            hue.add(hueValues);

            // compute the histogram
            Imgproc.calcHist(hue, new MatOfInt(0), new Mat(), hist_hue, histSize, new MatOfFloat(0, 179));

            // get the average for each bin
            for (int h = 0; h < 180; h++)
            {
                    average += (hist_hue.get(h, 0)[0] * h);
            }

            return average = average / hsvImg.size().height / hsvImg.size().width;
    }
    private static Mat doBackgroundRemoval(Mat frame) {

         // init
        Mat hsvImg = new Mat();
        List<Mat> hsvPlanes = new ArrayList<>();
        Mat thresholdImg = new Mat();

        // threshold the image with the histogram average value
        hsvImg.create(frame.size(), CvType.CV_8U);
        Imgproc.cvtColor(frame, hsvImg, Imgproc.COLOR_BGR2HSV);
        Core.split(hsvImg, hsvPlanes);

        double threshValue = getHistAverage(hsvImg, hsvPlanes.get(0));

        
        Imgproc.threshold(hsvPlanes.get(0), thresholdImg, threshValue, 179.0, Imgproc.THRESH_BINARY_INV);
   
       // Imgproc.threshold(hsvPlanes.get(0), thresholdImg, threshValue, 179.0, Imgproc.THRESH_BINARY);

        Imgproc.blur(thresholdImg, thresholdImg, new Size(5, 5));

        // dilate to fill gaps, erode to smooth edges
        Imgproc.dilate(thresholdImg, thresholdImg, new Mat(), new Point(-1, 1), 6);
        Imgproc.erode(thresholdImg, thresholdImg, new Mat(), new Point(-1, 1), 6);

        Imgproc.threshold(thresholdImg, thresholdImg, threshValue, 179.0, Imgproc.THRESH_BINARY);

        // create the new image
        Mat foreground = new Mat(frame.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        frame.copyTo(foreground, thresholdImg);

        return foreground;
    }
    public void bg_eraser() {
        // Loading the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Reading the Image from the file and storing it in to a Matrix object
        String file = "src\\gambar\\exterior.jpg";
        Mat man = Imgcodecs.imread(file);
        man = doCanny(man);
        man = doBackgroundRemoval(man);
        Image img = HighGui.toBufferedImage(man);
         // BufferedImage to ImageIcon
        ImageIcon imageIcon = new ImageIcon(img);
        gambar.setIcon(imageIcon);
        
    }
    public void dilatasi() {
        try{	
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         String file ="src\\gambar\\exterior.jpg";
         Mat src = Imgcodecs.imread(file);
         Mat destination = new Mat(src.rows(),src.cols(),src.type());
         destination = src;
         int dilation_size = 5;
         Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
         Imgproc.dilate(src, destination, element);
         Image img = HighGui.toBufferedImage(src);
         // BufferedImage to ImageIcon
         ImageIcon imageIcon = new ImageIcon(img);
         gambar.setIcon(imageIcon);
         
      } catch (Exception e) {
         System.out.println("error:" + e.getMessage());
      } 
    }
    public void erosi() {  
      try{	
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         String file ="src\\gambar\\exterior.jpg";
         Mat src = Imgcodecs.imread(file);
         Mat destination = new Mat(src.rows(),src.cols(),src.type());
         destination = src;
         int erosion_size = 5;
         Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*erosion_size + 1, 2*erosion_size+1));
         Imgproc.erode(src, destination, element);
         Image img = HighGui.toBufferedImage(src);
         // BufferedImage to ImageIcon
         ImageIcon imageIcon = new ImageIcon(img);
         gambar.setIcon(imageIcon);
         
      } catch (Exception e) {
         System.out.println("error:" + e.getMessage());
      } 
    }
    public void start(){
      //Loading the OpenCV core library
      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
      String file ="src\\gambar\\exterior.jpg";
      Mat src = Imgcodecs.imread(file);
      //Creating an empty matrices to store edges, source, destination
      Mat gray = new Mat(src.rows(), src.cols(), src.type());
      Mat edges = new Mat(src.rows(), src.cols(), src.type());
      Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
      //Converting the image to Gray
      Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGB2GRAY);
      //Blurring the image
      Imgproc.blur(gray, edges, new Size(3, 3));
      //Detecting the edges
      Imgproc.Canny(edges, edges, 100, 100*3);
      //Copying the detected edges to the destination matrix
      src.copyTo(dst, edges);      
      //Converting matrix to BufferedImage
      Image img = HighGui.toBufferedImage(dst);
      // BufferedImage to ImageIcon
      ImageIcon imageIcon = new ImageIcon(img);
      gambar.setIcon(imageIcon);
   }
    public main() {
        initComponents();
        screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screensize.width / 2) - (getSize().width / 2), (screensize.height / 2) - (getSize().height / 2));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        Dilatasi = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        gambar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OpenCV By Taufik Hidayah");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("OpenCV Program By Taufik Hidayah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(91, 91, 91))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButton1.setText("Detektor Tepi Canny");
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        Dilatasi.setText("Dilatasi");
        Dilatasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DilatasiActionPerformed(evt);
            }
        });

        jToggleButton3.setText("Erosi");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton4.setText("Background Eraser");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        gambar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/exterior.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gambar, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gambar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(Dilatasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jToggleButton1)
                        .addGap(18, 18, 18)
                        .addComponent(Dilatasi)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jToggleButton4))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
         if (jToggleButton1.isSelected()) {
            start();
        } else {
            gambar.setIcon(new ImageIcon("src\\gambar\\exterior.jpg"));
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void DilatasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DilatasiActionPerformed
        if (Dilatasi.isSelected()) {
            dilatasi();
        } else {
            gambar.setIcon(new ImageIcon("src\\gambar\\exterior.jpg"));
        }        
        
    }//GEN-LAST:event_DilatasiActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
        if (jToggleButton4.isSelected()) {
            bg_eraser();
        } else {
            gambar.setIcon(new ImageIcon("src\\gambar\\exterior.jpg"));
        }  
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1StateChanged

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
        if (jToggleButton3.isSelected()) {
            erosi();
        } else {
            gambar.setIcon(new ImageIcon("src\\gambar\\exterior.jpg"));
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Dilatasi;
    private javax.swing.JLabel gambar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    // End of variables declaration//GEN-END:variables
}
