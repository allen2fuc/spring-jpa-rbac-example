package com.example.example;

import com.example.example.auth.auditing.AbstractAuditingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;

// 声明登陆用户对象
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "sys_user")
@SQLDelete(sql = """
    update "sys_user" set "deleted" = true where "id" = ?""", check = ResultCheckStyle.COUNT)
@SQLRestriction("""
        "deleted" = false""")
public class SysUser extends AbstractAuditingEntity {

    // 登陆用户名
    private String username;

    // 登陆密码
    @JsonIgnore
    private String password;

    // 声明角色列表对象
    @ManyToMany
    @JsonIgnore
    private Set<SysRole> roles;

    public SysUser(Integer id, String username, String password, Set<SysRole> roles) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // 无参构造函数
    public SysUser() {
    }
}