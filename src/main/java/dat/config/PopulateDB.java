package dat.config;

import dat.dtos.*;
import dat.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class PopulateDB {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Opret LoanType (enum types)
        LoanType houseLoan = new LoanType(new LoanTypeDTO(null, LoanTypeE.HOUSE_LOAN));
        LoanType carLoan = new LoanType(new LoanTypeDTO(null, LoanTypeE.CAR_LOAN));
        LoanType personalLoan = new LoanType(new LoanTypeDTO(null, LoanTypeE.PERSONAL_LOAN));

        em.persist(houseLoan);
        em.persist(carLoan);
        em.persist(personalLoan);

        // Opret LoanUsers
        LoanUser loanUser1 = new LoanUser(new LoanUserDTO("John Doe"));
        LoanUser loanUser2 = new LoanUser(new LoanUserDTO("Jane Doe"));
        LoanUser loanUser3 = new LoanUser(new LoanUserDTO("Alice Smith"));
        em.persist(loanUser1);
        em.persist(loanUser2);
        em.persist(loanUser3);

        // Opret LoanRequests for hver bruger med forskellige lån
        LoanRequest loanRequest1 = new LoanRequest(new LoanRequestDTO(200000, 15, new LoanTypeDTO(houseLoan), new LoanUserDTO(loanUser1)));
        LoanRequest loanRequest2 = new LoanRequest(new LoanRequestDTO(100000, 10, new LoanTypeDTO(carLoan), new LoanUserDTO(loanUser2)));
        LoanRequest loanRequest3 = new LoanRequest(new LoanRequestDTO(50000, 5, new LoanTypeDTO(personalLoan), new LoanUserDTO(loanUser3)));
        em.persist(loanRequest1);
        em.persist(loanRequest2);
        em.persist(loanRequest3);

        // Opret Banker
        Bank bank1 = new Bank(new BankDTO("Ny Kredit"));
        Bank bank2 = new Bank(new BankDTO("Nordea"));
        Bank bank3 = new Bank(new BankDTO("Danske Bank"));

        em.persist(bank1);
        em.persist(bank2);
        em.persist(bank3);

        // Opret LoanOffers og link dem til forskellige banker og anmodninger
        LoanOffer loanOffer1 = new LoanOffer(new LoanOfferDTO(null, 3.5, 200000, 20, LocalDateTime.now(), new LoanTypeDTO(houseLoan), new BankDTO(bank1), new LoanRequestDTO(loanRequest1)));
        LoanOffer loanOffer2 = new LoanOffer(new LoanOfferDTO(null, 4.0, 150000, 15, LocalDateTime.now(), new LoanTypeDTO(carLoan), new BankDTO(bank2), new LoanRequestDTO(loanRequest2)));
        LoanOffer loanOffer3 = new LoanOffer(new LoanOfferDTO(null, 5.0, 100000, 10, LocalDateTime.now(), new LoanTypeDTO(personalLoan), new BankDTO(bank3), new LoanRequestDTO(loanRequest3)));

        em.persist(loanOffer1);
        em.persist(loanOffer2);
        em.persist(loanOffer3);

        // Tilføj flere LoanOffers uden at knytte dem til nogen LoanRequest (ingen bruger)
        LoanOffer loanOfferWithoutRequest1 = new LoanOffer(new LoanOfferDTO(null, 2.5, 300000, 30, LocalDateTime.now(), new LoanTypeDTO(houseLoan), new BankDTO(bank1), null));
        LoanOffer loanOfferWithoutRequest2 = new LoanOffer(new LoanOfferDTO(null, 3.0, 250000, 25, LocalDateTime.now(), new LoanTypeDTO(carLoan), new BankDTO(bank2), null));
        LoanOffer loanOfferWithoutRequest3 = new LoanOffer(new LoanOfferDTO(null, 4.5, 150000, 12, LocalDateTime.now(), new LoanTypeDTO(personalLoan), new BankDTO(bank3), null));

        em.persist(loanOfferWithoutRequest1);
        em.persist(loanOfferWithoutRequest2);
        em.persist(loanOfferWithoutRequest3);

        em.getTransaction().commit();
        em.close();

    }



}
