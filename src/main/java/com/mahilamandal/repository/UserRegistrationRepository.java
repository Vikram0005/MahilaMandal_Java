package com.mahilamandal.repository;

import com.mahilamandal.entity.UserRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationEntity,Integer> {
    UserRegistrationEntity findByMobileNoAndPassword(String mobileNo,String password);
}
