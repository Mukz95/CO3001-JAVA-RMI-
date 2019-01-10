package co3090.rmi.model;



import java.io.Serializable;

public class HousePrice implements Serializable {

	public String year;
	public Double salePrice;


	public HousePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public HousePrice(String year) {
	    this.year = year;
    }
	public HousePrice(String year, Double salePrice) {
		this.year = year;
		this.salePrice = salePrice;
	}


	public Double getPrice() {
		return salePrice;
	}

	public void setPrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	public String toString() {

	    if (salePrice == null){

            return "The house years are " + year;

        }
        else{
	        return "The house price is" + salePrice;

        }

	}

}
