package aircleanprojectback.restapi.mainpage.model.dao;

import aircleanprojectback.restapi.mainpage.model.dto.RankingDTO;
import aircleanprojectback.restapi.mainpage.model.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking,Integer> {

//    @Query(
//            value = "select a.member_name , a.member_image , b.branch_name, sum(d.total_branch_sales_cost) as total_revenue ," +
//                    "ROW_NUMBER() OVER (ORDER BY SUM(d.total_branch_sales_cost) DESC) AS rank_index" +
//                    " from tbl_members a join tbl_branch_owner c on a.member_id = c.member_id" +
//                    " join tbl_branch b on b.branch_code = c.branch_code " +
//                    " join tbl_branch_sales_report d on d.branch_code = b.branch_code " +
//                    "where month(d.branch_submission_date)=?1 and year(d.branch_submission_date)=?2 and " +
//                    "d.branch_report_status = ?" +
//                    "group by a.member_name, a.member_image, b.branch_name " +
//                    "order by total_revenue desc",nativeQuery = true
//    )
//    List<Ranking> getRanking(String month , String year , String y);

//    @Query(
//            value = "SELECT a.member_name, a.member_image, b.branch_name, SUM(d.total_branch_sales_cost) AS total_revenue " +
//                    "FROM tbl_members a " +
//                    "JOIN tbl_branch_owner c ON a.member_id = c.member_id " +
//                    "JOIN tbl_branch b ON b.branch_code = c.branch_code " +
//                    "JOIN tbl_branch_sales_report d ON d.branch_code = b.branch_code " +
//                    "WHERE MONTH(d.branch_submission_date) = ?1 " +
//                    "AND YEAR(d.branch_submission_date) = ?2 " +
//                    "AND d.branch_report_status = ?3 " +
//                    "GROUP BY a.member_name, a.member_image, b.branch_name " +
//                    "ORDER BY total_revenue DESC",
//            nativeQuery = true
//    )
//    List<Ranking> getRanking(String month, String year, String status);



    @Query(
            value = "SELECT ROW_NUMBER() OVER (ORDER BY SUM(d.total_branch_sales_cost) DESC) AS rank_index, " +
                    "a.member_name, a.member_image, b.branch_name, " +
                    "SUM(d.total_branch_sales_cost) AS total_revenue " +
                    "FROM tbl_members a " +
                    "JOIN tbl_branch_owner c ON a.member_id = c.member_id " +
                    "JOIN tbl_branch b ON b.branch_code = c.branch_code " +
                    "JOIN tbl_branch_sales_report d ON d.branch_code = b.branch_code " +
                    "WHERE MONTH(d.branch_submission_date) = ?1 AND YEAR(d.branch_submission_date) = ?2 " +
                    "AND d.branch_report_status = ?3 " +
                    "GROUP BY a.member_name, a.member_image, b.branch_name " +
                    "ORDER BY total_revenue DESC",
            nativeQuery = true
    )
    List<Object[]> getRankingRaw(int month, int year, String status);
}
