package org.example.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private LocalDateTime creationTime;

    @ManyToOne
    private User author;

    @PrePersist
    public void prePersist() {
        creationTime = LocalDateTime.now();
    }

}
