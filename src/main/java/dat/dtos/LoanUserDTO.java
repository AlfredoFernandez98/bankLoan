package dat.dtos;

import dat.entities.LoanUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanUserDTO {

    private Long id;
    private String name;
    private Set<LoanRequestDTO> loanRequest;

    public LoanUserDTO(LoanUser loanUser) {
        this.id = loanUser.getId();
        this.name = loanUser.getName();
        this.loanRequest = loanUser.getLoanRequests().stream().map(LoanRequestDTO::new).collect(Collectors.toSet());
    }
}
