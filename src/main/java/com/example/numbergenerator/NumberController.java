package com.example.numbergenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/number")
//public class NumberController {
//
//    @GetMapping("/random")
//    public String getRandomNumber() {
//        return "C399BA 116 RUS";
//    }
//
//    @GetMapping("/next")
//    public String getNextNumber() {
//        return "C400BA 116 RUS";
//    }
//}


@RestController
@RequestMapping("/number")
public class NumberController {

    @Autowired
    private NumberRepository numberRepository;

    @GetMapping("/random")
    public String getRandomNumber() {
        List<NumberEntity> numbers = numberRepository.findAll();
        if (numbers.isEmpty()) {
            return "No numbers found";
        }
        Random random = new Random();
        NumberEntity number = numbers.get(random.nextInt(numbers.size()));
        return number.getNumber();
    }

    @GetMapping("/next")
    public String getNextNumber() {
        List<NumberEntity> numbers = numberRepository.findAll();
        if (numbers.isEmpty()) {
            return "No numbers found";
        }
        NumberEntity lastNumber = numbers.get(numbers.size() - 1);
        String nextNumber = incrementNumber(lastNumber.getNumber());
        return nextNumber;
    }

    private String incrementNumber(String number) {
        String prefix = number.substring(0, 5);
        int suffix = Integer.parseInt(number.substring(6));
        suffix++;
        return prefix + " " + suffix + " RUS";
    }
}

