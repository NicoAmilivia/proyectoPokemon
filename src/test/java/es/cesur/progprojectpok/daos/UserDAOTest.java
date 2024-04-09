package es.cesur.progprojectpok.daos;

import es.cesur.progprojectpok.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDAOTest {
    UserDAO userDAO;

    @BeforeAll
    void initialize(){
        userDAO = new UserDAO();
    }

    @Test
    void findById() {
        User user = userDAO.findById(1);
        assertEquals(1, user.getUserId());
        assertEquals("miguel", user.getUsername());

    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAutoById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByUsernameAndPassword() {
    }
}