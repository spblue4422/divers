package com.spblue4422.divers.users;

import com.spblue4422.divers.users.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLoginId(String loginId);
    Optional<User> findUserByNickName(String nickName);
    Optional<User> findUserByLoginIdAndPassword(String loginId, String password);
    Optional<User> findUserByLoginIdAndDeletedAtIsNull(String loginId);
    Optional<User> findUserByNickNameAndDeletedAtIsNull(String nickName);
}
