package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanTypeDTO;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanTypeDAO implements IDAO<LoanTypeDTO> {

    private static final Map<Long, LoanTypeDTO> database = new HashMap<>();
    private static long currentId = 1;

    @Override
    public LoanTypeDTO create(LoanTypeDTO entity) {
        entity.setId(currentId++);
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<LoanTypeDTO> getById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Set<LoanTypeDTO> getAll() {
        return Set.copyOf(database.values());
    }

    @Override
    public void update(LoanTypeDTO entity) {
        if (database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<LoanTypeDTO> findByName(String name) {
        return database.values().stream()
                .filter(type -> type.getLoanTypeE().equals(name))
                .findFirst();
    }
}