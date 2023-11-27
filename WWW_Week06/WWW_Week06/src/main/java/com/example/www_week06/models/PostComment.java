package com.example.www_week06.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
@Entity
@Getter @Setter
@ToString
@Table(name = "post_comment")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="postId",referencedColumnName = "id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private PostComment parent;
    @OneToMany(mappedBy = "parent")
    private Set<PostComment> posts = new LinkedHashSet<PostComment>();
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "id")
    private User user;
    private String title;
    @Column(columnDefinition = "TINYINT")
    private int published;
    private LocalDate publishedAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String content;

    public PostComment(Post post, PostComment parent, User user, String title, int published, LocalDate publishedAt, LocalDate createdAt, LocalDate updatedAt, String content) {
        this.post = post;
        this.parent = parent;
        this.user = user;
        this.title = title;
        this.published = published;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public PostComment() {

    }
}
