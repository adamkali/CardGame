package CardGame.src.bin.card_dir;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardPack {

    private CardXmlParse cardXmlParse;
    private List<Card> packOfCards = new ArrayList<Card>();
    private Card exquisiteOrBetterCard;
    private Card rareOrBetterCard;
    private Random rand = new SecureRandom();

    public CardPack(String cardFileString) {

        setCardXmlParse(new CardXmlParse( cardFileString ));

        determineExquisiteOrBetter();
        determineRareOrBetter();

        /*
        * This loop here is to get the card list created by the xml file and get a random card from said list
        */
        int inst = 0;
        while ( inst < 5 ) {
            Card fooCard = getCardXmlParse().getCardList().get(rand.nextInt(getCardXmlParse().getCardList().size()));
            if ( getPullChance(fooCard) ) {
                addCardToPack(fooCard);
                inst++;
            }
        }


        // Adds in the determined rare or better or the exquisite or better cards
        getPack().add(getRareOrBetterCard());
        getPack().add(getExquisiteOrBetterCard());

    }

    public CardXmlParse getCardXmlParse() { return cardXmlParse; }

    private Card getRareOrBetterCard() { return rareOrBetterCard; }
    
    private Card getExquisiteOrBetterCard() { return exquisiteOrBetterCard; }

    public List <Card> getPack() { return packOfCards; }

    private void addCardToPack( Card card) { getPack().add(card); }

    private void setCardXmlParse(CardXmlParse cardXmlParse) { this.cardXmlParse = cardXmlParse; }


    /*
     * This is to decide whether the card is pulled 
     */
    private boolean getPullChance( Card card ) { return ( rand.nextDouble() <= card.getMultiplier() ); }

    /*
    * detemineRareOrBetter() and its sister method determineExquisiteOrBetter() gets a card of the desired quality or rarity, adds it to an empty array.
    *       Next, using the random seed, generated before in the class, we can pick out a guarenteed Exquisite or better, or a guarenteed Rare or better. 
    */
    private void determineRareOrBetter() {
        List<Card> rareCards = new ArrayList<>();
        List<Card> lCards = getCardXmlParse().getCardList();

        for ( int instance = 0; instance < lCards.size(); instance++ ) {
            Card fooCard = lCards.get(instance);
            
            if ( ( fooCard.getRarity().equals("Rare") || fooCard.getRarity().equals("Epic") || fooCard.getRarity().equals("Legendary") || fooCard.getRarity().equals("Mythic") ) )  {
                rareCards.add(fooCard);
            }
        }

        boolean flag = true;
        while ( flag ) {

            for ( int instance = 0; instance < rareCards.size(); instance++ ) {
                if ( getPullChance(rareCards.get(instance)) ) {
                    this.rareOrBetterCard = rareCards.get(instance);
                    flag = false;
                    break;                
                }
            }
        }
    }


    // See previous comment.
    private void determineExquisiteOrBetter() {
        List<Card> exquisiteCards = new ArrayList<>();
        List<Card> lCards = getCardXmlParse().getCardList();


        for ( int instance = 0; instance < lCards.size(); instance++ ) {
            Card fooCard = lCards.get(instance);
            
            if ( (fooCard.getQuality().equals("Exquisite") || fooCard.getQuality().equals("Extraordinary") || fooCard.getQuality().equals("Outstanding") ) ) {
                exquisiteCards.add(fooCard);
            }
        }

        boolean flag = true;
        while ( flag ) {
        
            for ( int instance = 0; instance < exquisiteCards.size(); instance++ ) {
                if ( getPullChance(exquisiteCards.get(instance)) ) { 
                    this.exquisiteOrBetterCard = exquisiteCards.get(instance);
                    flag = false;
                    break;
                }
            }
        }
    }

    public void printPackOfCards() {
        List <Card> tempList = getPack();

        int inst = 0;
        while ( inst < tempList.size() ) {
            System.out.println(tempList.get(inst) + "\n\n");
            inst++;            
        }
    }

    public static void main(String[] args) {
        CardPack cardPack = new CardPack("CardGame\\data\\cards.xml");
        cardPack.printPackOfCards();
    }
}
