package zad2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class simulation {
	private int time;
	public String[] channel;
	public int simTime =0;
	public boolean end = false;
	public simulation(int time) {
		this.time = time;
	}
	public void start() throws FileNotFoundException {
		channel = new String[100];
		Station stationA = new Station(0,"A","right");
		Station stationB = new Station(99,"B","left");
		for(int i=0; i< 100; i++) {
			channel[i]="-";
		}
		//Watek odliczajacy czas symulacji
		Thread timer = new Thread(() ->{
			while(simTime <time) {
				try {
					Thread.sleep(1000);
					simTime = simTime+1;
				}
				catch(InterruptedException e) {
					System.out.println(e);
					}
				}
			end = true;
		});
		stationA.propagate.start();
		stationB.propagate.start();
		timer.start();
		
	}
	
	public class Station {
		public String name;
		public int position;
		public boolean collision;
		public  String firstDirection;
		public String direction;
		public boolean transsmiting;
		public int collisions=0;
		public Station(int position, String name, String direction) {
			this.position = position;
			this.name = name;
			collision = false;
			this.direction = direction;
			this.firstDirection = direction;
			transsmiting = false;
		}
		public Thread propagate = new Thread(() -> {
			PrintWriter result;
			try {
				 result = new PrintWriter("result"+name+".txt");
			int head = position;
			int propagateCounter =0;
			//Watek odpowiedzialny za propagacje sygnalu wykonuje sie dopoki nie doszlo do konca symulacji
			while(!end) {
			
			int collisionHead = position;	
			String collisionDirection = direction;
			/* petla propaguje sygnal 
			przez czas potrzebny do podwojnego przejscia przez lacze 
			*/
			while( propagateCounter < 200 ) {
					if(channel[position].equals("-")) {
						transsmiting = true;
					}
					/*Dochodzi do kolizji kiedy na stacji pojawiaja sie inne informacje niz
					 * "-" i nazwa stacji (czyli tez kiedy sygnal z A dotrze do B i wroci ) => nastepuje kolizja z B 
					 */
					if(!channel[position].equals("-") && !channel[position].equals(name) && !collision && transsmiting){
						collision = true;
						collisions = collisions+1;
					}
					if(transsmiting && channel[head].equals("-")) {
						channel[head]=name;
					}
					else if (transsmiting){
						channel[head] = channel[head] +name;
					}
					if(transsmiting && direction.equals("right")) {
						head = head+1;
						//Jezeli sygnal dotarl do prawego konca lacza
						if(head ==100) {
							head=99;
							direction = "left";
						}
					}
					else if(transsmiting && direction.equals("left")) {
						head= head-1;
						//Jezeli sygnal dotarl do lewego konca lacza
						if(head ==-1) {
							head=0;
							direction = "right";
						}
					}
					//Jezeli doszlo do kolizji nastepuje zaprzestanie nadawania sygnalu
					//Jednak juz raz nadany sygnal idzie dalej 
					if(collision) {
						//Zmienna okreslajaca czy nadawany sygnal juz wraca
						boolean back =false;
						if(channel[collisionHead].contains(name+name)) {
							back = true;
						}
						channel[collisionHead]= channel[collisionHead].replaceAll(name, "");
						if(back) {
							back = false;
							channel[collisionHead] = channel[collisionHead] +name;
						}
						if(channel[collisionHead].isEmpty()) {
							channel[collisionHead] ="-";
						}
						if(collisionDirection.equals("right")) {
							collisionHead = collisionHead+1;
							//Jezeli sygnal dotarl do prawego konca lacza
							if(collisionHead ==100) {
								collisionHead=99;
								collisionDirection = "left";
								//Jezeli przerwa w transmisji dotarla do konca warunek przestaje sie wykonywac
								if(collisionDirection.equals(firstDirection)) {
									collision = false;
								}
							}
						}
						else if( collisionDirection.equals("left")) {
							collisionHead= collisionHead-1;
							//Jezeli sygnal dotarl do lewego konca lacza
							if(collisionHead ==-1) {
								collisionHead=0;
								collisionDirection = "right";
								//Jezeli przerwa w transmisji dotarla do konca warunek przestaje sie wykonywac
								if(collisionDirection.equals(firstDirection)) {
									collision = false;
								}
							}
						}
					}
					propagateCounter = propagateCounter +1;
					result.print(simTime + " ");
					for(int i=0; i< 100; i++) {
						result.print(channel[i] +" ");
					}
					result.println();
					Thread.yield();
					
				}
				//Po propagacji sygnalu parametry masyzny wracaja do poczatku oraz czyszcze kanal gdyby cos w nim zostalo
				transsmiting = false;
				direction = firstDirection;
				collision = false;
				head = position;
				propagateCounter=0;
				for(int i=0; i< 100; i++) {
					channel[i] = channel[i].replaceAll(name, "");
					if(channel[i].isEmpty()) {
						channel[i]="-";
					}
				}
				//Jezeli pojawily sie kolizje powstaja szczeliny i stacja wybiera 
				//Symuluje to poprzez kazanie stacji czekac przez okreslona liczbe milisekund
				if(collisions >0) {
					if(collisions > 16) {
						System.out.println("Doszlo do ponad 16 kolizji");
						System.exit(0);
					}
					Random rd = new Random();
					int slot = rd.nextInt(collisions+1);
					try {
						Thread.sleep(slot *100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					Thread.yield();
				}
			
			}
			result.close();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		});
	}
	
}
