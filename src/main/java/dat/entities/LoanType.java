package dat.entities;

import dat.dtos.LoanTypeDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class LoanType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_type", nullable = false)
    private LoanTypeE loanTypeE;

    public LoanType(LoanTypeDTO loanTypeDTO) {
        this.id=loanTypeDTO.getId();
        this.loanTypeE=loanTypeDTO.getLoanTypeE();
    }


}
