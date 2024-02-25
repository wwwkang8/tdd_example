package com.tdd.finecalculator;

import java.time.LocalDate;
import java.time.Period;

public class FineCalculator {

    public double cal(int speed, LocalDate expireDate) {

        Period diff = Period.between(LocalDate.now(), expireDate);
        long expiredMonths = diff.toTotalMonths();
        double basicFine = 0;
        if(speed<= 20) {
            /** 20km 이하는 과태료 4만원 */
            basicFine = 40000;
        }else if(speed > 20 && speed <=40) {
            /** 20km초과, 40km미만 과태료 7만원 */
            basicFine = 70000;
        }else if(speed > 40 && speed <=60) {
            /** 40km초과, 60km미만 과태료 10만원 */
            basicFine = 100000;
        }else {
            /** 60km초과 과태료 10만원 */
            basicFine = 130000;
        }
        double addedFine = calculateAddedFine(basicFine, expiredMonths);
        return basicFine + addedFine;
    }

    double calculateAddedFine(double basicFine, double expiredMonths) {
        double addedFine = 0;
        /** 첫 1개월 가산금은 3% */
        if(expiredMonths >= 1) {
            addedFine += basicFine * 0.03;
        }
        /** 2개월부터 가산금은 1.2% */
        if(expiredMonths >= 2) {
            addedFine += basicFine * 0.012 * (expiredMonths - 1);
        }
        return addedFine;
    }

}
