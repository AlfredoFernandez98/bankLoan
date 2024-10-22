package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.BankDTO;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class BankDAO implements IDAO<BankDTO> {
    @Override
    public BankDTO create(BankDTO entity) {
        return null;
    }

    @Override
    public Optional<BankDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<BankDTO> getAll() {
        return Set.of();
    }

    @Override
    public void update(BankDTO entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<BankDTO> findByName(String name) {
        return Optional.empty();
    }
}
