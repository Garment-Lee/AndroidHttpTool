package com.lgf.androidhttptool.util;

public class Decrpy {
	private String str;
	private int[] keyBox;

	public Decrpy(String str, String key) {
		this.str = str;
		keyBox = new int[3];
		keyBox[0] = Integer.parseInt(key.substring(0, 2));
		keyBox[1] = Integer.parseInt(key.substring(2, 4));
		keyBox[2] = Integer.parseInt(key.substring(4, 6));
	}

	public String encrypt() {
		String enStr = "";
		int tempKey;
		for (int i = 0; i < str.length(); i++) {
			tempKey = keyBox[i % 3] % 24;
			enStr = enStr
					+ getChEncrpyt(getIndexEncrpyt(str.charAt(i)) + tempKey);
		}
		return enStr;
	}

	public String decrypt() {
		String deStr = "";
		int tempKey;
		for (int i = 0; i < str.length(); i++) {
			tempKey = keyBox[i % 3] % 24;
			deStr += getChDecrpyt(getIndexDecrpyt(str.charAt(i)) - tempKey);
		}
		return deStr;
	}

	private char getChEncrpyt(int i) {
		if (i >= 0 && i <= 25) {
			return chr(ord('a') + i);
		} else if (i >= 26 && i <= 35) {
			return chr(ord('0') + i - 26);
		} else if (i >= 36 && i <= 61) {
			return chr(ord('A') + i - 36);
		}
		return '0';
	}

	private char getChDecrpyt(int i) {
		if (i == 0) {
			return '-';
		} else if (i == 1) {
			return ';';
		} else if (i >= 2 && i <= 11) {
			return chr(i + ord('0') - 2);
		} else if (i >= 12 && i <= 37) {
			return chr(i + ord('A') - 12);
		}
		return '0';
	}

	private int getIndexDecrpyt(char c) {
		if (ord(c) >= ord('0') && ord(c) <= ord('9')) {
			return ord(c) + 26 - ord('0');
		} else if (ord(c) >= ord('A') && ord(c) <= ord('Z')) {
			return ord(c) + 36 - ord('A');
		} else {
			return ord(c) - ord('a');
		}
	}

	private int getIndexEncrpyt(char c) {
		if (c == '-') {
			return 0;
		}
		if (c == ';') {
			return 1;
		} else if (c >= '0' && c <= '9') {
			return c - '0' + 2;
		} else if (c >= 'A' && c <= 'Z') {
			return c - 'A' + 12;
		}
		return 0;
	}

	private char chr(int i) {
		char c = (char) i;
		return c;
	}

	private int ord(char c) {
		int a = c;
		return a;
	}
}