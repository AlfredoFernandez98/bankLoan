package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanUserDTO;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanUserDAO implements IDAO<LoanUserDTO> {

    @Override
    public LoanUserDTO create(LoanUserDTO entity) {
        return null;
    }

    @Override
    public Optional<LoanUserDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<LoanUserDTO> getAll() {
        return Set.of();
    }

    @Override
    public void update(LoanUserDTO entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<LoanUserDTO> findByName(String name) {
        return Optional.empty();
    }
}
