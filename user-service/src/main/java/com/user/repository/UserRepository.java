package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.bean.UserVo;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Integer> {

	

}
