package academy.learnprogramming;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;

@Component
public class MessageGeneratorImpl implements MessageGenerator{

    // == constants ==
    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);

    // == private fields ==
    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init(){
        logger.debug("game value = {}", game);
    }

    @Override
    public String getMainMessage() {
        return MessageFormat.format("Number is between {0} and {1}, can you guess it?",
                game.getSmallest(),
                game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        if(game.isGameWon()){
            return MessageFormat.format("You guessed it! The Number was {0}", game.getNumber());
        }else if(game.isGameLost()){
            return MessageFormat.format("You lost. The number was {0}", game.getNumber());
        }else if(!game.isValidNumberRange()){
            return "Invalid number range --> Guess a number in range";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        }else{
            String direction = "Lower";

            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }

            return MessageFormat.format("{0}! You have {1} {2} left",
                    direction,
                    game.getRemainingGuesses(),
                    (game.getRemainingGuesses() > 1 ? "guesses" : "guess"));
        }
    }
}
