package com.example.example;

import org.springframework.data.jpa.repository.JpaRepository;

// 用户数据访问接口
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    // 根据用户名查询用户并且非逻辑删除
    SysUser findByUsername(String username);
}