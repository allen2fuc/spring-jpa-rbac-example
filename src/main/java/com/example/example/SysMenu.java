package com.example.example;

import com.example.example.auth.auditing.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.H2Dialect;

import java.util.Set;

/**
 * @author fucheng on 2023/12/4
 */
// 菜单
@Entity
@Table(name = "sys_menu")
@Getter
@Setter
// 所有查询条件都会自动加上 deleted = false
@SQLRestriction("""
        "deleted" = false""")
// 删除时，更新 deleted 字段，而不是真正删除
@SQLDelete(sql = """
        update "sys_menu" set "deleted" = true where "id" = ?""")
public class SysMenu extends AbstractAuditingEntity {
    // 资源名称
    private String name;

    // 资源代码
    private String code;

    // 菜单/按钮URL
    private String url;

    // 资源类型（1:菜单 2.按钮）
    private Integer type;

    // 一对多个子菜单
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    @JsonIgnore
    private Set<SysMenu> menus;

    // 排序号
    private Integer sort;

    // 角色列表
    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    private Set<SysRole> roles;

    public SysMenu(Integer id, String name, String code, String url, Integer type, Set<SysMenu> menus, Integer sort, Set<SysRole> roles) {
        this.setId(id);
        this.name = name;
        this.code = code;
        this.url = url;
        this.type = type;
        this.menus = menus;
        this.sort = sort;
        this.roles = roles;
    }

    public SysMenu() {
    }
}
