package it.polimi.ingsw.GC_36.data;

import java.io.IOException;

public class Main {
	private Main() {}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		g.createDevelopmentCard();
	}

}