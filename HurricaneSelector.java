
/**
 * Write a description of class HurricaneSelector here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
public class HurricaneSelector
{
    public static void main (String [] args) throws IOException
    {
       File hurricaneData = new File("hurricanedata.txt");
       Scanner hurricaneSelect = new Scanner(System.in);
       Scanner hurricaneRead = new Scanner(hurricaneData);
       BufferedReader br = new BufferedReader(new FileReader(hurricaneData));
       PrintWriter hurricaneDataS = new PrintWriter(new File("hurricanedatasummary.txt"));
       
       //Initialize
       String token = "";
       int index = 0;
       int totalPressure = 0;
       int totalCategories = 0;
       double totalWndSpd = 0;
       double max = Integer.MAX_VALUE;
       double min = Integer.MIN_VALUE;
        
       //Arrays
       String[] hurricaneYears = new String[157];
       String[] hurricaneMonths = new String[156];
       String[] hurricaneNames = new String[156];
       String[] hurricanePress = new String[156];
       //double
       String[] hurricaneWndSpd = new String[156];
       int[] categoryTotals = new int[5];
       int[] categories = new int[156];
       double[] average = new double[3];
       double[] maxValues = new double[3];
       double[] minValues = new double[3];
        
       System.out.println("Please indicate the starting year of the data you wish you view. You may choose any year between 1995 and 2015");
       int yearStart = hurricaneSelect.nextInt();
        
        while ((yearStart < 1995) || (yearStart > 2015))
        {
           System.out.println("The year you have entered is invalid, please choose a year between 1995 and 2015.");
          yearStart = hurricaneSelect.nextInt();
       }
        
       System.out.println("Please indicate the ending year of the data you wish you view.");
       int yearEnd = hurricaneSelect.nextInt();
        
       while ((yearEnd < yearStart) || (yearEnd > 2015))
       {
           System.out.println("The year you have entered is invalid, please choose a year higher than the starting year and less than 2016.");
           yearEnd = hurricaneSelect.nextInt();
       }
       
       //Reading hurricanedata.txt
       index = 0;
       String line;
       String[] arr = new String[5];
       while ((line = br.readLine())!= null)
       {
           arr = line.split("	");
           
           hurricaneYears[index] = arr[0];
           hurricaneMonths[index] = arr[1];
           hurricanePress[index] = arr[2];
           hurricaneWndSpd[index] = arr[3];
           hurricaneNames[index] = arr[4];
           
           index++;
       }
       hurricaneRead.close();
       br.close();
       
       
       
       //Calculating
       int i = 0;
       
       int hurricaneYearsI[] = new int[157];
       for (i = 0; i < 156; i++)
       {
          hurricaneYearsI[i] = Integer.parseInt(hurricaneYears[i]);
       }
       
       hurricaneYearsI[156] = hurricaneYearsI[155] + 1;
       
       i = 0;
       while (hurricaneYearsI[i] < yearStart)
       {
           i++;
       }
       
       int indexStart = i;
       
       i = 0;
       while (hurricaneYearsI[i] < (yearEnd + 1))
       {
           i++;
       }
       
       int indexEnd = i - 1;
       
       int indexLength = (indexEnd - indexStart) + 1;
       
       String hurricaneYearsL[] = new String[indexLength];
       for (i = indexStart; i <= indexEnd; i++)
       {
           hurricaneYearsL[i - indexStart] = hurricaneYears[i];
       }
       
       double hurricaneWndSpdL[] = new double[indexLength];
       for (i = indexStart; i <= indexEnd ; i++)
       {
          hurricaneWndSpdL[i - indexStart] = Double.parseDouble(hurricaneWndSpd[i]);
       }
       
       
       for (i = 0; i < indexLength; i++)
       {
            hurricaneWndSpdL[i] = (hurricaneWndSpdL[i])*1.15078;
       }
       
       String hurricaneNamesL[] = new String[indexLength];
       for (i = indexStart; i <= indexEnd; i++)
       {
           hurricaneNamesL[i - indexStart] = hurricaneNames[i];
       }
       
       int hurricanePressL[] = new int[indexLength];
       
       for (i = indexStart; i <= indexEnd; i++)
       {
           hurricanePressL[i - indexStart] = Integer.parseInt(hurricanePress[i]);
       }
       
       int categoriesL[] = new int[indexLength];
       for (i = indexStart; i <= indexEnd; i++)
       {
           categoriesL[i - indexStart] = categories[i];
       }
       
       index = 0;
       for (double mphWndSpd : hurricaneWndSpdL)
       {
            if ((mphWndSpd >= 74) && (mphWndSpd <= 95))
            {
                categoriesL[index] = 1;
                categoryTotals[0]++;
            }
            else if ((mphWndSpd >= 96) && (mphWndSpd <= 110))
            {
                categoriesL[index] = 2;
                categoryTotals[1]++;
            }
            else if ((mphWndSpd >= 111) && (mphWndSpd <= 129))
            {
                categoriesL[index] = 3;
                categoryTotals[2]++;
            }
            else if ((mphWndSpd >= 130) && (mphWndSpd <= 156))
            {
                categoriesL[index] = 4;
                categoryTotals[3]++;
            }
            else if (mphWndSpd >= 157)
            {
                categoriesL[index] = 5;
                categoryTotals[4]++;
            }
            index++;
        }
       
       
       max = Integer.MIN_VALUE;
       min = Integer.MAX_VALUE;
       for (int categoryValues : categoriesL)
       {
           totalCategories = totalCategories + categoryValues;
           if (categoryValues < min)
           {
               min = categoryValues;
           }
           else if (categoryValues > max)
           {
               max = categoryValues;
           }
       }
       average[0] = (int)(totalCategories/((double)(categoriesL.length)));
       minValues[0] = min;
       maxValues[0] = max;
       

       max = Integer.MIN_VALUE;
       min = Integer.MAX_VALUE;
        for (int pressureValues : hurricanePressL)
       {
           totalPressure = totalPressure + pressureValues;
           if (pressureValues < min)
           {
               min = pressureValues;
           }
           else if (pressureValues > max)
           {
               max = pressureValues;
           }
       }
    
       average[1] = (int)(totalPressure / ((double)(hurricanePressL.length)));
       minValues[1] = min;
       maxValues[1] = max;
       
       max = Integer.MIN_VALUE;
       min = Integer.MAX_VALUE;
       for (double wndSpdValues : hurricaneWndSpdL)
       {
           totalWndSpd = totalWndSpd + wndSpdValues;
           if (wndSpdValues < min)
           {
               min = wndSpdValues;
           }
           else if (wndSpdValues > max)
           {
               max = wndSpdValues;
           }
        }
       average[2] = (double)(totalWndSpd/((hurricaneWndSpdL.length)));
       minValues[2] = min;
       maxValues[2] = max;
       
       
       index = 0;
       System.out.println();
       System.out.println("                          Hurricanes " + yearStart + " - " + yearEnd);
       System.out.println();
       System.out.println("    Year     Hurricane   Category   Pressure(mb)   Wind Speed(mph)");
       System.out.println("  ===================================================================");
       for (String name : hurricaneNamesL)
       {
            System.out.printf("    %4s      %-9s %5d %13d %14.2f \n", hurricaneYearsL[index], name, categoriesL[index], hurricanePressL[index], hurricaneWndSpdL[index]);
            index++;
       }

       System.out.println("  ===================================================================");
       System.out.printf ("               Average: %5.0f %13.0f %14.2f \n", average[0], average[1], average[2]);
       System.out.printf ("               Minimum: %5.0f %13.0f %14.2f \n", minValues[0], minValues[1], minValues[2]);
       System.out.printf ("               Maximum: %5.0f %13.0f %14.2f \n", maxValues[0], maxValues[1], maxValues[2]);
       System.out.println();
       index = 0;
       for(int myValue : categoryTotals)
       {
            System.out.println("    Number of Category " + (index+1) + "s: " + myValue);
            index++;
       }
       
       //Print to "hurricanedatasummary.txt"
       hurricaneDataS.println();
       hurricaneDataS.println("                          Hurricanes " + yearStart + " - " + yearEnd);
       hurricaneDataS.println();
       hurricaneDataS.println("    Year     Hurricane   Category   Pressure(mb)   Wind Speed(mph)");
       hurricaneDataS.println("  ===================================================================");
       for (String name : hurricaneNamesL)
       {
            hurricaneDataS.printf("    %4s      %-9s %5d %13d %14.2f \n", hurricaneYearsL[index], name, categoriesL[index], hurricanePressL[index], hurricaneWndSpdL[index]);
            index++;
       }

       hurricaneDataS.println("  ===================================================================");
       hurricaneDataS.printf ("               Average: %5.0f %13.0f %14.2f \n", average[0], average[1], average[2]);
       hurricaneDataS.printf ("               Minimum: %5.0f %13.0f %14.2f \n", minValues[0], minValues[1], minValues[2]);
       hurricaneDataS.printf ("               Maximum: %5.0f %13.0f %14.2f \n", maxValues[0], maxValues[1], maxValues[2]);
       hurricaneDataS.println();
       index = 0;
       for(int myValue : categoryTotals)
       {
            hurricaneDataS.println("    Number of Category " + (index+1) + "s: " + myValue);
            index++;
       }
    }
}
   