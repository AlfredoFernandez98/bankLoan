package dat.daos.impl;

import dat.config.HibernateConfig;
import dat.config.Populator;
import dat.dtos.BankDTO;
import dat.entities.Bank;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BankDAOTest {

    private static Javalin app;
    private static EntityManagerFactory emf;
    private static BankDAO bankDAO;
    private static Populator populator;

    private static BankDTO b1,b2,b3,b4;

    private List<BankDTO> testBanks;
    @BeforeEach
    void setup(){

        emf = HibernateConfig.getEntityManagerFactoryForTest();
        bankDAO = BankDAO.getInstance(emf);
        populator = new Populator(bankDAO,emf);


        testBanks = populator.populate4Banks();
        b1 = testBanks.get(0);
        b2 = testBanks.get(1);
        b3 = testBanks.get(2);
        b4 = testBanks.get(3);
    }

    @AfterEach
    void tearDown() {
        populator.cleanBanksTest();
    }
    @Test
    void create() {

       BankDTO b5 = bankDAO.create(new BankDTO("Bank 5"));
        assertNotNull(b5.getId());
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByName() {
    }
}