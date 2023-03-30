package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.records.RecordListItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	@Query(value = "select r.recordId as recordId, u.userId as userId, s.spotId as spotId, u.loginId as loginId, u.nickName as nickName, s.name as spotName, s.location as location, r.logNo as logNo, r.diveAt as diveAt, r.createdAt as createdAt, r.opened as opened"
			+ " from tb_record r left join tb_user u on r.record_user = u.userId left join tb_spot s on r.record_spot = s.spotId where r.deletedAt is null", nativeQuery = true)
	Optional<List<RecordListItemInfo>> findAllRecords();

//	Class-based projections do not work with native queries AT ALL. As a workaround you may use named queries with ResultSetMapping or the Hibernate specific ResultTransformer.
	@Query(value="select r.recordId as recordId, u.userId as userId, s.spotId as spotId, u.loginId as loginId, u.nickName as nickName, s.name as spotName, s.location as location, r.logNo as logNo, r.diveAt as diveAt, r.createdAt as createdAt, r.opened as opened"
			+ " from tb_record r left join tb_user u on r.record_user = u.userId left join tb_spot s on r.record_spot = s.spotId where u.loginId=:loginId and r.deletedAt is null", nativeQuery = true)
	Optional<List<RecordListItemInfo>> findRecordsByLoginId(String loginId);

//	@Query(value = "select *", nativeQuery = true)
	Optional<Record> findByRecordIdAndDeletedAtIsNull(Long recordId);

//	@Query(value = "select r.*, u.id, u.userId, u.nickName, s.id, s.name, s.location from " +
//			"TB_Record r left join r.user u left join r.spot s where r.id = :id r.deletedAt = " +
//			"null and r.opened = true", nativeQuery = true)
//	Optional<Record> findOthersRecordByIdAndDeletedAtNullAndOpenedTrue(Long id);
}
