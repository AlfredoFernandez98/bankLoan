package dat.entities;

import dat.dtos.LoanUserDTO;
import dat.security.entities.User;
import jakarta.persistence.*;
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

