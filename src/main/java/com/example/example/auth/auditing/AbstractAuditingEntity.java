package com.example.example.auth.auditing;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author fucheng on 2023/12/6
 */
// 公共的审计字段
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AbstractAuditingEntity {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    // 创建人
    @CreatedBy //
    private String createdBy;

    // 创建时间
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDate;

    // 修改人
    @LastModifiedBy
    private String updatedBy;

    // 修改时间
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDate;

    // 逻辑删除字段
    private Boolean deleted = false;

    @PreRemove
    protected void deleted(){
        this.deleted = true;
    }
}
