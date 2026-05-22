import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root', // Add this line
})
export class EmpresaService {

    private http = inject(HttpClient);

    recuperarEmpresas(): any {

        const payload = {
        cpf: localStorage.getItem('cpf'),
        }

        const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        });

        const url = 'http://192.168.18.3:8080/empresa-fornecedor/empresa/todos';

        this.http.post(url, payload, { headers }).subscribe({
            next: (res) => {
            
                return res;

            },
            error: (err) => {
                console.error('Error retrieving empresas:', err);
            }
        });

    }


}