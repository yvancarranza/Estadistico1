

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
	
	

CREATE PROCEDURE sp_generar_resultado()
BEGIN
  
  DELETE FROM facEncuesta;
  
  
  
END //
DELIMITER ;



SELECT SUM(valrespuesta)
FROM   tbencuesta
WHERE  idencuesta =13 and CodPregunta IN (
SELECT CodvarOri
FROM   tbvarfinal TBF
INNER JOIN tbparamcal PCA ON (TBF.CodVarFin = PCA.CodVarFin)
WHERE  TBF.Codvarfin = 'ESTRES')




		SELECT @@Total = SUM(valrespuesta)
		FROM   tbencuesta
		WHERE  idencuesta = CUR_ENCUESTA.IDENCUESTA and CodPregunta IN (
		SELECT CodvarOri
		FROM   tbvarfinal TBF
		INNER JOIN tbparamcal PCA ON (TBF.CodVarFin = PCA.CodVarFin)
		WHERE  TBF.Codvarfin = 'ESTRES')
		
		INSERT INTO facencuesta(idEncuesta,codvarfin,valresult)
		VALUE(CUR_ENCUESTA.IDENCUESTA,'ESTRES',@@Total)



   SET FOREIGN_KEY_CHECKS = ON;
DELIMITER //
CREATE PROCEDURE sp_generar_resultado()
BEGIN

  DECLARE var_idencuesta INT;
  DECLARE finished INTEGER DEFAULT 0;
  DECLARE finished2 INTEGER DEFAULT 0;
  DECLARE var_suma INT DEFAULT 0;
  DECLARE var_CodVariable CHAR(15);
 
  
  DECLARE CUR_ENCUESTA CURSOR FOR SELECT DISTINCT idencuesta FROM tbencuesta;
  DECLARE CUR_VARIABLE CURSOR FOR SELECT CODVARFIN FROM tbvarfinal;

  DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;

DELETE FROM facEncuesta;
		  
  OPEN CUR_ENCUESTA;
   -- LOOP PARA RECORRER LAS ENCUESTAS
   getEncuesta: LOOP
	FETCH CUR_ENCUESTA INTO var_idencuesta;

		IF finished = 1 THEN 
			LEAVE getEncuesta;
		END IF;
				
	
			  
		OPEN CUR_VARIABLE;
		-- LOOP PARA RECORRER LAS VARIABLES RESULTANTES
		getVariable: LOOP
			FETCH CUR_VARIABLE INTO var_CodVariable;
			  IF finished = 1 THEN
			  		SET finished = 0;
			  	    LEAVE getVariable;
			  END IF;
			  
		   SET var_suma := (SELECT SUM(valrespuesta)
			FROM   tbencuesta
			WHERE  idencuesta = var_idencuesta and CodPregunta IN (
			SELECT CodvarOri
			FROM   tbvarfinal TBF
			INNER JOIN tbparamcal PCA ON (TBF.CodVarFin = PCA.CodVarFin)
			WHERE  TBF.Codvarfin = var_CodVariable));
		
			IF(var_suma IS NOT NULL) THEN		
					INSERT INTO facencuesta(IDEncuesta,codvarfin,valresult,codresultado) VALUES(var_idencuesta,var_CodVariable,var_suma,'XXX');
			END IF;
		END LOOP getVariable;
		CLOSE CUR_VARIABLE;
		
		
	END LOOP getEncuesta;
	CLOSE CUR_ENCUESTA;  
END //
DELIMITER ;


