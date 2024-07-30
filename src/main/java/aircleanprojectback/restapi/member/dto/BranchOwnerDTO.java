package aircleanprojectback.restapi.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchOwnerDTO {

    private int ownerCode;
    private BranchDTO branchDTO;
    private MemberDTO memberDTO;

    public BranchOwnerDTO(BranchDTO branchDTO, MemberDTO memberDTO) {
        this.branchDTO = branchDTO;
        this.memberDTO = memberDTO;
    }
}
