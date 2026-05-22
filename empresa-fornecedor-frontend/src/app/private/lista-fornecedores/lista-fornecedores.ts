import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { FornecedorService } from '../service/fornecedor.services';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-lista-fornecedores',
  imports: [],
  templateUrl: './lista-fornecedores.html',
  styleUrl: './lista-fornecedores.scss',
})
export class ListaFornecedores {

  private router = inject(Router);
  private http = inject(HttpClient);

  fornecedores: any[] = [];
  empresas: any[] = [];

  constructor(
    private fornecedorService: FornecedorService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    const cpf = localStorage.getItem('cpf');

    if (!cpf) return;

    this.fornecedorService.recuperarFornecedores(cpf)
      .subscribe({
        next: (data: any) => {

          this.fornecedores = data ?? [];

          this.cdr.detectChanges();
        },
        error: (err: any) => console.error(err)
      });

  }

  deletarFornecedor(fornecedor: any) {

    const payload = {
        cpfCnpj: fornecedor.cpfCnpj,
    }

    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = `${environment.apiUrl}/fornecedor/deletar`;

    this.http.post(url, payload, { headers }).subscribe({
      complete: () => {
        location.reload();
      }
    });

  }

  redirectEditarFornecedor(fornecedor: any) {

    localStorage.setItem('fornecedor', JSON.stringify(fornecedor));

    setTimeout(() => {
      this.router.navigate(['/private/fornecedor']);
    }, 100);

  }

}
