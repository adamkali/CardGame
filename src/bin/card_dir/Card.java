package CardGame.src.bin.card_dir;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class Card {

    static HashMap<String, Double> rarityHashMap = new HashMap<>();

    static {
        rarityHashMap.put("Common", 1.0);
        rarityHashMap.put("Rare",0.9);
        rarityHashMap.put("Epic",0.66);
        rarityHashMap.put("Legendary",0.4356);
        rarityHashMap.put("Mythic",0.287496);
        rarityHashMap.put("Divine",0.18974736);
    }

    static HashMap<String, Double> qualityHashMap = new HashMap<>();

    static {
        qualityHashMap.put("Normal", 1.0);
        qualityHashMap.put("Exquisite", 0.3333);
        qualityHashMap.put("Extraordinary", 0.1111);
        qualityHashMap.put("Outstanding", 0.36667);
    }

    private static final double FREQUENCY = 5f/7;
    private final String name;
    private final String type;
    private final String group;
    private final String rarity;
    private final String quality;
    private final String description;

    public Card(String Name, String Type, String Group, String Rarity, String Quality, String Description) {
        
        /*
        * Change to have set methods
        */
        
        this.name = Name;
        this.type = Type;
        this.group = Group;
        this.rarity = isValidRarity(Rarity);
        this.quality = Quality;
        this.description = Description;

    }

    public Card() {
        this.name = "Rabbit";
        this.type = "Character";
        this.group = "The Wild";
        this.rarity = "Common";
        this.quality = "Normal";
        this.description = "A fluffy rabbit. Deadly in cuteness, but not much else.";
	}

	public double getFrequency() { return FREQUENCY; }

    public String getName() { return this.name; }

    public String getType() { return this.type; }

    public String getGroup() { return this.group; }

    public String getRarity() { return this.rarity; }

    public String getQuality() { return this.quality; }

    public String getDescription() { return this.description; }

    public double getMultiplier() { return this.calculateFrequency(); }

    @Override
    public String toString() {
        return "Name: " + this.getName() + "\nType: " + this.getType() + "\nGroup: " + this.getGroup() + "\nRarity: " + this.getRarity() + "\nQuality: " + this.getQuality() + "\nFrequency: " + this.frequencyToString() + "\nDescription: " + this.getDescription();
    }

    private String isValidRarity(String rarityString ) {

        if ( rarityHashMap.containsKey(rarityString) ) {
            return rarityString;
        } else {
            System.out.println("Found bug!");
            System.exit(1);
            return ":(";
        }
    }

    private Double getRarityHashMapDouble() {
        Set< Entry< String, Double > > rEntries = rarityHashMap.entrySet();
        Iterator< Entry< String, Double > > iterator = rEntries.iterator();

        // Go through each instance and see if there is a key that matces the construction.
        while ( iterator.hasNext() ) {
            Entry< String, Double > instEntry = iterator.next();
            
            String rarityCheck = this.getRarity();
            String keyCheck = instEntry.getKey();
            
            if (rarityCheck != null && rarityCheck.equals(keyCheck)) {
                return instEntry.getValue();
            }
        }

        // Failsafe
        return 0.0;
    }

    private Double getQualityHashMapDouble() {
        Set< Entry< String, Double > > qEntries = qualityHashMap.entrySet();
        Iterator< Entry< String, Double > > iterator = qEntries.iterator();

        // Go through each instance and see if there is a key that matces the construction.
        while ( iterator.hasNext() ) {
            Entry< String, Double > instEntry = iterator.next();
            
            String qualityCheck = this.getQuality();
            String keyCheck = instEntry.getKey();
            
            if ( qualityCheck != null && qualityCheck.equals(keyCheck)) {
                return instEntry.getValue();
            }
        }
        // Failsafe
        return 0.0;
    }

    private Double calculateFrequency() {
        Double rarityMultiplier = this.getRarityHashMapDouble();
        Double qualityMultiplier = this.getQualityHashMapDouble();

        return rarityMultiplier*qualityMultiplier*FREQUENCY;
    }

    public String frequencyToString() {
        
        Double trueFrequency = this.calculateFrequency();

        if ( trueFrequency >= 0.4 ) {
            return "1-Star";
        } else if ( isBetween(trueFrequency, 0.2, 0.4)) {
            return "2-Star";
        } else if ( isBetween(trueFrequency, 0.1, 0.2)) {
            return "3-Star";
        } else if ( isBetween(trueFrequency, 0.33, 0.1)) {
            return "4-Star";
        } else {
            return "5-Star";
        }
    }

    public static boolean isBetween( double value, double min, double max ) {
        return ( ( value > min ) && ( value < max ) );
    }

    public static void main(String[] args) {
        Card fooCard = new Card("Rabbit", "Character", "The Wild", "Common", "Normal", "A fluffy rabbit. Deadly in cuteness, but not much else.");
        Card barCard = new Card("Imp", "Character", "The Corruption", "Common", "Normal", "An imp is the lowliest demon in the ranks of the Eldritch Entity.");
        System.out.println(fooCard.toString());
        System.out.println(barCard.toString());    
    }

}
