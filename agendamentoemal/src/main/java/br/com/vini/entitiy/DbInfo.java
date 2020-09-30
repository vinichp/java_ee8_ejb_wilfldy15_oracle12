package br.com.vini.entitiy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "DB_INFO2")
public class DbInfo implements Serializable{
			
	@Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_OUT_QUEUE")	
	private Long id;
	
	@Column
	private String name;
	
	@Column
	//@NotBlank(message = "{agendamentoEmail.email.vazio}")
	private String version;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	
	
	
}
