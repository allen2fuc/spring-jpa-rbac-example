package com.example.example.controller;

import com.example.example.RespResult;
import com.example.example.SysRole;
import com.example.example.SysRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fucheng on 2023/12/5
 */
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final SysRoleRepository roleRepository;

    // 查询角色列表
    @PreAuthorize("hasAuthority('sys:role:list')")
    @GetMapping("/list")
    public List<SysRole> listRole() {
        return roleRepository.findAll();
    }

    // 新增角色
    @PreAuthorize("hasAuthority('sys:role:add')")
    @PostMapping
    public RespResult<SysRole> addRole(SysRole role) {
        return RespResult.success(roleRepository.save(role));
    }

    // 修改角色
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PutMapping("/{id}")
    public RespResult<SysRole> updateRole(@PathVariable Integer id, SysRole role) {
        role.setId(id);
        return RespResult.success(roleRepository.save(role));
    }

    // 删除角色
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleRepository.deleteById(id);
    }
}
