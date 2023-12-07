package com.example.example;

import com.example.example.auth.auditing.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Subselect;

import java.util.Set;

/**
 * @author fucheng on 2023/12/4
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
// 所有查询条件都会自动加上 deleted = false
@SQLRestriction("""
        "deleted" = false""")
// 删除时，更新 deleted 字段，而不是真正删除
@SQLDelete(sql = """
        update "sys_role" set "deleted" = true where "id" = ?""")
public class SysRole extends AbstractAuditingEntity {
    // 角色名称
    private String name;

    // 声明用户对象，多对多
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<SysUser> users;

    // 声明角色菜单对象，多对多
    @ManyToMany
    @JsonIgnore
    private Set<SysMenu> menus;

    public SysRole(Integer id, String name, Set<SysUser> users, Set<SysMenu> menus) {
        this.setId(id);
        this.name = name;
        this.users = users;
        this.menus = menus;
    }

    public SysRole() {
    }
}
