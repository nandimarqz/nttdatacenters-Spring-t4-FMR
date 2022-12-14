package nttdatacenters.nttdatacentersSpringt4FMR.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.CustomerNotFound;
import nttdatacenters.nttdatacentersSpringt4FMR.exceptions.ExistingCustomer;
import nttdatacenters.nttdatacentersSpringt4FMR.persistence.Customer;
import nttdatacenters.nttdatacentersSpringt4FMR.services.CustomerManagmentServiceI;

/**
 * Controlador de los clientes
 * 
 * @author nandi
 *
 */
@Controller
@RequestMapping("/*")
public class CustomerController {

	/** Servicio de gestion de los clientes */
	@Autowired
	CustomerManagmentServiceI cms;
	
	/** Logger para la clase */
	final Logger CCLOG = LoggerFactory.getLogger(CustomerController.class);
	
	
	/**
	 * Lleva a la pagina home
	 * 
	 * @return String
	 */
	@RequestMapping("home")
	public String home() {
		
		CCLOG.debug("Redirigiendo al index.html");
		
		return "index";
		
	}
	
	/**
	 * Muestra la tabla con todos los clientes de la BBDD
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("showCustomers")
	public String tableCustomers(Model model) {
		CCLOG.debug("Redirigiendo al mostrarClientes.html");
		
		model.addAttribute("customers", cms.getAllCustomers());
		
		CCLOG.debug("Enviando clientes al front");
		
		return "layouts/mostrarClientes";
		

	}

	/**
	 * Lleva a la pagina del formulario de añadir clientes
	 * 
	 * @return String
	 */
	@RequestMapping("addCustomer")
	public String formAddCustomer() {
		
		CCLOG.debug("Redirigiendo al addCliente.html");
		
		return "layouts/addCliente";
		

	}
	
	/**
	 * Recoge la peticion y añade el cliente recogido del front
	 * 
	 * @param c
	 * @return String
	 */
	@PostMapping("addNewCustomer")
	public String addCustomer( @ModelAttribute("customer") Customer c) {
		
		CCLOG.debug("Añadiendo cliente");
		
		try {
			cms.insertNewCustomer(c);
		} catch (ExistingCustomer e) {
			
			CCLOG.debug(e.getMessage());
			
		}
		
		return "layouts/addCliente";
		

	}
	
	/**
	 * Lleva a la pagina de consulta por dni
	 * 
	 * @return String
	 */
	@RequestMapping("showCustomerByDni")
	public String showGetCustomerByDni() {
		
		CCLOG.debug("Redirigiendo al showCustomerByDni.html");
		
		return "layouts/showCustomerByDni";
		
	}
	
	/**
	 * Recoge el dni pasado por parametro y muestra el cliente con el dni recogido
	 * 
	 * @param dni
	 * @param model
	 * @return String
	 */
	@PostMapping("showCustomer")
	public String getCustomerDni(@RequestParam String dni, Model model) {
		
		CCLOG.debug("Mostrando cliente con el dni {}", dni);
		
		try {
			model.addAttribute("customer", cms.getCustomerByDNI(dni));
		} catch (CustomerNotFound e) {
			CCLOG.debug(e.getMessage());
		}
		
		return "layouts/showCustomerByDni";
		
		
		
	}
	
}
