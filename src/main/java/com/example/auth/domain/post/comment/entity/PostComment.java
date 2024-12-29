package com.example.auth.domain.post.comment.entity;

import com.example.auth.domain.member.member.entity.Member;
import com.example.auth.domain.post.post.entity.Post;
import com.example.auth.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    @Column(columnDefinition = "TEXT")
    private String content;
}