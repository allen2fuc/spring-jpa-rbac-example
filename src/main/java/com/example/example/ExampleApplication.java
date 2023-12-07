package com.example.example;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class ExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public ApplicationRunner initialization(
            SysUserRepository userRepository,
            SysRoleRepository roleRepository,
            SysMenuRepository menuRepository,
            PasswordEncoder passwordEncoder) {

        // 用户新增
        SysMenu userAdd = new SysMenu(null, "新增用户", "sys:user:add", "/user", 1, null, 1, null);
        // 用户列表
        SysMenu userList = new SysMenu(null, "用户列表", "sys:user:list", "/user", 1, null, 2, null);
        // 用户修改
        SysMenu userEdit = new SysMenu(null, "修改用户", "sys:user:edit", "/user/{id}", 1, null, 3, null);
        // 用户删除
        SysMenu userDelete = new SysMenu(null, "删除用户", "sys:user:delete", "/user/{id}", 1, null, 4, null);
        // 用户管理，父节点
        SysMenu userManage = new SysMenu(null, "用户管理", "", "/user", 1, Set.of(userAdd, userList, userEdit, userDelete), 1, null);

        // 新增角色
        SysMenu roleAdd = new SysMenu(null, "新增角色", "sys:role:add", "/role", 1, null, 1, null);
        // 角色列表
        SysMenu roleList = new SysMenu(null, "角色列表", "sys:role:list", "/role", 1, null, 2, null);
        // 角色修改
        SysMenu roleEdit = new SysMenu(null, "修改角色", "sys:role:edit", "/role/{id}", 1, null, 3, null);
        // 角色删除
        SysMenu roleDelete = new SysMenu(null, "删除角色", "sys:role:delete", "/role/{id}", 1, null, 4, null);
        // 角色管理，父节点
        SysMenu roleManage = new SysMenu(null, "角色管理", "", "/role", 1, Set.of(roleAdd, roleList, roleEdit, roleDelete), 2, null);

        // 新增菜单
        SysMenu menuAdd = new SysMenu(null, "新增菜单", "sys:menu:add", "/menu", 1, null, 1, null);
        // 菜单列表
        SysMenu menuList = new SysMenu(null, "菜单列表", "sys:menu:list", "/menu", 1, null, 2, null);
        // 菜单修改
        SysMenu menuEdit = new SysMenu(null, "修改菜单", "sys:menu:edit", "/menu/{id}", 1, null, 3, null);
        // 菜单删除
        SysMenu menuDelete = new SysMenu(null, "删除菜单", "sys:menu:delete", "/menu/{id}", 1, null, 4, null);
        // 菜单管理，父节点
        SysMenu menuManage = new SysMenu(null, "菜单管理", "", "/menu", 1, Set.of(menuAdd, menuList, menuEdit, menuDelete), 3, null);


        // 模拟视图角色
        SysRole viewRole = new SysRole(null, "view", null,
                Set.of(
                        roleManage, roleList,
                        userManage, userList,
                        menuManage, menuList
                )
        );

        // 模拟超级管理员角色
        SysRole adminRole = new SysRole(null, "admin", null,
                Set.of(
                        roleManage, roleList, roleAdd, roleEdit, roleDelete,
                        userManage, userList, userAdd, userEdit, userDelete,
                        menuManage, menuList, menuAdd, menuEdit, menuDelete
                )
        );

        // 模拟用户user1
        SysUser user1 = new SysUser(null, "user1", passwordEncoder.encode("123456"), Set.of(viewRole));

        // 模拟用户user2
        SysUser user2 = new SysUser(null, "user2", passwordEncoder.encode("123456"), Set.of(viewRole));

        // 模拟用户admin
        SysUser admin = new SysUser(null, "admin", passwordEncoder.encode("123456"), Set.of(adminRole));

        return args -> {
            // 初始化菜单
            menuRepository.saveAll(List.of(userManage, roleManage, menuManage));
            // 初始化角色
            roleRepository.saveAll(List.of(viewRole, adminRole));
            // 初始化用户
            userRepository.saveAll(List.of(user1, user2, admin));
        };
    }
}



