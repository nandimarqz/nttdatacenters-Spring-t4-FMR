package nttdatacenters.nttdatacentersSpringt4FMR.persistence;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa la tabla de clientes en la BBDD
 * 
 * @author nandi
 *
 */
@Entity
@Table(name="NTTDATA_T1_HIBERNATE_CUSTOMER")
public class Customer implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	/** ID del cliente en base de datos */
	private Long id;
	
	/** Nombre del cliente */
	private String name;
	
	/** Primer apellido del cliente */
	private String firstSurname;
	
	/** Segundo apellido del cliente */
	private String secondSurname;
	
	/** DNI del cliente */
	private String dni;
	
	/** Logger para la clase */
	private static final Logger CUSTOMERLOG = LoggerFactory.getLogger(Customer.class);

	/**
	 * Devuelve el ID del clietne
	 * 
	 * @return Long
	 */
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		CUSTOMERLOG.debug("Obteniendo el ID del cliente {}", this.dni);
		return id;
	}

	/**
	 * Establece el ID del cliente por el id pasado por parametro
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		CUSTOMERLOG.debug("Estableciendo el ID del cliente {}", this.dni);
		this.id = id;
	}
	
	/**
	 * Devuelve el nombre del cliente
	 * 
	 * @return String
	 */
	@Column(name="NAME")
	public String getName() {
		CUSTOMERLOG.debug("Obteniendo el nombre del cliente {}", this.dni);
		return name;
	}

	/**
	 * Establece el nombre del cliente por el nombre pasado por parametro
	 * 
	 * @param name
	 */
	public void setName(String name) {
		CUSTOMERLOG.debug("Estableciendo el nombre del cliente {}", this.dni);
		this.name = name;
	}

	/**
	 * Devuelve el primer apellido del cliente
	 * 
	 * @return String
	 */
	@Column(name="FIRST_SURNAME")
	public String getFirstSurname() {
		CUSTOMERLOG.debug("Obteniendo el primer apellido del cliente {}", this.dni);
		return firstSurname;
	}

	/**
	 * Establece el primer apellido del cliente por el apellido pasado por parametro
	 * 
	 * @param firstSurname
	 */
	public void setFirstSurname(String firstSurname) {
		CUSTOMERLOG.debug("Estableciendo el primer apellido del cliente {}", this.dni);
		this.firstSurname = firstSurname;
	}

	/**
	 * Devuelve el segundo apellido del cliente
	 * 
	 * @return String
	 */
	@Column(name="SECOND_SURNAME")
	public String getSecondSurname() {
		CUSTOMERLOG.debug("Obteniendo el segundo apellido del cliente {}", this.dni);
		return secondSurname;
	}

	/**
	 * Establece el segundo apellido del cliente por el apellido pasado por parametro
	 * 
	 * @param secondSurname
	 */
	public void setSecondSurname(String secondSurname) {
		CUSTOMERLOG.debug("Estableciendo el segundo apellido del cliente {}", this.dni);
		this.secondSurname = secondSurname;
	}

	/**
	 * Devuelve el DNI del cliente
	 * 
	 * @return String
	 */
	@Column(name="DNI", unique=true, nullable=false, length=9)
	public String getDni() {
		CUSTOMERLOG.debug("Obteniendo el DNI del cliente {}", this.dni);
		return dni;
	}

	/**
	 * Establece el DNI del cliente por el DNI pasado por parametro
	 * @param dni
	 */
	public void setDni(String dni) {
		CUSTOMERLOG.debug("Estableciendo el DNI del cliente {}", this.dni);
		this.dni = dni;
	}
	
	@Override
	public int hashCode() {
		CUSTOMERLOG.debug("Obteniendo el HASH del cliente {}", this.dni);
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", firstSurname=" + firstSurname + ", secondSurname="
				+ secondSurname + ", dni=" + dni + "]";
	}
	
	
	
}
