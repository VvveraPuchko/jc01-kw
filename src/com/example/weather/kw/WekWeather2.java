package com.example.weather.kw;

public class WekWeather2 {

    public static void main(String[] args) {
        String[] dates = new String[] {"2025-05-01", "2025-05-02", "2025-05-03", "2025-05-04", "2025-05-05", "2025-05-06", "2025-05-07"};
        double[] morningTemp = new double[] {10, 13, 5, 6, 25, 13, 5};
        double[] afternoonTemp = new double[] {15, 16, 15, 18, 20, 22, 21};
        double[] eveningTemp = new double[] {12, 13, 14, 15, 17, 19, 9};

        double[] averageTemps = new double[7];

        int startIndex = 0;
        int endIndex = 0;
        int curLength = 1;
        boolean periodExists = false;
        int maxLength = 1;
        int[][] maxPeriods = new int[4][2]; 
        int periodsCount = 0;

        for (int i = 0; i < dates.length; i++) {
            double averageTemp = calculateAverageTemp(morningTemp[i], afternoonTemp[i], eveningTemp[i]);
            showAverageTemp(dates[i], averageTemp, i);
            averageTemps[i] = averageTemp;
        }

        for (int i = 1; i < averageTemps.length; i++) {
            if (averageTemps[i] > averageTemps[i - 1]) {
                if (curLength == 1) {
                    startIndex = i - 1; 
                }
                endIndex = i;
                curLength++;
                periodExists = true;
            } else {
                if (periodExists) {
                    if (curLength > maxLength) {
                        maxLength = curLength;
                        periodsCount = 0; 
                        maxPeriods[periodsCount][0] = startIndex;
                        maxPeriods[periodsCount][1] = endIndex;
                        periodsCount++;
                    } else if (curLength == maxLength) {
                        maxPeriods[periodsCount][0] = startIndex;
                        maxPeriods[periodsCount][1] = endIndex;
                        periodsCount++;
                    }
                    curLength = 1;
                }
            }
        }

        if (periodExists) {
            if (curLength > maxLength) {
                maxLength = curLength;
                periodsCount = 0;
                maxPeriods[periodsCount][0] = startIndex;
                maxPeriods[periodsCount][1] = endIndex;
                periodsCount++;
            } else if (curLength == maxLength) {
                maxPeriods[periodsCount][0] = startIndex;
                maxPeriods[periodsCount][1] = endIndex;
                periodsCount++;
            }
        }

        if (periodsCount == 0) {
            System.out.println("Температура на этой неделе не повышалась");
        } else {
            System.out.println("Самый длинный период повышения: " + maxLength + " дня");
            for (int i = 0; i < periodsCount; i++) {
                System.out.println("(с " + dates[maxPeriods[i][0]] + " по " + dates[maxPeriods[i][1]] + ")");
            }
        }
    }

	public static double calculateAverageTemp(double morningTemp, double afternoonTemp, double eveningTemp) {
		double averageTemp = (morningTemp + afternoonTemp + eveningTemp) / 3;
		return averageTemp;
	}
	
	public static void showAverageTemp (String date, double averageTemp, int index) {
		if(index == 0) {
			System.out.print("Средние температуры:\n[");
		} 
		
		if(index == 6) {
			System.out.printf("\"" + date + "\" → " + "%.1f" + "]\n", averageTemp);
		} else {
			System.out.printf("\"" + date + "\" → " + "%.1f" + ",\n", averageTemp);
		}		
	}
	
	
}
