package it.polimi.ingsw.GC_36.data;

import java.io.IOException;
import java.nio.charset.Charset;

public class Main {
	private Main() {}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		Decoder d = new Decoder();
		//g.createDevelopmentCard();
		String content = g.readFile("cards.json",
				Charset.defaultCharset());
		System.out.println(
				d.buildDevelopmentCardList(content).get(0).getType());
		g.createDeckSetList(d.buildDevelopmentCardList(content));
	}

}