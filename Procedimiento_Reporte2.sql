 SET FOREIGN_KEY_CHECKS = ON;

DROP PROCEDURE IF EXISTS sp_consultar_repregion;

DELIMITER //estadistico
CREATE PROCEDURE sp_consultar_repregion()
BEGIN
SELECT rep.codvarfin,departamento,var.tipovar,sum(Ninguno) Ninguno,SUM(Bajo) Bajo,Sum(Normal) Normal,
      Sum(Medio) Medio,Sum(Moderado) Moderado, Sum(Alta) Alta,Sum(MuyAlta) MuyAlta,Sum(Severo) Severo,Sum(Extremo) Extremo,
		SUM(Ninguno + Bajo + Normal + Medio + Moderado + Alta + MuyAlta + Severo + Extremo + Cuenta) Total,
       SUM(ORDEN) AS ORDEN,SUM(media) AS media,SUM(Desviacion) AS Desviacion
FROM  (

	SELECT rep.codvarfin,rep.departamento,COUNT(*) Ninguno,0 as Bajo,0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Ninguno'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno,COUNT(*)  as Bajo,0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Bajo'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo,COUNT(*)  as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Normal'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,COUNT(*)  AS Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Medio'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, COUNT(*) as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Moderado'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     COUNT(*) as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Alta'
	GROUP BY rep.codvarfin,rep.departamento
   UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,COUNT(*)  as MuyAlta,0 as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Muy Alta'
	GROUP BY rep.codvarfin,rep.departamento
   UNION ALL	
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,COUNT(*)  as Severo, 0 as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Severo'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL
	SELECT rep.codvarfin,rep.departamento,0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, COUNT(*)   as Extremo,MAX(ORDEN) AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Extremadamente'
	GROUP BY rep.codvarfin,rep.departamento
	UNION ALL	
	SELECT rep.codvarfin,'TOTAL',COUNT(*) Ninguno,0 as Bajo,0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Ninguno'
	GROUP BY rep.codvarfin
	UNION ALL	
	SELECT rep.codvarfin,'TOTAL',0 Ninguno,COUNT(*)  as Bajo,0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Bajo'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo,COUNT(*)  as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0  AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Normal'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,COUNT(*)  AS Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Medio'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, COUNT(*) as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0  AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Moderado'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     COUNT(*) as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Alta'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0  as Alta,COUNT(*) AS MuyAlta,0 as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Muy Alta'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,COUNT(*)  as Severo, 0 as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Severo'
	GROUP BY rep.codvarfin
	UNION ALL
	SELECT rep.codvarfin,'TOTAL',0 Ninguno, 0  as Bajo, 0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, COUNT(*)   as Extremo,0 AS ORDEN,0 AS Media,0 AS Desviacion,0 AS Cuenta
	FROM   tbreporte rep
	WHERE  codresultado = 'Extremadamente'
	GROUP BY rep.codvarfin
	UNION ALL	
	SELECT rep.codvarfin,rep.departamento,0 Ninguno,0 as Bajo,0 as Normal,0 as Medio, 0 as Moderado,
	     0 as Alta,0 AS MuyAlta,0 as Severo, 0 as Extremo,1 AS ORDEN,AVG(Media) AS Media,AVG(Desviacion) AS Desviacion,COUNT(*) AS Cuenta
	FROM   tbreporte rep
	INNER  JOIN tbvarfinal var ON (rep.codvarfin = var.codvarfin)
	WHERE  var.tipovar = 'MEDIA'
	GROUP BY rep.codvarfin,rep.departamento
	
  ) rep
INNER JOIN tbvarfinal var ON (rep.CodVarfin = var.codvarfin)  
GROUP BY rep.codvarfin,departamento,var.tipovar
ORDER BY rep.codvarfin,ORDEN,departamento;

END //
DELIMITER ;
