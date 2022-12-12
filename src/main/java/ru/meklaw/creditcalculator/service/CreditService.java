package ru.meklaw.creditcalculator.service;

import org.springframework.stereotype.Service;
import ru.meklaw.creditcalculator.models.Credit;
import ru.meklaw.creditcalculator.models.CreditInterval;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditService {

    public Credit createCredit() {
        Credit credit = new Credit();
        credit.setPercent(round(Math.random() * 12 + 12.9));

        return credit;
    }

    public List<CreditInterval> calculateIntervals(Credit credit) {
        List<CreditInterval> intervals = new ArrayList<>();
        CreditInterval step = new CreditInterval();

        step.setNumOfPayment(1);
        step.setMonthAndYear(getDate(1));
        step.setSumInTheBeginning(credit.getSum());
        step.setPercentPayment(getSumCreditPercent(credit.getSum(), credit.getPercent()));
        step.setAnnuity(calculateAnnuity(credit));
        step.setAnnuityMinusPercent(round(step.getAnnuity() - step.getPercentPayment()));
        step.setSumInTheEnd(round(step.getSumInTheBeginning() - step.getAnnuityMinusPercent()));

        intervals.add(step);

        fillIntervals(intervals, credit.getDate(), credit.getPercent());

        return intervals;
    }

    private void fillIntervals(List<CreditInterval> intervals, int date, double creditPercent) {
        if (intervals.size() != 1)
            throw new RuntimeException("intervals should contains only first step");

        for (int i = 2; i <= date; i++) {
            CreditInterval step = new CreditInterval();
            CreditInterval lastStep = intervals.get(intervals.size() - 1);

            step.setNumOfPayment(i);
            step.setMonthAndYear(getDate(i));
            step.setSumInTheBeginning(lastStep.getSumInTheEnd());
            step.setPercentPayment(getSumCreditPercent(step.getSumInTheBeginning(), creditPercent));
            step.setAnnuity(lastStep.getAnnuity());
            step.setAnnuityMinusPercent(round(step.getAnnuity() - step.getPercentPayment()));
            step.setSumInTheEnd(round(step.getSumInTheBeginning() - step.getAnnuityMinusPercent()));

            intervals.add(step);
        }

        intervals.get(intervals.size() - 1).setSumInTheEnd(0);
    }

    private String getDate(int n) {
        return String.format("%d/%d", ((n - 1) % 12 + 1), ((n - 1) / 12 + 1));
    }

    private double round(double n) {
        return Math.round(n * 100.0) / 100.0;
    }

    private double calculateAnnuity(Credit credit) {
        final double sum = credit.getSum();
        final double date = credit.getDate();
        final double percentPerInterval = credit.getPercent() / 12 / 100;

        return round(
                sum * percentPerInterval * Math.pow(1 + percentPerInterval, date)
                        / (Math.pow(1 + percentPerInterval, date) - 1)
        );
    }

    private double getSumCreditPercent(double sum, double creditPercent) {
        final double percentPerInterval = creditPercent / 12 / 100;

        return round(sum * percentPerInterval);
    }
}
