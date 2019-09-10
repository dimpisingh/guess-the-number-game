package academy.learnprogramming;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class NumberGeneratorImpl implements NumberGenerator {

    // == fields ==
    private final SecureRandom random = new SecureRandom();
    private int maxNumber = 100;

    // == public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber);
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
