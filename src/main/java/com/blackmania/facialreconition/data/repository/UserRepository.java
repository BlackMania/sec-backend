package com.blackmania.facialreconition.data.repository;

import com.blackmania.facialreconition.data.tabellen.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

}
