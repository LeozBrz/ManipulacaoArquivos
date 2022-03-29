package aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidade.Produto;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Produto> lista = new ArrayList<>();
		System.out.println("Entre com o nome do Arquivo: ");
		String arquivo = sc.nextLine();
		
		File arquivoStr = new File(arquivo);
		String pasta = arquivoStr.getParent();
		
		boolean sucesso = new File(pasta + "\\out").mkdir();
		
		String direcionarArquivo = pasta + "\\loja\\sumario.csv"; 
		
		try (BufferedReader br = new BufferedReader(new FileReader(arquivoStr))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {

				String[] campo = itemCsv.split(",");
				String nome = campo[0];
				double preco = Double.parseDouble(campo[1]);
				int quantidade = Integer.parseInt(campo[2]);

				lista.add(new Produto(nome, preco, quantidade));

				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(direcionarArquivo))) {

				for (Produto item : lista) {
					bw.write(item.getNome() + "," + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(direcionarArquivo + " CRIADO!");
				
			} catch (IOException e) {
				System.out.println("ERRO ESCREVENDO ARQUIVO: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("ERRO LENDO ARQUIVO: " + e.getMessage());
		}

		sc.close();
	}
}
