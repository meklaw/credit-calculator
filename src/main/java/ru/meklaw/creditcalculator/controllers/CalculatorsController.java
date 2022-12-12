package ru.meklaw.creditcalculator.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.meklaw.creditcalculator.models.Credit;
import ru.meklaw.creditcalculator.service.CreditService;

@Controller
@RequestMapping("/calculator")
@SessionAttributes(types = Credit.class)
public class CalculatorsController {
    final private CreditService creditService;

    @Autowired
    public CalculatorsController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public String openCalculator(Model model) {
        model.addAttribute(creditService.createCredit());

        return "calculator";
    }

    @PostMapping
    public String showResul(@ModelAttribute @Valid Credit credit,
                            BindingResult bindingResult,
                            SessionStatus status,
                            Model model) {
        if (bindingResult.hasErrors())
            return "calculator";

        model.addAttribute("intervals", creditService.calculateIntervals(credit));

        status.setComplete();

        return "credit";
    }
}
