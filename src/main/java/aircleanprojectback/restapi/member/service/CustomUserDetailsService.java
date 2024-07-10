package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Member;
import aircleanprojectback.restapi.member.entity.MemberRole;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper){
        this.memberRepository=memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId);

        MemberDTO memberDTO = modelMapper.map(member,MemberDTO.class);


        return null;
    }
}
