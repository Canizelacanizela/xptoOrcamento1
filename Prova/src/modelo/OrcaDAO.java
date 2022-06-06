package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OrcaDAO {

	private BufferedReader br;
	private BufferedWriter bw;
	private String path = "./dados/orcamentos.csv";

	public ArrayList<Orca> ler() {
		ArrayList<Orca> linhas = new ArrayList<>();
		Orca o;
		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while (linha != null) {
				o = new Orca(linha);
				linhas.add(o);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return linhas;
	}

	public void escrever(ArrayList<Orca> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Orca o : linhas) {
				bw.write(o.toCSV() + "\r\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}