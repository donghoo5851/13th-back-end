package likelion.likelion.service;

import likelion.likelion.controller.domain.Member;
import likelion.likelion.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {


        private final MemberRepository memberRepository;
        public MemberService(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        // 회원 가입
        public Long join(Member member) {
            // 같은 이름이 있는 중복 회원 X
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
             .ifPresent( m -> {
             throw new IllegalArgumentException("이미 존재하는 회원입니다.");
         });
    }
        // 전체 회원 조회
        public List<Member> findMembers() {
            return memberRepository.findAll();

        }

        public Optional<Member> findOne(Long memberId) {
            return memberRepository.findByID(memberId);
        }

}
