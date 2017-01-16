/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsdirectory.frontend;

import contactsdirectory.backend.ContactType;
import contactsdirectory.backend.MailContact;
import contactsdirectory.backend.Person;
import contactsdirectory.backend.PhoneContact;
import java.awt.Color;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 *
 * @author Martin
 */
public class PersonJDialog extends javax.swing.JDialog
{

    private final ResourceBundle localizedTexts;
    private Person person;
    private int dialogResult;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        updateDataFields();
    }
    
    public int showDialog()
    {
        setVisible(true);
        return dialogResult;
    }

    /**
     * Creates new form PersonJFrame
     */
    public PersonJDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        localizedTexts = ResourceBundle.getBundle("contactsdirectory.frontend.resources.personForm");
        initComponents();
        getRootPane().setDefaultButton(jButtonOk);
        //getContentPane().setBackground(new Color(0xCC, 0xE0, 0xFF));
        dialogResult = JOptionPane.CANCEL_OPTION;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelName = new javax.swing.JLabel();
        jLabelSurname = new javax.swing.JLabel();
        jLabelNote = new javax.swing.JLabel();
        jButtonOk = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldSurname = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaName = new javax.swing.JTextArea();

        setTitle(localizedTexts.getString("title"));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(490, 270));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setPreferredSize(new java.awt.Dimension(611, 300));
        setType(java.awt.Window.Type.UTILITY);

        jLabelName.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelName.setText(localizedTexts.getString("name") + ":"
        );

        jLabelSurname.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelSurname.setText(localizedTexts.getString("surname") + ":");

        jLabelNote.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelNote.setText(localizedTexts.getString("note") + ":");

        jButtonOk.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonOk.setText(localizedTexts.getString("okBtn"));
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jButtonCancel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel.setText(localizedTexts.getString("cancelBtn"));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jTextFieldName.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jTextFieldSurname.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jTextAreaName.setColumns(20);
        jTextAreaName.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTextAreaName.setLineWrap(true);
        jTextAreaName.setRows(5);
        jScrollPane1.setViewportView(jTextAreaName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelName)
                            .addComponent(jLabelSurname)
                            .addComponent(jLabelNote))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldName)
                            .addComponent(jTextFieldSurname)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonOk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSurname)
                    .addComponent(jTextFieldSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNote)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOkActionPerformed
    {//GEN-HEADEREND:event_jButtonOkActionPerformed
        if (!validateData())
        {
            return;
        }
        
        tryCreateContact();
        
        updatePerson();
        
        dialogResult = JOptionPane.OK_OPTION;
        
        setVisible(false);
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelSurname;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaName;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldSurname;
    // End of variables declaration//GEN-END:variables

    private void updateDataFields()
    {
        if (person == null)
        {
            return;
        }

        jTextFieldName.setText(person.getName());
        jTextFieldSurname.setText(person.getLastName());
        jTextAreaName.setText(convertInternalFormToMultiline(person.getNote()));
        
    }

    private void tryCreateContact()
    {
        if (person == null)
        {
           person = new Person();
        }
    }
    
    private boolean validateData() {

        
        if (jTextFieldName.getText().trim().isEmpty())
        {
            String field = jLabelName.getText();
            String msg = MessageFormat.format(localizedTexts.getString("noDataMsg"), field.substring(0, field.length() - 1));
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("errorMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (jTextFieldSurname.getText().trim().isEmpty())
        {
            String field = jLabelSurname.getText();
            String msg = MessageFormat.format(localizedTexts.getString("noDataMsg"), field.substring(0, field.length() - 1));
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("errorMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
       
        
        if (jTextAreaName.getText().length() > 255)
        {
            String field = jLabelNote.getText();
            String msg = localizedTexts.getString("longerNoteMsg"); //MessageFormat.format();
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("errorMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;    
    }

    private void updatePerson() {
        person.setName(jTextFieldName.getText());
        person.setLastName(jTextFieldSurname.getText());
        person.setNote(convertMultilineToInternalForm(jTextAreaName.getText()));
    
    }

    private String convertMultilineToInternalForm(String text) {
        return text.replaceAll(System.lineSeparator(), "\n");   
    }
    
    private String convertInternalFormToMultiline(String text)
    {
        return text.replaceAll("\n",System.lineSeparator());
    }
    
}