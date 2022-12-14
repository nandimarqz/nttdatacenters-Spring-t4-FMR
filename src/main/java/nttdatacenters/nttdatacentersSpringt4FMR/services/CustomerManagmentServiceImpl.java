package nttdatacenters.nttdatacentersSpringt4FMR.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.CustomerNotFound;
import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.ExistingCustomer;
import nttdatacenters.nttdatacentersSpringt4FMR.persistence.Customer;
import nttdatacenters.nttdatacentersSpringt4FMR.persistence.repository.CustomerRepositoyI;

/**
 * Implementacion de la interfaz del servicio de gestion de clientes
 * 
 * @author nandi
 *
 */
@Service
public class CustomerManagmentServiceImpl implements CustomerManagmentServiceI {

	/** Repositorio de los clientes */
	@Autowired
	private CustomerRepositoyI cr;

	/** Logger para la clase */
	private static final Logger CUSTOMERMSLOG = LoggerFactory.getLogger(CustomerManagmentServiceImpl.class);

	@Override
	public void insertNewCustomer(Customer customer) throws ExistingCustomer {

		// Si el DNI cliente pasado por parametro no es nulo y el id del cliente es nulo
		// entra en la condicion
		if (customer != null && customer.getId() == null && customer.getDni() != null) {
			CUSTOMERMSLOG.debug("Insertando un nuevo cliente DNI: {}", customer.getDni());

			// Si el cliente que obtiene es igual a null es que no existe ningun
			// cliente con su dni
			// Entra en la condicion y lo inserta si es distinto a null lanza una excepcion
			if (cr.findByDni(customer.getDni()) == null) {

				// inserta el cliente
				cr.save(customer);

			} else {

				CUSTOMERMSLOG.info("Existe un cliente con el DNI: {}", customer.getDni());
				throw new ExistingCustomer("Ya existe un cliente con el mismo DNI el cliente no se insertara");

			}

		}

	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerNotFound {

		// Si el el id del cliente no es nulo
		// entra en la condicion
		if (customer != null && customer.getId() != null) {
			CUSTOMERMSLOG.debug("Actualizando el cliente con DNI: {}", customer.getDni());

			// Si el cliente que devuelve con el id del cliente pasado por parametro
			// es distinto a null
			// entra en la condicion y actualiza el cliente
			if (cr.findById(customer.getId()) != null) {

				// actualiza el cliente
				cr.save(customer);

			} else {

				CUSTOMERMSLOG.info("No se ha encontrado cliente con DNI: {}", customer.getDni());
				throw new CustomerNotFound("No se encuentra el cliente que se quiere actualizar");

			}

		}

	}

	@Override
	public void deleteCustomer(Customer customer) {

		// Si el cliente pasado por parametro no es nulo y el id del cliente no es nulo
		// entra en la condicion
		if (customer != null && customer.getId() != null) {
			CUSTOMERMSLOG.debug("Eliminando el cliente con DNI: {}", customer.getDni());
			
			// borra el cliente
			cr.delete(customer);

		}

	}

	@Override
	public Customer getCustomerById(Long customerId) {

		Customer customer = null;

		// Si el id pasado por paraemtro no es null entra en la condicion
		if (customerId != null) {
			CUSTOMERMSLOG.debug("Obteniendo el cliente con ID: {}", customerId);

			// obtiene el cliente
			customer = cr.findById(customerId).get();

		}

		// Devuelve el cliente
		return customer;

	}

	@Override
	public List<Customer> getAllCustomers() {

		CUSTOMERMSLOG.debug("Obteniendo todos los clientes");

		List<Customer> customerList = null;

		// obtiene todos los clientes
		customerList = cr.findAll();

		return customerList;
	}

	@Override
	public Customer getCustomerByDNI(String dni) throws CustomerNotFound {

		Customer customer = null;

		// Si el dni pasado por parametro no es nulo y la longitud del dni es 9 entra en
		// la condicion
		if (dni != null && dni.length() == 9) {
			CUSTOMERMSLOG.debug("Obteniendo el cliente con DNI: {}", dni);

			if (cr.findByDni(dni) != null) {

				// obtiene el cliente
				customer = cr.findByDni(dni);

			} else {

				CUSTOMERMSLOG.info("No se ha encontrado cliente con DNI: {}", dni);
				throw new CustomerNotFound("No se ha encontrado el cliente con DNI: " + dni);

			}

		}

		// Devuelve el cliente
		return customer;
	}

	@Override
	public void deleteCustomerById(Long customerId) {

		// Si el id no es null entra en la condicion
		if (customerId != null) {
			CUSTOMERMSLOG.debug("Eliminando el cliente con Id: {}", customerId);

			// borra el cliente con el id
			cr.deleteById(customerId);

		}

	}

}
