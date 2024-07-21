package yojo.stwPlugIn.Client.parser;

import java.util.ArrayList;
import java.util.List;

public class Token {

	public static Token[] toTokenArray(String line) {
		List<Token> tokens = new ArrayList<>();
		boolean isString = false;
		StringBuilder strBuilder = new StringBuilder();
		
		for(char c : line.toCharArray()) {
			if(c == '"' || c == '\'') {
				isString = !isString;
				continue;
			}
			if(isString) {
				strBuilder.append(c);
				continue;
			}
			
			switch(c) {
			case '=':
				if(strBuilder.length() != 0) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder= new StringBuilder();
				}
				tokens.add(EQUAL);
				break;
			case '<':
				if(strBuilder.length() != 0) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder= new StringBuilder();
				}
				tokens.add(START);
				break;
			case '>':
				if(strBuilder.length() != 0) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder= new StringBuilder();
				}
				tokens.add(END);
				break;
			case '/':
				if(strBuilder.length() != 0) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder= new StringBuilder();
				}
				tokens.add(SLASH);
				break;
			case ' ':
				if(strBuilder.length() != 0) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder= new StringBuilder();
				}
			default:
				strBuilder.append(c);
				break;
			}
		}
		return tokens.toArray(new Token[tokens.size()]);
	}

	public static final Token START = new Token();
	public static final Token END = new Token();
	public static final Token SLASH = new Token();
	public static final Token EQUAL = new Token();
	
	public final String value;
	
	private Token() {
		value = null;
	}
	
	private Token(String value) {
		this.value = value;
	}
	
	
	
	
	
}
