package co3090.rmi.model;



import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.stream.Stream;

public class CarFuel implements Serializable {

	public String year;
	public Double pumpPrice;


    public CarFuel(String year) {
        this.year = year;
    }

    public CarFuel(Double pumpPrice) {
		this.pumpPrice = pumpPrice;
	}
	
	public CarFuel(String year, Double pumpPrice) {
		this.year = year;
		this.pumpPrice = pumpPrice;
	}
	
	public String toString() {

        if (year == null)
        {
            return "The price is " + pumpPrice;

        }else{

            return "The year is " + year;

        }

	}

}
