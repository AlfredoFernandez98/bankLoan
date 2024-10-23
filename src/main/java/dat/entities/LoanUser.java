package dat.entities;

import dat.dtos.LoanUserDTO;
import dat.security.entities.User;
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
public class LoanUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "loanUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_user_id", nullable = false)
    private Set<LoanRequest> loanRequests;

    public LoanUser(LoanUserDTO loanUser) {
        this.id = loanUser.getId();
        this.name = loanUser.getName();
        this.loanRequests = loanUser.getLoanRequest().stream().map(LoanRequest::new).collect(Collectors.toSet());
    }

    public void setLoanrequest(Set<LoanRequest>loanRequests) {

        // Hvis loanRequests ikke er null, opdateres feltet og hver LoanRequest får sat LoanUser

        if(loanRequests !=null){
            this.loanRequests = loanRequests;
            for(LoanRequest loanRequest : loanRequests){
                loanRequest.setLoanUser(this);
            }
        }

    }

    public void addLoanRequest(LoanRequest loanRequest) {
        if (loanRequest != null) {
            this.loanRequests.add(loanRequest);
            loanRequest.setLoanUser(this);
        }

    }
}

