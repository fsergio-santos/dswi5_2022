package com.sistema.models.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="TAB_USUARIO")
public class Usuario implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 9015489403187296128L;

	private Long    id ;
	private String  username;
	private String  password;
	private String  confirmPassword;
	private String  email;
	private Integer failedLogin = 0;
	private Date    lastLogin;
	private String  foto;
	private String  contentType;
	private boolean ativo = true;
	
	private List<Role> roles;
	
	
		
	public Usuario() {
		
	}
	
    public Usuario(Long id, String username, String password, String confirmPassword, String email, Integer failedLogin,
			Date lastLogin, String foto, String contentType, boolean ativo) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
		this.failedLogin = failedLogin;
		this.lastLogin = lastLogin;
		this.foto = foto;
		this.contentType = contentType;
		this.ativo = ativo;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USUARIO")
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="ATIVO")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

    @Column(name="EMAIL_USUARIO", length = 100, nullable = false, unique = true )
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="FAILED_LOGIN", nullable = true)
	public Integer getFailedLogin() {
		return failedLogin;
	}


	public void setFailedLogin(Integer failedLogin) {
		this.failedLogin = failedLogin;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name="LAST_LOGIN", nullable = true )
	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

    @Column(name="FOTO_USUARIO",nullable = true) 
	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}

    @Column(name="CONTENTTYPE_USUARIO")
	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	@Column(name="SENHA_USUARIO", nullable = false, length = 100)
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    @Column(name="NOME_USUARIO", nullable = false, length = 100)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return ativo;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return ativo;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return ativo;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return ativo;
	}
	
	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Size(min=1,message = "Informar pelo menos um {min} papel para o usuário")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="TAB_USUARIO_ROLE",
    			joinColumns = @JoinColumn(name="ID_USUARIO"),
    			inverseJoinColumns = @JoinColumn(name="ID_ROLE"))
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", email=" + email + ", failedLogin=" + failedLogin + ", lastLogin=" + lastLogin
				+ ", foto=" + foto + ", contentType=" + contentType + ", ativo=" + ativo + "]";
	}

	
     
}
