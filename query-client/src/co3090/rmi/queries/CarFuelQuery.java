package co3090.rmi.queries;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co3090.rmi.model.CarFuel;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

public class CarFuelQuery extends AbstractQuery {

	//Stream<String> Carstream;
	//private static final long serialVersionUID = 1L;
    CarFuel carFuel;
    List<CarFuel> objectListPrice;
    List<CarFuel> objectListYear;


	public CarFuelQuery(String filename, CarFuel filter) {
		super(filename);
		carFuel = filter;
		System.out.println("this is debug" + carFuel.pumpPrice);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() throws RemoteException {
		// TODO Auto-generated method stub

		try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.ISO_8859_1))
        {
			objectListPrice = stream
			.map(line -> Arrays.asList(line.split(",")))
            .filter(lineList->
            			lineList.size()>1 &&
            			isNumeric(lineList.get(1)) &&
            			Double.parseDouble(lineList.get(1)) > carFuel.pumpPrice)  //Filter records where pump price is greater than 100
            .map(row -> new CarFuel(Double.parseDouble(row.get(1))))
            .collect(Collectors.toList());



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try (Stream<String> stream = Files.lines(Paths.get(filename),StandardCharsets.ISO_8859_1)) {
           objectListYear = stream
                    .map(line -> Arrays.asList(line.split(",")))
                    .filter(lineList->
                            lineList.size()>1 &&
                                    isValidDate(lineList.get(0)) &&
                                    lineList.get(0).contains(carFuel.year))  //Filter records where pump price is greater than 100
                    .map(row -> new CarFuel((row.get(0))))
                   .collect(Collectors.toList());



        }
        catch (IOException e) {
            e.printStackTrace();
        }

        printResults(objectListPrice);
        printResults(objectListYear);


        return objectListPrice;
	}
	public void printResults(List<?> carObjects) {
		try {
			carObjects.forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


}
