public class FinancialForecastingTest {
    public static void main(String[] args) {
        double presentValue = 1000.0;
        double growthRate = 0.05;
        int periods = 10;

        double futureValue = FinancialForecasting.calculateFutureValue(presentValue, growthRate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}
