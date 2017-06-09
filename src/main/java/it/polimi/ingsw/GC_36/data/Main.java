package it.polimi.ingsw.GC_36.data;

import java.io.IOException;

public class Main {
	private Main() {}

	public static void main(String[] args) throws IOException {
		Generator g = new Generator();
		Decoder d = new Decoder();
		g.createDevelopmentCard();

		/*
		String s="Vi1ncAM2are e4 no5i a studiare ";
		if (s.contains("studiare")) {
			int a=Integer.parseInt(s.replaceAll("[\\D]", ""));
			System.out.println(a);
		}

		Generator g=new Generator();
		JsonArray jsonObject= g.buildPersonaBoard();

		Gson gson=new Gson();
		String s=gson.toJson(jsonObject);
		System.out.println(s);
		*/
		//g.createBonusTile();
		//g.createDevelopmentCard();

	}
}