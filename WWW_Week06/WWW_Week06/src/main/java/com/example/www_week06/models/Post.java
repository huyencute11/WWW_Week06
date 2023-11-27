package com.example.www_week06.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "post")
@Getter @Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name="authorId")
    private User user;
    private String title;
    @ManyToOne
    @JoinColumn(name = "parentId")
    private Post parent;
    private String metaTitle;
    @Column(columnDefinition = "TINYTEXT")
    private String summary;
    @Column(columnDefinition = "TINYINT")
    private int published;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate publishedAt;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "parent")
    private Set<Post> posts = new LinkedHashSet<Post>();
    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

    public Post(User user, String title, String metaTitle, String summary, int published, LocalDate createdAt, LocalDate updatedAt, LocalDate publishedAt, String content) {
        this.user = user;
        this.title = title;
        this.metaTitle = metaTitle;
        this.summary = summary;
        this.published = published;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Post() {
    }

    public Post(long id) {
        this.id = id;
    }
}
