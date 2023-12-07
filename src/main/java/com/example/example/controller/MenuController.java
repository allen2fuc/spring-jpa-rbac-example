package com.example.example.controller;

import com.example.example.RespResult;
import com.example.example.SysMenu;
import com.example.example.SysMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fucheng on 2023/12/5
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final SysMenuRepository menuRepository;

    // 查询菜单列表
    @PreAuthorize("hasAuthority('sys:menu:list')")
    @GetMapping("/list")
    public RespResult<List<SysMenu>> listMenu() {
        return RespResult.success(menuRepository.findAll());
    }

    // 新增菜单
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @PostMapping
    public RespResult<SysMenu> addMenu(SysMenu menu) {
        return RespResult.success(menuRepository.save(menu));
    }

    // 修改菜单
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @PutMapping("/{id}")
    public RespResult<SysMenu> updateMenu(@PathVariable Integer id, SysMenu menu) {
        menu.setId(id);
        return RespResult.success(menuRepository.save(menu));
    }

    // 删除菜单
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Integer id) {
        menuRepository.deleteById(id);
    }
}
