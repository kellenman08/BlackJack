import java.util.LinkedList;



public class Player {
	private double chipCount;
	private int handTotal;
	private double bet;
	private LinkedList<Card> hand;
	private Strategy strat;
	private Dealer d;
	private String strategy;
	private boolean soft;
	
	public Player(int startingChips){
		chipCount = startingChips;
		hand = new LinkedList<Card>();
		strat = new Strategy();
		soft = false;
	}
	
	public void addToHand(Card card){
		hand.add(card);
		changeHandTotal(card.faceValue);
	}
	
	private void changeHandTotal(int faceValue) {//changing hand total is unique for cards
		if(faceValue >10){
			faceValue = 10;
		}
		if(soft && faceValue+handTotal>21){
			handTotal-=10;
			soft = false;
		}
		if(faceValue == 1 && handTotal +11 >21){
			handTotal += 1;
		}
		else{
			switch(faceValue){
			case 1: handTotal += 11; soft=true; break;
			case 10: handTotal +=10; break;
			default: handTotal += faceValue;
			}
		}
	}

	public int getHandTotal(){
		return handTotal;
	}
	
	public void resetHand(){
		handTotal = 0;
		hand = new LinkedList<Card>();
	}
	
	public void addToChips(double x){
		chipCount+=x;
	}
	
	public void removeChips(double x){
		chipCount-=x;
	}
	
	public double getChipCount(){
		return chipCount;
	}
	
	private String strategy(){
		if(this.handTotal >21){
			return "B";
		}
		return strat.getStrategy(this,d);
	}
	
	public String getStrategy(){
		if(this.strategy == null){
			return strategy();
		}else if(!this.strategy.equals("X")){
			return this.strategy();
		}else{
			return strategy;
		}
	}

	public LinkedList<Card> getHand() {
		return hand;
	}

	public void setDealer(Dealer deal) {
		this.d = deal;
	}

	public int numCards() {
		return hand.size();
	}
	
	public void doubleMoney(){
		bet *=2;
	}

	public void setBet() {
		bet = 5;
	}
	
	public double getBet() {
		return bet;
	}
	
	public boolean hasBlackJack(){
		if(this.getHandTotal() == 21 && this.numCards() ==2){
			return true;
		} else{
			return false;
		}
	}

	public void setStrategy(String strat) {
		this.strategy = strat;
	}

	public String toString(){
		return this.getHand().toString();
	}

	public boolean isSoft() {
		return soft;
	}

	public void setSoft(boolean b) {
		this.soft = b;
		
	}
}
