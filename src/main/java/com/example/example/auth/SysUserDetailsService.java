package com.example.example.auth;

import com.example.example.SysMenu;
import com.example.example.SysRole;
import com.example.example.SysUser;
import com.example.example.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fucheng on 2023/12/4
 */
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {
    private final SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        SysUser sysUser = sysUserRepository.findByUsername(username);

        // 如果用户不存在则抛出异常
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取用户权限列表
        Set<GrantedAuthority> authorities = getGrantedAuthorities(sysUser.getRoles());

        // 返回带有用户权限信息的User
        return new SysUserDetails(sysUser, authorities);
    }

    // 从角色列表中获取权限列表
    private Set<GrantedAuthority> getGrantedAuthorities(Set<SysRole> roles) {
        // 定义权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();

        // 遍历角色列表
        for (SysRole role : roles) {
            // 获取角色关联的菜单
            Set<SysMenu> menus = role.getMenus();
            // 遍历菜单列表
            for (SysMenu menu : menus) {
                // 将权限标识作为权限
                String code = menu.getCode();

                // 判断权限标识是否为空
                if (StringUtils.hasText(code)) {
                    // 将权限标识封装为SimpleGrantedAuthority对象
                    authorities.add(new SimpleGrantedAuthority(code));
                }
            }
        }
        return authorities;
    }
}
