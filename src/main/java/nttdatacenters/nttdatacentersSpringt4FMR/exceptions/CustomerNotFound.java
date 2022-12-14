package nttdatacenters.nttdatacentersSpringt4FMR.exceptions;

/**
 * Excepcion para cuando no se encuentra un cliente
 * 
 * @author nandi
 *
 */
public class CustomerNotFound extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param msg
	 */
	public CustomerNotFound(String msg) {
		super(msg);
		
		
	}

}
