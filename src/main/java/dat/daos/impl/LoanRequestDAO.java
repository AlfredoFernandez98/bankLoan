package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanRequestDTO;
import dat.entities.LoanRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanRequestDAO implements IDAO<LoanRequestDTO> {

    private static LoanRequestDAO instance;
    private static EntityManagerFactory emf;

    public static LoanRequestDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LoanRequestDAO();
        }
        return instance;
    }

    @Override
    public LoanRequestDTO create(LoanRequestDTO entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanRequest loanRequest = new LoanRequest(entity);
            em.persist(loanRequest);
            em.getTransaction().commit();
            return new LoanRequestDTO(loanRequest);
        }
    }

    @Override
    public Optional<LoanRequestDTO> getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            LoanRequest loanRequest = em.find(LoanRequest.class, id);
            return loanRequest != null ? Optional.of(new LoanRequestDTO(loanRequest)) : Optional.empty();
        }
    }

    @Override
    public Set<LoanRequestDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dat.dtos.LoanRequestDTO(l) FROM LoanRequest l", LoanRequestDTO.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public void update(LoanRequestDTO entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanRequest loanRequest = em.find(LoanRequest.class, entity.getId());
            loanRequest.setId(entity.getId());
            em.merge(loanRequest);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanRequest loanRequest = em.find(LoanRequest.class, id);
            if (loanRequest != null) {
                em.remove(loanRequest);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public Optional<LoanRequestDTO> findByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            LoanRequest loanRequest = em.createQuery("SELECT l FROM LoanRequest l WHERE l.id = :name", LoanRequest.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return loanRequest != null ? Optional.of(new LoanRequestDTO(loanRequest)) : Optional.empty();
        }
    }
}