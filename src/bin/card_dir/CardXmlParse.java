package CardGame.src.bin.card_dir;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CardXmlParse {

    private Document doc;
    private List< Card > cardList = new ArrayList<> ();

    public CardXmlParse( String fileName ) {
        initializeDocument( fileName );
        createCardArray();

    }

    public void initializeDocument(String FileName) {

        File inputFile = new File(FileName);
        DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dBuilderFactory.newDocumentBuilder();
            this.setDoc(dBuilder.parse(inputFile));
            this.doc.getDocumentElement().normalize();
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
    }

    public void createCardArray() {
        NodeList nodeList = getDoc().getElementsByTagName( "Card" );

        for ( int iterator = 0; iterator < nodeList.getLength(); iterator++) {
            cardList.add(getCardInitialization(nodeList.item(iterator)));            
        }
    }

    public Document getDoc() {
        return doc;
    }

    public List< Card > getCardList() {
        return cardList;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    private static Card getCardInitialization( Node node ) {

        String nameString;
        String typeString;
        String groupString;
        String rarityString;
        String qualityString;
        String descriptionString;
        

        if ( node.getNodeType() == Node.ELEMENT_NODE ) {
            Element ele = (Element) node;
            nameString = getTagValue("Name", ele);
            typeString = getTagValue("Type", ele);
            groupString = getTagValue("Group", ele);
            rarityString = getTagValue("Rarity", ele);
            qualityString = getTagValue("Quality", ele);
            descriptionString = getTagValue("Description", ele);
        } else {
            return new Card();
        }


        return new Card(nameString, typeString, groupString, rarityString, qualityString, descriptionString);
    }

    private static String getTagValue(String string, Element ele) {
        NodeList nodeList = ele.getElementsByTagName(string).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    public void printAllCardsInXmlDoc() {
        List < Card > cList = this.cardList;
        for (int iterator = 0; iterator < cList.size(); iterator++ ) {
            System.out.println("Card No."+iterator+"\n"+ cList.get(iterator).toString()+"\n\n");
        }
    }

    public static void main(String[] args) {
        CardXmlParse cardXmlParse = new CardXmlParse("CardGame\\data\\cards.xml");
        cardXmlParse.printAllCardsInXmlDoc();
   } 
}
