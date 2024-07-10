package aircleanprojectback.restapi.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    private String memberId;
    private String memberPassword;


}
