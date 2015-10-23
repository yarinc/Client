package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.CLI;
import view.MyCLIView;
import view.MyGUIView;

public class Run {

	public static void main(String[] args) {
		MyModel m = new MyModel();
		if(m.getProperties().getViewType().equals("CLI")) { 
			CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
			MyCLIView ui = new MyCLIView(cli);
			cli.setView(ui);
			Presenter p = new Presenter(ui,m);
			ui.addObserver(p);
			m.addObserver(p);
			ui.start();
		}
		else if(m.getProperties().getViewType().equals("GUI")){
			MyGUIView ui = new MyGUIView("Mazer", 500, 500);
			Presenter p = new Presenter(ui,m);
			ui.addObserver(p);
			m.addObserver(p);
			ui.start();
		}
	}
}
