package co3090.rmi.queries;

import co3090.rmi.model.CarFuel;
import co3090.rmi.model.HousePrice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HouseQuery extends AbstractQuery {

	//Stream<String> Carstream;
	//private static final long serialVersionUID = 1L;
    HousePrice housePrice;
    List<HousePrice> objectListYear;


	public HouseQuery(String filename, HousePrice filter) {
		super(filename);
		housePrice = filter;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute() throws RemoteException {
		// TODO Auto-generated method stub


        try (Stream<String> stream = Files.lines(Paths.get(filename))) {

           objectListYear =  stream
                    .map(line -> Arrays.asList(line.split(",")))
                    .filter(lineList->
                            lineList.get(2).contains(housePrice.year))
                    .map(row-> new HousePrice(row.get(2))).collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objectListYear;
	}
	public void printResults(List<?> houseObjects) {
		try {
			houseObjects.forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}
