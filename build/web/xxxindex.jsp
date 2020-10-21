<%-- 
    Document   : index
    Created on : 30/10/2019, 10:27:35 PM
    Author     : Guillermo Gonzales Lozano
                 Yvan Carranza Villalobos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SanaMente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
	<meta name="description" content="pagina venta server">
	<meta name="keywords" content="server">
	<meta name="autor" content="Jhosep, Yvan , Guillermo"> 
	<link rel="stylesheet" type="text/css" href="estilos/css/estilos.css">
<!----------------------script para slider----------------------------------------------------------->
        <script>

                //INTEGRANTES:
                // JHOSEP  GARCIA TORRES
                // GUILLERMO GONZALES
                // YVAN CARRANZA
            /**
            * Array con las imagenes que se iran mostrando en la web
            */
            var imagenes=new Array(
                'estilos/imagenes/1.jpg',
                'estilos/imagenes/2.jpg',
                'estilos/imagenes/3.jpg',
                'estilos/imagenes/4.jpg',
                'estilos/imagenes/5.jpg',
                'estilos/imagenes/6.jpg',
                'estilos/imagenes/7.jpg',
                'estilos/imagenes/8.jpg',
                'estilos/imagenes/9.png',
            );

            /**
            * Funcion para cambiar la imagen
            */
            function rotarImagenes()
            {
                // obtenemos un numero aleatorio entre 0 y la cantidad de imagenes que hay
                var index=Math.floor((Math.random()*imagenes.length));

                // cambiamos la imagen
                document.getElementById("imagen").src=imagenes[index];
            }

            /**
            * Función que se ejecuta una vez cargada la página
            */
            onload=function()
            {
                // Cargamos una imagen aleatoria
                rotarImagenes();

                // Indicamos que cada 5 segundos cambie la imagen
                setInterval(rotarImagenes,5000);
            }
        </script>
        <!----------------------script para slider----------------------------------------------------------->
    </head>
    <body>
        <header>
		<div class="contenedor">
                    <div id="marca">
                        <img src="estilos/imagenes/SanaMenteLogo2.png" width="120px" height="120px" >     
                    </div>
                        
                   <div id="marca">
                            <h1><span style="color: #830C32;">Sana</span>Mente</h1>
                            <small style="color: green">Buscador online de Profesionales de la Salud Mental</small>  
                    </div>
                   
                        <nav>
				<ul>
					<li class="actual"> <a href="index.jsp">Inicio</a></li>
					<li> <a href="nosotros.jsp">Nosotros</a></li>
					<li> <a href="login.jsp">Servicios</a></li>
                                        <li> <a href="noticias.jsp">Noticias</a></li>
					<li> <a href="contacto.jsp">Contacto</a></li>
				</ul>
			</nav>
                    <marquee> <font face="arial" color="#830C32" size="4">  Empresa peruana con experiencia en servicios de búsqueda online... Necesitas ayuda?... Busca tu profesional de Salud Mental en solo un click...        
                        --> Registro Gratis... <--- </font></marquee>
		</div>	

	</header>

	<section class="cabezera" >
			<img src="" id="imagen" width="100%" height="300">
	</section>

	<section id="boletin">
		<div class="contenedor">
			<h1>Empieza tu Búsqueda</h1>
			<form>
				<input type="email" name="email" placeholder="Ingrese correo...">
				<button type="submit" class="boton1">Regístrate</button>
			</form>
		</div>
	</section>

	<section id="cajas">
		<div class="contenedor">
			<div class="caja">
				<img src="estilos/imagenes/IniCuerpo1.png">
				<h3>Ubícalos</h3>
				<p>Encontrar a un profesional que te pueda ayudar a minimizar tus problemas de estrés, ansiedad, depresión, angustia, 
                                    entre otros, es más fácil de lo que imaginas. Pruébalo ahora mismo.</p>
			</div>
			<div class="caja">
				<img src="estilos/imagenes/IniCuerpo2.png">
				<h3>Dialogar ayuda!</h3>
				<p>Sabías que la mayoria de problemas se resuelven en el primer nivel de atención (sin tratamiento). Es decir, que basta con 
                                 conversar con un profesional de Salud Mental, puedes dar un gran paso.</p>
			</div>

			<div class="caja">
				<img src="estilos/imagenes/IniCuerpo3.png">
				<h3>Sabias que?</h3>
				<p>La salud mental incluye nuestro bienestar emocional, psicológico y social. Afecta la forma en que pensamos, sentimos y 
                                    actuamos cuando enfrentamos la vida...</p>
			</div>

			<div class="caja">
				<img src="estilos/imagenes/IniCuerpo4.jpg">
				<h3>Localización Digital</h3>
				<p>La llegada de las nuevas tecnologías, especialmente los medios 2.0, permiten el acceso a información global de forma 
                                    inmediata y segura, gracias al internet desde cualquier lugar.</p>
			</div>
                        <div class="caja">
				<img src="estilos/imagenes/IniCuerpo5.jpg">
				<h3>Profesionales Esperando</h3>
				<p>A travéz de este portal web, podrás encontrar y recibir, toda la atención de calidad de diversos profesionales
                                    especialistan en Salud Mental, ya sean Psiquiatras y Psicólogos.</p>
			</div>
                        <div class="caja">
				<img src="estilos/imagenes/IniCuerpo6.jpg">
				<h3>Me siento bien?</h3>
				<p>Cuando diferentes circunstancias y situaciones de la vida superan  nuestras posibilidad de resolverlas; cuando no logramos 
                                    desarrollar algún aspecto de nuestra persona o nuestra vida satisfactoriamente, la consulta psicológica, un proceso psicoterapéutico, 
                                    una orientación profesional  frente a una crisis vital, puede transformarse en una oportunidad.</p>
			</div>

		</div>	
	</section>
	<footer>
		<p>Web Peruana de Búsqueda de Servicios Online, Copyright &copy;2019</p>
	</footer>
    </body>
</html>
