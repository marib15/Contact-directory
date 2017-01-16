/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsdirectory.backend;

/**
 *
 * @author Martin
 */
public class MailContact extends Contact {
    private String mailAddress;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
