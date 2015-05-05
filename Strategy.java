import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Strategy {
	private String[][] table;
	
	public Strategy(){
		table = new String[27][10];
		File file = new File("Strategy");
		try {
			Scanner scan = new Scanner(file);
			scan.nextLine();
			scan.nextLine();
			for(int x =0; x<27;x++){
				scan.next();
				for(int y=0; y<10;y++){
					table[x][y] = scan.next();
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String[][] getTable(){
		return table;
	}

	public String getStrategy(Player p, Dealer d){
		int x = getColumn(d);
		int y = getRow(p);
		if(p.numCards()>2 && table[y][x].equals("D")){
			return "H";
		}
		if(y==-1){
			return "B";//B for bust
		}
		if(y==-2) return "BJ";//BlackJack!
		return table[y][x];//return value of table based on dealer and player
	}
	
	private int getColumn(Dealer d){
		Card up = d.getUpCard();
		if(up.getFaceValue()=="A"){
			return 9;
		}else{
			int val;
			try{
				val = Integer.parseInt(up.getFaceValue());
			}catch(NumberFormatException e){
				val = 10;//has to be a face card
			}
			return val-2;
		}
	}
	
	private int getRow(Player p){
		if(p.numCards() == 2){
			//strategy is different with two cards than it is with more
			String c1 = p.getHand().get(0).getFaceValue();
			String c2 = p.getHand().get(1).getFaceValue();
			
			if(p.getHandTotal()==21){
				//blackjack
				return -2;
			}
			
			if(c1.equals(c2)){//duplicates
				int val;
				try{
					val = Integer.parseInt(c1);
				}catch (NumberFormatException e){
					if(c1 == "A"){
						val = 11;
					}else{
						val = 10;
					}
				}
				return val +15;
				//deciding to split duplicates or not starts 15 rows down on strategy chart
			}
			
			if(c1 == "A"){
				//not duplicates so c2 != A if c1 = A
				int c = Integer.parseInt(c2);
				if(c==9){
					c=8;
				}
				return c + 8;
				//c2 is not a facecard since total isn't equal to 21
			} else if(c2 == "A"){//check if c2 == A
				int c = Integer.parseInt(c1);
				if(c==9){
					c=8;
				}
				return c +8;
				//in this case c1 is not a facecard since total isn't equal to 21
			}
			
		}
		int sum = p.getHandTotal();
		//cards are different and there is more than 2 so check if soft or get total
		if(p.isSoft()){
			if(p.getHandTotal()>=19){
				return 16;
			}
			return p.getHandTotal() -3;
		}
		if(sum>21){
			return -1;//busted!
		}
		if(sum <= 8){//start on the first line of strategy
			return 0;
		}
		if(sum>=17){//start on the last of the sum lines of strategy
			return 9;
		}
		return sum - 8;
	}
}
