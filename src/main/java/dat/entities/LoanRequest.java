package dat.entities;

import dat.dtos.LoanRequestDTO;
import dat.security.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "loan_user_id", nullable = false)
    private LoanUser loanUser;

    @OneToMany(mappedBy = "loanRequest",orphanRemoval = true)
    private Set<LoanOffer> loanOffers;

    public LoanRequest(LoanRequestDTO loanRequestDTO){
        this.id = loanRequestDTO.getId();
        this.amount = loanRequestDTO.getAmount();
        this.duration = loanRequestDTO.getDuration();
        this.loanType = new LoanType(loanRequestDTO.getLoanType());
        this.loanUser = loanRequestDTO.getLoanUser() == null ? null : new LoanUser(loanRequestDTO.getLoanUser());
        this.loanOffers = loanRequestDTO.getLoanOffers() != null
                ? loanRequestDTO.getLoanOffers().stream().map(LoanOffer::new).collect(Collectors.toSet())
                : Collections.emptySet();

    }

}
