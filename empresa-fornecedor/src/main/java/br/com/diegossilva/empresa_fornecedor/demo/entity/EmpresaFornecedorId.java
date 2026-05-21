package br.com.diegossilva.empresa_fornecedor.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class EmpresaFornecedorId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String empresa;  // matches Empresa's @Id type
    private String fornecedor;  // matches Fornecedor's @Id type
    
    public EmpresaFornecedorId() {}
    
    public EmpresaFornecedorId(String empresa, String fornecedor) {
        this.empresa = empresa;
        this.fornecedor = fornecedor;
    }
    
    public String getEmpresa() {
        return empresa;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String getFornecedor() {
        return fornecedor;
    }
    
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpresaFornecedorId that = (EmpresaFornecedorId) o;
        return Objects.equals(empresa, that.empresa) &&
               Objects.equals(fornecedor, that.fornecedor);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(empresa, fornecedor);
    }
}