package sg.edu.nus.iss.ssfws13redo;

import java.io.File;
import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Ssfws13redoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Ssfws13redoApplication.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");

		if(opsVal != null) {
			//create dir
			File file = new File(opsVal.get(0));
			file.mkdirs();
		} else {
			//1 means unsuccessful termination
			//0 means successful termination
			System.out.println("no dataDir provided");
			System.exit(1);

		}
		app.run(args);
		// SpringApplication.run(Ssfworkshop13redoApplication.class, args);

	}
	// public static void main(String[] args) {

	// 	SpringApplication app = new SpringApplication(Ssfws13redoApplication.class);

	// 	DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

	// 	List<String> opsVal = appArgs.getOptionValues("dataDir");

	// 	System.out.println(opsVal);


	// 	if(opsVal != null){
	// 		//create dir
	// 		Utility.createDir(opsVal.get(0));
	// 	}else{
	// 		//terminate program
	// 		System.out.println("no data dir provided..");
	// 		System.exit(1);
	// 	}
	// 	app.run(args);
	// 	//SpringApplication.run(Day13DemoApplication.class, args);
	// }

}	