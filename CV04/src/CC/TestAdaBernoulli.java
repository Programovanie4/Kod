package CC;

import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Test;

import LISTTestScoring.*;

public class TestAdaBernoulli {
	private static LISTTestScoring scoring = null;

	@BeforeClass
	public static void initScoring() {
		scoring = new LISTTestScoring();
		scoring.setScore("lang:common_list_test_scoring_name", 0, 100);
	}


	@Test
	public void testBernoulliNumbers() {
		for (int i = 0; i < 26; i++) {
			assertEquals("test BernoulliNumbers", TestAdaBernoulli.mybernouilli(i), Ada.bernoulli(i), 0.001);
		}
		TestAdaBernoulli.scoring.updateScore("lang:common_list_test_scoring_name", 75);
	}
	
    public static double mybernouilli(int nn) {
    	int N = nn+1;
        // precompute binomial coefficients
        BigInteger[][] binomial = new BigInteger[N+1][N+1];
        for (int n = 1; n <= N; n++) binomial[0][n] = BigInteger.ZERO;
        for (int n = 0; n <= N; n++) binomial[n][0] = BigInteger.ONE;

        // bottom-up dynamic programming
        for (int n = 1; n <= N; n++)
            for (int k = 1; k <= N; k++)
                binomial[n][k] = binomial[n-1][k-1].add(binomial[n-1][k]);


        // now compute Bernoulli numbers
        BigRational[] bernoulli = new BigRational[N+1];
        bernoulli[0] = new BigRational(1, 1);
        bernoulli[1] = new BigRational(-1, 2);
        for (int k = 2; k < N; k++) {
            bernoulli[k] = new BigRational(0, 1);
            for (int i = 0; i < k; i++) {
                BigRational coef = new BigRational(binomial[k + 1][k + 1 - i], BigInteger.ONE);
                bernoulli[k] = bernoulli[k].minus(coef.times(bernoulli[i]));
            }
            bernoulli[k] = bernoulli[k].divides(new BigRational(k+1, 1));
        }
        return bernoulli[N-1].doubleValue();
    }

}
