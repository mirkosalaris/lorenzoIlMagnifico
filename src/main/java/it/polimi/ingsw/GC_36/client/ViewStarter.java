package it.polimi.ingsw.GC_36.client;

import it.polimi.ingsw.GC_36.client.view.ViewCLI;
import it.polimi.ingsw.GC_36.client.view.ViewGUI;
import it.polimi.ingsw.GC_36.client.view.ViewInterface;
import javafx.application.Application;

public class ViewStarter {
	public static final int CLI = 0;
	public static final int GUI = 1;
	private int type;

	public ViewStarter(int type) {
		if (type != CLI && type != GUI) {
			throw new IllegalArgumentException("Type has to be CLI or GUI");
		}
		this.type = type;
	}

	public ViewInterface start() throws InterruptedException {
		if (type == CLI) {
			return new ViewCLI();
		} else {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Application.launch(ViewGUI.class);
				}
			}).start();

			while (ViewGUI.instance == null) {
				Thread.sleep(100);
			}
			return ViewGUI.instance;
		}
	}
}
