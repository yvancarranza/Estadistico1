-- Tabla de Preguntas
CREATE TABLE tbpregunta (
  idpregunta   int(11) NOT NULL,
  codpregunta   char(50) NOT NULL,
  nompregunta  varchar(250) DEFAULT NULL,
  tipocampo    char(50) NOT NULL,
  formulario   char(50) NOT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbpregunta
  ADD PRIMARY KEY (idpregunta);
  
ALTER TABLE tbpregunta
  MODIFY idpregunta int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

-- Tabla de Respuestas

CREATE TABLE tbrespuesta (
  idrespuesta      int(11) NOT NULL,
  codpregunta  char(50) NOT NULL,
  codrespuesta     char(50) NOT NULL,
  nomrespuesta     varchar(250) DEFAULT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbrespuesta
  ADD PRIMARY KEY (idrespuesta);
  
ALTER TABLE tbrespuesta
  MODIFY idrespuesta int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


-- Tabla de Encuestas

CREATE TABLE tbencuesta (
  idregistro   int(11) NOT NULL,
  idencuesta   int(11) NOT NULL,
  codpregunta  char(50) NOT NULL,
  valrespuesta     char(50) NOT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbencuesta
  ADD PRIMARY KEY (idregistro);

ALTER TABLE tbencuesta
  MODIFY idregistro int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;



	SELECT B1.idencuesta,b1.codpregunta,b2.nompregunta,b1.valrspta,b3.nomrespuesta
	FROM   tbencuesta B1
	INNER JOIN tbpregunta B2 ON (B1.codpregunta = B2.codpregunta)
	LEFT OUTER JOIN tbrespuesta B3 ON (B1.codpregunta = b3.codpregunta and b1.valrspta = b3.codrespuesta)
	where b1.idencuesta = 8
	Order by b1.idencuesta,b1.codpregunta,b1.codrespuesta



	select *
	from tbrespuesta
	where codpregunta = "i14___4"
	
	
	select p1.codpregunta,p1.nompregunta,r1.codrespuesta,r1.nomrespuesta
	from   tbpregunta p1
	left outer join tbrespuesta  r1 on (p1.codpregunta = r1.codpregunta)
	where p1.codpregunta = "i14___2"
	order by p1.codpregunta,r1.codrespuesta
	
	
	
	select * from tbencuesta where idencuesta = 2
	
	
30100



      
            
            // FIN DE IMPORTACION
            
    archCSV = "D:\\DataVariables.csv";
    csvReader = new CSVReader(new FileReader(archCSV));
    fila = null;
    numreg = 0;
    String Preguntas[] =null;
    while((fila = csvReader.readNext()) != null){           
        if(numreg == 0){
            Preguntas = fila;
            numreg++;
          }
        else {            
                int idencuesta = Integer.parseInt(fila[0]);
                for(int ifila = 1;ifila<fila.length;ifila++ ){                    
                    PreparedStatement pstm1;
                    String valrspta;
                    String codpregunta;
                    valrspta = fila[ifila].trim();
                    codpregunta = Preguntas[ifila].trim();
                    if(!valrspta.isEmpty())  {
                        String sql1 = "insert into tbencuesta(idregistro,idencuesta,codpregunta,valrspta)" + 
                             " values(null,?,?,?)";            
                        try
                           {
                                pstm1 = cnn.prepareStatement(sql1);
                                pstm1.setInt(1, idencuesta);
                                pstm1.setString(2,codpregunta);
                                pstm1.setString(3,valrspta);
                                salida = pstm1.executeUpdate();
                           }
                        catch (SQLException ex)
                            {

                               System.out.println(ex.toString());
                               System.out.println(valrspta);
                            }  
                    }
                }                   
                 numreg++;
                }            
           }
           csvReader.close();
           try {
                cnn.close();
               }
           catch (SQLException ex) {
            Logger.getLogger(encuesta.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            ///