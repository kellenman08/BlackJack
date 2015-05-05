import java.util.Random;
import java.util.Stack;


public class Deck {
	private double numberOfCards;
	private Stack<Card> deck;
	private int count;
	
	public Deck(){
		deck = new Stack<Card>();
		@SuppressWarnings("unused")
		Card card;
		for(int x=0;x<52;x++){
			addToDeck(card = new Card(x));
		}
		count = 0;
		numberOfCards = 52.0;
	}
	
	public void setNumberOfCards(double x){
		numberOfCards = x;
	}

	public void addToDeck(Card card) {
		deck.push(card);		
	}

	public Card Draw() {
		if(this.deck.size()!=0){
			Card card = this.deck.pop();
			changeCount(card.faceValue);
			return card;
		}
		return null;
	}

	public void Shuffle() {
		if(deck.size()!=0){
			Card[] array = new Card[deck.size()];
			Random rand = new Random();
			int size = deck.size();
			
			int x =0;
			while(deck.size()!=0){
				array[x] = deck.pop();
				x++;
			}

			for(int y = 0; y<array.length; y++){
				array = swap(array, y,rand.nextInt(size));
			}
			
			for(int y=0; y<array.length;y++){
				deck.push(array[y]);
			}
		}
	}

	private Card[] swap(Card[] array, int y, int x) {
		Card temp =array[y];
		array[y] = array[x];
		array[x] = temp;
		return array;
	}

	private void changeCount(int cardValue){
		if(cardValue == 2 || cardValue == 7){
			count++;
		}else if(cardValue >=3 && cardValue<=6){
			count += 2;
		}else if(cardValue == 9 || cardValue == 1){
			--count;
		}else if(cardValue >=10){
			count -=2;
		}
	}

	public int count() {
		return this.count;
	}

	public double percentageDrawn() {
		return 1-this.numberCardsLeft()/numberOfCards;
	}
	
	public int numberCardsLeft(){
		return this.deck.size();
	}
	
	public boolean hasMore(){
		return this.deck.size()!=0;
	}

}
