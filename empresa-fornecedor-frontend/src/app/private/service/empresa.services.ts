import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root', // Add this line
})
export class EmpresaService {

    private http = inject(HttpClient);

    recuperarEmpresas(cpfEntry: string): any {

        const payload = {
            cpf: cpfEntry,
        }

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('authToken')
        });

        const url = `${environment.apiUrl}/empresa/todos`;

        // RETURN the observable
        return this.http.post(url, payload, { headers });

    }


}