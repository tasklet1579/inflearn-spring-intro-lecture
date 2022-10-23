package edu.inflearn.intro.service;

import edu.inflearn.intro.domain.Member;
import edu.inflearn.intro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    private MemberService service;
    private MemoryMemberRepository repository;

    @BeforeEach
    void before() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    void after() {
        repository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long id = service.join(member);

        // then
        Member saved = service.findOne(id).get();
        assertThat(member.getName()).isEqualTo(saved.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member spring1 = new Member();
        spring1.setName("spring");

        Member spring2 = new Member();
        spring2.setName("spring");

        // when
        service.join(spring1);

//        try {
//            service.join(spring2);
//            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
//        } catch (IllegalArgumentException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> service.join(spring2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
