 SET FOREIGN_KEY_CHECKS = ON;

DROP PROCEDURE IF EXISTS sp_generar_resultado;

DELIMITER //
CREATE PROCEDURE sp_generar_resultado()
BEGIN

  DECLARE var_idencuesta INT;
  DECLARE finished INTEGER DEFAULT 0;
  DECLARE finished2 INTEGER DEFAULT 0;
  DECLARE var_suma INT DEFAULT 0;
  DECLARE var_CodVariable CHAR(15);
  DECLARE var_operacion CHAR(15);
  DECLARE var_origen CHAR(15);
  DECLARE var_resultado CHAR(15);
  DECLARE var_porcentaje DECIMAL(11,2);
  DECLARE var_desviacion DECIMAL(11,2);
  DECLARE var_media      DECIMAL(11,2);
  DECLARE var_departamento CHAR(20);
  DECLARE var_duplicar CHAR(2);
  DECLARE var_sexo CHAR(20);
  DECLARE var_orden  INT;
  DECLARE CUR_ENCUESTA CURSOR FOR SELECT DISTINCT idencuesta FROM tbencuesta;
  DECLARE CUR_VARIABLE CURSOR FOR SELECT CODVARFIN,OPERACION,ORIGEN,DUPLICAR FROM tbvarfinal;

  DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET finished = 1;

DELETE FROM tbreporte;
		  
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
			FETCH CUR_VARIABLE INTO var_CodVariable,var_operacion,var_origen,var_duplicar;
			  IF finished = 1 THEN
			  		SET finished = 0;
			  	    LEAVE getVariable;
			  END IF;
			SET var_suma:= 0; 
			IF (var_operacion = 'SUMA') THEN

		   	SET var_suma := (SELECT SUM(valrespuesta)
				FROM   tbencuesta
				WHERE  idencuesta = var_idencuesta and CodPregunta IN (
						SELECT CodvarOri
						FROM   tbvarfinal TBF
						INNER JOIN tbparamcal PCA ON (TBF.CodVarFin = PCA.CodVarFin)
						WHERE  TBF.Codvarfin = var_CodVariable));
			END IF;
			IF(var_suma IS NOT NULL) THEN		
					IF(var_duplicar = 'NO') THEN
						SET var_suma:= var_suma;
					ELSE
						SET var_suma:= var_suma*2;	
					END IF;
						
					SET Var_Resultado := (SELECT desrango FROM tbrangovar 
												WHERE codvarfin = var_CodVariable  AND 
												var_suma >= rangoini AND var_suma <= rangofin);
					SET Var_Orden := (SELECT orden FROM tbrangovar 
												WHERE codvarfin = var_CodVariable  AND 
												var_suma >= rangoini AND var_suma <= rangofin);
												
					IF(Var_Resultado IS NULL) THEN		
						 SET Var_Resultado := 'OUT_RANGE';
					END IF;
					
					IF(Var_Orden IS NULL) THEN		
						 SET Var_Orden := 999;
					END IF;

					SET var_porcentaje:= 0;
					SET var_desviacion:= 0;
					SET var_media := 0;
					
					IF(var_CodVariable = 'RESILIENCIA') THEN
						 SET var_porcentaje:= ((var_suma - 14)/56)*100;
						 SET var_media := var_suma/14;
					 
						 
						 SET var_desviacion := (SELECT STD(CONVERT(valrespuesta,INTEGER))
						                        FROM   tbencuesta
														WHERE  idencuesta = var_idencuesta and CodPregunta IN (
														       SELECT CodvarOri
																 FROM   tbvarfinal TBF
																 INNER JOIN tbparamcal PCA ON (TBF.CodVarFin = PCA.CodVarFin)
																 WHERE  TBF.Codvarfin = var_CodVariable)
													  ); 
					END IF;
					
					/* Obtener departamento */
					SET var_departamento:= (SELECT rpta.nomrespuesta 
					                        FROM   tbencuesta enc
													INNER JOIN  tbrespuesta rpta ON (enc.codpregunta = rpta.codpregunta AND enc.valrespuesta = rpta.codrespuesta) 
													WHERE enc.codpregunta = 'dep' AND enc.idencuesta = var_idencuesta);

					SET var_sexo:= (SELECT rpta.nomrespuesta 
					                        FROM   tbencuesta enc
													INNER JOIN  tbrespuesta rpta ON (enc.codpregunta = rpta.codpregunta AND enc.valrespuesta = rpta.codrespuesta) 
													WHERE enc.codpregunta = 'sex' AND enc.idencuesta = var_idencuesta);

					
					INSERT INTO tbreporte(IDEncuesta,departamento,sexo,codvarfin,suma,codresultado,porcentaje,media,desviacion,orden) 
					VALUES(var_idencuesta,var_departamento,var_sexo,var_CodVariable,var_suma,Var_Resultado,var_porcentaje,var_media,var_desviacion,var_orden);
			END IF;
		END LOOP getVariable;
		CLOSE CUR_VARIABLE;
		
		
	END LOOP getEncuesta;
	CLOSE CUR_ENCUESTA;  
END //
DELIMITER ;


