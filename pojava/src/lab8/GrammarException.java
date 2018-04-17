package lab8;

import java.util.List;

public class GrammarException extends Exception {
	int nMistakes;
	List<String> mistakes;
	String list;
	public GrammarException() {

	}
	public GrammarException(int nMistakes) {
		this.nMistakes = nMistakes;
	}
	public String toString(){
		return "You've made " + nMistakes + " mistakes";
		
	}
}
