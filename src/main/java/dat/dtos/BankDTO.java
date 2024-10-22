package dat.dtos;

import dat.entities.Bank;
import dat.entities.LoanOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private Long id;
    private String name;
    private double interestRate;
    private int minLoanAmount;
    private int maxLoanAmount;
    private int loanDuration;
    private Set<LoanOfferDTO> loanOffers;


    public BankDTO(Bank  bank){
        this.id = bank.getId();
        this.name = bank.getName();
        this.interestRate = bank.getInterestRate();
        this.minLoanAmount = bank.getMinLoanAmount();
        this.maxLoanAmount = bank.getMaxLoanAmount();
        this.loanDuration = bank.getLoanDuration();
        this.loanOffers =bank.getLoanOffers().stream().map(LoanOfferDTO::new).collect(java.util.stream.Collectors.toSet());
    }
}
