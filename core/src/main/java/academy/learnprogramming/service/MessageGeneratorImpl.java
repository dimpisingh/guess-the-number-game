package academy.learnprogramming.service;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class MessageGeneratorImpl implements MessageGenerator{

    // == constants ==
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN = "game.win";
    private static final String LOOSE = "game.loose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";
    private static final String SINGULAR_GUESS = "game.singular.guess";
    private static final String PLURAL_GUESS = "game.plural.guess";

    // == private fields ==
    @Getter(AccessLevel.NONE)
    private final Game game;
    private final MessageSource messageSource;

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {
        this.game = game;
        this.messageSource =  messageSource;
    }

    @PostConstruct
    public void init(){
        log.debug("game value = {}", game);
    }

    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE,
                game.getSmallest(),
                game.getBiggest());
    }

    @Override
    public String getResultMessage() {

        if(game.isGameWon()){
            return getMessage(WIN, game.getNumber());

        }else if(game.isGameLost()){
            return getMessage(LOOSE, game.getNumber());

        }else if(!game.isValidNumberRange()){
            return getMessage(INVALID_RANGE);
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return getMessage(FIRST_GUESS);
        }else{
            String direction = getMessage(LOWER);

            if(game.getGuess() < game.getNumber()){
                direction = getMessage(HIGHER);
            }

            return getMessage(REMAINING,
                    direction,
                    game.getRemainingGuesses(),
                    (game.getRemainingGuesses() > 1 ?
                            getMessage(PLURAL_GUESS) :
                            getMessage(SINGULAR_GUESS)
                    )
            );
        }
    }

    // == private methods ==
    private String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
