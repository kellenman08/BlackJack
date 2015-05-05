import java.util.LinkedList;


public class Dealer extends Player {

	public Dealer() {//represents bank aka infinite money
		super(0);
	}
	
	public String getStrategy(){//override player strategy since dealer has a different strategy
		if(this.getHandTotal() <=16){
			return "H";//H for hit
		}
		if(this.getHandTotal() >21){
			return "B";//B for bust
		}
		return "X";//X for stand
	}
	
	public LinkedList<Card> getHand(){
		return super.getHand();
	}

	public Card getUpCard(){
		return super.getHand().get(0);
	}
	
	public double getChipCount(){
		return 0;//always at 0 since we don't care at least for now
	}
	
	public String toString(){
		return this.getUpCard().toString() + "; Hand Total = " + this.getHandTotal();
	}
}
