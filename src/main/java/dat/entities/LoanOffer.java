package dat.entities;


import dat.dtos.LoanOfferDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class LoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double interestRate;
    private int amount;
    private int duration;
    private LocalDateTime relevanceDate;

    @ManyToOne
    @JoinColumn(name = "loan_type_id", nullable = false)
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "loan_request_id", nullable = false)
    private LoanRequest loanRequest;



    public LoanOffer(LoanOfferDTO loanOfferDTO) {
        this.id = loanOfferDTO.getId();
        this.interestRate = loanOfferDTO.getInterestRate();
        this.amount = loanOfferDTO.getAmount();
        this.duration = loanOfferDTO.getDuration();
        this.relevanceDate = loanOfferDTO.getRelevanceDate();
        this.loanType = loanOfferDTO.getLoanType() == null ? null : new LoanType(loanOfferDTO.getLoanType());
        this.bank = loanOfferDTO.getBank() == null ? null : new Bank(loanOfferDTO.getBank());
        this.loanRequest = loanOfferDTO.getLoanRequest() == null ? null : new LoanRequest(loanOfferDTO.getLoanRequest());
    }





}
