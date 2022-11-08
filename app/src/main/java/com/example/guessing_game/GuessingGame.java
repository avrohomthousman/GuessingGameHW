package com.example.guessing_game;

import java.util.Random;

/**
 * This class is the model for a simple guessing game. On instantiation
 * you need to give the model a maximum number for the guessing game.
 * You can then start a game and guess the number any
 * amount of times.
 */
public class GuessingGame {
    private static final Random generator = new Random();
    private final int MIN = 0;
    private final int max;
    private int winningNumber;

    public GuessingGame(int max){
        this.max = max;
    }

    /**
     * Resets the game, and picks a winning number at random
     */
    public void startNewGame(){
        winningNumber = generator.nextInt(max) + 1;
    }


    /**
     * used to guess what the winning number is
     * @param guess the number you think is the winning number
     * @return true if the guess is correct, and false otherwise
     */
    public boolean guessNumber(int guess){
        if(guess > max || guess < MIN){
            throw new IllegalStateException(String.format(
                    "You can only guess numbers from %d-%d (inclusive). Got %d"
                            , MIN, max, guess));
        }

        return guess == winningNumber;
    }

}
