import java.io.*;
import java.util.*;

public class TypeChecker {

	private static String[] variableType = { "boolean", "char", "int", "float",
			"double", "short", "String", "byte", "long" };
	private static List<String> mainFunction = new ArrayList<String>();
	private static List<String> varableNames = new ArrayList<String>();

	public static void main(String[] args) {
		ArrayList<String> code = new ArrayList<String>();

		try {
			code = readProgram("Program.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Class Declaration Check
		System.out.println("\nis class declarartion correct:"
				+ checkClassDeclaration(code));

		// MainCount
		System.out
				.println("\nis there only a single main with the correct declations: "
						+ checkMainCount(code));

		// Check Variable Declaration Names
		System.out.println("\nis there dublicate varable names: "
				+ checkDulicateNames(varableNames));
		
	

		// Checking boolean variables
		 System.out.println("\nChecking Boolean variables");
		 System.out.println("Do boolean variables have the right assignment: "
		 + checkBooleanVar(mainFunction));

		 //Checking String variables
		 System.out.println("\nChecking String variables");
		 System.out.println("Do String variables have the right assignment: "
		 + checkStringVar(mainFunction));

		// Checking char variables
		 System.out.println("\nChecking char variables");
		 System.out.println("Do char variables have the right assignment: "
		 + checkCharVar(mainFunction));
		
		 //Checking int variables
		 System.out.println("\nChecking int variables");
		 System.out.println("Do int variables have the right assignment: " +
		 checkIntVar(mainFunction));
		
		
		// Checking double variables
		 System.out.println("\nChecking double variables");
		 System.out.println("Do double variables have the right assignment: "
		 + checkDoubleVar(mainFunction));
		
		 //Checking float variables
		 System.out.println("\nChecking float variables");
		 System.out.println("Do float variables have the right assignment: " +
		 checkFloatVar(mainFunction));
		
		 //Checking short variables
		 System.out.println("\nChecking short variables");
		 System.out.println("Do short variables have the right assignment: " +
		 checkShortVar(mainFunction));
		
		 //Checking byte variables
		 System.out.println("\nChecking byte variables");
		 System.out.println("Do byte variables have the right assignment: " +
		 checkByteVar(mainFunction));
		
		 //Checking long variables
		 System.out.println("\nChecking long variables");
		 System.out.println("Do long variables have the right assignment: " +
		 checkLongVar(mainFunction));

		// Checking if statment
		 System.out.println("\nChecking if statment");
		 System.out.println("Does if statment have the right form: " +
		 checkIfStatment(mainFunction));

		// Checking while statment
		 System.out.println("\nChecking while statment");
		 System.out.println("Does while statment have the right form: " +
		 checkWhileStatment(mainFunction));

		// Checking while statment
		System.out.println("\nChecking for statment");
		System.out.println("Does for statment have the right form: "
				+ checkForStatment(mainFunction));

	}

	private static boolean checkForStatment(List<String> code) {
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("for")) {
				String result = line.replace("for", "");
				if (!result.contains("(")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains(")")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains("{}")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}

				result = result.replace("(", "");
				result = result.replace(")", "");
				result = result.replace("{", "");
				result = result.replace("}", "");

				String[] contents = result.split(";");

				if (contents.length < 3) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}

				// First :: Declaration
				String firstSlot = contents[0].replace("int", "");
				int indexOfEqual = firstSlot.indexOf("=");
				firstSlot = firstSlot.substring(indexOfEqual + 1,
						firstSlot.length());
				try{
					int intResult = Integer.parseInt(firstSlot.trim());
				}catch(Exception e){
					noError = false;
					System.out.println("--::Error --> " + result.trim());
				}

			}
		}
		return noError;
	}

	private static boolean checkWhileStatment(List<String> code) {
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("while")) {
				String result = line.replace("while", "");

				if (!result.contains("(")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains(")")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains("{}")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
			}
		}

		return noError;
	}

	private static boolean checkIfStatment(List<String> code) {
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("if")) {
				String result = line.replace("if", "");

				if (!result.contains("(")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains(")")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
				if (!result.contains("{}")) {
					noError = false;
					System.out.println("--::Error --> " + line);
					continue;
				}
			}
		}
		return noError;
	}

	private static boolean checkIntVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("int")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}

			}
		}

		for (String intger : variable) {
			int indexOfEqual = intger.indexOf("=");
			String result = intger.substring(indexOfEqual + 1, intger.length());
			result = result.replace(";", "");
			try {
				int intResult = Integer.parseInt(result.trim());
			} catch (Exception e) {
				noError = false;
				System.out.println("--::Error --> " + result.trim());
			}
		}

		for (String integervar : array) {
			if (!(integervar.contains("{") && integervar.contains("}"))) {
				System.out.println("--::Error --> " + integervar);
				noError = false;
			} else {
				int indexOfEqual = integervar.indexOf("=");
				String result = integervar.substring(indexOfEqual + 1,
						integervar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						int integer = Integer.parseInt(var.trim());
					} catch (Exception e) {
						noError = false;
						System.out.println("--::Error --> " + result.trim());
					}
				}
			}
		}

		return noError;
	}

	private static boolean checkCharVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("char")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {

					variable.add(line);
				}
			}
		}

		for (String qchar : variable) {
			int indexOfEqual = qchar.indexOf("=");
			String result = qchar.substring(indexOfEqual + 1, qchar.length());
			result = result.replace(";", "");
			if (!(result.trim().startsWith("'") && result.trim().endsWith("'") && result
					.trim().length() == 3)) {
				System.out.println("--::Error --> " + result.trim());
				noError = false;
			}

		}

		for (String charvar : array) {
			if (!(charvar.contains("{") && charvar.contains("}"))) {
				System.out.println("--::Error --> " + charvar);
				noError = false;
			} else {
				int indexOfEqual = charvar.indexOf("=");
				String result = charvar.substring(indexOfEqual + 1,
						charvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {

					if (!(var.trim().startsWith("'") && var.trim()
							.endsWith("'"))) {
						System.out.println("--::Error --> " + charvar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkDulicateNames(List<String> code) {
		boolean noError = false;
		for (int i = 0; i < code.size(); i++) {
			int indexOfname = i + 1;
			for (int j = indexOfname; j < code.size(); j++) {
				if (code.get(i).equals(code.get(j))) {
					noError = true;
					System.out.println(code.get(i).trim() + "  < -- > " + code.get(j).trim());
				}
			}
		}
		return noError;
	}

	private static boolean checkStringVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("String")) {

				if (line.contains("[]")) {
					// Checks Array
					array.add(line);
				} else {

					variable.add(line);
				}

			}
		}

		for (String string : variable) {
			int indexOfEqual = string.indexOf("=");
			String result = string.substring(indexOfEqual + 1, string.length());
			result = result.replace(";", "");
			if (!(result.trim().startsWith("\"") && result.trim()
					.endsWith("\""))) {
				System.out.println("--::Error --> " + string);
				noError = false;
			}
		}

		for (String stringvar : array) {
			if (!(stringvar.contains("{") && stringvar.contains("}"))) {
				System.out.println("--::Error --> " + stringvar);
				noError = false;
			} else {
				int indexOfEqual = stringvar.indexOf("=");
				String result = stringvar.substring(indexOfEqual + 1,
						stringvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					if (!(var.trim().startsWith("\"") && var.trim().endsWith(
							"\""))) {
						System.out.println("--::Error --> " + stringvar);
						noError = false;
					}
				}
			}

		}

		return noError;
	}

	private static boolean checkBooleanVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("boolean")) {
				variable.add(line);
			}
		}

		for (String bool : variable) {
			int indexOfEqual = bool.indexOf("=");
			String result = bool.substring(indexOfEqual + 1, bool.length());
			result = result.replace(";", "");
			if (!(result.trim().equals("true") || result.trim().equals("false"))) {

				System.out.println("--::Error --> " + bool);
				noError = false;
			}
		}

		return noError;
	}

	private static boolean checkFloatVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("float")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}
			}
		}

		for (String floatvar : variable) {
			int indexOfEqual = floatvar.indexOf("=");
			String result = floatvar.substring(indexOfEqual + 1,
					floatvar.length());// right-side assignment
			result = result.replace(";", "");// removes semi-colon
			try {
				float check = Float.parseFloat(result.trim());
			} catch (Exception e) {
				System.out.println("--::Error --> " + floatvar);
				noError = false;
			}
		}

		for (String floatvar : array) {
			if (!(floatvar.contains("{") && floatvar.contains("}"))) {
				System.out.println("--::Error --> " + floatvar);
				noError = false;
			} else {
				int indexOfEqual = floatvar.indexOf("=");
				String result = floatvar.substring(indexOfEqual + 1,
						floatvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						float check = Float.parseFloat(var.trim());
					} catch (Exception e) {
						System.out.println("--::Error --> " + floatvar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkDoubleVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("double")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}

			}
		}

		for (String doublevar : variable) {
			int indexOfEqual = doublevar.indexOf("=");
			String result = doublevar.substring(indexOfEqual + 1,
					doublevar.length());
			result = result.replace(";", "");
			try {
				double check = Double.parseDouble(result.trim());
			} catch (Exception e) {
				System.out.println("--::Error --> " + doublevar);
				noError = false;
			}
		}

		for (String doubelvar : array) {
			if (!(doubelvar.contains("{") && doubelvar.contains("}"))) {
				System.out.println("--::Error --> " + doubelvar);
				noError = false;
			} else {
				int indexOfEqual = doubelvar.indexOf("=");
				String result = doubelvar.substring(indexOfEqual + 1,
						doubelvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						double check = Double.parseDouble(var.trim());
					} catch (Exception e) {
						System.out.println("--::Error --> " + doubelvar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkShortVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("short")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}
			}
		}

		for (String shortvar : variable) {
			int indexOfEqual = shortvar.indexOf("=");
			String result = shortvar.substring(indexOfEqual + 1,
					shortvar.length());// right-side assignment
			result = result.replace(";", "");// removes semi-colon
			try {
				short check = Short.parseShort(result.trim());
			} catch (Exception e) {
				System.out.println("--::Error --> " + shortvar);
				noError = false;
			}
		}

		for (String shortvar : array) {
			if (!(shortvar.contains("{") && shortvar.contains("}"))) {
				System.out.println("--::Error --> " + shortvar);
				noError = false;
			} else {
				int indexOfEqual = shortvar.indexOf("=");
				String result = shortvar.substring(indexOfEqual + 1,
						shortvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						short check = Short.parseShort(var.trim());
					} catch (Exception e) {
						System.out.println("--::Error --> " + shortvar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkByteVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("byte")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}
			}
		}

		for (String bytevar : variable) {
			int indexOfEqual = bytevar.indexOf("=");
			String result = bytevar.substring(indexOfEqual + 1,
					bytevar.length());// right-side assignment
			result = result.replace(";", "");// removes semi-colon
			try {
				byte check = Byte.parseByte(result.trim());
			} catch (Exception e) {
				System.out.println("--::Error --> " + bytevar);
				noError = false;
			}
		}

		for (String bytevar : array) {
			if (!(bytevar.contains("{") && bytevar.contains("}"))) {
				System.out.println("--::Error --> " + bytevar);
				noError = false;
			} else {
				int indexOfEqual = bytevar.indexOf("=");
				String result = bytevar.substring(indexOfEqual + 1,
						bytevar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						byte check = Byte.parseByte(var.trim());
					} catch (Exception e) {
						System.out.println("--::Error --> " + bytevar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkLongVar(List<String> code) {
		List<String> variable = new ArrayList<String>();
		List<String> array = new ArrayList<String>();
		boolean noError = true;
		for (String line : code) {
			if (line.startsWith("long")) {
				if (line.contains("[]")) {
					// For Checks Array
					array.add(line);
				} else {
					// For Non Array
					variable.add(line);
				}
			}
		}

		for (String longvar : variable) {
			int indexOfEqual = longvar.indexOf("=");
			String result = longvar.substring(indexOfEqual + 1,
					longvar.length());// right-side assignment
			result = result.replace(";", "");// removes semi-colon
			try {
				long check = Long.parseLong(result.trim());
			} catch (Exception e) {
				System.out.println("--::Error --> " + longvar);
				noError = false;
			}
		}

		for (String longvar : array) {
			if (!(longvar.contains("{") && longvar.contains("}"))) {
				System.out.println("--::Error --> " + longvar);
				noError = false;
			} else {
				int indexOfEqual = longvar.indexOf("=");
				String result = longvar.substring(indexOfEqual + 1,
						longvar.length());
				result = result.replace(";", "");
				result = result.replace("{", "");
				result = result.replace("}", "");
				String[] varables = result.split(",");
				for (String var : varables) {
					try {
						long check = Long.parseLong(var.trim());
					} catch (Exception e) {
						System.out.println("--::Error --> " + longvar);
						noError = false;
					}
				}
			}
		}
		return noError;
	}

	private static boolean checkMainCount(ArrayList<String> code) {

		int mainCount = 0;
		for (String line : code) {
			if (line.contains("public static void main(String []args)")
					&& !(line.contains("\"")) && !(line.contains("//"))) {
				mainCount++;
			}
		}

		if (mainCount == 1) {
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

						for (String a : variableType) {
							if (mainLines.trim().startsWith(a)) {
								int indexOfType = mainLines.indexOf(a);
								int indexOfEqual = mainLines.indexOf("=");
								String result = mainLines.trim().substring(
										indexOfType + a.length(),
										indexOfEqual - 1);// left-side
															// assignment
								result = result.replace(";", "");// removes
								// semi-colon
								varableNames.add(result);
							}
						}
					}
					if (line.equals("}")) {
						run = false;
					}
				}
			}
		}
		in.close();

		return code;
	}

}
