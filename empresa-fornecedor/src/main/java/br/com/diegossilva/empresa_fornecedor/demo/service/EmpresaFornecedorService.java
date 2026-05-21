package br.com.diegossilva.empresa_fornecedor.demo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.diegossilva.empresa_fornecedor.demo.entity.EmpresaFornecedor;
import br.com.diegossilva.empresa_fornecedor.demo.repository.EmpresaFornecedorRepository;
import br.com.diegossilva.empresa_fornecedor.demo.repository.EmpresaRepository;
import br.com.diegossilva.empresa_fornecedor.demo.repository.FornecedorRepository;

@Service
@Lazy
public class EmpresaFornecedorService {

	@Autowired
	private EmpresaFornecedorRepository repo;
	
	@Autowired
	private EmpresaRepository repoEmpresa;
	
	@Autowired
	private FornecedorRepository repoFornecedor;
	
	public EmpresaFornecedor cadastrarEmpresaFornecedor(EmpresaFornecedor empresaFornecedor) throws Exception {
		
		// Popular os dados
		empresaFornecedor.setEmpresa(repoEmpresa.findByCnpj(empresaFornecedor.getEmpresa().getCnpj()));
		empresaFornecedor.setFornecedor(repoFornecedor.findByCpfCnpj(empresaFornecedor.getFornecedor().getCpfCnpj()));
		
		// Validar CEP, é do Paraná?
		HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + empresaFornecedor.getEmpresa().getCep() + "/json/"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());
        
        String jsonString = response.body();
        
        JSONObject jsonObject = new JSONObject(jsonString); 
        
        String estado = jsonObject.getString("uf");  // Use "uf" for state abbreviation
        
        // É CPF? Então vê se é um maior de idade.
        if(empresaFornecedor.getFornecedor().getRg() != null && !empresaFornecedor.getFornecedor().getRg().equals("")) {
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate birthDate = LocalDate.parse(String.valueOf(empresaFornecedor.getFornecedor().getNasc()), formatter);
            
            LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
            
            if(estado.equals("PR") && birthDate.isAfter(eighteenYearsAgo)) {
            	throw new Exception("No Paraná, é proibido contratar um fornecedor pessoa física menor de 18 anos.");
            }
        	
        }
		
		return repo.save(empresaFornecedor);
	}
	
	public List<EmpresaFornecedor> todosEmpresaFornecedoresPorEmpresa(String cnpj) {
	    return repo.findByEmpresa_Cnpj(cnpj);
	}

	public List<EmpresaFornecedor> todosEmpresaFornecedoresPorFornecedor(String cpfCnpj) {
	    return repo.findByFornecedor_CpfCnpj(cpfCnpj);
	}
	
	public EmpresaFornecedor umaEmpresaFornecedor(String empresaCnpj, String fornecedorCpfCnpj) {
		return repo.findByEmpresa_CnpjAndFornecedor_CpfCnpj(empresaCnpj, fornecedorCpfCnpj);
	}
	
	public void deletarEmpresaFornecedor(EmpresaFornecedor empresaFornecedor) {
		repo.delete(empresaFornecedor);
	}
	
}