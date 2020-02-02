package com.github.captainobvious0.cardutil;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    ArrayList<Integer> deck = new ArrayList<Integer>();
    ArrayList<Integer> currentDeck = new ArrayList<Integer>();
    
    public Deck() {
        createDeck();
    }
    
    public void createDeck() {
        for (int i = 1; i < 52; i++) {
            deck.add(i);
        }
        currentDeck = deck;
    }
    
    public void resetCurrentDeck() {
        currentDeck = deck;
    }
    
    // Suits:
    // 1 = Hearts
    // 2 = Diamonds
    // 3 = Clubs
    // 4 = Spades
    public static int getSuit(int card) {
        double val = card / 13D;
        if (val <= 1D) {
            return 1;
        } else if (val <= 2D) {
            return 2;
        } else if (val <= 3D) {
            return 3;
        } else if (val <= 4D) {
            return 4;
        }
        return 0;
    }
    
    // Card Values:
    // value + 1 (Since cards start at 1)
    // Ex. 1 is actually 2, 9 is 10, 0 is Ace
    public static int getValue(int card) {
        return card % 13;
    }
    
    // BlackJack
    public static int getGameValue(int card) {
        int val = getValue(card);
        if (val >= 10 && val != 0) {
            return 10;
        } else if (val == 0) {
            return 11;
        } else {
            return val + 1;
        }
    }
    
    // BlackJack
    public int getHandTotal(ArrayList<Integer> cards) {
        int total = 0;
        for (int card : cards) {
            total += getGameValue(card);
        }
        return total;
    }
    
    // Converts a card to a readable String
    public static String cardToString(int card) {
        int suit = getSuit(card);
        int value = getValue(card);
        String sSuit = "";
        String sValue = "";
        
        if (suit == 1) {
            sSuit = "Hearts";
        } else if (suit == 2) {
            sSuit = "Diamonds";
        } else if (suit == 3) {
            sSuit = "Clubs";
        } else {
            sSuit = "Spades";
        }
        
        if (value <= 9 || value == 1) {
            sValue = (value + 1) + "";
        } else if (value == 10) {
            sValue = "Jack";
        } else if (value == 11) {
            sValue = "Queen";
        } else if (value == 12) {
            sValue = "King";
        } else {
            sValue = "Ace";
        }
        
        return sValue + " of " + sSuit;
    }
    
    public ArrayList<Integer> createPlayerHand() {
        ArrayList<Integer> deck = new ArrayList<Integer>();
        Random rand = new Random();
        int rand1 = rand.nextInt((currentDeck.size() - 1) + 1);
        int rand2 = rand.nextInt((currentDeck.size() - 1) + 1);
        if (rand1 == rand2) return createPlayerHand();
        deck.add(currentDeck.get(rand1));
        currentDeck.remove(rand1);
        deck.add(currentDeck.get(rand2));
        currentDeck.remove(rand2);
        return deck; 
    }
    
    public int drawAnotherCard() {
        Random rand = new Random();
        int rand1 = rand.nextInt((currentDeck.size() - 1) + 1);
        int card = currentDeck.get(rand1);
        currentDeck.remove(rand1);
        return card;
    }
    
}