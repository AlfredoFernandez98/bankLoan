package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.BankDTO;
import dat.dtos.LoanOfferDTO;
import dat.entities.Bank;
import dat.entities.LoanOffer;
import dat.entities.LoanRequest;
import dat.entities.LoanType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanOfferDAO implements IDAO<LoanOfferDTO> {

    private static LoanOfferDAO instance;
    private static EntityManagerFactory emf;

    public static LoanOfferDAO getInstance(EntityManagerFactory _emf) {

        if (instance == null) {
            emf = _emf;
            instance = new LoanOfferDAO();
        }
        return instance;
    }

    public BankDTO addLoanOfferToBank(Long bankId, Long loanOfferId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Bank bank = em.find(Bank.class, bankId);
            LoanOffer loanOffer = em.find(LoanOffer.class, loanOfferId);
            bank.addLoanOffer(loanOffer);
            Bank mergedBank = em.merge(bank);
            em.getTransaction().commit();
            return new BankDTO(mergedBank);
        }
    }
    @Override
    public LoanOfferDTO create(LoanOfferDTO entity) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanOffer loanOffer = new LoanOffer(entity);
            em.persist(loanOffer);
            em.getTransaction().commit();
            return new LoanOfferDTO(loanOffer);

        }

    }

    @Override
    public Optional<LoanOfferDTO> getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            LoanOffer loanOffer = em.find(LoanOffer.class, id);
            return loanOffer != null ? Optional.of(new LoanOfferDTO(loanOffer)) : Optional.empty();
        }
    }

    @Override
    public Set<LoanOfferDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dat.dtos.LoanOfferDTO(l) FROM LoanOffer l", LoanOfferDTO.class)
                    .getResultList()
                    .stream()
                    .collect(java.util.stream.Collectors.toSet());
        }
    }

    @Override
    public void update(LoanOfferDTO entity) {

            try(EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
                LoanOffer loanOffer = em.find(LoanOffer.class, entity.getId());
                loanOffer.setInterestRate(entity.getInterestRate());
                loanOffer.setAmount(entity.getAmount());
                loanOffer.setDuration(entity.getDuration());
                loanOffer.setRelevanceDate(entity.getRelevanceDate());
                loanOffer.setLoanType(entity.getLoanType() == null ? null : new LoanType(entity.getLoanType()));
                loanOffer.setBank(entity.getBank() == null ? null : new Bank(entity.getBank()));
                loanOffer.setLoanRequest(entity.getLoanRequest() == null ? null : new LoanRequest(entity.getLoanRequest()));

                LoanOffer mergedLoanOffer = em.merge(loanOffer);
                em.getTransaction().commit();

            }

    }

    @Override
    public void delete(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanOffer loanOffer = em.find(LoanOffer.class, id);
            if(loanOffer != null){
                em.remove(loanOffer);
                em.getTransaction().commit();
            }
        }

    }

    @Override
    public Optional<LoanOfferDTO> findByName(String name) {
      return Optional.empty();
    }
}
