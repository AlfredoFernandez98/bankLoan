package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanOfferDTO;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanOfferDAO implements IDAO<LoanOfferDTO> {

    @Override
    public LoanOfferDTO create(LoanOfferDTO entity) {
        return null;
    }

    @Override
    public Optional<LoanOfferDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<LoanOfferDTO> getAll() {
        return Set.of();
    }

    @Override
    public void update(LoanOfferDTO entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<LoanOfferDTO> findByName(String name) {
        return Optional.empty();
    }
}
