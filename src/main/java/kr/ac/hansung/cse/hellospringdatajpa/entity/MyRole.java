package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
public class MyRole
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true)
    private String rolename;

    // mappedBy: User.roles 필드가 이 관계를 관리
    @ManyToMany(mappedBy="roles")
    private List<MyUser> users;

    public MyRole(String rolename) {
        this.rolename = rolename;
    }
}