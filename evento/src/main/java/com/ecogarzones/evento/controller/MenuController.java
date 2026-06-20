package com.ecogarzones.evento.controller;

import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.crearMenu(menu);
        return ResponseEntity.ok(nuevoMenu);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> obtenerTodosLosMenus() {
        List<Menu> menus = menuService.obtenerTodosLosMenus();
        if (menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerMenuPorId(@PathVariable Integer id) {
        try {
            Menu menu = menuService.obtenerMenuPorId(id);
            return ResponseEntity.ok(menu);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}