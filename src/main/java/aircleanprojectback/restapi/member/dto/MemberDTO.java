package aircleanprojectback.restapi.member.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String memberId;
    private String memberpassword;
    private String memberName;
    private String memberEmail;
    private Collection<GrantedAuthority> authorities;
    private String memberStatus;

}
