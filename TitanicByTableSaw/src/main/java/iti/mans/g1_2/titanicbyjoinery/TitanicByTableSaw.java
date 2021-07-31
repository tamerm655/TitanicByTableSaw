/*
 * Copyright 2021 ActFramework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iti.mans.g1_2.titanicbyjoinery;

import java.io.IOException;
import joinery.DataFrame;
import joinery.DataFrame.JoinType;
import tech.tablesaw.api.Table;

/**
 *
 * @author Dubai
 */
public class TitanicByTableSaw {
    public static void main(String[] args) throws Exception {
        //Reading Titanic Data
        
        System.out.println("Reading Titanic Data");
        Table t1 = Table.read().csv("src/main/resources/Titanic.csv"); 
        System.out.println(t1);
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(t1.structure().printAll());
        System.out.println("///////////////////////////////////////////////////////////");
        
        //Reading vgsales Data
        System.out.println("Reading vgsales Data");
        Table t2 = Table.read().csv("src/main/resources/vgsales.csv");
        System.out.println(t2);
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println(t2.structure().printAll());
        System.out.println("///////////////////////////////////////////////////////////");
        
        System.out.println("joining passengerCol with rankCol");
        
        //joining two columns from both tables
        Table passengerCol = t1.retainColumns("PassengerId");
        Table rankCol = t2.retainColumns("Rank");
        Table joined = passengerCol.concat(rankCol);
        System.out.println(joined);
        
        //Adding column to table
        System.out.println("///////////////////////////////////////////////////////////");
        System.out.println("adding passengerCol to vgsales table");
        
        Table rankCol2 = t2.addColumns(t1.column("PassengerId"));
        System.out.println(rankCol2);
        
    }
}
