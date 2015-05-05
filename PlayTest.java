import java.util.Arrays;

import org.junit.Test;


public class PlayTest {

	@Test
	public void test() {
		BlackJack table = new BlackJack(2,.75,3,100);//75% of deck should be gone through
		Play play = new Play(table,100);
		System.out.println(Arrays.toString(play.getChipCount()));
	}

}
