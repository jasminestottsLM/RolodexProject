package com.liberymutual.goforcode.rolodex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liberymutual.goforcode.rolodex.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

}
