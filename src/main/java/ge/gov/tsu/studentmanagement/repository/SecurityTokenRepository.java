package ge.gov.tsu.studentmanagement.repository;


import ge.gov.tsu.studentmanagement.entity.SecurityToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecurityTokenRepository extends JpaRepository<SecurityToken, Long> {

    @Query("FROM #{#entityName} e where " +
            "(:token IS NULL OR e.token = :token) AND " +
            "(:status IS NULL OR e.status = :status) AND " +
            "(:actionType IS NULL OR e.actionType = :actionType) AND " +
            "(:userId IS NULL OR e.userId = :userId) ")
    SecurityToken search(
            @Param("token") String token,
            @Param("status") Integer status,
            @Param("userId") Long userId,
            @Param("actionType") SecurityToken.ActionType actionType
    );


    @Modifying
    @Query("UPDATE #{#entityName} e SET e.status = " +  SecurityToken.Status.OVERRIDDEN + " WHERE " +
            "e.userId = :userId AND " +
            "e.actionType = :actionType AND " +
            "e.status = " + SecurityToken.Status.ACTIVE)
    void expireOldTokens(
            @Param("userId") Long userId,
            @Param("actionType") SecurityToken.ActionType actionType
    );



    @Query(value = "SELECT nextval('SEQ_SECURITY_TOKENS')", nativeQuery = true)
    Long getNextval();
}
