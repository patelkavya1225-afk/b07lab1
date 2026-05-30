import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Polynomial {
	private double[] coefficients;
	private int[] exponents;
	
	public Polynomial() {
		coefficients = new double[] {0};
		exponents = new int[] {0};
	}
	
	public Polynomial(double[] coeffs, int[] exps) {
		coefficients = new double[coeffs.length];
		exponents = new int[exps.length];
		for (int i = 0; i < coeffs.length; i++) {
			coefficients[i] = coeffs[i];
			exponents[i] = exps[i];
		}
	}

	public Polynomial add(Polynomial other) {
		double[] result = new double[10];
		int[] exps = new int[10];
		for (int i = 0; i < 10; i++) {
			exps[i] = i;
		}
		
		for (int i = 0; i < this.coefficients.length; i++) {
			result[this.exponents[i]] += this.coefficients[i];
		}
		for (int i = 0; i < other.coefficients.length; i++) {
			result[other.exponents[i]] += other.coefficients[i];
		}
		
		return new Polynomial(result, exps);
	}
		
	
	public double evaluate(double x) {
		double result = 0;
		double power = 1;
		for (int i = 0; i < this.coefficients.length; i++){
			result += coefficients[i] * power;
			power *= x;
		}
		return result;
	}
	
	public boolean hasRoot (double x) {
		return evaluate(x) == 0;
	}
	
	public Polynomial multiply(Polynomial other) {
		int highest_exp = (this.coefficients.length - 1) + (other.coefficients.length - 1);
		double[] temp = new double[highest_exp + 1];
		
		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < other.coefficients.length; j++) {
				temp[this.exponents[i] + other.exponents[j]] += this.coefficients[i] * other.coefficients[j];
			}
		}
		
		int counter = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != 0) {
				counter++;
			}
		}
		if (counter == 0) {
			return new Polynomial();
		}
		
		double[] resultCoeff = new double[counter];
		int[] resultExp = new int[counter];
		int index = 0;
		for (int i = 0; i < temp.length; i++){
			if (temp[i] != 0) {
				resultCoeff[index] = temp[i];
				resultExp[index] = i;
				index++;
			}
		}
		return new Polynomial(resultCoeff, resultExp);
	}
	
	public Polynomial(File file) throws Exception {
		Scanner scanner = new Scanner(file);
		String line = scanner.nextLine();
		scanner.close();
		
		if (line.charAt(0) != '-') {
			line = "+" + line;
		}
		
		int counter = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '+' || line.charAt(i) == '-') {
				counter++;
			}
		}
		
		coefficients = new double[counter];
		exponents = new int[counter];
		
		int index = 0;
		int i = 0;
		while(i < line.length()) {
			double sign = 1;
			if (line.charAt(i) == '-') {
				sign = -1;
			}
			i++;
			
			String term = "";
			while (i < line.length() && line.charAt(i) != '+' && line.charAt(i) != '-') {
				term += line.charAt(i);
				i++;
			}
			
			if (term.contains("x")) {
				String[] parts = term.split("x");
				if (parts[0].equals("")) {
					coefficients[index] = sign;
				} else {
					coefficients[index] = sign * Double.parseDouble(parts[0]);
				}
				if (parts.length == 1) {
					exponents[index] = 1;
				} else {
					exponents[index] = Integer.parseInt(parts[1]);
				}
			} else {
					coefficients[index] = sign * Double.parseDouble(term);
					exponents[index] = 0;
				}
				index++;
			}
		}
	/*
		public void saveToFile(String f_name) {
			FileWriter writer = new FileWriter(f_name);
			String result = "";
			
			for (int i = 0; i < coefficients.length; i++) {
				if (coeffieicnts[i] == 0) {
					continue;
				}
				if (exponents[i] == 0) {
	
	UNSURE HOW TO FINISH
	*/	
}