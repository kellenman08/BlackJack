

public class Shoe extends Deck {
	
	public Shoe(int numberOfDecks){
		for(int x=0;x<numberOfDecks-1;x++){
			Deck temp = new Deck();
			while(temp.hasMore()){
				super.addToDeck(temp.Draw());
			}
		}
		super.setNumberOfCards(52.0*numberOfDecks);
	}
}
