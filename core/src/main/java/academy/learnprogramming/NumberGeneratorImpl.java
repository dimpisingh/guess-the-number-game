package academy.learnprogramming;

import academy.learnprogramming.annotations.MaxNumber;
import academy.learnprogramming.annotations.MinNumber;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Slf4j
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final SecureRandom random = new SecureRandom();

    @Getter
    private final int maxNumber;

    @Getter
    private final int minNumber;

    // == constructor ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber){
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // == public methods ==
    @Override
    public int next() {
        // example: min=5 max=20 -> max-min=15 -> range 0-15 + min -> 5-20
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }
}