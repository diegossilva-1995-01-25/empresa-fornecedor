import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { EmpresaService } from '../service/empresa.services';

@Component({
  selector: 'app-lista-empresas',
  imports: [],
  templateUrl: './lista-empresas.html',
  styleUrl: './lista-empresas.scss',
})
export class ListaEmpresas {

  private router = inject(Router);
  private http = inject(HttpClient);

  fornecedores: any[] = [];
  empresas: any[] = [];

  constructor(
    private empresaService: EmpresaService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {

    const cpf = localStorage.getItem('cpf');

    if (!cpf) return;

    this.empresaService.recuperarEmpresas(cpf)
      .subscribe({
        next: (data: any) => {

          this.empresas = data ?? [];

          this.cdr.detectChanges();
        },
        error: (err: any) => console.error(err)
      });

  }

  deletarEmpresa(empresa: any) {

    const payload = {
        cnpj: empresa.cnpj,
    }

    const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
    });

    const url = 'http://192.168.18.3:8080/empresa-fornecedor/empresa/deletar';

    this.http.post(url, payload, { headers }).subscribe({
      complete: () => {
        location.reload();
      }
    });

  }

  redirectEditarEmpresa(empresa: any) {

    localStorage.setItem('empresa', JSON.stringify(empresa));

    setTimeout(() => {
      this.router.navigate(['/private/empresa']);
    }, 100);

  }

}
