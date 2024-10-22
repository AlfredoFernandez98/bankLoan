package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanRequestDTO;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanRequestDAO implements IDAO<LoanRequestDTO> {

    @Override
    public LoanRequestDTO create(LoanRequestDTO entity) {
        return null;
    }

    @Override
    public Optional<LoanRequestDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<LoanRequestDTO> getAll() {
        return Set.of();
    }

    @Override
    public void update(LoanRequestDTO entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<LoanRequestDTO> findByName(String name) {
        return Optional.empty();
    }
}
