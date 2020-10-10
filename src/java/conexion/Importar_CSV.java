package conexion;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Importar_CSV {



public void main(String arg[]) throws FileNotFoundException, IOException, CsvValidationException    
{
    String archCSV = "D:\\UTP\\DataVariables.csv";
    CSVReader csvReader = new CSVReader(new FileReader(archCSV));
    String[] fila = null;
    while((fila = csvReader.readNext()) != null)
    {
        System.out.println(fila[0]
                  + " | " + fila[1]
                  + " |  " + fila[2]);
   }
    csvReader.close();
  }
}
