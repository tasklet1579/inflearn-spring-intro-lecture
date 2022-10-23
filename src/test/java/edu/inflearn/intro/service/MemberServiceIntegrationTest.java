package edu.inflearn.intro.service;

import edu.inflearn.intro.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class MemberServiceIntegrationTest {
    @Autowired
    private MemberService service;

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

        // then
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> service.join(spring2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
