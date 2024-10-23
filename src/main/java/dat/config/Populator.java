package dat.config;

import dat.daos.impl.BankDAO;
import dat.daos.impl.LoanOfferDAO;
import dat.dtos.BankDTO;
import dat.entities.Bank;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Populator {

    private static BankDAO bankDAO;
    private static EntityManagerFactory emf;



    public Populator(BankDAO bankDAO , EntityManagerFactory emf){
        this.bankDAO = bankDAO;
        this.emf = emf;

    }


    public List<BankDTO> populate4Banks(){
        BankDTO b1, b2, b3, b4;

        b1 = new BankDTO("Ny kredit");
        b2 = new BankDTO("Nordea");
        b3 = new BankDTO("Danske Bank");
        b4 = new BankDTO("Jyske Bank");

        bankDAO.create(b1);
        bankDAO.create(b2);
        bankDAO.create(b3);
        bankDAO.create(b4);

        Bank bank1 =new Bank(b1);
        Bank bank2 =new Bank(b2);
        Bank bank3 =new Bank(b3);
        Bank bank4 =new Bank(b4);


//        LoanTypeDTO loanType1 = new LoanTypeDTO(null, LoanTypeE.CAR_LOAN);
//        LoanTypeDTO loanType2 = new LoanTypeDTO(null, LoanTypeE.HOUSE_LOAN);
//        LoanTypeDTO loanType3 = new LoanTypeDTO(null, LoanTypeE.PERSONAL_LOAN);
//
//
//
//        Set<LoanOfferDTO> loanOfferDTO1= new HashSet<>();
//        loanOfferDTO1.add(new LoanOfferDTO(null, 1.5,200000,15, LocalDateTime.now(),loanType1,b1,null));
//

        return List.of(b1, b2, b3, b4);

    }

    public void cleanBanksTest(){
        // Delete all data from database
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Bank b").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE bank_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        }
    }
}
