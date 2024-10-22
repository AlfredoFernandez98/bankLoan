package dat.dtos;

import dat.entities.Bank;
import dat.entities.LoanOffer;
import dat.entities.LoanRequest;
import dat.entities.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanOfferDTO {

    private Long id;
    private double interestRate;
    private int amount;
    private int duration;
    private LocalDateTime relevanceDate;
    private LoanTypeDTO loanType;
    private BankDTO bank;
    private LoanRequestDTO loanRequest;

    public LoanOfferDTO(LoanOffer loanOffer) {
        this.id = loanOffer.getId();
        this.interestRate = loanOffer.getInterestRate();
        this.amount = loanOffer.getAmount();
        this.duration = loanOffer.getDuration();
        this.relevanceDate = loanOffer.getRelevanceDate();
        this.loanType = loanOffer.getLoanType() == null ? null : new LoanTypeDTO(loanOffer.getLoanType());
        this.bank = new BankDTO(loanOffer.getBank());
        this.loanRequest = loanOffer.getLoanRequest() == null ? null : new LoanRequestDTO(loanOffer.getLoanRequest());
    }
}
