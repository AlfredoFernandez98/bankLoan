package dat.dtos;

import dat.entities.LoanType;
import dat.entities.LoanTypeE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanTypeDTO {

    private Long id;
    private LoanTypeE loanTypeE;

    public LoanTypeDTO(LoanType loanType) {
        this.id = loanType.getId();
        this.loanTypeE = loanType.getLoanTypeE();
    }

}
