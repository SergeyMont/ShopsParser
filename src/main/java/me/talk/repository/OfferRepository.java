package me.talk.repository;

import me.talk.model.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findAllByNameContainsIgnoreCaseOrModelContainsIgnoreCase(String text, String str, Pageable pageable);
}
