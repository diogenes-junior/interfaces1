package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String carModel = input.nextLine();
		Vehicle vehicle = new Vehicle(carModel);
		
		System.out.print("Pickup (dd/MM/yyyy hh:mm): ");
		Date dateStart = sdf.parse(input.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:mm): ");
		Date dateReturn = sdf.parse(input.nextLine());
		CarRental carRental = new CarRental(dateStart, dateReturn, vehicle);		
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = input.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = input.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(carRental);

		System.out.println("\nINVOICE");
		System.out.println("Basic Payment: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Tax: "+String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f", carRental.getInvoice().getTotalPayment()));
		
		input.close();
		
	}

}
