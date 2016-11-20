import java.io.*;
import java.util.*;

public class main {

	private static String[] varableType = { "boolean", "char", "int", "float",
											"double", "short", "String", "byte"};
	private static List<String> mainFunction = new ArrayList<String>();

	public static void main(String[] args) {
		ArrayList<String> code = new ArrayList<String>();

		try {
			code = readProgram("Program.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Class Declaration Check
		System.out.println("\nIs class declarartion correct:"
				+ checkClassDeclaration(code));

		// MainCount
		System.out
				.println("\nis there only a single main with the correct declations: "
						+ checkMainCount(code));
		
		
		//Checking boolean variables
		System.out.println("\nChecking Boolean Varables");
		System.out.println("Do boolean varables have the right assignment: "  + checkBooleanVar(mainFunction));
		
		//Checking String variables
		System.out.println("\nChecking String Varables");
		System.out.println("Do String varables have the right assignment: " + checkStringVar(mainFunction));
		
		//Checking String variables
		System.out.println("\nChecking char Varables");
		System.out.println("Do char varables have the right assignment: " + checkCharVar(mainFunction));

		//Checking String variables
		System.out.println("\nChecking int Varables");
		System.out.println("Do int varables have the right assignment: " + checkIntVar(mainFunction));


	}
	
	private static boolean checkIntVar(List<String> code){
		List<String> varable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("int")){
				varable.add(line);
			}
		}
		
		for(String intger : varable){
			int indexOfEqual = intger.indexOf("=");
			String result = intger.substring(indexOfEqual+ 1, intger.length());
			result = result.replace(";", "");			
			try{
				int intResult = Integer.parseInt(result.trim());
			}catch(Exception e){
				noError = false;
				System.out.println("--::Error --> " + result.trim());
			}
			
		}
		
		return false;
	}
	
	private static boolean checkCharVar(List<String> code){
		List<String> varable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("char")){
				varable.add(line);
			}
		}
		
		for(String qchar : varable){
			int indexOfEqual = qchar.indexOf("=");
			String result = qchar.substring(indexOfEqual+ 1, qchar.length());
			result = result.replace(";", "");
			if(!(result.trim().startsWith("'") && result.trim().endsWith("'") && result.trim().length() == 3)){
				System.out.println("--::Error --> " + result.trim());
				noError = false;
			}
			
		}
		
		
		return noError;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	
	private static boolean checkStringVar(List<String> code){
		List<String> varable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("String")){
				varable.add(line);
			}
		}
		
		
		for(String string : varable){
			int indexOfEqual = string.indexOf("=");
			String result = string.substring(indexOfEqual+ 1, string.length());
			result = result.replace(";", "");
			if(!(result.trim().startsWith("\"") && result.trim().endsWith("\""))){
				System.out.println("--::Error --> " + string);
				noError = false;
			}
		}
		
		return noError;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	private static boolean checkBooleanVar(List<String> code){
		List<String> varable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("boolean")){
				varable.add(line);
			}
		}
		
		for(String bool : varable){
			int indexOfEqual = bool.indexOf("=");
			String result = bool.substring(indexOfEqual+ 1, bool.length());
			result = result.replace(";", "");
			if(!(result.trim().equals("true") || result.trim().equals("false")))
			{

				System.out.println("--::Error --> " + bool);
				noError = false;
			}
		}
		
		return noError;
	}

	private static boolean checkMainCount(ArrayList<String> code) {
		
		int mainCount = 0;
		for(String line: code){
			if (line.contains("public static void main(String []args)")
					&& !(line.contains("\"")) && !(line.contains("//"))){
				mainCount ++;
			}
		}
		
		if(mainCount == 1) {
			return true;
		}
		return false;
	}

	private static boolean checkClassDeclaration(ArrayList<String> code) {
		if (code.get(0).contains("public class") && code.get(0).contains("{")) {
			return true;
		}
		return false;
	}

	public static ArrayList<String> readProgram(String fileName)
			throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;
		String mainLines;
		boolean run = true;
		ArrayList<String> code = new ArrayList<String>();
		while ((line = in.readLine()) != null) {
			code.add(line);
			if (line.contains("public static void main(String []args)")
					&& !(line.contains("\"")) && !(line.contains("//"))) {
				while ((mainLines = in.readLine()) != null && run == true) {
					if (mainLines.trim().length() > 1) {
						mainFunction.add(mainLines.trim());
					}
					if (mainLines.contains("}")) {
						run = false;
					}
				}
			}
		}
		in.close();

		return code;
	}

}
