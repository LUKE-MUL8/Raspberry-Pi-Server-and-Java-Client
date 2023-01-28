package greater;

import java.util.ArrayList;

public class DataEngine {
	private String data = "|11|-9|5|45|46|-9|46|19.6";
	private String[] dataArray;
	
	public ArrayList<Double> Array = new ArrayList<Double>();
	public double max= 0;
	public double min = 0;
	public double avg = 0;
	
	DataEngine(String data){
		this.data = data;
		run();
	}
	
	private void run() {
		//Separate the string into small parts using | and extract values.
		this.dataArray = data.split("\\|");
		
		//Go trough an loop to get just the numbers for temperatures and humidity
		for(int x=1; x<this.dataArray.length;x++) {
			this.Array.add( Double.parseDouble(dataArray[x]) );
		}
		
		System.out.print("Data recorded: ");
		System.out.println(this.Array.toString());
		this.min = this.Array.get(10);
		this.max = this.Array.get(11);
		this.avg = this.Array.get(12);
		System.out.printf("Max temp: %.2f\nMin temp: %.2f\nAvg temp: %.2f\n",max,min,avg);
	}
	//hard coded values
	public static void main(String[] args) {
		DataEngine x = new DataEngine("|34.58|34.61|34.61|34.58|34.54|34.59|34.54|34.56|34.52|34.61|34.52|34.61|34.574");
	}
	
}
