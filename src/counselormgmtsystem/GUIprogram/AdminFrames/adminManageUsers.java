/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package counselormgmtsystem;

import counselormgmtsystem.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class adminManageUsers extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminManageUsers.class.getName());
    private Admin currentAdmin;


    public adminManageUsers(Admin admin) {        
        initComponents();
        this.currentAdmin = admin;
        recepUserIconAvatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/counselormgmtsystem/recep.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        counselorUserIconAvatar.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/counselormgmtsystem/advisory.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        recepUserIconAvatar = new javax.swing.JLabel();
        recepUserBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        counselorUserIconAvatar = new javax.swing.JLabel();
        counselorUserBtn = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Manage Receptionists");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 540));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Manage Users");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 60));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setText("Select a user account type:");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        recepUserIconAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/counselormgmtsystem/recep.png"))); // NOI18N
        jPanel2.add(recepUserIconAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 42, 116, 109));

        recepUserBtn.setText("Receptionist");
        recepUserBtn.addActionListener(this::recepUserBtnActionPerformed);
        jPanel2.add(recepUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 184, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        counselorUserIconAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/counselormgmtsystem/advisory.png"))); // NOI18N
        jPanel3.add(counselorUserIconAvatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 43, 123, 109));

        counselorUserBtn.setText("Counselor");
        counselorUserBtn.addActionListener(this::counselorUserBtnActionPerformed);
        jPanel3.add(counselorUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 185, -1, -1));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(113, 113, 113)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(124, 124, 124)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(289, 289, 289)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 310, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(107, 107, 107)
                .add(jLabel2)
                .add(32, 32, 32)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recepUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recepUserBtnActionPerformed
        adminManageRecep recepPage = new adminManageRecep(this.currentAdmin);
        recepPage.setLocationRelativeTo(null);
        recepPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_recepUserBtnActionPerformed

    private void counselorUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_counselorUserBtnActionPerformed
        adminManageCounselor counsPage = new adminManageCounselor(this.currentAdmin);
        counsPage.setLocationRelativeTo(null);
        counsPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_counselorUserBtnActionPerformed

    
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

        Admin mockupAdmin = new Admin("ADM000", "admin", "admin123", "System Admin", "012-3456789", "admin@apu.edu.my", "Room 4.2");
        new adminManageUsers(mockupAdmin).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton counselorUserBtn;
    private javax.swing.JLabel counselorUserIconAvatar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton recepUserBtn;
    private javax.swing.JLabel recepUserIconAvatar;
    // End of variables declaration//GEN-END:variables
}
