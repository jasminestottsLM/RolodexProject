package com.libertymutual.goforcode.rolodex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.rolodex.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

}
