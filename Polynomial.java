public class Polynomial {
	public double[] coefficients;
	
	public Polynomial() {
		coefficients = new double[] {0};
	}
	
	public Polynomial(double[] coeff_A) {
		coefficients = new double[coeff_A.length];
		for (int i = 0; i < coeff_A.length; i++) {
			coefficients[i] = coeff_A[i];
		}
	}

	public Polynomial add(Polynomial other) {
		int len_one = this.coefficients.length;
		int len_two = other.coefficients.length;
		int max_len;
		if (len_one >= len_two) {
			max_len = len_one;
		}
		else {
			max_len = len_two;
		}
		
		double[] result = new double[max_len];
		for (int i = 0; i < this.coefficients.length; i++) {
			result[i] += this.coefficients[i];
		}
		for (int i = 0; i < other.coefficients.length; i++) {
			result[i] += other.coefficients[i];
		}
		return new Polynomial(result);
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
}