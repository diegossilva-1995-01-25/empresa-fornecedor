package br.com.diegossilva.empresa_fornecedor.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.Empresa;
import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedorId;
import br.com.diegossilva.empresa_fornecedor.demo.entity.Fornecedor;

@Repository
public interface EmpresaFornecedorRepository extends JpaRepository<EmpresaFornecedor, EmpresaFornecedorId> {
    
    List<EmpresaFornecedor> findAllByEmpresaCnpj(Empresa e);
    
    List<EmpresaFornecedor> findAllByFornecedorCpfCnpj(Fornecedor f);
    
    EmpresaFornecedor findByEmpresaCnpjAndFornecedorCpfCnpj(Empresa e, Fornecedor f);
    
}