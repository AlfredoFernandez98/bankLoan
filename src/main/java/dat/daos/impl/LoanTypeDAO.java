package dat.daos.impl;


import dat.daos.IDAO;
import dat.dtos.LoanTypeDTO;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanTypeDAO implements IDAO<LoanTypeDTO> {
    @Override
    public LoanTypeDTO create(LoanTypeDTO entity) {
        return null;
    }

    @Override
    public Optional<LoanTypeDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<LoanTypeDTO> getAll() {
        return Set.of();
    }

    @Override
    public void update(LoanTypeDTO entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<LoanTypeDTO> findByName(String name) {
        return Optional.empty();
    }
}
