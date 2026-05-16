
package arayuz;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TelemetryEkran extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelemetryEkran.class.getName());

    
    public TelemetryEkran() {
        initComponents();
    }

    private int irtifa = 0;
    private int hiz = 0;
    private int batarya = 100;
    private int sicaklik = 40;
    private javax.swing.Timer ucusTimer;
    
    private void simulasyonuBaslat() {
        // Her 1000 milisaniyede (1 saniyede) bir tetiklenecek asenkron motor
        ucusTimer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (batarya > 0) {
                    irtifa += (int)(Math.random() * 40) + 10;   // Her saniye 10-50 metre arası tırmansın
                    hiz = 115 + (int)(Math.random() * 15);      // Hız 115-130 km/h arasında dalgalansın
                    batarya -= 1;                               // Batarya her saniye %1 azalsın
                    sicaklik = 55 + (int)(Math.random() * 6);   // Motor sıcaklığı 55-61 °C arası oynasın
                    
                    jLabel_irtifa.setText("İrtifa (Altitude): " + irtifa + " m");
                    jLabel_hiz.setText("Hız (Airspeed): " + hiz + " km/h");
                    jLabel_batarya.setText("Batarya (Battery): %" + batarya);
                    jLabel_sicaklik.setText("Motor Sıcaklığı: " + sicaklik + " °C");
                    simulasyon.UcusMotoru.telemetriKaydet(irtifa, hiz, batarya, sicaklik);
                    // Her saniye verileri dosyaya logluyoruz (Askeri Kara Kutu Mantığı)
                    try (FileWriter fw = new FileWriter("ucus_loglari.txt", true);
                    PrintWriter pw = new PrintWriter(fw)) {

                    DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String zamanDamgasi = dft.format(LocalDateTime.now());

                    pw.println(String.format("[%s] Alt: %d m | Speed: %d km/h | Bat: %d%% | Temp: %d C", 
                    zamanDamgasi, irtifa, hiz, batarya, sicaklik));

                } catch (Exception ex) {
                    System.out.println("Log yazma hatası: " + ex.getMessage());
            }
                    // Proaktif Karar Destek Alarmı: Batarya %20'nin altına düşerse metni kırmızı yap
                    if (batarya <= 20) {
                        jLabel_batarya.setForeground(java.awt.Color.RED);
                    }
                } else {
                    
                    ucusTimer.stop();
                    jButton_baslat.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Batarya tükendi! İHA güvenli otonom iniş moduna geçti.", "KRİTİK SEVİYE ALARMI", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        ucusTimer.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel_baslik = new javax.swing.JLabel();
        jLabel_irtifa = new javax.swing.JLabel();
        jLabel_hiz = new javax.swing.JLabel();
        jLabel_batarya = new javax.swing.JLabel();
        jLabel_sicaklik = new javax.swing.JLabel();
        jButton_baslat = new javax.swing.JButton();
        jButton_bitir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel_baslik.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_baslik.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_baslik.setText("Yer Kontrol İstasyonu - Anlık Telemetri Paneli");

        jLabel_irtifa.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_irtifa.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_irtifa.setText("İrtifa (Altitude): 0 m");

        jLabel_hiz.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_hiz.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_hiz.setText("Hız (Airspeed): 0 km/h");

        jLabel_batarya.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_batarya.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_batarya.setText("Batarya (Battery): %100");

        jLabel_sicaklik.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel_sicaklik.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_sicaklik.setText("Motor Sıcaklığı: 40 °C");

        jButton_baslat.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButton_baslat.setText("UÇUŞU BAŞLAT");
        jButton_baslat.addActionListener(this::jButton_baslatActionPerformed);

        jButton_bitir.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButton_bitir.setText("UÇUŞU BİTİR");
        jButton_bitir.addActionListener(this::jButton_bitirActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel_baslik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_irtifa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_hiz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_batarya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_sicaklik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(329, 329, 329))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jButton_baslat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton_bitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(117, 117, 117)))
                .addGap(107, 107, 107))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel_baslik)
                .addGap(39, 39, 39)
                .addComponent(jLabel_irtifa)
                .addGap(18, 18, 18)
                .addComponent(jLabel_hiz)
                .addGap(18, 18, 18)
                .addComponent(jLabel_batarya)
                .addGap(18, 18, 18)
                .addComponent(jLabel_sicaklik)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_baslat)
                    .addComponent(jButton_bitir))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_baslatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_baslatActionPerformed
    simulasyonuBaslat();
    jButton_baslat.setEnabled(false); 
    }//GEN-LAST:event_jButton_baslatActionPerformed

    private void jButton_bitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bitirActionPerformed
    if (ucusTimer != null && ucusTimer.isRunning()) {
        ucusTimer.stop();
        jButton_baslat.setEnabled(true); 
    JOptionPane.showMessageDialog(this, "Uçuş görevi operatör tarafından başarıyla sonlandırıldı. Telemetri logları kaydedildi.", "Görev Tamamlandı", JOptionPane.INFORMATION_MESSAGE);
}
    }//GEN-LAST:event_jButton_bitirActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelemetryEkran().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_baslat;
    private javax.swing.JButton jButton_bitir;
    private javax.swing.JLabel jLabel_baslik;
    private javax.swing.JLabel jLabel_batarya;
    private javax.swing.JLabel jLabel_hiz;
    private javax.swing.JLabel jLabel_irtifa;
    private javax.swing.JLabel jLabel_sicaklik;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
