/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package counselormgmtsystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Serene Sow
 */
public class adminManageCounselor extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminManageCounselor.class.getName());

    private DefaultTableModel model = new DefaultTableModel();
    private String[] columnName = new String[]{"ID", "Full Name", "Contact Number", "Email", "Specialisation", "Status"};
    
    private ArrayList<Counselor> counselorRefs = new ArrayList<>();
    private Counselor selectedCounselor = null;
    
    private Admin currentAdmin;
    public adminManageCounselor(Admin admin) {
        this.currentAdmin = admin;
        
        if (FileHandler.userList.isEmpty()) {
            new FileHandler().loadDataFromFiles();
        }
        
        model.setColumnIdentifiers(columnName);
        initComponents();
        statusCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        
        loadCounselors();
    }

    private void loadCounselors() {
        model.setRowCount(0);
        counselorRefs.clear();
        for (User u : FileHandler.userList) {
            if (u instanceof Counselor c) {
                counselorRefs.add(c);
                model.addRow(new Object[]{c.ID, c.fullName, c.contactNumber, c.email, c.specialization, c.status});
            }
        }
        clearFields();
    }

    private void clearFields() {
        nameTf.setText("");
        contactTf.setText("");
        emailTf.setText("");
        specialisationTf.setText("");
        passwordTf.setText("");
        statusCb.setSelectedIndex(0);
        recepTable.clearSelection();
        selectedCounselor = null;
    }

    private void applyFilters() {
        // 1. Set up the sorter
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        recepTable.setRowSorter(sorter);

        // 2. Get text
        String generalQuery = searchTf.getText().trim();

        // 3. Apply the filter to Name, Contact, and Email columns (1, 2, and 3)
        if (generalQuery.isEmpty() || generalQuery.equals("Search...")) {
            sorter.setRowFilter(null); 
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + generalQuery, 1, 2, 3));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        contactTf = new javax.swing.JTextField();
        nameTf = new javax.swing.JTextField();
        emailTf = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordTf = new javax.swing.JPasswordField();
        addBtn = new javax.swing.JButton();
        statusCb = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        specialisationTf = new javax.swing.JTextField();
        backBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        recepTable = new javax.swing.JTable();
        searchTf = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Filter Counselors (Name, Contact Number & Email):");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        contactTf.addActionListener(this::contactTfActionPerformed);

        nameTf.addActionListener(this::nameTfActionPerformed);

        emailTf.addActionListener(this::emailTfActionPerformed);

        editBtn.setText("Edit");
        editBtn.addActionListener(this::editBtnActionPerformed);

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(this::deleteBtnActionPerformed);

        jLabel1.setText("Full Name");

        jLabel2.setText("Contact Number");

        jLabel3.setText("Email");

        jLabel4.setText("Password");

        passwordTf.setText("jPasswordField1");

        addBtn.setText("Add");
        addBtn.addActionListener(this::addBtnActionPerformed);

        statusCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        statusCb.addActionListener(this::statusCbActionPerformed);

        jLabel7.setText("Status");

        jLabel8.setText("Specialisation");

        specialisationTf.addActionListener(this::specialisationTfActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(69, 69, 69)
                                .addComponent(contactTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(specialisationTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                                .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(statusCb, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contactTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(emailTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(specialisationTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(deleteBtn)
                    .addComponent(addBtn))
                .addGap(52, 52, 52))
        );

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Manage Counselors");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        recepTable.setModel(model);
        recepTable.setGridColor(new java.awt.Color(255, 255, 255));
        recepTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                recepTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(recepTable);

        searchTf.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        searchTf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTfKeyPressed(evt);
            }
        });

        searchBtn.setText("Search");
        searchBtn.addActionListener(this::searchBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backBtn)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBtn))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backBtn)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contactTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactTfActionPerformed
        contactTf.requestFocus();
    }//GEN-LAST:event_contactTfActionPerformed

    private void nameTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTfActionPerformed
        nameTf.requestFocus();
    }//GEN-LAST:event_nameTfActionPerformed

    private void emailTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTfActionPerformed
        emailTf.requestFocus();
    }//GEN-LAST:event_emailTfActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (selectedCounselor == null) {
            JOptionPane.showMessageDialog(this, "Please select a counselor from the table to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String pw = new String(passwordTf.getPassword());

        // Validate using Admin method
        String errorMessage = currentAdmin.validateUser(nameTf.getText(), contactTf.getText(), emailTf.getText(), pw, false, selectedCounselor.ID);
        if (errorMessage != null) { 
            JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.WARNING_MESSAGE); 
            return; 
        }

        // Update object
        selectedCounselor.fullName = nameTf.getText().trim();
        selectedCounselor.contactNumber = contactTf.getText().trim();
        selectedCounselor.email = emailTf.getText().trim();
        selectedCounselor.specialization = specialisationTf.getText().trim();
        selectedCounselor.status = statusCb.getSelectedItem().toString();
        
        if (pw.length() > 0 && !pw.equals("jPasswordField1")) {
            selectedCounselor.password = pw;
        }

        // Call Admin method to UPDATE
        currentAdmin.manageUserAccounts(FileHandler.userList, selectedCounselor, "UPDATE");
        new FileHandler().saveDataToFiles();
        
        JOptionPane.showMessageDialog(this, "Counselor account updated successfully.");
        loadCounselors();
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (selectedCounselor == null) {
            JOptionPane.showMessageDialog(this, "Please select a counselor from the table to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int confirm = JOptionPane.showConfirmDialog(this, "Delete counselor \"" + selectedCounselor.fullName + "\"? This cannot be undone.", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Call Admin method to DELETE
            currentAdmin.manageUserAccounts(FileHandler.userList, selectedCounselor, "DELETE");
            new FileHandler().saveDataToFiles();
            
            JOptionPane.showMessageDialog(this, "Counselor account deleted.");
            loadCounselors();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
String pw = new String(passwordTf.getPassword());

        // Validate using Admin method
        String errorMessage = currentAdmin.validateUser(nameTf.getText(), contactTf.getText(), emailTf.getText(), pw, true, null);
        if (errorMessage != null) { 
            JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.WARNING_MESSAGE); 
            return; 
        }

        // Generate data
        String newID = currentAdmin.generateNextCounselorID();
        String username = nameTf.getText().trim().toLowerCase().replaceAll("\\s+", ".");
        String selectedStatus = statusCb.getSelectedItem().toString();
        
        Counselor newC = new Counselor(
            newID, username, pw, nameTf.getText().trim(), selectedStatus, 
            contactTf.getText().trim(), emailTf.getText().trim(), specialisationTf.getText().trim()
        );

        // Call Admin method to ADD
        currentAdmin.manageUserAccounts(FileHandler.userList, newC, "ADD");
        new FileHandler().saveDataToFiles();
        
        JOptionPane.showMessageDialog(this, "Counselor " + newID + " successfully added.");
        loadCounselors();
    }//GEN-LAST:event_addBtnActionPerformed

    private void statusCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCbActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        adminManageUsers userPage = new adminManageUsers(this.currentAdmin);
        userPage.setLocationRelativeTo(null);
        userPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void recepTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recepTableMouseReleased
    int viewRow = recepTable.getSelectedRow();
        if (viewRow < 0) return;

        int modelRow = recepTable.convertRowIndexToModel(viewRow);

        selectedCounselor = counselorRefs.get(modelRow);

        nameTf.setText(selectedCounselor.fullName);
        contactTf.setText(selectedCounselor.contactNumber);
        emailTf.setText(selectedCounselor.email);
        specialisationTf.setText(selectedCounselor.specialization);
        statusCb.setSelectedItem(selectedCounselor.status);
    }//GEN-LAST:event_recepTableMouseReleased

    private void searchTfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTfKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            applyFilters();
        }
    }//GEN-LAST:event_searchTfKeyPressed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        applyFilters();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void specialisationTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specialisationTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specialisationTfActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            // Dummy admin to allow the page to run individually for testing
            Admin mockupAdmin = new Admin("ADM000", "admin", "admin123", "System Admin", "Active", "012-3456789", "admin@apu.edu.my", "Room 4.2");
            new adminManageCounselor(mockupAdmin).setVisible(true);
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JTextField contactTf;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField emailTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nameTf;
    private javax.swing.JPasswordField passwordTf;
    private javax.swing.JTable recepTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTf;
    private javax.swing.JTextField specialisationTf;
    private javax.swing.JComboBox<String> statusCb;
    // End of variables declaration//GEN-END:variables
}
