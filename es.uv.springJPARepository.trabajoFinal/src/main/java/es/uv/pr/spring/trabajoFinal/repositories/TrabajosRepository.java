package es.uv.pr.spring.trabajoFinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uv.pr.spring.trabajoFinal.domain.Trabajo;



public interface TrabajosRepository extends JpaRepository<Trabajo, Integer> {
	
	//(NO HACER CASO)

	/*
	 * Subir un fichero de datos (PF3). Junto con el fichero de texto, se indicará
	 * el título, descripción y palabras clave. Para emular la carga de ficheros
	 * grandes, se indicará de manera manual un tamaño de fichero (en MB) que no
	 * tiene porqué coincidir con el tamaño real del fichero. Se comprobará el
	 * formato y que no se exceda la cuota anual. El fichero se creará en estado
	 * pendiente de revisión (por un validador). Requerirá autenticación y que su
	 * estado sea activo.
	 */

	// JPA almacenara el productor que lo sube , el validador que lo tiene que
	// validar y lo vinculara con el Mongo
	//void subirFichero(Trabajo t);

	// faltaría implementar el de mongo en MongoRepository
	// Alamacenar la información del fichero

	// Consultar el listado de ficheros de datos del productor (PF4). Requerirá
	// autenticación y que su estado sea activo.
	//

	//List<Trabajo> findByProductor(Productor p);

	// Modificar la información de un fichero de datos del productor (PF5). Se
	// podrán
	// actualizar el título, descripción y palabras clave. Requerirá autenticación y
	// que su
	// estado sea activo.

	// JPA comprobara que dicho fichero exixta y que su productor esté en modo
	// activo
	//Trabajo updateFicheroProductor(Trabajo t, Productor p);

	// faltaria la parte de MongoRepository
	// Actualizar la parte de la informacion del fichero

	// Eliminar un fichero de datos del productor (PF6). Requerirá autenticación y
	// que su
	// estado sea activo.

	// JPA comprobara que dicho fichero exixta y eliminará la entrada y que su productor este en modo
	// activo
	//void deleteFicheroProductor(Trabajo t, Productor p);

}
