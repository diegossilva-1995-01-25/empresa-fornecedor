package br.com.diegossilva.empresa_fornecedor.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedorId;

@Repository
public interface EmpresaFornecedorRepository extends JpaRepository<EmpresaFornecedor, EmpresaFornecedorId> {
    
    List<EmpresaFornecedor> findAllByEmpresaCnpj(String cnpj);
    
    List<EmpresaFornecedor> findAllByFornecedorCpfCnpj(String cpfCnpj);
    
    EmpresaFornecedor findByEmpresaCnpjAndFornecedorCpfCnpj(String empresaCnpj, String fornecedorCpfCnpj);
    
}