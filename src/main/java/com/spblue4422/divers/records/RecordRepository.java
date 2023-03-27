package com.spblue4422.divers.records;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value = "select r.*, u.userId, u.nickName, s.name from TB_Record r left join r.user u left join r.spot s where r.deletedAt = null")
    Optional<List<Record>> findMyRecordsDeletedAtNull(String userId);

    @Query(value = "select r.*, u.userId, u.nickName, s.name from TB_Record r left join r.user u left join r.spot s where r.isOpened = true and r.deletedAt = null")
    Optional<List<Record>> findOthersRecordsIsOpenedTrueAndDeletedAtNull(String userId);
    Optional<Record> findRecordByIdAndDeletedAtNull(Long id);
}
