package main.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "posts")
public class Post
{
    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Setter
    @Getter
    @Column(name="is_active", nullable = false)
    private byte isActive;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "moderation_status", nullable = false, columnDefinition = "enum('NEW', 'ACCEPTED', 'DECLINED') default 'NEW'")
    private ModerationStatus moderationStatus;

    @Setter
    @Getter
    @Column(name = "time", nullable = false, columnDefinition = "datetime")
    private LocalDateTime time;

    @Setter
    @Getter
    @Column(name = "moderator_id")
    private Integer moderatorId = null;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Getter
    @Column(name = "title",nullable = false)
    private String title;

    @Setter
    @Getter
    @Column(name = "text",nullable = false, columnDefinition = "text")
    private String text;

    @Setter
    @Getter
    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "tag2post",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> setTags = new HashSet<>();

    @Setter
    @Getter
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)   //example
    @JoinColumn(name = "post_id")
    @Where(clause = "value = 1")
    private List<PostVotes> likesUsers = new ArrayList<>();;

    @Setter
    @Getter
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)  //example
    @JoinColumn(name = "post_id")
    @Where(clause = "value = -1")
    private List<PostVotes> disLikesUsers = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostComments> comments = new ArrayList<>();;

}

    /*@Setter
    @Getter
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TagToPost> setTags;*/