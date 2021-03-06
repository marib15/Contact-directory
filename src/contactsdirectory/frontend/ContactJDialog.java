/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsdirectory.frontend;

import contactsdirectory.backend.Contact;
import contactsdirectory.backend.ContactType;
import contactsdirectory.backend.MailContact;
import contactsdirectory.backend.PhoneContact;
import java.awt.Color;
import java.awt.Component;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Martin
 */
public class ContactJDialog extends javax.swing.JDialog//javax.swing.JFrame
{

    private Contact contact;
    private static ResourceBundle localizedTexts;
    private int dialogResult;

    public Contact getContact()
    {
        return contact;
    }

    public void setContact(Contact contact)
    {
        if (contact != null)
        {
            this.contact = contact;
            jComboBoxType.setEnabled(false);
            updateDataFields();
        }
    }

    public int showDialog()
    {
        setVisible(true);
        return dialogResult;
    }

    /**
     * Creates new form ContactJFrame
     */
    public ContactJDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        ContactJDialog.localizedTexts = ResourceBundle.getBundle("contactsdirectory.frontend.resources.contactForm");
        initComponents();
        getRootPane().setDefaultButton(jButtonOk);

        dialogResult = JOptionPane.CANCEL_OPTION;
        //getContentPane().setBackground(new Color(0xCC,0xE0,0xFF));//B2,0xD1,0xFF));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCancel = new javax.swing.JButton();
        jButtonOk = new javax.swing.JButton();
        jLabelData = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jLabelNote = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jComboBoxType = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaNote = new javax.swing.JTextArea();

        setTitle(localizedTexts.getString("title")
        );
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(490, 270));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setType(java.awt.Window.Type.UTILITY);

        jButtonCancel.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonCancel.setText(localizedTexts.getString("cancelBtn"));
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonOk.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jButtonOk.setText(localizedTexts.getString("okBtn"));
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });

        jLabelData.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelData.setText(localizedTexts.getString("mailAddress") + ":");

        jLabelType.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelType.setText(localizedTexts.getString("contactType") + ":"
        );

        jLabelNote.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelNote.setText(localizedTexts.getString("note") + ":");

        jTextFieldData.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N

        jComboBoxType.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jComboBoxType.setModel(enumToCBModel()
        );
        jComboBoxType.setRenderer(new ContactTypeRenderer()
        );
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeActionPerformed(evt);
            }
        });

        jTextAreaNote.setColumns(20);
        jTextAreaNote.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jTextAreaNote.setRows(5);
        jScrollPane1.setViewportView(jTextAreaNote);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNote)
                    .addComponent(jLabelData)
                    .addComponent(jLabelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldData)
                    .addComponent(jComboBoxType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addComponent(jButtonOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancel)
                .addGap(10, 10, 10))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNote)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonOk))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOkActionPerformed
    {//GEN-HEADEREND:event_jButtonOkActionPerformed
        if (!validateData())
        {
            return;
        }

        tryCreateContact();

        updateContact();

        dialogResult = JOptionPane.OK_OPTION;

        setVisible(false);
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxTypeActionPerformed
    {//GEN-HEADEREND:event_jComboBoxTypeActionPerformed
        switch (((ContactType) jComboBoxType.getSelectedItem()))//.getType())
        {
            case MAIL:
                jLabelData.setText(localizedTexts.getString("mailAddress") + ":");
                break;
            case PHONE:
                jLabelData.setText(localizedTexts.getString("phoneNumber") + ":");
                break;
        }
    }//GEN-LAST:event_jComboBoxTypeActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaNote;
    private javax.swing.JTextField jTextFieldData;
    // End of variables declaration//GEN-END:variables

    private void updateDataFields()
    {
        if (contact == null)
        {
            return;
        }

        String data = null;

        switch (contact.getType())
        {
            case MAIL:
                data = ((MailContact) contact).getMailAddress();
                break;
            case PHONE:
                data = ((PhoneContact) contact).getPhoneNumber();
                break;
        }

        jTextFieldData.setText(data);
        jTextAreaNote.setText(convertInternalFormToMultiline(contact.getNote()));
        jComboBoxType.setSelectedItem(contact.getType());
    }

    private ComboBoxModel enumToCBModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (ContactType item : ContactType.values())
        {
            model.addElement(item);//new ContactTypeItem(item));
        }
        return model;
    }

    private boolean validateData()
    {
        int dataLength = (ContactType)jComboBoxType.getSelectedItem() == ContactType.MAIL ? 70 : 20;
        
        if (jTextFieldData.getText().trim().isEmpty())
        {
            String field = jLabelData.getText();
            String msg = MessageFormat.format(localizedTexts.getString("noDataMsg"), field.substring(0, field.length() - 1));
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("noDataMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (jTextFieldData.getText().length() > dataLength)
        {
            String field = jLabelData.getText();
            String msg = MessageFormat.format(localizedTexts.getString("longerDataMsg"), field.substring(0, field.length() - 1), dataLength);
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("noDataMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (jTextAreaNote.getText().length() > 255)
        {
            String field = jLabelData.getText();
            String msg = localizedTexts.getString("longerNoteMsg"); //MessageFormat.format();
            JOptionPane.showMessageDialog(this, msg, localizedTexts.getString("noDataMsgTitle"), JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    private void tryCreateContact()
    {
        if (contact == null)
        {
            switch (((ContactType) jComboBoxType.getSelectedItem()))//.getType())
            {
                case MAIL:
                    contact = new MailContact();
                    contact.setType(ContactType.MAIL);
                    break;
                case PHONE:
                    contact = new PhoneContact();
                    contact.setType(ContactType.PHONE);
                    break;
            }
        }
    }

    private void updateContact()
    {
        switch (((ContactType) jComboBoxType.getSelectedItem()))//.getType())
        {
            case MAIL:                
                ((MailContact) contact).setMailAddress(jTextFieldData.getText());
                break;
            case PHONE:
                ((PhoneContact) contact).setPhoneNumber(jTextFieldData.getText());
                break;
        }
        
        contact.setNote(convertMultilineToInternalForm(jTextAreaNote.getText()));
    }

    private String convertMultilineToInternalForm(String text)
    {
        return text.replaceAll(System.lineSeparator(), "\n");
    }
    
    private String convertInternalFormToMultiline(String text)
    {
        return text.replaceAll("\n",System.lineSeparator());
    }

    private static class ContactTypeRenderer extends JLabel implements ListCellRenderer<ContactType>
    {

        public ContactTypeRenderer()
        {
            setOpaque(true);
            //setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends ContactType> list, ContactType value, int index, boolean isSelected, boolean cellHasFocus)
        {
            //ContactType type = (ContactType) value;

            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setText(localizedTexts.getString(value.name().toLowerCase()));
            setFont(list.getFont());

            return this;
        }

    }

    /*private class ContactTypeItem
     {

     private ContactType type;

     public ContactType getType()
     {
     return type;
     }

     public ContactTypeItem(ContactType type)
     {
     this.type = type;
     }

     @Override
     public String toString()
     {
     return localizedTexts.getString(type.name().toLowerCase());
     }
     }*/
}
