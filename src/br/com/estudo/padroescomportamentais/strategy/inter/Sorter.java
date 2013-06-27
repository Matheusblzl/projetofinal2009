package br.com.estudo.padroescomportamentais.strategy.inter;

import java.util.List;

public interface Sorter {
	<T extends Comparable <? super T>> List <T> sort (List <T> list );
}
