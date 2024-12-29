package com.example.auth.global.rq;

import com.example.auth.domain.member.member.entity.Member;
import com.example.auth.domain.member.member.service.MemberService;
import com.example.auth.global.exceptions.ServiceException;
import com.example.auth.standard.util.Ut;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@RequestScope
@Component
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;
    private final HttpServletRequest request;
    public Member checkAuthentication() {
        String credentials = request.getHeader("Authorization");
        String apiKey = credentials == null ?
                ""
                :
                credentials.substring("Bearer ".length());
        if (Ut.str.isBlank(apiKey))
            throw new ServiceException("401-1", "apiKey를 입력해주세요.");
        Optional<Member> opActor = memberService.findByApiKey(apiKey);
        if (opActor.isEmpty())
            throw new ServiceException("401-1", "비밀번호가 일치하지 않습니다.");
        return opActor.get();
    }
}