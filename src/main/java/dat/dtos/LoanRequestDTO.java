package dat.dtos;

import dat.entities.LoanOffer;
import dat.entities.LoanRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequestDTO {

    private Long id;
    private int amount;
    private int duration;
    private LoanTypeDTO loanType;
    private LoanUserDTO loanUser;
    private Set<LoanOfferDTO> loanOffers;

    public LoanRequestDTO(int amount, int duration, LoanTypeDTO loanType, LoanUserDTO loanUser) {
        this.amount = amount;
        this.duration = duration;
        this.loanType = loanType;
        this.loanUser = loanUser;
    }

    public LoanRequestDTO(LoanRequest loanRequest) {
        this.id = loanRequest.getId();
        this.amount = loanRequest.getAmount();
        this.duration = loanRequest.getDuration();
        this.loanType = loanRequest.getLoanType() == null ? null : new LoanTypeDTO(loanRequest.getLoanType());
        this.loanUser = loanRequest.getLoanUser() == null ? null : new LoanUserDTO(loanRequest.getLoanUser());
        this.loanOffers = loanRequest.getLoanOffers() != null
                ? loanRequest.getLoanOffers().stream().map(LoanOfferDTO::new).collect(Collectors.toSet())
                : Collections.emptySet();
    }
}
