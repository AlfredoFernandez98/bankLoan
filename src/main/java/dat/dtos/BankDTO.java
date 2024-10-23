package dat.dtos;

import dat.entities.Bank;
import dat.entities.LoanOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private Long id;
    private String name;
    private Set<LoanOfferDTO> loanOffers= new HashSet<>();


    public BankDTO(String name) {
        this.name = name;
    }


    public Set<LoanOfferDTO> getLoanOffers() {
        return loanOffers;
    }

    public void setLoanOffers(Set<LoanOfferDTO> loanOffers) {
        this.loanOffers = loanOffers;
    }


    public BankDTO(Bank  bank){
        this.id = bank.getId();
        this.name = bank.getName();
        this.loanOffers =bank.getLoanOffers().stream().map(LoanOfferDTO::new).collect(java.util.stream.Collectors.toSet());
    }
}
