package com.spblue4422.divers.users;

import com.spblue4422.divers.users.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByNickName(String nickName);
    Optional<User> findUserByUserIdAndPassword(String userId, String password);
    Optional<User> findUserByUserIdAndDeletedAtNull(String userId);
    Optional<User> findUserByNickNameAndDeletedAtNull(String nickName);
}
