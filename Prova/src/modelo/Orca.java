package modelo;

import java.util.Objects;

public class Orca {

	private int id;
	private String fornecedor;
	private String produto;
	private double preco;
	//boolean barato;
	
	public Orca(int id) {
		this.id = id;
	}
	
	public Orca(int id, String fornecedor, String produto, double preco) {
		this.id = id;
		this.fornecedor = fornecedor;
		this.produto = produto;
		this.preco = preco;
		
	}
	
	public Orca(String linha) {
		this.id = Integer.parseInt(linha.split(";")[0]);
		this.fornecedor = linha.split(";")[1];
		this.produto = linha.split(";")[2];
		this.preco = Double.parseDouble(linha.split(";")[3]);
	}

	public int getId() {
		return id;
	}

	public String getId(String s) {
		return String.format("%d", id);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}
	
	public String getPreco(String s) {
		return String.format("%.2f", preco);
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	//public boolean getBarato() {
	//	return barato;
	//}
	
	//public void setBarato() {
		
	//}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orca other = (Orca) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return id + "\t" + fornecedor + "\t" + produto + "\t" + preco;
	}
	
	public String toCSV() {
		return id + ";" + fornecedor + ";" + produto + ";" + preco;
	}
	
	
	
	
}
