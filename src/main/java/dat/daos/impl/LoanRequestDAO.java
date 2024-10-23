package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanRequestDTO;
import dat.entities.LoanOffer;
import dat.entities.LoanRequest;
import dat.entities.LoanUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanRequestDAO implements IDAO<LoanRequestDTO> {

    private static LoanRequestDAO instance;
    private static EntityManagerFactory emf;

    public static LoanRequestDAO getInstance( EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LoanRequestDAO();
        }
        return instance;
    }

    public void addLoandRequestToLoanOffer(Long loanOfferId, Long loanRequestId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanOffer loanOffer = em.find(LoanOffer.class, loanOfferId);
            LoanRequest loanRequest = em.find(LoanRequest.class, loanRequestId);
            // Tjek om begge entiteter blev fundet
            if (loanOffer == null) {
                throw new IllegalArgumentException("LoanOffer with ID " + loanOfferId + " not found.");
            }
            if (loanRequest == null) {
                throw new IllegalArgumentException("LoanRequest with ID " + loanRequestId + " not found.");
            }
            loanOffer.setLoanRequest(loanRequest);
            em.getTransaction().commit();
        }
    }


    public LoanUser addLoanRequestToLoanUser(Long loanUserId, Long loanRequestId) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            LoanUser loanUser = em.find(LoanUser.class, loanUserId);
            LoanRequest loanRequest = em.find(LoanRequest.class, loanRequestId);
            loanUser.addLoanRequest(loanRequest);
            LoanUser mergedLoanUser = em.merge(loanUser);
            em.getTransaction().commit();
            return mergedLoanUser;
        }
    }

    @Override
    public LoanRequestDTO create(LoanRequestDTO entity) {
        try(EntityManager em = emf.createEntityManager()){
          em.getTransaction().begin();
            LoanRequest loanRequest = new LoanRequest(entity);
            em.persist(loanRequest);
            em.getTransaction().commit();
            return new LoanRequestDTO(loanRequest);
        }
    }

    @Override
    public Optional<LoanRequestDTO> getById(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            LoanRequest loanRequest = em.find(LoanRequest.class, id);
            return loanRequest != null ? Optional.of(new LoanRequestDTO(loanRequest)) : Optional.empty();
        }
    }

    @Override
    public Set<LoanRequestDTO> getAll() {
        try(EntityManager em = emf.createEntityManager()){
            return em.createQuery("SELECT new dat.dtos.LoanRequestDTO(l) FROM LoanRequest l", LoanRequestDTO.class)
                    .getResultList()
                    .stream()
                    .collect(java.util.stream.Collectors.toSet());

        }
    }

    @Override
    public void update(LoanRequestDTO entity) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }

    }

    @Override
    public void delete(Long id) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            LoanRequest loanRequest = em.find(LoanRequest.class, id);
            em.remove(loanRequest);
            em.getTransaction().commit();
        }

    }

    @Override
    public Optional<LoanRequestDTO> findByName(String name) {
        return Optional.empty();
    }
}
