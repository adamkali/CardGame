package CardGame.src.test;

import java.util.List;

import CardGame.src.bin.card_dir.*;

public class CardPackTest {

    public boolean isItBetterThanRare( Card fooCard ) { 
        if ( fooCard.getRarity().equals("Rare") || fooCard.getRarity().equals("Epic") || fooCard.getRarity().equals("Legendary") || fooCard.getRarity().equals("Mythic") ) {
            return true;
        } else {
            return false;
        }
    }

    public void getHowManyRareOrBetterCards( List<Card> cardList ) {
        int counter = 0;

        for ( int instance = 0 ; instance < cardList.size() ; instance++ ) {
            if ( isItBetterThanRare(cardList.get(instance)) ) {
                counter++;
            }
        }
        System.out.println("Number of Rare card or better: " + counter);
    }
 
    public boolean isItBetterThanExquisite( Card fooCard ) {  
        if ( fooCard.getQuality().equals("Exquisite") || fooCard.getQuality().equals("Extraordinary") || fooCard.getQuality().equals("Otherworldly") ) {
            return true;
        } else {
            return false;
        }
    }

    public void getHowManyExquisiteOrBetterCards( List<Card> cardList ) {
        int counter = 0;

        for ( int instance = 0 ; instance < cardList.size() ; instance++ ) {
            if ( isItBetterThanExquisite(cardList.get(instance)) ) {
                counter++;
            }
        }
        System.out.println("Number of Exquisite card or better: " + counter);
    }

    public void getHowManyCards(List<Card> cardList) {
        System.out.println("Total Cards: " + cardList.size());
    }

    public void testALotOfPacks( int howMany, String sourceString ) {
        int iterator = 0;

        while ( iterator < howMany ) {
            List<Card> cList = new CardPack(sourceString).getPack();
            
            System.out.println("Pack No. " + (iterator + 1));
            getHowManyRareOrBetterCards(cList);
            getHowManyExquisiteOrBetterCards(cList);
            
            iterator++;
        }

    }

    public static void main(String[] args) {

        CardPackTest test = new CardPackTest();

        String fileString = "CardGame\\data\\cards.xml";
        var testXmlCards = new CardXmlParse(fileString).getCardList();

        test.getHowManyCards(testXmlCards);
        test.getHowManyRareOrBetterCards(testXmlCards);
        test.getHowManyExquisiteOrBetterCards(testXmlCards);

        test.testALotOfPacks(10, fileString);

    }

}
