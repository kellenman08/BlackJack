import java.util.Arrays;


public class Play {
	private BlackJack table;
	
	public Play(BlackJack BTable, int hands){
		this.table = BTable;
		int x = 0;
		while(x<hands){
			if(table.canDealMore()){
				setBets();
				start();
				playHand();
				payout();
				resetHands();
				x++;
			} else{
				table.resetShoe();
			}
		}
	}

	private void resetHands() {
		for(int x=0;x<table.getPlayers().length;x++){
			Player p = table.getPlayers()[x];
			p.setSoft(false);
			p.resetHand();
		}
		
	}

	private void setBets() {
		for(int x =0;x<table.getPlayers().length-1;x++){
			Player p = table.getPlayers()[x];
			p.setBet();
		}
	}

	private void payout() {
		Dealer d = table.getDealer();
		String ds = d.getStrategy();
		int dt = d.getHandTotal();
		for(int x=0;x<table.getPlayers().length-1;x++){
			Player p = table.getPlayers()[x];
			String ps = p.getStrategy();
			int pt = p.getHandTotal();
			if(p.hasBlackJack() && !d.hasBlackJack()){
				//player has black jack but dealer does not
				p.addToChips(p.getBet()*1.5);
			}else if(ds.equals("B")&&!ps.equals("B")){
				//dealer broke and player didn't
				p.addToChips(p.getBet());
			}else if(ps.equals("B") || pt<dt){
				//player broke or has lower total than dealer
				p.removeChips(p.getBet());
			}else if(pt>dt){
				//player didn't break and has higher total than dealer
				p.addToChips(p.getBet());
			}//else push and nothing happens
		}
		
	}

	private void playHand() {
		if(table.getDealer().hasBlackJack()){
			//play stops and dealer takes everyones money or pushes with blackjack
			//no insurance yet need to work on that
			return;
		}
		int bustOrBlack = 0;
		for(int x=0;x<table.getPlayers().length;x++){
			Player p = table.getPlayers()[x];
			//Dealer d = table.getDealer();//debugging purposes
			if(bustOrBlack == table.getPlayers().length -1){
				//checks if everyone before the dealer busted or has blackjack
				return;
			}
			String s = p.getStrategy();
			while(!s.equals("BJ") && !s.equals("X") && !s.equals("B")){
				//player doesn't have blackjack, isn't broke and isn't standing
				if(s.equals("H")){
					hit(p);
				}else if(s.equals("S")){
					split(p);
				}else if(s.equals("D")){
					doubleDown(p);
				}
				s=p.getStrategy();
			}
			if(s.equals("B") || s.equals("BJ")){
				bustOrBlack++;
			}
		}
		
	}

	private void doubleDown(Player p) {
		p.doubleMoney();
		p.addToHand(table.drawFromShoe());
		p.setStrategy("X");
	}

	private void split(Player p) {
		p.setStrategy("X");
	}

	private void hit(Player p) {
		Card c = table.drawFromShoe();
		p.addToHand(c);
	}

	private void start() {
		//just deal two cards to each player and dealer, order doesn't matter statistically speaking
		for(int x = 0; x<table.getPlayers().length;x++){
			Player p = table.getPlayers()[x];
			Card c1 = table.drawFromShoe();
			p.addToHand(c1);
			
			Card c2 = table.drawFromShoe();
			p.addToHand(c2);
			
			p.setStrategy(null);
		}
	}
	
	public double[] getChipCount(){
		double[] chips = new double[table.getPlayers().length-1];
		for(int x = 0;x<table.getPlayers().length -1;x++){
			chips[x] = table.getPlayers()[x].getChipCount();
		}
		return chips;
	}
	
	public String toString(){
		return Arrays.toString(this.getChipCount());
	}
}
