package edu.inflearn.intro.repository;

import edu.inflearn.intro.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    private final MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void after() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member saved = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(saved);
    }

    @Test
    void findByName() {
        Member spring1 = new Member();
        spring1.setName("spring1");
        repository.save(spring1);

        Member spring2 = new Member();
        spring2.setName("spring2");
        repository.save(spring2);

        Member saved = repository.findByName("spring1").get();

        assertThat(spring1).isEqualTo(saved);
    }

    @Test
    void findAll() {
        Member spring1 = new Member();
        spring1.setName("spring1");
        repository.save(spring1);

        Member spring2 = new Member();
        spring2.setName("spring2");
        repository.save(spring2);

        List<Member> members = repository.findAll();

        assertThat(members).hasSize(2);
    }
}
