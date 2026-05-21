package br.com.diegossilva.empresa_fornecedor.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@IdClass(EmpresaFornecedorId.class)
public class EmpresaFornecedor {

    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cnpj", nullable = false)
    private Empresa empresa;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cpf_cnpj", nullable = false)
    private Fornecedor fornecedor;
    
}