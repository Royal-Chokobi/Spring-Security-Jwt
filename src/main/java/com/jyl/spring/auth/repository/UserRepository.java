package com.jyl.spring.auth.repository;

import com.jyl.spring.auth.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.jyl.spring.auth.repository
 * fileName       : UserRepository
 * author         : leejaeyoon
 * date           : 2022/05/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/18        leejaeyoon       최초 생성
 */
@Repository
public interface UserRepository extends MongoRepository<UserModel, Long> {

}
