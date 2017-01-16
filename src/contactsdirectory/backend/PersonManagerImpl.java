/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contactsdirectory.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class PersonManagerImpl implements PersonManager
{

    public static final Logger logger = Logger.getLogger(PersonManagerImpl.class.getName());

    
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    private void checkDataSource() { 
        if (dataSource == null) {
            throw new IllegalStateException("DataSource is not set");
        }
    }
    
    @Override
    public void createPerson(Person person) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "person '{0}' creating", person);
        checkDataSource();
        validate(person);
        
        if (person.getId() != null) {
            logger.log(Level.WARNING, "person with id '{0}' already exists", person.getId());
            throw new IllegalArgumentException("person id is already set");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            
            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO Person (name,surname, note) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, person.getName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getNote());
            
            int count = st.executeUpdate();
            DBUtilities.checkUpdatesCount(count, person, true);

            Long id = DBUtilities.getId(st.getGeneratedKeys());
            person.setId(id);
            conn.commit();
            logger.log(Level.FINE, "person '{0}' created", person);
        } catch (SQLException ex) {
            String msg = "Error when inserting person into db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtilities.doRollbackQuietly(conn);
            DBUtilities.closeQuietly(conn, st);
        }
    }
    
    
    
    @Override
    public void editPerson(Person person) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "person '{0}' updating", person);
        checkDataSource();
        validate(person);
        if (person.getId() == null) {
            logger.log(Level.WARNING, "person with id '{0}' already exists", person.getId());
            throw new IllegalEntityException("person id is null");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement("UPDATE person SET name = ?, surname = ?, note = ?  WHERE id = ?");
            
            st.setString(1, person.getName());
            st.setString(2, person.getLastName());
            st.setString(3, person.getNote());
            st.setLong(4, person.getId());

            int count = st.executeUpdate();
            DBUtilities.checkUpdatesCount(count, person, false);
            conn.commit();
            logger.log(Level.FINE, "person '{0}' updated", person);
        } catch (SQLException ex) {
            String msg = "Error when updating person in the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtilities.doRollbackQuietly(conn);
            DBUtilities.closeQuietly(conn, st);
        }
    }
    
    
    @Override
    public void removePerson(Person person) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "person '{0}' removing", person);
        checkDataSource();
        validate(person);
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            // Temporary turn autocommit mode off. It is turned back on in 
            // method DBUtils.closeQuietly(...) 
            conn.setAutoCommit(false);
            st = conn.prepareStatement("DELETE FROM person WHERE id = ?");        
            st.setLong(1, person.getId());
            int count = st.executeUpdate();
            DBUtilities.checkUpdatesCount(count, person, false);
            conn.commit();
            logger.log(Level.FINE, "person '{0}' removed", person);
        } catch (SQLException ex) {
            String msg = "Error when deleting person from the db";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
           DBUtilities.doRollbackQuietly(conn);
           DBUtilities.closeQuietly(conn, st);
        }
    }
    
    @Override
    public Person findPersonById(Long id) throws IllegalArgumentException
    {
        logger.log(Level.FINE, "searching person by id: {0}", id);
        checkDataSource();
        
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.prepareStatement(
                    "SELECT id, name, surname, note FROM person WHERE id = ?");
            st.setLong(1, id);
            logger.log(Level.FINE, "person searched");
            return executeQueryForSinglePerson(st);
        } catch (SQLException ex) {
            String msg = "Error when getting person with id = " + id + " from DB";
            logger.log(Level.SEVERE, msg, ex);
            throw new ServiceFailureException(msg, ex);
        } finally {
            DBUtilities.closeQuietly(conn, st);
        }
    }  
    
    @Override
    public List<Person> getAllPeople()
    {
        logger.log(Level.FINE, "searching all people");
        List<Person> people = new ArrayList<>();

        try(Connection conn = dataSource.getConnection();
                PreparedStatement st = conn.prepareStatement("SELECT id, name, surname, note FROM person");
                ResultSet rs = st.executeQuery())
        {
            while(rs.next())
            {
                people.add(resultSetToPerson(rs));
            }
        logger.log(Level.FINE, "searching completed");
        } catch (SQLException ex)
        {
            throw new ServiceFailureException("Error when retrieving all people from db.", ex);
        }
        
        if (people.isEmpty())
        {
            return Collections.EMPTY_LIST;
        } else
        {
            return people;
        }
    }

    private Long getKey(ResultSet keyRS, Person person) throws ServiceFailureException, SQLException
    {
        if (keyRS.next())
        {
            if (keyRS.getMetaData().getColumnCount() != 1)
            {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert person " + person
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next())
            {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert person " + person
                        + " - more keys found");
            }
            return result;
        } else
        {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert person " + person
                    + " - no key found");
        }
    }

    private Person resultSetToPerson(ResultSet rs) throws SQLException
    {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setLastName(rs.getString("surname"));
        person.setNote(rs.getString("note"));
        return person;
    }
    
    private Person executeQueryForSinglePerson(PreparedStatement st) throws SQLException, ServiceFailureException{
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Person result = rowToPerson(rs);                
            if (rs.next()) {
                throw new ServiceFailureException(
                        "Internal integrity error: more graves with the same id found!");
            }
            return result;
        } else {
            return null;
        }
    }
    
    private static Person rowToPerson(ResultSet rs) throws SQLException {
        Person result = new Person();
        result.setId(rs.getLong("id"));
        result.setName(rs.getString("name"));
        result.setLastName(rs.getString("surname"));
        result.setNote(rs.getString("note"));
        return result;
    }
    
    private void validate(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("person is null");
        } 
        if (person.getName() == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (person.getLastName() == null) {
            throw new IllegalArgumentException("surname is null");
        }
    }
}
