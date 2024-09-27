package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndSpace() {
        String path = "./data/pair_with_comment_and_space.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Emelyanov Oleg");
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);

        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void whenPairWithdoubleEqulsSign() {
        String path = "./data/pair_with_double_equals_sign.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Oleg=kek=");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);

    }

}