package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_ask")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Ask {

    @Id
    @Column(name = "ask_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int askNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "ask_description")
    private String askDescription;

    @Column(name="member_email")
    private String memberEmail;

    @Column(name = "ask_status")
    private String askStatus;

    public Ask memberId(String var){
        memberId = var;
        return this;
    }

    public Ask askDescription(String var){
        askDescription=var;
        return this;
    }
    public Ask memberEmail(String var){
        memberEmail = var;
        return this;
    }

    public Ask askStatus(String var){
        askStatus =var;
        return this;
    }

    public Ask builder(){
        return new Ask(this.askNo,this.memberId,this.askDescription,this.memberEmail,this.askStatus);
    }
}
