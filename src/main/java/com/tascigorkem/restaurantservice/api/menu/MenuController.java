package com.tascigorkem.restaurantservice.api.menu;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.menu.MenuDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface MenuController {

    @GetMapping("/menus")
    Mono<Response> getMenus();

    @GetMapping("/menus/{id}")
    Mono<Response> getMenuById(@PathVariable("id") UUID id);

    @PostMapping("/menus")
    Mono<Response> addMenu(@RequestBody MenuControllerRequestDto menuControllerRequestDto);

    @PutMapping("/menus/{id}")
    Mono<Response> updateMenu(@PathVariable("id") UUID id, @RequestBody MenuControllerRequestDto menuControllerRequestDto);

    @DeleteMapping("/menus/{id}")
    Mono<Response> removeMenu(@PathVariable("id") UUID id);

    Function<MenuDto, MenuControllerResponseDto> mapToMenuControllerResponseDto();

    Function<MenuControllerRequestDto, MenuDto> mapToMenuDto();
}
