package com.example.auth.domain.member.member.service;

import com.example.auth.domain.member.member.entity.Member;
import com.example.auth.domain.member.member.repository.MemberRepository;
import com.example.auth.global.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        memberRepository
                .findByUsername(username)
                .ifPresent(existingMember  -> {
                    throw new ServiceException("400-1", "해당 username은 이미 사용중입니다.");
                });


        Member member = Member.builder()
                .username(username)
                .password(password)
                .apiKey(UUID.randomUUID().toString())
                .nickname(nickname)
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(long authorId) {
        return memberRepository.findById(authorId);
    }

    public Optional<Member> findByApiKey(String apiKey) {
        return memberRepository.findByApiKey(apiKey);
    }
}