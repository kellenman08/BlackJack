
public class BlackJack {
	private Player[] players;
	private Shoe shoe;
	private int numDecks;
	private double dealPercentage;//tells when to stop dealing from shoe
	

	public BlackJack(int numberOfPlayers, double deckPercentage, int shoeSize, int StartingChipCount){
		players = new Player[numberOfPlayers +1];
		this.numDecks = shoeSize;
		Dealer deal = new Dealer();
		for(int x=0;x<numberOfPlayers;x++){
			players[x] = new Player(StartingChipCount);
			players[x].setDealer(deal);
		}
		players[players.length -1] = deal;
		shoe = new Shoe(shoeSize);
		shoe.Shuffle();
		dealPercentage = deckPercentage;//dealPercentage is how much of the shoe you want to go through before reset
	}

	public boolean canDealMore(){
		int pl = players.length*2;
		double dP = dealPercentage;
		double pD = shoe.percentageDrawn();
		//int cardsDrawn = 52*this.numDecks - shoe.numberCardsLeft();//debugging
		int cardsLeft = shoe.numberCardsLeft();
		if(pl < cardsLeft && dP > pD){
			//not perfect test! could still possibly deal more than number of cards left
			return true;
		}
		return false;
	}

	public Player[] getPlayers() {
		return players;
	}
	
	public Dealer getDealer(){
		return (Dealer)players[players.length-1];
	}

	public void resetShoe() {
		this.shoe = new Shoe(numDecks);
		this.shoe.Shuffle();
	}

	public Card drawFromShoe() {
		Card c = shoe.Draw();
		return c;
	}
}
