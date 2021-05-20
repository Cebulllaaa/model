package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.zip.CRC32;

public class codeManager {
	private String filename;
	private final String startSequence ="01111110";
	private final String endSequence ="01111110";
	
	public codeManager(String filename) {
		this.filename = filename;
	}
	public void code() throws UnsupportedEncodingException, FileNotFoundException {
		String data = "";
		String sequence = "";
		int oneCounter =0;
		boolean countOne = false;
		File file = new File(filename);
		try {
		Scanner scan = new Scanner(file);
		data = scan.nextLine();
		scan.close();
		}
		catch(Exception e) {
			System.out.println("Nie udalo sie znalezc pliku");
			System.exit(0);
		}
		//Dodaje kod CRC
		CRC32 crc = new CRC32();
		crc.update(data.getBytes());
		String crcString = Long.toBinaryString(crc.getValue());
		//Jezeli liczba jest zbyt mala kod crc jest krotszy od 32 bitow co skutkuje problemami w dekodowaniu
		while(crcString.length() <32) {
			crcString = "0" +crcString;
		}
		data = data + crcString;
		System.out.println("CrcString " + crcString);
		//Sprawdzam czy w wiadomosci nie dochodzi do sytuacji gdzie pojawia sie 0 i 5 jedynek
		//Jezeli tak to po 5 jedynce dopisuje 0
		for(int i=0; i< data.length() ; i++) {
			sequence = sequence + data.charAt(i);
			if(!countOne &&sequence.length() >= 2 && sequence.substring(sequence.length()-2).equals(endSequence.subSequence(0, 2))) {
				countOne = true;
				sequence = sequence.substring(sequence.length()-2);
			}
			else if(!countOne && sequence.length() >=2) {
				if(sequence.charAt(sequence.length()-1)=='0') {
					sequence = "0";
				}
				else {
					sequence = "";
				}
			}
			else if(!countOne && sequence.length() == 2 && sequence.equals("00")) {
				sequence = sequence.substring(1);
			}
			if(countOne) {
				if(sequence.charAt(sequence.length()-1)== '1') {
					oneCounter = oneCounter +1;
				}
				else {
					countOne = false;
					oneCounter =0;
					if(sequence.charAt(sequence.length()-1)=='0') {
						sequence = "0";
					}
					else {
						sequence = "";
					}
				}
			}
			if(countOne && oneCounter ==5) {
				String partOne = data.substring(0, i+1) ;
				String partTwo = data.substring(i+1, data.length());
				data = partOne + "0" + partTwo;
				countOne = false;
				oneCounter =0;
				sequence ="";
			}
			
		}
	//	System.out.println("Data " + data);
		//Dodawanie znaku poczatku i konca do wiadomosci
		data = startSequence + data + endSequence;
		PrintWriter resultFile = new PrintWriter(filename.substring(0, filename.length()-4) + "Result" +".txt");
		resultFile.println(data);
		resultFile.close();
	}
	public void decode() throws FileNotFoundException {
		String data = "";
		String pack;
		boolean started = false;
		pack = "";
		//Odczyt z pliku
		File file = new File(filename.substring(0, filename.length()-4) + "Result" +".txt");
		try {
		Scanner scan = new Scanner(file);
		data = scan.nextLine();
		scan.close();
		}
		catch(Exception e) {
			System.out.println("Nie udalo sie znalezc pliku");
			System.exit(0);
		}
		PrintWriter resultFile = new PrintWriter(filename.substring(0, filename.length()-4) + "Message" +".txt");
		int iterator =0;
		String sequence ="";
		for(int i=0; i < data.length(); i++) {
			//Poszukiwanie sekwencji startu
			sequence = sequence + data.charAt(i);
			if(sequence.charAt(iterator) == startSequence.charAt(iterator) && !started) {
				iterator = iterator +1;
			}
			else if (!started) {
				if( iterator < sequence.length() && sequence.charAt(iterator) == startSequence.charAt(0) ) {
					sequence = sequence.substring(iterator);
					iterator =1;
				}
				else {
					iterator =0;
					sequence = "";
				}
			}
			//Po znalezieniu sekwencji startu
			if(iterator == startSequence.length() && !started) {
				sequence ="";
				iterator =0;
				started = !started;
				i= i+1;
				sequence = sequence + data.charAt(i);
			}
			//Pobieranie pakietu oraz poszukiwanie sekwencji konca
			if(started && sequence.charAt(iterator) == endSequence.charAt(iterator)) {
				pack = pack + data.charAt(i);
				iterator = iterator +1;
			}
			else if (started) {
				if( iterator < sequence.length() && sequence.charAt(iterator) == startSequence.charAt(0) ) {
					sequence = sequence.substring(iterator);
					iterator =1;
				}
				else {
					sequence = "";
					iterator =0;
				}
				pack = pack + data.charAt(i);
			}
			//Po znalezieniu sekwencji konca
			if(iterator == endSequence.length() && started) {
				pack = pack.substring(0, pack.length()-iterator);
				//System.out.println("pack " + pack);
				for(int j=0; j<pack.length()-5;j++) {
					if(pack.substring(j, j+5).equals("11111") && pack.charAt(j+5) =='0') {
						String firstPart = pack.substring(0, j+5);
						String secondPart;
						if(j+6 <pack.length()) {
							secondPart = pack.substring(j+6);
						}
						else {
							secondPart = "";
						}
						pack = firstPart + secondPart;
						j = j+4;
					}
				}
			//	System.out.println("p2ck " + pack);
				String packCrc = pack.substring(pack.length()-32, pack.length());
			//	System.out.println("pack Crc "+ packCrc);
				//Sprawdzam czy crc zapisane w pakiecie jest rowne wyliczonemu teraz z pakeitu
				pack = pack.substring(0,pack.length()-32);
			//	System.out.println(pack);
				CRC32 crc = new CRC32();
				crc.update(pack.getBytes());
				String codeCrc = Long.toBinaryString(crc.getValue());
				while(codeCrc.length() < 32) {
					codeCrc = "0" + codeCrc;
				}
				System.out.println("Code CrC " + codeCrc);
				System.out.println("Pack Crc "  + packCrc);
				if(packCrc.equals(codeCrc)) {
					System.out.println("Sprawdzanie CRC nie zwrocilo  bledow");
				}
				else {
					System.out.println("Sprawdzanie CRC zwrocilo blad");
					System.exit(0);
				}
				resultFile.println(pack);
				sequence = "";
				iterator =0;
				pack = "";
				started = !started;
			}
			
		}
		resultFile.close();
	}
}
