package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.records.RecordListItemResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	@Query(value = "select r.id, r.logNo, r.diveAt, r.createdAt, u.id, u.userId, u.nickName, s.id," +
			" s.name, s.location from TB_Record r left join r.user u left join r.spot s where r" +
			".opened = true r.deletedAt = null", nativeQuery = true)
	Optional<List<RecordListItemResponseDto>> findAllRecords();

	@Query(value = "select r.id, r.logNo, r.diveAt, r.createdAt, u.id, u.userId, u.nickName, s.id,"
			+ " s.name, s.location from TB_Record r left join r.user u left join r.spot s where"
			+ " u.userId = :userId and r.deletedAt = null", nativeQuery = true)
	Optional<List<RecordListItemResponseDto>> findMyRecords(String userId);

	@Query(value = "select r.id, r.logNo, r.diveAt, r.createdAt, u.id, u.userId, u.nickName, s.id,"
			+ " s.name, s.location from TB_Record r left join r.user u left join r.spot s where u"
			+ ".userId = :userId and r.opened = true and r.deletedAt = null", nativeQuery = true)
	Optional<List<RecordListItemResponseDto>> findOthersRecords(String userId);

	@Query(value = "select r.*, u.id, u.userId, u.nickName, s.id, s.name, s.location from " +
			"TB_Record r left join r.user u left join r.spot s where r.id = :id r.deletedAt = " +
			"null", nativeQuery = true)
	Optional<Record> findRecordDetail(Long id);

//	@Query(value = "select r.*, u.id, u.userId, u.nickName, s.id, s.name, s.location from " +
//			"TB_Record r left join r.user u left join r.spot s where r.id = :id r.deletedAt = " +
//			"null and r.opened = true", nativeQuery = true)
//	Optional<Record> findOthersRecordByIdAndDeletedAtNullAndOpenedTrue(Long id);
}
