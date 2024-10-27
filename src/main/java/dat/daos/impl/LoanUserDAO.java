package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanUserDTO;
import dat.entities.LoanUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

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
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = new LoanUser(entity);
            em.persist(loanUser);
            em.getTransaction().commit();
            return new LoanUserDTO(loanUser);

        }
    }

    @Override
    public Optional<LoanUserDTO> getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<LoanUserDTO> query = em.createQuery("SELECT new dat.dtos.LoanUserDTO(lu) FROM LoanUser lu WHERE lu.id = :id", LoanUserDTO.class);
            query.setParameter("id", id);
            LoanUserDTO loanUserDTO = query.getSingleResult();
            em.getTransaction().commit();
            return Optional.of(loanUserDTO);
        }
    }

    @Override
    public Set<LoanUserDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<LoanUserDTO> query = em.createQuery("SELECT new dat.dtos.LoanUserDTO(lu) FROM LoanUser lu", LoanUserDTO.class);
            Set<LoanUserDTO> loanUserDTOs = query.getResultList().stream().collect(java.util.stream.Collectors.toSet());
            em.getTransaction().commit();
            return loanUserDTOs;
        }
    }

    @Override
    public void update(LoanUserDTO entity) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = em.find(LoanUser.class, entity.getId());
            loanUser.setName(entity.getName());
            LoanUser mergedLoanUser = em.merge(loanUser);
            em.getTransaction().commit();
        }

    }

    @Override
    public void delete(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = em.find(LoanUser.class, id);
            if (loanUser != null) {
                em.remove(loanUser);
            }
            em.getTransaction().commit();
        }

    }

    @Override
    public Optional<LoanUserDTO> findByName(String name) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<LoanUser> query= em.createQuery("SELECT lu FROM LoanUser lu WHERE lu.name = :name", LoanUser.class);
            query.setParameter("name", name);
            LoanUser loanUser = query.getSingleResult();
            em.getTransaction().commit();
            return loanUser != null ? Optional.of(new LoanUserDTO(loanUser)) : Optional.empty();
        }
    }
}
