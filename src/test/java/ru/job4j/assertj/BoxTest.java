package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisNotNullNotEmptySphere() {
        Box box = new Box(0, 322);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisNotNullNotEmptyCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(322, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualTo("Unknown object");
    }

    @Test
    void isThisSphereAreaWithEdge322() {
        Box box = new Box(0, 322);
        double area = box.getArea();
        assertThat(area).isNotNull()
                .isGreaterThan(1000000.1d)
                .isLessThan(2000000.1d)
                .isEqualTo(1302931.57d, withPrecision(0.001));
    }

    @Test
    void isThisCubeAreaWithEdge322() {
        Box box = new Box(8, 322);
        double area = box.getArea();
        assertThat(area).isNotNull()
                .isGreaterThan(600000.1d)
                .isLessThan(700000.1d)
                .isEqualTo(622104, withPrecision(0.0001));
    }

    @Test
    void whatThisItIsCube() {
        Box box = new Box(8, 322);
        String type = box.whatsThis();
        assertThat(type).isNotNull()
                .isNotEmpty()
                .isEqualTo("Cube");
    }

    @Test
    void isItNumberOfVerticesOfSphere() {
        Box box = new Box(0, 322);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotNull()
                .isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isItNumberOfVerticesOfCube() {
        Box box = new Box(8, 1);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotNull()
                .isGreaterThan(0)
                .isLessThan(322)
                .isEqualTo(8);
    }

    @Test
    void isItCubeExist() {
        Box box = new Box(8, 1);
        boolean existence = box.isExist();
        assertThat(existence).isNotNull()
                .isTrue();
    }

    @Test
    void isItFigureExist() {
        Box box = new Box(-1, 1);
        boolean existence = box.isExist();
        assertThat(existence).isNotNull()
                .isFalse();
    }
}