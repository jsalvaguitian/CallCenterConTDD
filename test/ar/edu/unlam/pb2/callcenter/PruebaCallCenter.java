package ar.edu.unlam.pb2.callcenter;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PruebaCallCenter {

	@Test
	public void queSePuedaGuardarElNombreDeLaEmpresaOesteCableColor() {
		//Preparación de datos
		final String NOMBRE_EMPRESA_ESPERADA= "Oeste Cable Color";
		Empresa unaEmpresa;
		
		//Ejecución
		unaEmpresa = new Empresa(NOMBRE_EMPRESA_ESPERADA);
		
		//Validación
		assertEquals(NOMBRE_EMPRESA_ESPERADA, unaEmpresa.getNombre());
	}
	
	
	@Test
	public void queSePuedaGuardarUnNumeroDeCelularSolicitandoElCodPaisCodAreaYNroDeCel() {
		// Preparacion de datos
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		final String NUMERO_DE_CELULAR_ESPERADO = "+"+CODIGO_PAIS+CODIGO_AREA+NRO_CELULAR;
		final String NRO_CELULAR_YA_ARMADO;
		Contacto unContacto;
		
		//Ejecucion
		unContacto = new Contacto();
		NRO_CELULAR_YA_ARMADO = Contacto.crearSuNumeroCelular(CODIGO_PAIS,CODIGO_AREA,NRO_CELULAR);
		unContacto.setNumeroCelular(NRO_CELULAR_YA_ARMADO);
		
		//Assert | Afirmar o validar
		assertEquals(NUMERO_DE_CELULAR_ESPERADO, unContacto.getNumeroCelular());
	}
	
	
	@Test(expected = NumberFormatException.class)
	public void queNoSePuedaGuardarUnNumeroDeCelularQueContengaLetraUOtroSigno() {
		// Preparacion de datos
		final String CODIGO_PAIS = "5ABD4";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		final String NRO_CELULAR_YA_ARMADO;
		Contacto unContacto;
		
		//Ejecucion
		unContacto = new Contacto();
		NRO_CELULAR_YA_ARMADO = Contacto.crearSuNumeroCelular(CODIGO_PAIS,CODIGO_AREA,NRO_CELULAR);
		unContacto.setNumeroCelular(NRO_CELULAR_YA_ARMADO);
		
	}
	
	
	
	
	@Test
	public void queSePuedaGuardarUnEmailConUnArrobaYPuntos() {
		//Preparacion de datos
		final String MAIL = "jesibel@mail.com.ar";
		Contacto contactoTest = new Contacto();
		
		//Ejecucion
		contactoTest.setMail(MAIL);
		
		//Validacion
		assertEquals(MAIL, contactoTest.getMail());
		assertTrue(contactoTest.esMailValido(MAIL));	
	}
	
	@Test
	public void queNoSePuedaGuardarUnEmailSinArrobaYSinPunto() {
		//Preparacion de datos
		final String MAIL = "jesibelmailcomar";
		Contacto contactoTest = new Contacto();
		
		//Ejecucion
		contactoTest.setMail(MAIL);
				
		//Validación
		assertFalse(contactoTest.esMailValido(MAIL));	

	}
	
	@Test
	public void queNoSePuedaGuardarUnEmailConDosArroba() {
		//Preparación de datos
		final String MAIL = "jesibel@mail@com.ar";
		Contacto contactoTest;
		
		//Ejecucion
		contactoTest  = new Contacto();
		contactoTest.setMail(MAIL);
				
		//Validación
		assertFalse(contactoTest.esMailValido(MAIL));	
		
	}
	
	@Test //Preguntar si puedo cambiar la consigna en vez de 23 a 24 | No tengo ejecucion igual sirve
	public void queLaListaDeProvinciasArgentinasContengaVeinticuatroProvincias() {
		//Preparacion de datos
		final Integer CANTIDAD_DE_PROVINCIA_ESPERADA = 24;
		
		//Validación
		assertEquals(CANTIDAD_DE_PROVINCIA_ESPERADA, (Integer)Provincia.values().length);
	}
	
	@Test
	public void queCuandoSeCreaUnContactoInicialmenteNoSeaClienteYSiDeseaSerLlamado() {
		//Preparacion de datos
		Contacto contactoTest;
		
		//Ejecucion
		contactoTest = new Contacto();
		
		//Validacion
		assertFalse(contactoTest.isEsCliente());
		assertTrue(contactoTest.isDeseaSerLlamado());
		
	}


	@Test
	public void queSePuedaRegistrarUnContactoConTodosSusDatosEnUnListadoDeContactosDeLaEmpresa() {
		//Preparacion de datos
		final String NOMBRE_Y_APELLIDO = "Jesi Belen Salva";
		
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "6124293";
		
		final String CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
		final String MAIL = "jesibel@mail.com.ar";
		final String DIRECCION = "Av. Siempre viva 123";
		final Integer CODIGO_POSTAL = 1000;
		final String LOCALIDAD = "CABA";
		final Provincia PROVINCIA = Provincia.CABA;
		Contacto unContacto;
		Empresa unaEmpresa;
		
		final Integer CANTIDAD_DE_CONTACTO_ESPERADA = 1;
		
		//Ejecucion
		unaEmpresa = new Empresa("Oeste Cable Color");
		unContacto = new Contacto(NOMBRE_Y_APELLIDO,CELULAR,MAIL,DIRECCION,CODIGO_POSTAL,LOCALIDAD,PROVINCIA);
		
		//Validacion
		assertTrue(unaEmpresa.agregarUnContacto(unContacto));
		assertEquals(CANTIDAD_DE_CONTACTO_ESPERADA, unaEmpresa.darLaCantidadDeContactosYaRegistrados());
	}
	//****************************************************************
	
	@Test
	public void queNoSePuedaRegistrarUnContactoConCelularErroneoEnLaListaDeContactosDeEmpresa(){
		
		final String NOMBRE_Y_APELLIDO = "Jesi Belen Salva";
		final String CODIGO_PAIS = "5A4";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "612P34293";
		
		final String CELULAR; 
		final String MAIL = "jesibel@mail.com.ar";
		final String DIRECCION = "Av. Siempre viva 123";
		/*final Integer CODIGO_POSTAL = 1000;
		final String LOCALIDAD = "CABA";
		final Provincia PROVINCIA = Provincia.CABA;*/
		
		Contacto unContacto;
		Empresa unaEmpresa;
		
		final Integer CANTIDAD_DE_CONTACTO_ESPERADA = 1;
		
		//Ejecucion
		unaEmpresa = new Empresa("Oeste Cable Color");
		try {
			CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
			unContacto = new Contacto(NOMBRE_Y_APELLIDO,CELULAR,MAIL,DIRECCION);
		} catch (DatosIncorrectos e) {
			e.printStackTrace();
			
		}catch(NumberFormatException e) {
			System.err.println("El formato del celular no es correcto.");
		}
		
	}
	//****************************************************************
	@Test
	public void queNoSePuedaRegistrarUnContactoConEmailInvalidoEnLaListaDeContactosDeEmpresa() {
		final String NOMBRE_Y_APELLIDO = "Jesi Belen Salva";
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		
		final String CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
		final String MAIL = "belenmailcomar";
		final String DIRECCION = "Av. Siempre viva 123";
		
		final Integer CANTIDAD_DE_CONTACTO_ESPERADA = 0;

		
		Contacto unContacto;
		Empresa unaEmpresa;
		
		//Ejecucion
		unaEmpresa = new Empresa("Oeste Cable Color");
		
		try {
			unContacto = new Contacto(NOMBRE_Y_APELLIDO,CELULAR,MAIL,DIRECCION);
			unaEmpresa.agregarUnContacto(unContacto);
		} catch (DatosIncorrectos e) {
			System.err.println("El mail fue inválido, no tiene un arroba o no tiene punto");
		}
		
		//Validacion
		assertEquals(CANTIDAD_DE_CONTACTO_ESPERADA, unaEmpresa.darLaCantidadDeContactosYaRegistrados());
		
		
	}

	
	@Test
	public void queSePuedaBuscarUnCandidatoQueNoSeaClienteYDeseaSerLlamadoYQueElCodigoPostalDeEsteExistaEnLaZonaDeCobertura() {
		// Preparacion de datos
		//CONTACTO 1		
		final String NOMBRE_Y_APELLIDO = "Jesi Salva";
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		final String CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
		final String MAIL = "jesibel@mail.com.ar";
		final String DIRECCION = "Av. Siempre viva 123";
		final Integer CODIGO_POSTAL = 1000;
		final String LOCALIDAD = "CABA";
		final Provincia PROVINCIA = Provincia.CABA;
		
		//CONTACTO 2
		final String NOMBRE_Y_APELLIDO2 = "Bart Simpson";
		final String CODIGO_PAIS2 = "001";
		final String CODIGO_AREA2 = "22";
		final String NRO_CELULAR2 = "77171723";
		final String CELULAR2 = Contacto.crearSuNumeroCelular(CODIGO_PAIS2, CODIGO_AREA2, NRO_CELULAR2);
		final String MAIL2 = "elbarto@mail.com.ar";
		final String DIRECCION2 = "Av. Siempre viva 777";
		final Integer CODIGO_POSTAL2 = 5000;
		final String LOCALIDAD2 = "Buenos Aires";
		final Provincia PROVINCIA2 = Provincia.BUENOS_AIRES;
		
		//CONTACTO 3
		final String NOMBRE_Y_APELLIDO3 = "Homer Simpson";
		final String CODIGO_PAIS3 = "001";
		final String CODIGO_AREA3 = "72";
		final String NRO_CELULAR3 = "77178983";
		final String CELULAR3 = Contacto.crearSuNumeroCelular(CODIGO_PAIS3, CODIGO_AREA3, NRO_CELULAR3);
		final String MAIL3 = "duff@mail.com.ar";
		final String DIRECCION3 = "Av. Siempre viva 3928";
		final Integer CODIGO_POSTAL3 = 1714;
		final String LOCALIDAD3 = "Buenos Aires";
		final Provincia PROVINCIA3 = Provincia.SALTA;
		
		final Integer CANTIDAD_DE_CONTACTO_ESPERADA = 3;

		// Ejecucion
		Empresa empresa = new Empresa("Oeste Cable Color");
		Contacto contacto = new Contacto(NOMBRE_Y_APELLIDO,CELULAR,MAIL,DIRECCION,CODIGO_POSTAL,LOCALIDAD,PROVINCIA);
		empresa.agregarUnContacto(contacto);
		
		contacto = new Contacto(NOMBRE_Y_APELLIDO2,CELULAR2,MAIL2,DIRECCION2,CODIGO_POSTAL2,LOCALIDAD2,PROVINCIA2);
		empresa.agregarUnContacto(contacto);
		
		
		contacto = new Contacto(NOMBRE_Y_APELLIDO3,CELULAR3,MAIL3,DIRECCION3,CODIGO_POSTAL3,LOCALIDAD3,PROVINCIA3);
		empresa.agregarUnContacto(contacto);
		
		Contacto candidato = empresa.buscarCandidato();
		

		// Validacion
		assertEquals(CANTIDAD_DE_CONTACTO_ESPERADA, empresa.darLaCantidadDeContactosYaRegistrados());
		assertFalse(candidato.isEsCliente());
		assertTrue(candidato.isDeseaSerLlamado());
		assertTrue(empresa.elCodigoPostalExisteEnLasZonasDeCobertura(candidato.getCodigoPostal()));
	}
	
	
	
	
	@Test
	public void queSePuedaRegistraUnaLlamadaExitosaAlContactoCandidato() {
		//Preparacion de datos
		//CONTACTO 1		
		final String NOMBRE_Y_APELLIDO = "Jesi Salva";
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		final String CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
		final String MAIL = "jesibel@mail.com.ar";
		final String DIRECCION = "Av. Siempre viva 123";
		final Integer CODIGO_POSTAL = 1000;
		final String LOCALIDAD = "CABA";
		final Provincia PROVINCIA = Provincia.CABA;
		
		//CONTACTO 2
		final String NOMBRE_Y_APELLIDO2 = "Bart Simpson";
		final String CODIGO_PAIS2 = "001";
		final String CODIGO_AREA2 = "22";
		final String NRO_CELULAR2 = "77171723";
		final String CELULAR2 = Contacto.crearSuNumeroCelular(CODIGO_PAIS2, CODIGO_AREA2, NRO_CELULAR2);
		final String MAIL2 = "elbarto@mail.com.ar";
		final String DIRECCION2 = "Av. Siempre viva 777";
		final Integer CODIGO_POSTAL2 = 5000;
		final String LOCALIDAD2 = "Buenos Aires";
		final Provincia PROVINCIA2 = Provincia.BUENOS_AIRES;
		
		//CONTACTO 3
		final String NOMBRE_Y_APELLIDO3 = "Homer Simpson";
		final String CODIGO_PAIS3 = "001";
		final String CODIGO_AREA3 = "72";
		final String NRO_CELULAR3 = "77178983";
		final String CELULAR3 = Contacto.crearSuNumeroCelular(CODIGO_PAIS3, CODIGO_AREA3, NRO_CELULAR3);
		final String MAIL3 = "duff@mail.com.ar";
		final String DIRECCION3 = "Av. Siempre viva 3928";
		final Integer CODIGO_POSTAL3 = 1714;
		final String LOCALIDAD3 = "Buenos Aires";
		final Provincia PROVINCIA3 = Provincia.SALTA;
				
		//DATOS DE LA LLAMADA REALIZADA
		final Boolean LA_LLAMADA_FUE_EXITOSA = true;
		final String OBSERVACIONES_DE_LA_LLAMADA =  "El candidato quiere ser cliente por lo tanto no lo volveremos a llamar.";
		final Boolean DESEA_SER_LLAMADO_CANDIDATO = false; 
		final Integer CANTIDAD_DE_LLAMADA_ESPERADA = 1;
		
		// Ejecucion
		Empresa empresa = new Empresa("Oeste Cable Color");
		Contacto contacto = new Contacto(NOMBRE_Y_APELLIDO,CELULAR,MAIL,DIRECCION,CODIGO_POSTAL,LOCALIDAD,PROVINCIA);
		empresa.agregarUnContacto(contacto);
		
		contacto = new Contacto(NOMBRE_Y_APELLIDO2,CELULAR2,MAIL2,DIRECCION2,CODIGO_POSTAL2,LOCALIDAD2,PROVINCIA2);
		empresa.agregarUnContacto(contacto);
		
		
		contacto = new Contacto(NOMBRE_Y_APELLIDO3,CELULAR3,MAIL3,DIRECCION3,CODIGO_POSTAL3,LOCALIDAD3,PROVINCIA3);
		empresa.agregarUnContacto(contacto);
		Contacto candidato = empresa.buscarCandidato();
		empresa.realizarLallamadaAlCandidato(candidato, LA_LLAMADA_FUE_EXITOSA, OBSERVACIONES_DE_LA_LLAMADA, DESEA_SER_LLAMADO_CANDIDATO);
		
		//Validacion
		assertFalse(candidato.isDeseaSerLlamado());
		assertEquals(CANTIDAD_DE_LLAMADA_ESPERADA, candidato.darLaCantidadTotalDeLlamadas());
		assertTrue(candidato.obtenerUnaLlamadaDeterminada(0).getFueExitosa());
		assertEquals(OBSERVACIONES_DE_LA_LLAMADA, candidato.obtenerUnaLlamadaDeterminada(0).getObservaciones());
		
	}
		
	
	@Test
	public void queAlBuscarUnCandidatoSePuedaMostrarLosDatosDelMismo() {
		// Preparacion de datos
		// CONTACTO 1
		final String NOMBRE_Y_APELLIDO = "Jesi Salva";
		final String CODIGO_PAIS = "54";
		final String CODIGO_AREA = "11";
		final String NRO_CELULAR = "61234293";
		final String CELULAR = Contacto.crearSuNumeroCelular(CODIGO_PAIS, CODIGO_AREA, NRO_CELULAR);
		final String MAIL = "jesibel@mail.com.ar";
		final String DIRECCION = "Av. Siempre viva 123";
		final Integer CODIGO_POSTAL = 1000;
		final String LOCALIDAD = "CABA";
		final Provincia PROVINCIA = Provincia.CABA;

		// CONTACTO 2
		final String NOMBRE_Y_APELLIDO2 = "Bart Simpson";
		final String CODIGO_PAIS2 = "001";
		final String CODIGO_AREA2 = "22";
		final String NRO_CELULAR2 = "77171723";
		final String CELULAR2 = Contacto.crearSuNumeroCelular(CODIGO_PAIS2, CODIGO_AREA2, NRO_CELULAR2);
		final String MAIL2 = "elbarto@mail.com.ar";
		final String DIRECCION2 = "Av. Siempre viva 777";
		final Integer CODIGO_POSTAL2 = 5000;
		final String LOCALIDAD2 = "Buenos Aires";
		final Provincia PROVINCIA2 = Provincia.BUENOS_AIRES;

		// CONTACTO 3
		final String NOMBRE_Y_APELLIDO3 = "Homer Simpson";
		final String CODIGO_PAIS3 = "001";
		final String CODIGO_AREA3 = "72";
		final String NRO_CELULAR3 = "77178983";
		final String CELULAR3 = Contacto.crearSuNumeroCelular(CODIGO_PAIS3, CODIGO_AREA3, NRO_CELULAR3);
		final String MAIL3 = "duff@mail.com.ar";
		final String DIRECCION3 = "Av. Siempre viva 3928";
		final Integer CODIGO_POSTAL3 = 1714;
		final String LOCALIDAD3 = "Buenos Aires";
		final Provincia PROVINCIA3 = Provincia.SALTA;

		final Integer CANTIDAD_DE_CONTACTO_ESPERADA = 3;

		// Ejecucion
		Empresa empresa = new Empresa("Oeste Cable Color");
		Contacto contacto = new Contacto(NOMBRE_Y_APELLIDO, CELULAR, MAIL, DIRECCION, CODIGO_POSTAL, LOCALIDAD,
				PROVINCIA);
		empresa.agregarUnContacto(contacto);

		contacto = new Contacto(NOMBRE_Y_APELLIDO2, CELULAR2, MAIL2, DIRECCION2, CODIGO_POSTAL2, LOCALIDAD2,
				PROVINCIA2);
		empresa.agregarUnContacto(contacto);

		contacto = new Contacto(NOMBRE_Y_APELLIDO3, CELULAR3, MAIL3, DIRECCION3, CODIGO_POSTAL3, LOCALIDAD3,
				PROVINCIA3);
		empresa.agregarUnContacto(contacto);

		Contacto candidato = empresa.buscarCandidato();

		String informacionEsperadaDelCandidato = "Nombre y apellido: " + candidato.getNombreYApellido() + 
												"\n Celular: " + candidato.getNumeroCelular() + 
												"\n Mail: " + candidato.getMail() + 
												"\n Direccion: " + candidato.getDireccion() + 
												"\n Codigo Postal: " + candidato.getCodigoPostal() + 
												"\n Localidad: " + candidato.getLocalidad() + 
												"\n Provincia: " + candidato.getProvincia() +
												"\n Llamadas: " + Arrays.toString(candidato.getLlamadas());

		// Validación
		assertEquals(informacionEsperadaDelCandidato, candidato.toString()); 
																				
	}

	
	
	
	
	
	
	
	
	

}
