package dat.daos.impl;


import dat.daos.IDAO;
import dat.dtos.LoanTypeDTO;
import dat.entities.LoanType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanTypeDAO implements IDAO<LoanTypeDTO> {
    private static LoanTypeDAO instance;
    private static EntityManagerFactory emf;

    public static LoanTypeDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LoanTypeDAO();
        }
        return instance;
    }


    @Override
    public LoanTypeDTO create(LoanTypeDTO entity) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanType loanType = new LoanType(entity);
            em.persist(loanType);
            em.getTransaction().commit();
            return new LoanTypeDTO(loanType);
        }
    }

    @Override
    public Optional<LoanTypeDTO> getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            LoanType loanType = em.find(LoanType.class, id);
            return loanType != null ? Optional.of(new LoanTypeDTO(loanType)) : Optional.empty();
        }
    }

    @Override
    public Set<LoanTypeDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dat.dtos.LoanTypeDTO(lt) FROM LoanType lt", LoanTypeDTO.class)
                    .getResultList()
                    .stream()
                    .collect(java.util.stream.Collectors.toSet());
        }
    }

    @Override
    public void update(LoanTypeDTO entity) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanType loanType = em.find(LoanType.class, entity.getId());
            // Update the fields of the LoanType entity
            if (loanType != null) {
                loanType.setLoanTypeE(entity.getLoanTypeE()); // Update the enum value
                // You can update other fields if needed here
            }
            em.getTransaction().commit();
        }

    }

    @Override
    public void delete(Long id) {

        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanType loanType = em.find(LoanType.class, id);
            if (loanType != null) {
                em.remove(loanType);
            }
            em.getTransaction().commit();
        }

    }

    @Override
    public Optional<LoanTypeDTO> findByName(String name) {
        try(EntityManager em = emf.createEntityManager()) {
            LoanType loanType = em.createQuery("SELECT lt FROM LoanType lt WHERE lt.loanTypeE = :name", LoanType.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return loanType != null ? Optional.of(new LoanTypeDTO(loanType)) : Optional.empty();
        }
    }
}
