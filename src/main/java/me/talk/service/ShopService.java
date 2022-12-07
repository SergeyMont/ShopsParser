package me.talk.service;

import lombok.RequiredArgsConstructor;
import me.talk.model.Offer;
import me.talk.model.Shop;
import me.talk.repository.OfferRepository;
import me.talk.repository.ShopRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final OfferRepository offerRepository;
    private final ShopRepository shopRepository;

    public List<Shop> getAllShops(int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return shopRepository.findAll(pageable).stream().collect(Collectors.toList());
    }

    public List<Offer> getAllOffers(String text, int from, int size) {
        Pageable pageable = PageRequest.of(from, size);
        return offerRepository.findAllByNameContainsIgnoreCaseOrModelContainsIgnoreCase(text, text, pageable);
    }
}
