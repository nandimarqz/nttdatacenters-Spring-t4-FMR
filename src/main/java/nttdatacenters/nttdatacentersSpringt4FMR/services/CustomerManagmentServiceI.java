package nttdatacenters.nttdatacentersSpringt4FMR.services;

import java.util.List;

import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.CustomerNotFound;
import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.ExistingCustomer;
import nttdatacenters.nttdatacentersSpringt4FMR.persistence.Customer;

/**
 * Interfaz para el servicio de gestion de clientes
 * 
 * @author nandi
 *
 */
public interface CustomerManagmentServiceI {

	/**
	 * Inserta un nuevo cliente
	 * 
	 * @param customer
	 * @throws ExistingCustomer 
	 */
	public void insertNewCustomer(Customer customer) throws ExistingCustomer;

	/**
	 * Actualiza un cliente existente
	 * 
	 * @param customer
	 * @throws CustomerNotFound 
	 */
	public void updateCustomer(Customer customer) throws CustomerNotFound;

	/**
	 * Borra un cliente existente
	 * 
	 * @param customer
	 */
	public void deleteCustomer(Customer customer);

	/**
	 * Borra un cliente existente con el dni pasado por parametro
	 * 
	 * @param customerId
	 */
	public void deleteCustomerById(Long customerId);

	/**
	 * Devulve un cliente existente con el id pasado por parametro
	 * 
	 * @param customerId
	 * @return Customer
	 */
	public Customer getCustomerById(Long customerId);

	/**
	 * Devuelve un cliente existente con el dni pasado por parametro
	 * 
	 * @param dni
	 * @return Customer
	 * @throws CustomerNotFound 
	 */
	public Customer getCustomerByDNI(String dni) throws CustomerNotFound;

	/**
	 * Devuelve una lista con todos los clientes existentes
	 * 
	 * @return
	 */
	public List<Customer> getAllCustomers();

}
