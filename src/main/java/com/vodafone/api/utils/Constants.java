package com.vodafone.api.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

	public static Map<Character, Integer> letterMonthMap = new HashMap<>() {
		{
			put('A', 1);
			put('B', 2);
			put('C', 3);
			put('D', 4);
			put('E', 5);
			put('H', 6);
			put('L', 7);
			put('M', 8);
			put('P', 9);
			put('R', 10);
			put('S', 11);
			put('T', 12);
		}
	};

	public static Map<Character, Integer> oddValueMap = new HashMap<>() {
		{
			put('0', 1);
			put('1', 0);
			put('2', 5);
			put('3', 7);
			put('4', 9);
			put('5', 13);
			put('6', 15);
			put('7', 17);
			put('8', 19);
			put('9', 21);
			put('A', 1);
			put('B', 0);
			put('C', 5);
			put('D', 7);
			put('E', 9);
			put('F', 13);
			put('G', 15);
			put('H', 17);
			put('I', 19);
			put('J', 21);
			put('K', 2);
			put('L', 4);
			put('M', 18);
			put('N', 20);
			put('O', 11);
			put('P', 3);
			put('Q', 6);
			put('R', 8);
			put('S', 12);
			put('T', 14);
			put('U', 16);
			put('V', 10);
			put('W', 22);
			put('X', 25);
			put('Y', 24);
			put('Z', 23);
		}
	};

	public static Map<Character, Integer> evenValueMap = new HashMap<>() {
		{
			put('0', 0);
			put('1', 1);
			put('2', 2);
			put('3', 3);
			put('4', 4);
			put('5', 5);
			put('6', 6);
			put('7', 7);
			put('8', 8);
			put('9', 9);
			put('A', 0);
			put('B', 1);
			put('C', 2);
			put('D', 3);
			put('E', 4);
			put('F', 5);
			put('G', 6);
			put('H', 7);
			put('I', 8);
			put('J', 9);
			put('K', 10);
			put('L', 11);
			put('M', 12);
			put('N', 13);
			put('O', 14);
			put('P', 15);
			put('Q', 16);
			put('R', 17);
			put('S', 18);
			put('T', 19);
			put('U', 20);
			put('V', 21);
			put('W', 22);
			put('X', 23);
			put('Y', 24);
			put('Z', 25);
		}
	};

	public static Map<Integer, Character> valueCheckDigitMap = new HashMap<>() {
		{
			put(0, 'A');
			put(1, 'B');
			put(2, 'C');
			put(3, 'D');
			put(4, 'E');
			put(5, 'F');
			put(6, 'G');
			put(7, 'H');
			put(8, 'I');
			put(9, 'J');
			put(10, 'K');
			put(11, 'L');
			put(12, 'M');
			put(13, 'N');
			put(14, 'O');
			put(15, 'P');
			put(16, 'Q');
			put(17, 'R');
			put(18, 'S');
			put(19, 'T');
			put(20, 'U');
			put(14, 'O');
			put(15, 'P');
			put(16, 'Q');
			put(17, 'R');
			put(18, 'S');
			put(19, 'T');
			put(20, 'U');
			put(21, 'V');
			put(22, 'W');
			put(23, 'X');
			put(24, 'Y');
			put(25, 'Z');
		}
	};

	public static Map<Integer, Character> valueOmocodiaLetterMap = new HashMap<>() {
		{
			put(0, 'L');
			put(1, 'M');
			put(2, 'N');
			put(3, 'P');
			put(4, 'Q');
			put(5, 'R');
			put(6, 'S');
			put(7, 'T');
			put(8, 'U');
			put(9, 'V');
		}
	};

	public static List<Integer> digitPositions = Arrays.asList(6, 7, 9, 10, 12, 13, 14);
	public static List<Character> vowels = Arrays.asList('A', 'E', 'I', 'O', 'U');
}
