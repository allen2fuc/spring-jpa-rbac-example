package com.example.example.controller;

import com.example.example.RespResult;
import com.example.example.SysUser;
import com.example.example.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fucheng on 2023/12/5
 */
// 用户管理
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final SysUserRepository userRepository;

    // 查询用户列表
    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/list")
    public List<SysUser> listUser() {
        return userRepository.findAll();
    }

    // 新增用户
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping
    public RespResult<SysUser> addUser(SysUser user) {
        return RespResult.success(userRepository.save(user));
    }

    // 修改用户
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PutMapping("/{id}")
    public RespResult<SysUser> updateUser(@PathVariable Integer id, SysUser user) {
        user.setId(id);
        return RespResult.success(userRepository.save(user));
    }

    // 删除用户
    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
