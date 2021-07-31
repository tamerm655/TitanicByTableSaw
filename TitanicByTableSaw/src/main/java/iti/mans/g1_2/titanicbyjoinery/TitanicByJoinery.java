package iti.mans.g1_2.titanicbyjoinery;

import java.io.IOException;
import joinery.DataFrame;

public class TitanicByJoinery {
    public static void main(String[] args) throws Exception {
        //Reading Titanic Data
        System.out.println("Reading Titanic Data");
        DataFrame<Object> df1 = new DataFrame<>();
        try{
            df1 = DataFrame.readCsv("src/main/resources/Titanic.csv")
            .describe();
            //df.iterrows ().forEachRemaining (System.out::println);
            System.out.println(df1.toString());
        }catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println("///////////////////////////////////////////////////////////");
        //Reading vgsales Data
        System.out.println("Reading vgsales Data");
        DataFrame<Object> df2 = new DataFrame<>();
        try{
            df2 = DataFrame.readCsv("src/main/resources/vgsales.csv")
            .describe();
            //df.iterrows ().forEachRemaining (System.out::println);
            System.out.println(df2.toString());
        }catch (IOException e) {
            e.printStackTrace ();
        }
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println("joining passengerCol with rankCol");
        
        //joining two columns from both tables
        DataFrame<Object> passengerCol = new DataFrame<>();
        passengerCol = df1.retain("PassengerId");
        DataFrame<Object> rankCol = new DataFrame<>();
        rankCol = df2.retain("Rank").join(passengerCol);
        System.out.println(rankCol.toString());
        
        //Adding column to table
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println("adding passengerCol to vgsales table");
        
        DataFrame<Object> rankCol2 = df2.add(df1.col("PassengerId"));
        System.out.println(rankCol2.toString());
        
        //Merging two columns
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println("merging passengerCol with rankCol");
        
        try{
            DataFrame<Object> rankCol3 = DataFrame.readCsv("src/main/resources/vgsales.csv")
            .retain("Rank")
            .merge(passengerCol, DataFrame.JoinType.LEFT);
            System.out.println(rankCol3.toString());
        }catch (IOException e) {
            e.printStackTrace ();
        }
        
    }
}
