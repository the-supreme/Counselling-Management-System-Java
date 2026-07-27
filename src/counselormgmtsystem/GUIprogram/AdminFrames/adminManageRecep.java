/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package counselormgmtsystem;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
/////****when doing code for each button, if theres a formula or wtv call a function dont straight away put it inside the gui itself
///input validation put in the admin class then call to gui

public class adminManageRecep extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminManageRecep.class.getName());
    private DefaultTableModel model = new DefaultTableModel();
    private String columnName[] = {"ID", "Full Name","Contact Number","Email","Status"};
    
    private ArrayList<Receptionist> receptionistRefs = new ArrayList<>();
    private Receptionist selectedReceptionist = null;
    private Admin currentAdmin;

    public adminManageRecep(Admin admin) {
       this.currentAdmin = admin;
        
        if (FileHandler.userList.isEmpty()) {
            new FileHandler().loadDataFromFiles();
        }
        
        model.setColumnIdentifiers(columnName);
        initComponents();
        statusCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        
        loadReceptionists();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        recepTable = new javax.swing.JTable();
        searchTf = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        contactTf.addActionListener(this::contactTfActionPerformed);

        nameTf.addActionListener(this::nameTfActionPerformed);

        emailTf.addActionListener(this::emailTfActionPerformed);

        editBtn.setText("Edit");
        editBtn.addActionListener(this::editBtnActionPerformed);

        deleteBtn.setBackground(new java.awt.Color(255, 102, 102));
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusCb, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(69, 69, 69)
                            .addComponent(contactTf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addComponent(emailTf, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
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
                    .addComponent(jLabel4)
                    .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(deleteBtn)
                    .addComponent(addBtn))
                .addGap(52, 52, 52))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Manage Receptionists");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        recepTable.setBorder(new javax.swing.border.MatteBorder(null));
        recepTable.setModel(model);
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

        jLabel6.setText("Filter Receptionists (Name, Contact Number & Email):");

        backBtn.setText("Back");
        backBtn.addActionListener(this::backBtnActionPerformed);

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
    private void loadReceptionists() {
        model.setRowCount(0);
        receptionistRefs.clear();
        for (User u : FileHandler.userList) {
            if (u instanceof Receptionist) {
                Receptionist r = (Receptionist) u;
                receptionistRefs.add(r);
                model.addRow(new Object[]{r.ID, r.fullName, r.contactNumber, r.email, r.status});            }
        }
        clearFields();
    }
 
    private void clearFields() {
        nameTf.setText("");
        contactTf.setText("");
        emailTf.setText("");
        passwordTf.setText("");
        recepTable.clearSelection();
        selectedReceptionist = null;
    }

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        if (selectedReceptionist == null) {
                JOptionPane.showMessageDialog(this, "Please select a receptionist from the table to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String pw = new String(passwordTf.getPassword());

            // Call the validation through the Admin object
            // isNewUser is false, and pass the ID so it doesn't flag itself as a duplicate email
            String errorMessage = currentAdmin.validateUser(nameTf.getText(), contactTf.getText(), emailTf.getText(), pw, false, selectedReceptionist.ID);

            if (errorMessage != null) {
                JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            // Update the Receptionist object's data
            selectedReceptionist.fullName = nameTf.getText().trim();
            selectedReceptionist.contactNumber = contactTf.getText().trim();
            selectedReceptionist.email = emailTf.getText().trim();
            selectedReceptionist.status = statusCb.getSelectedItem().toString();

            // Only update the password if they actually typed a new one
            if (pw.length() > 0 && !pw.equals("passwordTf")) {
                selectedReceptionist.password = pw;
            }

            currentAdmin.manageUserAccounts(FileHandler.userList, selectedReceptionist, "UPDATE");

            new FileHandler().saveDataToFiles();
            JOptionPane.showMessageDialog(this, "Receptionist account updated successfully.");
            loadReceptionists();

    }//GEN-LAST:event_editBtnActionPerformed

    private void contactTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactTfActionPerformed
        contactTf.requestFocus();
    }//GEN-LAST:event_contactTfActionPerformed

    private void nameTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTfActionPerformed
        nameTf.requestFocus();
    }//GEN-LAST:event_nameTfActionPerformed

    private void emailTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTfActionPerformed
        emailTf.requestFocus();
    }//GEN-LAST:event_emailTfActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        String query = searchTf.getText();
        executeSearch(query);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void searchTfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTfKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            String query = searchTf.getText();
            executeSearch(query);
    }
    }//GEN-LAST:event_searchTfKeyPressed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String pw = new String(passwordTf.getPassword());

            //call the validation through the Admin object!
            String errorMessage = currentAdmin.validateUser(nameTf.getText(), contactTf.getText(), emailTf.getText(), pw, true, null);

            if (errorMessage != null) {
                JOptionPane.showMessageDialog(this, errorMessage, "Input Error", JOptionPane.WARNING_MESSAGE);
                return; 
            }

        String newID = currentAdmin.generateNextReceptionistID();        
        String username = nameTf.getText().trim().toLowerCase().replaceAll("\\s+", ".");
        String selectedStatus = statusCb.getSelectedItem().toString();
        
        Receptionist newReceptionist = new Receptionist(
            newID, 
            username, 
            new String(pw),
            nameTf.getText().trim(), 
            selectedStatus, 
            contactTf.getText().trim(),
            emailTf.getText().trim()    
        );

        //admin method overload
        currentAdmin.manageUserAccounts(FileHandler.userList, newReceptionist, "ADD");

        new FileHandler().saveDataToFiles();

        JOptionPane.showMessageDialog(this, "Receptionist " + newID + " successfully added.");
        loadReceptionists();
    }//GEN-LAST:event_addBtnActionPerformed

    private void recepTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recepTableMouseReleased
        int viewRow = recepTable.getSelectedRow();

        if (viewRow < 0) {
            return;
        }

        int modelRow = recepTable.convertRowIndexToModel(viewRow);

        String name = String.valueOf(model.getValueAt(modelRow, 1));
        String contact = String.valueOf(model.getValueAt(modelRow, 2));
        String email = String.valueOf(model.getValueAt(modelRow, 3));

        nameTf.setText(name);
        contactTf.setText(contact);
        emailTf.setText(email);

        selectedReceptionist = receptionistRefs.get(modelRow);
        statusCb.setSelectedItem(selectedReceptionist.status);
    }//GEN-LAST:event_recepTableMouseReleased

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        if (selectedReceptionist == null) {
            JOptionPane.showMessageDialog(this, "Please select a receptionist from the table to delete.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete receptionist \"" + selectedReceptionist.fullName + "\"? This cannot be undone.",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        currentAdmin.manageUserAccounts(FileHandler.userList, selectedReceptionist, "DELETE");

        new FileHandler().saveDataToFiles();

        JOptionPane.showMessageDialog(this, "Receptionist account deleted.");
        loadReceptionists();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void statusCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusCbActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        adminManageUsers userPage = new adminManageUsers(this.currentAdmin);
        userPage.setLocationRelativeTo(null);
        userPage.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void executeSearch(String query) {
        if (recepTable == null) return; 

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) recepTable.getModel();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        recepTable.setRowSorter(sorter);

        if (query.trim().isEmpty() || query.equals("Search...")) {
            sorter.setRowFilter(null); 
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query));
        }
    }

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        FileHandler fh = new FileHandler();
        try {
            fh.loadDataFromFiles();
        } catch (Exception e) {
            System.out.println("Warning: Data load failed, starting with empty state: " + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            Admin mockupAdmin = new Admin("ADM000", "admin", "admin123", "System Admin", "Active", "012-3456789", "admin@apu.edu.my", "Room 4.2");
            new adminManageRecep(mockupAdmin).setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nameTf;
    private javax.swing.JPasswordField passwordTf;
    private javax.swing.JTable recepTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTf;
    private javax.swing.JComboBox<String> statusCb;
    // End of variables declaration//GEN-END:variables

}
