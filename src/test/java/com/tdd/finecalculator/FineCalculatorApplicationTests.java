package com.tdd.finecalculator;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FineCalculatorApplicationTests {


    @Test
    void 속도위반_20km이하는_과태료_사만원() {

        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(20, LocalDate.now());
        assertEquals(40000, fine);
    }
    @Test
    void 속도위반_35km는_과태료_칠만원() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(35, LocalDate.now());

        assertEquals(70000, fine);
    }

    @Test
    void 속도위반_50km는_과태료_십만원() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(50, LocalDate.now());

        assertEquals(100000, fine);
    }

    @Test
    void 속도위반_70km는_과태료_십삼만원() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(70, LocalDate.now());

        assertEquals(130000, fine);
    }

    @Test
    void 사만원과태료_납부기한_이개월_체납한경우() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(20, LocalDate.of(2024, 4,30));
        // 4만원 + 첫달 가산금(1200원) + 둘째달 가산금(480)
        assertEquals(41680, fine);
    }

    @Test
    void 칠만원과태료_납부기한_1개월_체납한경우() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(45, LocalDate.of(2024, 3,30));
        assertEquals(103000, fine);
    }

    @Test
    void 최대_과태료_테스트() {
        FineCalculator fineCalculator = new FineCalculator();
        double fine = fineCalculator.cal(45, LocalDate.of(2060, 3,30));
        assertEquals(175000, fine);
    }

}
