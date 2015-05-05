
public class Card {
	public int faceValue;
	public Character suit;

	public Card(int cardNumber) {
		this.setFaceValue((cardNumber % 13) + 1);
		this.setSuit(cardNumber/13);
	}
	
	public Card(String c) {//doesn't work for 10's figure out later try using if statement?
		switch (c.charAt(0)){
		case 'A': this.setFaceValue(1); break;
		case 'K': this.setFaceValue(13); break;
		case 'Q': this.setFaceValue(12); break;
		case 'J': this.setFaceValue(11); break;
		default: int x = Character.getNumericValue(c.charAt(0)); this.setFaceValue(x);
		}
		this.suit = c.charAt(1);
	}

	private void setSuit(int cardNumber) {
		switch(cardNumber){
		case 0: this.suit = 'D'; break;
		case 1: this.suit = 'C'; break;
		case 2: this.suit = 'H'; break;
		case 3: this.suit = 'S'; break;
		}
	}
	
	public Character getSuit(){
		return this.suit;
	}

	private void setFaceValue(int cardNumber){
		this.faceValue=cardNumber;
	}
	
	public String getFaceValue(){
		switch(this.faceValue){
		case 1: return "A";
		case 11: return "J";
		case 12: return "Q";
		case 13: return "K";
		}
		return Integer.toString(this.faceValue);
	}
	
	
	public String toString(){
		String face = new String();
		face = face.concat(getFaceValue());
		face = face.concat(getSuit().toString());
		return face;
	}

}
