package core.backend.member.domain;

import core.backend.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    //-비즈니스 로직-//
    @Builder
    public Member(Role role, String password, String nickname) {
        this.role = role;
        this.password = password;
        this.nickname = nickname;
    }
}
