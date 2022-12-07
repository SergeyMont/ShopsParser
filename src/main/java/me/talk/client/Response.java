package me.talk.client;

import lombok.Data;
import me.talk.model.ShopDto;

import java.util.List;

@Data
public class Response {
    private List<ShopDto> results;
}
