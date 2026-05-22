import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root', // Add this line
})
export class FornecedorService {

    private http = inject(HttpClient);

    recuperarFornecedores(cpfEntry: string): any {
    
        const payload = {
          cpf: cpfEntry,
        }
    
        const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        });
    
        const url = `${environment.apiUrl}/fornecedor/todos`;
    
        // RETURN the observable
        return this.http.post(url, payload, { headers });
        
      }
    

}