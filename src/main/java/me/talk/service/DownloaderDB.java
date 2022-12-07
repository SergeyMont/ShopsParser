package me.talk.service;

import lombok.RequiredArgsConstructor;
import me.talk.client.WebClientImpl;
import me.talk.model.Shop;
import me.talk.model.ShopDto;
import me.talk.repository.ShopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DownloaderDB {
    private final ShopRepository shopRepository;
    private final WebClientImpl webClient;
    private final ModelMapper modelMapper;

    public void downloadDB() {
        List<ShopDto> list = webClient.getShopList().block().getResults();
        shopRepository.saveAll(list.stream().map(this::toShop).collect(Collectors.toList()));
    }

    private Shop toShop(ShopDto shopDto) {
        return modelMapper.map(shopDto, Shop.class);
    }
}
