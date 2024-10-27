package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanUserDTO;
import dat.entities.LoanUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanUserDAO implements IDAO<LoanUserDTO> {

    private static LoanUserDAO instance;
    private static EntityManagerFactory emf;

    public static LoanUserDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LoanUserDAO();
        }
        return instance;
    }

    @Override
    public LoanUserDTO create(LoanUserDTO entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = new LoanUser(entity);
            em.persist(loanUser);
            em.getTransaction().commit();
            return new LoanUserDTO(loanUser);
        }
    }

    @Override
    public Optional<LoanUserDTO> getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            LoanUser loanUser = em.find(LoanUser.class, id);
            return loanUser != null ? Optional.of(new LoanUserDTO(loanUser)) : Optional.empty();
        }
    }

    @Override
    public Set<LoanUserDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dat.dtos.LoanUserDTO(l) FROM LoanUser l", LoanUserDTO.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public void update(LoanUserDTO entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = em.find(LoanUser.class, entity.getId());
            loanUser.setName(entity.getName());
            em.merge(loanUser);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = em.find(LoanUser.class, id);
            if (loanUser != null) {
                em.remove(loanUser);
                em.getTransaction().commit();
            }
        }
    }

    @Override
    public Optional<LoanUserDTO> findByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            LoanUser loanUser = em.createQuery("SELECT l FROM LoanUser l WHERE l.name = :name", LoanUser.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return loanUser != null ? Optional.of(new LoanUserDTO(loanUser)) : Optional.empty();
        }
    }
}