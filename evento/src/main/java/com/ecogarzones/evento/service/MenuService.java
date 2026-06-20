package com.ecogarzones.evento.service;

import com.ecogarzones.evento.model.Menu;
import com.ecogarzones.evento.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu crearMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Transactional(readOnly = true)
    public List<Menu> obtenerTodosLosMenus() {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Menu obtenerMenuPorId(Integer id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menú no encontrado con ID: " + id));
    }
}