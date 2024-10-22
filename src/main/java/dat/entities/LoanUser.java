package dat.entities;

import dat.dtos.LoanUserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
public class LoanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public LoanUser(LoanUserDTO loanUser) {
        this.id = loanUser.getId();
        this.name = loanUser.getName();
    }
}

