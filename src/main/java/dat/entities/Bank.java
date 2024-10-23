package dat.entities;

import dat.dtos.BankDTO;
import dat.dtos.LoanOfferDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "loanOffers")
    private Set<LoanOffer> loanOffers;

    public Bank(BankDTO bankDTO) {
        this.id = bankDTO.getId();
        this.name = bankDTO.getName();
        this.loanOffers = bankDTO.getLoanOffers().stream().map(LoanOffer::new).collect(Collectors.toSet());
    }

    public void addLoanOffer(LoanOffer loanOffer) {
        if(loanOffer != null){
            this.loanOffers.add(loanOffer);
            loanOffer.setBank(this);
        }
    }


}
