package nttdatacenters.nttdatacentersSpringt4FMR.exceptions;

/**
 * Excepcion para cuando un cliente exista
 * 
 * @author nandi
 *
 */
public class ExistingCustomer extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param msg
	 */
	public ExistingCustomer(String msg) {
		super(msg);

	}

}
