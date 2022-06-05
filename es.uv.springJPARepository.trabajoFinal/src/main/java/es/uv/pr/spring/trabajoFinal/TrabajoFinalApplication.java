package es.uv.pr.spring.trabajoFinal;

import java.util.ArrayList;

import java.util.Optional;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Trabajo;
import es.uv.pr.spring.trabajoFinal.domain.Validador;
import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.repositories.TrabajosRepository;
import es.uv.pr.spring.trabajoFinal.repositories.ValidadoresRepository;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TrabajoFinalApplication implements ApplicationRunner {

	@Autowired
	private ProductoresRepository productoresRepository;

	@Autowired
	private ValidadoresRepository validadoresRepository;

	@Autowired
	private TrabajosRepository trabajosRepository;


	public static void main(String[] args) {
		SpringApplication.run(TrabajoFinalApplication.class, args);
	}

	@Override
	public void run( ApplicationArguments args ) throws Exception {
		
		//Llamamos al metodo que nos cerará las instancias de prueba de la BD
		if(productoresRepository.findAll() == null || validadoresRepository.findAll() == null) {
				generateProductores(10);
				generateValidadores(10);
				generateTrabajos(10);
		}
	
		//System.exit(0);
	}

	public String randomString(int length) {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(length)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		// System.out.println(generatedString);
		return generatedString;
	}

	public void generateProductores(int numProds) {
		Random rand = new Random(); // instance of random class

		String[] nombres = new String[] { "David", "Joan", "María", "Irene", "Elena", "Alexandre", "Núria", "Pepe",
				"Alejandro", "Joaquín" };
		String[] apellidos = new String[] { "Gómez", "Álvarez", "Moreno", "López", "Castillo", "Martínez", "Núñez",
				"Mas", "Morán", "LLiso" };
		String[] emails = new String[] { "davidnumar@gmail.com", "masmoran@gmail.com", "elenuki999@gmail.com",
				"irorigabrad44@hotmail.es", "allstendres@gmail.com", "jolufu77@etse.uv.es",
				"antonio.soriano.asensi@hotmail.es", "juangutierrez.UV@gmail.com" };

		ArrayList<Productor> productores = new ArrayList<Productor>();

		for (int i = 0; i < numProds; i++) {

			Productor p = new Productor();
			p.setId(i);
			int randomNombre = rand.nextInt(nombres.length);
			int randomApellido1 = rand.nextInt(apellidos.length);
			int randomApellido2 = rand.nextInt(apellidos.length);
			double randomCuota = rand.nextDouble(20000);
			int randomEmail = rand.nextInt(emails.length);
			String randomNIF = Integer.toString(new Random().nextInt((99999999 - 11111111) + 1) + 11111111)
					+ randomString(1).toUpperCase();
			// System.out.println(randomNIF);

			p.setNombre(nombres[randomNombre]);
			p.setApellidos(apellidos[randomApellido1] + " " + apellidos[randomApellido2]);
			p.setCuota(randomCuota);
			p.setEmail(emails[randomEmail]);
			p.setNIF(randomNIF);

			// poner estado random
			p.setEstado(Productor.Estado.A);
			// poner tipo random
			p.setTipo(Productor.Tipo.F);
			p.setPassword(new BCryptPasswordEncoder().encode("1234"));
			productores.add(p);
		}

		for (Productor p : productores) {
			System.out.println(p.toString());
		}

		this.productoresRepository.saveAll(productores);
	}

	public void generateValidadores(int numVals) {
		Random rand = new Random(); // instance of random class

		String[] nombres = new String[] { "David", "Joan", "María", "Irene", "Elena", "Alexandre", "Núria", "Pepe",
				"Alejandro", "Joaquín" };
		String[] apellidos = new String[] { "Gómez", "Álvarez", "Moreno", "López", "Castillo", "Martínez", "Núñez",
				"Mas", "Morán", "LLiso" };
		String[] emails = new String[] { "davidnumar@gmail.com", "masmoran@gmail.com", "elenuki999@gmail.com",
				"irorigabrad44@hotmail.es", "allstendres@gmail.com", "jolufu77@etse.uv.es",
				"antonio.soriano.asensi@hotmail.es", "juangutierrez.UV@gmail.com" };

		ArrayList<Validador> validadores = new ArrayList<Validador>();

		for (int i = 0; i < numVals; i++) {

			Validador v = new Validador();
			v.setId(i);
			int randomNombre = rand.nextInt(nombres.length);
			int randomApellido1 = rand.nextInt(apellidos.length);
			int randomApellido2 = rand.nextInt(apellidos.length);
			int randomEmail = rand.nextInt(emails.length);
			
			v.setNombre(nombres[randomNombre]);
			v.setApellidos(apellidos[randomApellido1] + " " + apellidos[randomApellido2]);
			v.setEmail(emails[randomEmail]);
			v.setPassword(new BCryptPasswordEncoder().encode("1234"));
			validadores.add(v);
		}

		for (Validador v : validadores) {
			System.out.println(v.toString());
		}

		this.validadoresRepository.saveAll(validadores);
	}

	public void generateTrabajos(int numTrab) {

		ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();

		for (int i = 0; i < numTrab; i++) {

			Optional<Productor> p = productoresRepository.findById(i);

			Optional<Validador> v = validadoresRepository.findById(i);
			
			if (p.isEmpty() || v.isEmpty())
				return;

			Trabajo t = new Trabajo();
			t.setId(i);
			t.setProductor(p.get());
			t.setValidador(v.get());
			t.setNum_desc(0);
			t.setNum_prev(0);

			trabajos.add(t);
		}

		for (Trabajo t : trabajos) {
			System.out.println(t.toString());
		}

		this.trabajosRepository.saveAll(trabajos);
	}

}
