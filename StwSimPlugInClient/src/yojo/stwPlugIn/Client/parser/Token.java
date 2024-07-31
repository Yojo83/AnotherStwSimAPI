package yojo.stwPlugIn.Client.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * used for the lexical analysis to sort a raw xml message into tokens
 */
public class Token {

	/**
	 * does the lexical analysis for a given xml line
	 * @param line
	 * @return
	 */
	public static Token[] toTokenArray(String line) {
		List<Token> tokens = new ArrayList<>();
		boolean isString = false;
		StringBuilder strBuilder = new StringBuilder();
		
		for(char c : line.toCharArray()) {
			if(c == '"' || c == '\'') {
				if(isString) {
					tokens.add(new Token(strBuilder.toString()));
					strBuilder = new StringBuilder();
				}
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
				break;
			default:
				strBuilder.append(c);
				break;
			}
		}
		return tokens.toArray(new Token[tokens.size()]);
	}

	/**
	 * a token representing < for xml messages
	 */
	public static final Token START = new Token('<');
	/**
	 * a token representing > for xml messages
	 */
	public static final Token END = new Token('>');
	/**
	 * a token representing / for xml messages
	 */
	public static final Token SLASH = new Token('/');
	/**
	 * a token representing = for xml messages
	 */
	public static final Token EQUAL = new Token('=');
	
	
	/**
	 * the value of a string token
	 * null if this is not a string token
	 */
	public final String value;
	/**
	 * a char represented by the token
	 * ' ' if this is a string token
	 */
	public final char typeName;
	
	/**
	 * creates a new not string token
	 * @param type used for toString()
	 */
	private Token(char type) {
		value = null;
		typeName = type;
	}
	
	/**
	 * creates a new string token
	 * @param value the string represented by this token
	 */
	private Token(String value) {
		this.value = value;
		this.typeName = ' ';
	}
	
	/**
	 * returns the string value if its a string token
	 * and the char represented by this token otherwise
	 */
	@Override
	public String toString() {
		return value == null ? String.valueOf(typeName) : value;
	}
	
	
}
