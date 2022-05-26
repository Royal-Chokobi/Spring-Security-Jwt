package com.jyl.spring.auth.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.jyl.spring.auth.model
 * fileName       : RoleModel
 * author         : leejaeyoon
 * date           : 2022/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23        leejaeyoon       최초 생성
 */
@Entity
@Builder @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user_role",
        indexes = @Index(name = "tb_role_idx", columnList = "id", unique = true))
@Getter
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @PrePersist
    public void createDate() {
        this.regDate = LocalDateTime.now();
    }
}
