package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.dto.BranchAndMember;
import aircleanprojectback.restapi.report.entity.BranchSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchSalesRepository extends JpaRepository<BranchSales, Integer> {
    // memberIdPrefix : member_id가 특정 접두사로 시작하는 데이터를 필터링하기 위해 사용되는 파라미터
    // 이 파라미터를 통해 member_id가 ‘a’로 시작하는 데이터를 검색하도록 설정할 수 있습니다.
//    List<BranchSales> findByBranchCodeAndMemberIdStartingWith(int branchCode, String a);
}
