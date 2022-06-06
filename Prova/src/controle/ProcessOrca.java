package controle;

import java.util.ArrayList;

import modelo.Orca;
import modelo.OrcaDAO;

public class ProcessOrca {

	public static ArrayList<Orca> orca = new ArrayList<>();
	private static OrcaDAO od = new OrcaDAO();
	
	public static void abrir() {
		orca = od.ler();
	}
	
	public static void salvar() {
		od.escrever(orca);
	}
}
