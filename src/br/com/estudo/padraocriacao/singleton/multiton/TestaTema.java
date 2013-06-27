package br.com.estudo.padraocriacao.singleton.multiton;

/**
 * <p>
 * Objetivo:<br>
 * Permitir a criação de uma quantidade limitada de instâncias de determinada classe e
 * fornecer um modo para recuperá-las.
 * </p>
 * <p>
 * Exemplo prático:<br>
 * Estamos desenvolvendo uma aplicação que possuirá diversos temas para interface do usuário. O
 * número de temas é limitado e cada usuário poderá escolher o de sua preferência. Podemos implementar
 * os temas através de uma classe.
 * </p> 
 * @author daniel.oliveira
 */
public class TestaTema {
	
	public static void main(final String[] args) {
		Tema temaFire = Tema.getInstance(Tema.FIRE);
		System.out.println(" Tema " + temaFire.getNome());
		System.out.println(" Cor Da Fonte : " + temaFire.getCorDaFonte());
		System.out.println(" Cor Do Fundo : " + temaFire.getCorDoFundo());

		Tema temaFire2 = Tema.getInstance(Tema.FIRE);

		System.out.println(" --------");
		System.out.println(" Comparando as referências ... ");
		System.out.println(temaFire == temaFire2 ? "Igual!!!" : "Diferente!!!");
	}
	
}
