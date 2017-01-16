/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsdirectory.backend;

import static contactsdirectory.backend.PersonManagerImpl.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Martin
 */
public class DirectoryManagerImpl implements DirectoryManager
{

    private static final Logger logger = Logger.getLogger(DirectoryManagerImpl.class.getName());

    private DataSource dataSource;

    public void setDataSource(DataSource ds)
    {
        dataSource = ds;
    }

    @Override
    public void addContactToPerson(Person person, Contact contact) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "contact '{0}' asigning to '{1}'", new Object[]
        {
            contact, person
        });
        
        checkDataSource();
        validatePerson(person);
        validateContact(contact);

        if (person.getId() == null)
        {
            logger.log(Level.WARNING, "person '{0}' has null id", person);
            throw new IllegalEntityException("person id is null");
        }

        if (contact.getId() == null)
        {
            logger.log(Level.WARNING, "contact '{0}' has null id", contact);
            throw new IllegalEntityException("contact id is null");
        }

        try (Connection conn = dataSource.getConnection();
                PreparedStatement st = conn.prepareStatement("UPDATE contact SET personid = ? WHERE id = ?"))
        {
            try
            {
                conn.setAutoCommit(false);

                st.setLong(1, person.getId());
                st.setLong(2, contact.getId());

                int updated = st.executeUpdate();
                if (updated != 1)
                {
                    throw new ServiceFailureException("Internal Error: More rows "
                            + "updated when trying to assign contact:" + contact + " to person:" + person);
                }
                conn.commit();
                conn.setAutoCommit(true);

                logger.log(Level.FINE, "contact '{0}' asigned to '{1}'", new Object[]
                {
                    contact, person
                });
            } catch (SQLException | ServiceFailureException e)
            {
                String msg = "Error when assign contact to person in db";
                logger.log(Level.SEVERE, msg, e);

                conn.rollback();
                conn.setAutoCommit(true);

                throw new ServiceFailureException(msg, e);
            }
        } catch (SQLException e)
        {
            String msg = "Error when assign contact to person in db";
            logger.log(Level.SEVERE, msg, e);
            throw new ServiceFailureException(msg, e);
        }
    }

    @Override
    public void removeContactFromPerson(Person person, Contact contact) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "contact '{0}' deleting from person {1}", new Object[]
        {
            contact, person
        });
        checkDataSource();
        validatePerson(person);
        validateContact(contact);

        if (person.getId() == null)
        {
            logger.log(Level.WARNING, "person with id '{0}' already exists", person.getId());
            throw new IllegalEntityException("person id is null");
        }

        if (contact.getId() == null)
        {
            logger.log(Level.WARNING, "contact '{0}' has null id", contact);
            throw new IllegalEntityException("contact id is null");
        }

        ContactManager cm = new ContactManagerImpl();
        cm.setDataSource(dataSource);
        cm.removeContact(contact);
        logger.log(Level.FINE, "contact '{0}' deleted from person {1}", new Object[]
        {
            contact, person
        });
    }

    @Override
    public List<Contact> findAllContactsOfPerson(Person person) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "serching for contact of person '{0}'", person);

        List<Contact> contacts = new ArrayList<>();

        checkDataSource();
        validatePerson(person);
        if (person.getId() == null)
        {
            logger.log(Level.WARNING, "person '{0}' has null id", person);
            throw new IllegalEntityException("person id is null");
        }

        try (Connection conn = dataSource.getConnection())
        {
            try (PreparedStatement st = conn.prepareStatement("SELECT id FROM contact WHERE personid = ?"))
            {
                st.setLong(1, person.getId());
                try (ResultSet rs = st.executeQuery())
                {
                    ContactManager cm = new ContactManagerImpl();
                    cm.setDataSource(dataSource);

                    while (rs.next())
                    {
                        contacts.add(cm.getContact(rs.getLong("id")));
                    }

                    logger.log(Level.FINE, "serching for contact of person '{0}' completed", person);

                    return contacts.isEmpty() ? Collections.EMPTY_LIST : contacts;
                }
            }
        } catch (SQLException e)
        {
            String msg = "Error when retreaving contacts from db";
            logger.log(Level.SEVERE, msg, e);
            throw new ServiceFailureException(msg, e);
        }
    }

    @Override
    public Person findPersonWithContact(Contact contact) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "finding person with contact {0}",contact);
        Person result = null;
        checkDataSource();
        validateContact(contact);
        if (contact.getId() == null)
        {
            logger.log(Level.WARNING, "contact '{0}' has null id", contact);
            throw new IllegalEntityException("contact id is null");
        }

        Connection conn = null;
        PreparedStatement st = null;
        try
        {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT p.* FROM person p INNER JOIN contact c ON p.id=c.personid WHERE c.id = ?");
            st.setLong(1, contact.getId());
            return executeQueryForSinglePerson(st);
        } catch (SQLException ex)
        {
            String msg = "Error when getting person with id = " + contact.getId() + " from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally
        {
            DBUtilities.closeQuietly(conn, st);
        }
    }

    private void checkDataSource()
    {
        if (dataSource == null)
        {
            logger.log(Level.SEVERE, "dataSource is null");
            throw new IllegalStateException("DataSource is not set");
        }
    }

    private static void validatePerson(Person person)
    {
        if (person == null)
        {
            throw new IllegalArgumentException("person is null");
        }

        if (person.getName() == null)
        {
            throw new ValidationException("person name is null");
        }

        if (person.getLastName() == null)
        {
            throw new ValidationException("person surname is null");
        }
    }

    private static void validateContact(Contact contact)
    {
        if (contact == null)
        {
            logger.log(Level.WARNING, "contact is null");
            throw new IllegalArgumentException("contact is null");
        }
        if (contact.getType() == null)
        {
            logger.log(Level.WARNING, "contact has null type");
            throw new ValidationException("contact type is null");
        }
        if ((contact instanceof PhoneContact) && (((PhoneContact) contact).getPhoneNumber() == null))
        {
            logger.log(Level.WARNING, "contact has null phone number");
            throw new ValidationException("phone number is null");
        }
        if ((contact instanceof MailContact) && (((MailContact) contact).getMailAddress() == null))
        {
            logger.log(Level.WARNING, "contact has null mail address");
            throw new ValidationException("mail address is null");
        }
    }

    /*private void removeContactFromTypeTable(Contact contact, String table) throws SQLException
     {
     validateTable(table);
     String sql = "DELETE FROM " + table + " WHERE contactid = ?";

     try (Connection conn = dataSource.getConnection();
     PreparedStatement st = conn.prepareStatement(sql))
     {
     try
     {
     conn.setAutoCommit(false);

     st.setLong(1, contact.getId());
     int removed = st.executeUpdate();

     if (removed != 1)
     {
     throw new ServiceFailureException("Internal Error: More rows "
     + "deleted when trying to delete contact " + contact);
     }

     conn.commit();
     conn.setAutoCommit(true);
     } catch (SQLException | ServiceFailureException e)
     {
     conn.rollback();
     conn.setAutoCommit(true);
     throw e;
     }
     }
     }*/

    /*private void validateTable(String table) throws SQLException
     {
     switch (table)
     {
     case "phonecontact":
     case "mailcontact":
     return;
     default:
     throw new SQLException("Illegal table was selected for record searching.");
     }
     }*/
    private static Person rowToPerson(ResultSet rs) throws SQLException
    {
        Person result = new Person();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setLastName(rs.getString("surname"));
        return result;
    }

    private Person executeQueryForSinglePerson(PreparedStatement st) throws SQLException, ServiceFailureException
    {
        ResultSet rs = st.executeQuery();
        if (rs.next())
        {
            Person result = rowToPerson(rs);
            if (rs.next())
            {
                throw new ServiceFailureException(
                        "Internal integrity error: more graves with the same id found!");
            }
            return result;
        } else
        {
            return null;
        }
    }
}
