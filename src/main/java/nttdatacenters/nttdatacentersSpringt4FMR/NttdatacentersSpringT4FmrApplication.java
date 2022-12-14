package nttdatacenters.nttdatacentersSpringt4FMR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.CustomerNotFound;
import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.ExistingCustomer;
import nttdatacenters.nttdatacentersSpringt4FMR.persistence.Customer;
import nttdatacenters.nttdatacentersSpringt4FMR.services.CustomerManagmentServiceI;

@SpringBootApplication
public class NttdatacentersSpringT4FmrApplication implements CommandLineRunner {

	/** Inyecta el servicio de gestion de clientes */
	@Autowired
	CustomerManagmentServiceI customerService ;
	
	
	public static void main(String[] args) {
		SpringApplication.run(NttdatacentersSpringT4FmrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		/** Logger para la clase */
		final Logger MAINLOG = LoggerFactory.getLogger(NttdatacentersSpringT4FmrApplication.class);

			MAINLOG.debug("Inicio del programa");

		
			
			/** Cliente 1 */
			Customer c1 = new Customer();
			c1.setDni("11111111L");
			c1.setName("Fernando");
			c1.setFirstSurname("Márquez");
			c1.setSecondSurname("Rodríguez");

			/** Cliente 2 */
			Customer c2 = new Customer();
			c2.setDni("22222222L");
			c2.setName("Juan Alejandro");
			c2.setFirstSurname("Téllez");
			c2.setSecondSurname("Rubio");

			/**
			 * Cliente 3 (Este cliente cuando se inserte no deberia insertarse, ya que tiene
			 * el DNI del Cliente 1)
			 */
			Customer c3 = new Customer();
			c3.setDni("11111111L");
			c3.setName("Pepe");
			c3.setFirstSurname("Villuela");
			c3.setSecondSurname("Torres");

			// Consume el servicio de gestion de clientes e inserta los clientes
			try {
				customerService.insertNewCustomer(c1);
				customerService.insertNewCustomer(c2);
				customerService.insertNewCustomer(c3);
			} catch (ExistingCustomer e) {

				MAINLOG.error(e.getMessage());
				System.out.println(e.getMessage());
			}

			/**
			 * Cliente 4 (Es el cliente que devuelve el servicio con el DNI pasado por
			 * parametro)
			 */
			Customer c4 = new Customer();
			try {
				c4 = customerService.getCustomerByDNI("11111111L");
			} catch (CustomerNotFound e) {

				MAINLOG.error(e.getMessage());
				System.out.println(e.getMessage());
			}

			// Cambia el DNI del cliente recuperado
			c4.setDni("22222222X");

			// Hace la actualizacion del cliente
			try {
				customerService.updateCustomer(c4);
				customerService.updateCustomer(null);
			} catch (CustomerNotFound e) {

				MAINLOG.error(e.getMessage());
				System.out.println(e.getMessage());
			}

			/**
			 * Cliente 5(Creamos un cliente que no exista en la BBDD para comprobar si
			 * actualizamos que ocurre. Si no lo controla si no existe, lo inserta en la
			 * BBDD)
			 */
			Customer c5 = new Customer();
			c5.setDni("11111111L");
			c5.setId(3L);
			c5.setName("Pepe");
			c5.setFirstSurname("Villuela");
			c5.setSecondSurname("Torres");

			// Hace el actualizado del cliente en la BBDD
			// (Al tenerlo controlado en el servicio saltaria la excepcion)
			try {
				customerService.updateCustomer(c5);
			} catch (CustomerNotFound e) {

				MAINLOG.error(e.getMessage());
				System.out.println(e.getMessage());
			}

			/**
			 * Cliente 6 (Es el cliente que devuelve el servicio con el id que se pasa por
			 * parametro)
			 */
			Customer c6 = customerService.getCustomerById(1L);

			// Mostramos el cliente
			System.out.println(c6);
			

			// Mostramos todos los clientes
			System.out.println(customerService.getAllCustomers());

//			//Borramos los Clientes
//			customerService.deleteCustomer(c2);
//			customerService.deleteCustomerById(1L);

			MAINLOG.debug("Fin del programa");
	}
}
