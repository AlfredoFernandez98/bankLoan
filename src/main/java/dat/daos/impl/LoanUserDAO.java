package dat.daos.impl;

import dat.daos.IDAO;
import dat.dtos.LoanUserDTO;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class LoanUserDAO implements IDAO<LoanUserDTO> {

    private static final Map<Long, LoanUserDTO> database = new HashMap<>();
    private static long currentId = 1;

    @Override
    public LoanUserDTO create(LoanUserDTO entity) {
        entity.setId(currentId++);
        database.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<LoanUserDTO> getById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Set<LoanUserDTO> getAll() {
        return Set.copyOf(database.values());
    }

    @Override
    public void update(LoanUserDTO entity) {
        if (database.containsKey(entity.getId())) {
            database.put(entity.getId(), entity);
        }
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }

    @Override
    public Optional<LoanUserDTO> findByName(String name) {
        return database.values().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }
}