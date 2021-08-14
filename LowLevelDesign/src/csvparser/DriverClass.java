package csvparser;

import java.util.List;

public class DriverClass {

    public static void main(String args[]){
        CsvParser csvParser = CsvParser.getInstance();
        List<Data> dataList = csvParser.openCsvAndGetData("LowLevelDesign/src/csvparser/test.txt");
        if(dataList==null){
            System.out.println("No data found");
        }
        else {
            for (Data data: dataList){
                System.out.println(data.getId()+","+data.getName()+","+data.getAge()+","+data.getSalary()+","+data.getDesignation());
            }
        }


        dataList = csvParser.fGrep("LowLevelDesign/src/csvparser/test.txt","iqbal");
        if(dataList==null){
            System.out.println("No data found");
        }
        else {
            for (Data data: dataList){
                System.out.println(data.getId()+","+data.getName()+","+data.getAge()+","+data.getSalary()+","+data.getDesignation());
            }
        }

        //ADD sorting and all
    }
}
