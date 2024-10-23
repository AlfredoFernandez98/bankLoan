package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.BankDTO;
import dat.entities.Bank;
import dat.entities.LoanOffer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class BankDAO implements IDAO<BankDTO> {

    private static BankDAO instance;
    private static EntityManagerFactory emf;

    public static BankDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankDAO();
        }
        return instance;
    }


    @Override
    public BankDTO create(BankDTO bankDTO) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Bank bank = new Bank(bankDTO);
            em.persist(bank);
            em.getTransaction().commit();
            return new BankDTO(bank);

        }
    }

    @Override
    public Optional<BankDTO> getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            Bank bank = em.find(Bank.class, id);
            return bank != null ? Optional.of(new BankDTO(bank)) : Optional.empty();
        }
    }

    @Override
    public Set<BankDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dat.dtos.BankDTO(b) FROM Bank b", BankDTO.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public void update(BankDTO entity) {

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Bank bank = em.find(Bank.class, entity.getId());
            bank.setName(entity.getName());
            Bank mergedBank = em.merge(bank);
            em.getTransaction().commit();
        }

    }

    @Override
    public void delete(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Bank bank = em.find(Bank.class, id);
            if(bank != null){
                em.remove(bank);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public Optional<BankDTO> findByName(String name) {
        try(EntityManager em = emf.createEntityManager()) {
            Bank bank = em.createQuery("SELECT b FROM Bank b WHERE b.name = :name", Bank.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return bank != null ? Optional.of(new BankDTO(bank)) : Optional.empty();
        }
    }
}
