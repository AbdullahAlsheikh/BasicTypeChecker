import java.io.*;
import java.util.*;

public class TypeChecker {

	private static String[] variableType = { "boolean", "char", "int", "float",
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
		System.out.println("\nChecking Boolean variables");
		System.out.println("Do boolean variables have the right assignment: "  + checkBooleanVar(mainFunction));
		
		//Checking String variables
		System.out.println("\nChecking String variables");
		System.out.println("Do String variables have the right assignment: " + checkStringVar(mainFunction));
		
		//Checking String variables
		System.out.println("\nChecking char variables");
		System.out.println("Do char variables have the right assignment: " + checkCharVar(mainFunction));

		//Checking String variables
		System.out.println("\nChecking int variables");
		System.out.println("Do int variables have the right assignment: " + checkIntVar(mainFunction));
		
		//TODO more data types
		//Checking double variables
		System.out.println("\nChecking double variables");
		System.out.println("Do double variables have the right assignment: " + checkDoubleVar(mainFunction));

		//Checking float variables
		System.out.println("\nChecking float variables");
		System.out.println("Do float variables have the right assignment: " + checkFloatVar(mainFunction));
		
		//Checking short variables
		System.out.println("\nChecking short variables");
		System.out.println("Do short variables have the right assignment: " + checkShortVar(mainFunction));
		
		//Checking byte variables
		System.out.println("\nChecking float variables");
		System.out.println("Do byte variables have the right assignment: " + checkByteVar(mainFunction));
		
		//Checking byte variables
		System.out.println("\nChecking long variables");
		System.out.println("Do long variables have the right assignment: " + checkLongVar(mainFunction));


	}
	
	private static boolean checkIntVar(List<String> code){
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("int")){
				variable.add(line);
			}
		}
		
		for(String intger : variable){
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
		
		return noError;
	}
	
	private static boolean checkCharVar(List<String> code){
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("char")){
				variable.add(line);
			}
		}
		
		for(String qchar : variable){
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
	
	private static boolean checkStringVar(List<String> code){
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("String")){
				variable.add(line);
			}
		}
		
		
		for(String string : variable){
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
	
	private static boolean checkBooleanVar(List<String> code){
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("boolean")){
				variable.add(line);
			}
		}
		
		for(String bool : variable){
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
	
	private static boolean checkFloatVar(List<String> code) { 
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("float")){
				variable.add(line);
			}
		}
		
		for (String floatvar : variable) { 
			int indexOfEqual = floatvar.indexOf("=");
			String result = floatvar.substring(indexOfEqual+ 1, floatvar.length());// right-side assignment
			result = result.replace(";", "");//removes semi-colon
			try { 
				float check = Float.parseFloat(result.trim());
			} catch (Exception e) { 
				System.out.println("--::Error --> " + floatvar);
				noError = false;
			}
		}
		return noError;
	}
	
	private static boolean checkDoubleVar(List<String> code) { 
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("double")){
				variable.add(line);
			}
		}
		
		for (String doublevar : variable) { 
			int indexOfEqual = doublevar.indexOf("=");
			String result = doublevar.substring(indexOfEqual+ 1, doublevar.length());
			result = result.replace(";", "");
			try { 
				double check = Double.parseDouble(result.trim());
			} catch (Exception e) { 
				System.out.println("--::Error --> " + doublevar);
				noError = false;
			}
		}
		return noError;
	}
	
	private static boolean checkShortVar(List<String> code) { 
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("short")){
				variable.add(line);
			}
		}
		
		for (String shortvar : variable) { 
			int indexOfEqual = shortvar.indexOf("=");
			String result = shortvar.substring(indexOfEqual+ 1, shortvar.length());// right-side assignment
			result = result.replace(";", "");//removes semi-colon
			try { 
				short check = Short.parseShort(result.trim());
			} catch (Exception e) { 
				System.out.println("--::Error --> " + shortvar);
				noError = false;
			}
		}
		return noError;
	}
	
	private static boolean checkByteVar(List<String> code) { 
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("byte")){
				variable.add(line);
			}
		}
		
		for (String bytevar : variable) { 
			int indexOfEqual = bytevar.indexOf("=");
			String result = bytevar.substring(indexOfEqual+ 1, bytevar.length());// right-side assignment
			result = result.replace(";", "");//removes semi-colon
			try { 
				byte check = Byte.parseByte(result.trim());
			} catch (Exception e) { 
				System.out.println("--::Error --> " + bytevar);
				noError = false;
			}
		}
		return noError;
	}
	
	private static boolean checkLongVar(List<String> code) { 
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for(String line : code){
			if(line.startsWith("long")){
				variable.add(line);
			}
		}
		
		for (String longvar : variable) { 
			int indexOfEqual = longvar.indexOf("=");
			String result = longvar.substring(indexOfEqual+ 1, longvar.length());// right-side assignment
			result = result.replace(";", "");//removes semi-colon
			try { 
				long check = Long.parseLong(result.trim());
			} catch (Exception e) { 
				System.out.println("--::Error --> " + longvar);
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