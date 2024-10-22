package dat.dtos;

import dat.entities.LoanUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanUserDTO {

    private Long id;
    private String name;

    public LoanUserDTO(LoanUser loanUser) {
        this.id = loanUser.getId();
        this.name = loanUser.getName();
    }
}
