package org.example.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    // показываем, что поля состоят из enum.
    // targetClass - не обязательно
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    // Показываем, какие значения будут в таблице.
    // по-умолчанию в дополнительной таблице user_roles будет два столбца:
    // user_id и roles. В user_id - id пользователя, в roles - порядковый номер
    // значения enum (с нуля). Если указать EnumType.STRING - то в колонке roles будет значение роли,
    // то есть ADMIN или USER (Только отображение в таблице изменяется)
    @Enumerated(EnumType.STRING)
    // Можно настроить дополнительные параметры вспомогательной таблицы, не обязательная аннотаия
    // @CollectionTable(joinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Post> posts = new ArrayList<>();

}
