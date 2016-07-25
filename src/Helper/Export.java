package Helper;

import java.io.File;
import java.util.ArrayList;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Export {
    
    public static void printDataToExcel(ArrayList<ArrayList> completeData) throws Exception{
            //determine path
            //get path for Desktop
            String path = System.getProperty("user.dir") + "/Data.xls";
            System.out.println("Path: "+path);
               File file = new File(path);
                    WritableWorkbook myExcel = Workbook.createWorkbook(file);
                    WritableSheet dataSheet = myExcel.createSheet("Data", 0);
                    
                    //write data into 
                    //iterate trough every datarow
                    int rowNumber=0;
                    for(ArrayList<String> dataRow: completeData){
                        int columnNumber=0;
                        //iterate trough every cell
                        for(String dataCell:dataRow){
                            Label cell = new Label(columnNumber, rowNumber, dataCell);
                            dataSheet.addCell(cell);
                            columnNumber++;

                        }
                        rowNumber++;
                    }
                    myExcel.write();
                    myExcel.close();
                    System.out.println("Successfully saved data into Excel file");
    }
    
}
