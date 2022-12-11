package ru.meklaw.creditcalculator.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Getter
@Component
@Scope("session")
public class Credit {
    @Min(value = 100_000, message = "Сумма кредита должна быть от 100_000 до 5_000_000")
    @Max(value = 5_000_000, message = "Сумма кредита должна быть от 100_000 до 5_000_000")
    private int sum;

    @Min(value = 12, message = "Срок кредита должен быть от 12 до 60")
    @Max(value = 60, message = "Срок кредита должен быть от 12 до 60")
    private int date;

    private double percent;
}
