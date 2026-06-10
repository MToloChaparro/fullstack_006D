package com.ecogarzones.evento.service;

import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu crearMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> obtenerTodosLosMenus() {
        return menuRepository.findAll();
    }

    public Menu obtenerMenuPorId(Integer id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + id));
    }
}