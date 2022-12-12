package ru.meklaw.creditcalculator.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditInterval {
    private int numOfPayment;
    private String monthAndYear;
    private double annuityMinusPercent;
    private double percentPayment;
    private double sumInTheEnd;
    private double annuity;

    private double sumInTheBeginning;
}
