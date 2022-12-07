package me.talk.controller;

import lombok.RequiredArgsConstructor;
import me.talk.model.Offer;
import me.talk.model.Shop;
import me.talk.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public List<Shop> getAllShops(@RequestParam(defaultValue = "0") int from,
                                  @RequestParam(defaultValue = "10") int size) {
        return shopService.getAllShops(from, size);
    }

    @GetMapping("/products")
    public List<Offer> getAllOffers(@RequestParam String text,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "10") int size) {
        return shopService.getAllOffers(text, from, size);
    }
}
