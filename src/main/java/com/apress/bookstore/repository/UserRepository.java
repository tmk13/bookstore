package com.apress.bookstore.repository;

import com.apress.bookstore.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Long>{

//    @Query("SELECT u.id FROM User u WHERE u.userName = :userName")
//    boolean findByUserNameExists(@Param("userName") String userName);

    @Query("SELECT CASE when count (u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.userName = :userName")
    Boolean existsByUserName(@Param("userName") String userName);

    @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.userPassword = :userPassword")
    User findByUserNameAndUserPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);
}
