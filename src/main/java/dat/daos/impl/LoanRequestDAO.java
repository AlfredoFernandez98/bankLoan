package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanRequestDTO;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanRequestDAO implements IDAO<LoanRequestDTO> {

    private static final Map<Long, LoanRequestDTO> database = new HashMap<>();
    private static long currentId = 1;

    @Override
    public LoanRequestDTO create(LoanRequestDTO entity) {
        entity.setId(currentId++);
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<LoanRequestDTO> getById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Set<LoanRequestDTO> getAll() {
        return Set.copyOf(database.values());
    }

    @Override
    public void update(LoanRequestDTO entity) {
        if (database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<LoanRequestDTO> findByName(String name) {
        return database.values().stream()
                .filter(request -> request.getLoanOffers().equals(name))
                .findFirst();
    }
}