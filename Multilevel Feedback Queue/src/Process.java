
public class Process {

	public static int ID=0; 
	public static int process=0;
	public static int systemTime=0;
	public static int arrivalTime=0;
	public static int burstLength=0;
	public static int waitTime=0, responseTime=0, turnAroundTime=0;

	public Process(int name, int arrivalTime, int completionTime) {

		Process.ID = name;
		Process.arrivalTime = arrivalTime;
		Process.burstLength = completionTime;

	}

	public Process() 
	{ 
		Process.ID=0;
		Process.burstLength=0;
		Process.arrivalTime=0;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		Process.arrivalTime = arrivalTime;
	}
	public int getBurstLength() {
		return burstLength;
	}
	public void setBurstLength(int burstLength) {
		Process.burstLength = burstLength;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		Process.waitTime = waitTime;
	}
	public int getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(int responseTime) {
		Process.responseTime = responseTime;
	}
	public int getTurnAroundTime() {
		return turnAroundTime;
	}
	public void setTurnAroundTime(int turnAroundTime) {
		Process.turnAroundTime = turnAroundTime;
	}

	public static void run(Process input) {

		int systemTime=0;
		LinkedQueue auxillaryQueue = new LinkedQueue();
		auxillaryQueue.enqueue(input);
		
		LinkedQueue auxillaryQueue1 = new LinkedQueue();
		auxillaryQueue1.enqueue(input);
		
		LinkedQueue auxillaryQueue2 = new LinkedQueue();
		auxillaryQueue2.enqueue(input);
		
		LinkedQueue auxillaryQueue3 = new LinkedQueue();
		auxillaryQueue3.enqueue(input);
		
		

		while(!auxillaryQueue.isEmpty()) {
			int j=(((Process) auxillaryQueue.first()).getArrivalTime());
			while(j>systemTime ){	systemTime++;				
			System.out.println("<system time\t"+ systemTime+">\t"+ "\t CPU is idle ..");	
			waitTime++;}
			if(j<=systemTime) {
				burstLength+=((Process)auxillaryQueue.first()).getBurstLength();
				arrivalTime+=((Process)auxillaryQueue.first()).getArrivalTime();
				auxillaryQueue1.enqueue(auxillaryQueue.dequeue());	}
			while(!auxillaryQueue1.isEmpty()) {	int n=0;				
			while(n<=((Process)auxillaryQueue1.first()).getBurstLength()&&n<4) {
				System.out.println("<system time\t"+ ++systemTime+">"+ "\t process "+((Process) auxillaryQueue1.first()).getID()+ " is runnig");	 n++;

				//if the process in head of Queue A has CPU burst of 4 or less than just remove 
				//if another process has arrived before the head process has reached than add it 
				if(n==((Process)auxillaryQueue1.first()).getBurstLength()) {
					System.out.println("<system time\t"+ systemTime+">"+ "\t process "+((Process) auxillaryQueue1.first()).getID()+ " is Finished...");
					((Process)auxillaryQueue1.first()).setTurnAroundTime(systemTime-((Process)auxillaryQueue1.first()).getArrivalTime());

				}
				responseTime+=n;
			}
			if(!auxillaryQueue1.isEmpty()){
				((Process)auxillaryQueue1.first()).setBurstLength(((Process)auxillaryQueue1.first()).getBurstLength()-n);
				if(((Process)auxillaryQueue1.first()).getBurstLength()<=0){
					arrivalTime+=((Process)auxillaryQueue1.first()).getTurnAroundTime();	
					process++;
					auxillaryQueue1.dequeue();}else {	
						auxillaryQueue2.enqueue(auxillaryQueue1.dequeue());	
					}
				break;
			}
			}
		}

		while(!auxillaryQueue2.isEmpty()) {	int n=0;				
		while(n<((Process)auxillaryQueue2.first()).getBurstLength()&&n<8) {
			System.out.println("<system time\t"+ ++systemTime+">"+ "\t process "+((Process) auxillaryQueue2.first()).getID()+ " is runnig");	 n++;
			if(n==((Process)auxillaryQueue2.first()).getBurstLength()) {
				System.out.println("<system time\t"+ systemTime+">"+ "\t process "+((Process) auxillaryQueue2.first()).getID()+ " is Finished...");
				((Process)auxillaryQueue2.first()).setTurnAroundTime(systemTime-((Process)auxillaryQueue2.first()).getArrivalTime());

			}
		}
		if(!auxillaryQueue2.isEmpty()){
			((Process)auxillaryQueue2.first()).setBurstLength(((Process)auxillaryQueue2.first()).getBurstLength()-n);
			if(((Process)auxillaryQueue2.first()).getBurstLength()<=0){
				arrivalTime+=((Process)auxillaryQueue2.first()).getTurnAroundTime();	
				process++;
				auxillaryQueue2.dequeue();}
			else {	
				auxillaryQueue3.enqueue(auxillaryQueue2.dequeue());	

			}
		}	
		}
		while(!auxillaryQueue3.isEmpty()&&((Process)auxillaryQueue3.first()).getBurstLength()!=0) {
			System.out.println("<system time\t"+ ++systemTime+">"+ "\t process "+((Process) auxillaryQueue3.first()).getID()+ " is runnig");	 
			((Process)auxillaryQueue3.first()).setBurstLength(((Process)auxillaryQueue3.first()).getBurstLength()-1);

			if(((Process)auxillaryQueue3.first()).getBurstLength()==0) {
				System.out.println("<system time\t"+ systemTime+">"+ "\t process "+((Process) auxillaryQueue3.first()).getID()+ " is Finished...");
				((Process)auxillaryQueue3.first()).setTurnAroundTime(systemTime-((Process)auxillaryQueue3.first()).getArrivalTime());
				arrivalTime+=((Process)auxillaryQueue3.first()).getTurnAroundTime();	
				process++;
				auxillaryQueue3.dequeue();
			}
			//System.out.println(TAT/p);
		}

		//trun time= time of arrival until finish including the wait time in B and C queue
		//idal
		//cpu usage...s-idl/s*100
		System.out.println("============================================================");

		System.out.println("CPU usage :              "+((systemTime-waitTime)/systemTime)*100+"%");

		System.out.println("Average wait time:       "+1.0*(arrivalTime-burstLength)/process);

		System.out.println("Average response time:   "+(1.0*(responseTime-arrivalTime)/process));

		System.out.println("Average turnaround time: "+1.0*arrivalTime/process);

	}



}
